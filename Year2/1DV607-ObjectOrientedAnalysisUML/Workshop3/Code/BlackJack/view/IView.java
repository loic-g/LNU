package BlackJack.view;

import BlackJack.model.rules.IObserverGame;

public interface IView
{
  void DisplayWelcomeMessage();
  void GetInput();
  void DisplayCard(BlackJack.model.Card a_card);
  void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);
  void DisplayGameOver(boolean a_dealerIsWinner);
  void addCommand(IObserverGame checkCommand);

}