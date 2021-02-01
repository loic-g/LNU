package BlackJack.view;

import BlackJack.controller.CheckCommand;
import BlackJack.controller.PlayGame;
import BlackJack.model.Game;
import BlackJack.model.rules.IObserverGame;

import java.util.ArrayList;
import java.util.List;

public class SimpleView implements IView
{
    private List<IObserverGame> checkCommands = new ArrayList<>();
    public void DisplayWelcomeMessage()
    {
        for(int i = 0; i < 50; i++) {System.out.print("\n");}
        System.out.println(" ==============================");
        System.out.println("| 10                           |");
        System.out.println("| ♠                            |");
        System.out.println("|              *               |");
        System.out.println("|            *   *             |");
        System.out.println("|          *       *           |");
        System.out.println("|          * *   * *           |");
        System.out.println("|     Welcome to BLACKJACK     |");
        System.out.println("|              **              |");
        System.out.println("|             ****             |");
        System.out.println("|                              |");
        System.out.println("|                            ♠ |");
        System.out.println("|                            10|");
        System.out.println(" ==============================");

        //System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
        GetInput();
    }

    public void GetInput() {
        try {
            System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
                c = System.in.read();
            }
            if(c == 'p'){
                for (IObserverGame checkCommand : checkCommands){
                    checkCommand.setNewGame();
                }
            } else if (c == 'h'){
                for (IObserverGame checkCommand : checkCommands){
                    checkCommand.setHit();
                }
            } else if(c == 's'){
                for (IObserverGame checkCommand : checkCommands){
                    checkCommand.setStand();
                }
            } else if(c=='q'){
                for (IObserverGame checkCommand : checkCommands){
                    checkCommand.setExit();
                }
            }
            GetInput();
        } catch (java.io.IOException e) {
            System.out.println("" + e);

        }
    }

    public void addCommand(IObserverGame checkCommand){
        checkCommands.add(checkCommand);
    }

    public void DisplayCard(BlackJack.model.Card a_card)
    {
        System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
    }

    public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        DisplayHand("Player", a_hand, a_score);
    }

    public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        System.out.println("-----------------------");
        DisplayHand("Dealer", a_hand, a_score);
    }

    private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
    {

        System.out.println(a_name + " Has: ");
        for(BlackJack.model.Card c : a_hand)
        {
            DisplayCard(c);
        }
        System.out.println("Score: " + a_score);
        System.out.println("");
    }

    public void DisplayGameOver(boolean a_dealerIsWinner)
    {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner)
        {
            System.out.println("Dealer Won!");
        }
        else
        {
            System.out.println("You Won!");
        }

    }
}