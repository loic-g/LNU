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
package jmines.actions.robot;

import java.awt.event.ActionEvent;

import jmines.actions.JMinesAction;
import jmines.panels.MainFrame;
import jmines.persistence.Configuration;

/**
 * The class representing the action used when the user click the Cheat menu
 * item.
 *
 * @author Zleurtor
 */
public class Cheat extends JMinesAction {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * The unique serial version identifier.
     */
    private static final long serialVersionUID = -7427646632556531560L;

    //==========================================================================
    // Attributes
    //==========================================================================

    //==========================================================================
    // Constructors
    //==========================================================================
    /**
     * Construct a new Cheat action.
     *
     * @param name The name of the menu item.
     * @param mainFrame The main frame of the application.
     */
    public Cheat(final String name, final MainFrame mainFrame) {
        super(name, mainFrame);

        setStatusText(Configuration.getInstance().getText(Configuration.KEY_STATUSTEXT_ROBOT_CHEAT));
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
     * The Method used when the user click on the menu item. Enable or disable
     * the possibility for the robot to cheat.
     *
     * @param evt The event object relating the event that occurred.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public final void actionPerformed(final ActionEvent evt) {
        getMainPanel().getRobot().setCheater(!getMainPanel().getRobot().isCheater());
        Configuration.getInstance().putRealTimeconfiguration(Configuration.KEY_ROBOT_CHEAT, Boolean.toString(getMainPanel().getRobot().isCheater()));

        super.emptyStatusBar();
    }

    //==========================================================================
    // Static methods
    //==========================================================================

    //==========================================================================
    // Methods
    //==========================================================================
}
