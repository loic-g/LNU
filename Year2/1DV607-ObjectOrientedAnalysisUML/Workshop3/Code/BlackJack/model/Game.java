package BlackJack.model;

import BlackJack.model.rules.IObserverGame;

public class Game {

  private Dealer m_dealer;
  private Player m_player;

  public Game()
  {
    m_dealer = new Dealer(new BlackJack.model.rules.RulesFactory());
    m_player = new Player();
  }
    
    
  public boolean IsGameOver()
  {
    return m_dealer.IsGameOver();
  }
  
  public boolean IsDealerWinner()
  {
    return m_dealer.IsDealerWinner(m_player);
  }
  
  public boolean NewGame()
  {
    return m_dealer.NewGame(m_player);
  }
  
  public boolean Hit()
  {
    return m_dealer.Hit(m_player);
  }
  
  public boolean Stand()
  {
    // TODO: Implement this according to Game_Stand.sequencediagram
    return m_dealer.Stand();
  }
  
  public Iterable<Card> GetDealerHand()
  {
    return m_dealer.GetHand();
  }
  
  public Iterable<Card> GetPlayerHand()
  {
    return m_player.GetHand();
  }
  
  public int GetDealerScore()
  {
    return m_dealer.CalcScore();
  }
  
  public int GetPlayerScore()
  {
    return m_player.CalcScore();
  }

  public void addGameObserver(IObserverGame observerGame){
    m_dealer.addGameObserver(observerGame); //Because GameObservable is the super class and Player is extending it and then Dealer is extends Player.
    m_player.addGameObserver(observerGame);// Therefore both Player and Dealer have access to the method addGameObserver of the abstract class "GameObservable"
  }
    
  
}