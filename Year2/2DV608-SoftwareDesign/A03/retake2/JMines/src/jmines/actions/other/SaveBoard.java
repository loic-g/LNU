/*
 * This file is part of JMines.
 * Copyright (C) 2009 Zleurtor
 *
 * JMines is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JMines is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JMines.  If not, see <http://www.gnu.org/licenses/>.
 */
package jmines.actions.other;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import jmines.actions.JMinesAction;
import jmines.panels.MainFrame;
import jmines.persistence.BoardAccess;
import jmines.persistence.Configuration;

/**
 * The class representing the action used when the user click on the save board
 * menu item.
 *
 * @author Zleurtor
 */
public class SaveBoard extends JMinesAction {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = 5073524774158637185L;

    //==========================================================================
    // Attributes
    //==========================================================================

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new SaveBoard action.
     *
     * @param name The name of the menu item.
     * @param mainFrame The main frame of the application.
     */
    public SaveBoard(final String name, final MainFrame mainFrame) {
        super(name, mainFrame);

        setStatusText(Configuration.getInstance().getText(Configuration.KEY_STATUSTEXT_OTHER_SAVEBOARD));
    }

    //==========================================================================
    // Getters
    //==========================================================================

    //==========================================================================
    // Setters
    //==========================================================================

    //==========================================================================
    // Inherited methods
    //==========================================================================
    /**
     * The Method used when the user click on the menu item.

     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        boolean running = getMainPanel().getTimer().isRunning();
        if (running) {
            getMainPanel().getTimer().pause();
        }

        int warning = JOptionPane.showConfirmDialog(getMainPanel(), Configuration.getInstance().getText(Configuration.KEY_TEXT_SAVEWARNING), Configuration.getInstance().getText(Configuration.KEY_TITLE_SAVEBOARD), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (warning == JOptionPane.NO_OPTION || warning == JOptionPane.CANCEL_OPTION) {
            if (running) {
                getMainPanel().getTimer().resume();
            }
            super.emptyStatusBar();
            return;
        }

        final String suffix = Configuration.getInstance().getString(Configuration.KEY_FILE_BOARD_SUFFIX);

        // Create the dialog used to ask the user the name of the file to write to.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle(Configuration.getInstance().getText(Configuration.KEY_TITLE_SAVEBOARD));

        for (FileFilter filter : fileChooser.getChoosableFileFilters()) {
            fileChooser.removeChoosableFileFilter(filter);
        }

        fileChooser.setFileFilter(Configuration.getBoardFileFilter());

        // Ask the user the filename
        fileChooser.showSaveDialog(getMainPanel());
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            if (!file.getName().endsWith(suffix)) {
                file = new File(file.getAbsolutePath() + suffix);
            }
            try {
                BoardAccess.saveBoard(getMainPanel(), file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getClass().getSimpleName() + " (" + e.getMessage() + ")", Configuration.getInstance().getText(Configuration.KEY_TITLE_ERROR), JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (running) {
            getMainPanel().getTimer().resume();
        }
        super.emptyStatusBar();
    }

    //==========================================================================
    // Static methods
    //==========================================================================

    //==========================================================================
    // Methods
    //==========================================================================
}
