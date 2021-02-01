package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinsOnEqualScore implements IWhoWinsOnEqualScore {
    @Override
    public boolean IsDealerWinner(Player a_player, Dealer a_dealer, int g_maxScore) {
        if (a_player.CalcScore() > g_maxScore) {
            return true; //Player lost
        } else if (a_dealer.CalcScore() > g_maxScore) {
            return false; //Dealer Lost
        }
        return a_dealer.CalcScore() >= a_player.CalcScore(); //Player loose on equal score and when the dealer has an higher score
    }
}
