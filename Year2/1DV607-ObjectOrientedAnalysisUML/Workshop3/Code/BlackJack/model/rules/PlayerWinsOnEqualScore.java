package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class PlayerWinsOnEqualScore implements IWhoWinsOnEqualScore {
    @Override
    public boolean IsDealerWinner(Player a_player, Dealer a_dealer, int g_maxScore) {
        if (a_player.CalcScore() > g_maxScore) {
            return true; //Player lost
        } else if (a_dealer.CalcScore() > g_maxScore) {
            return false; //Dealer Lost
        }else if(a_dealer.CalcScore() == a_player.CalcScore()){
            return false; //Player wins if it is in equal score with Dealer
        }
        return a_dealer.CalcScore() > a_player.CalcScore();
    }
}
