package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface IWhoWinsOnEqualScore {
    public boolean IsDealerWinner(Player a_player, Dealer a_dealer, int g_maxScore);
}
