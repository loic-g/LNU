package BlackJack.controller;

import BlackJack.model.rules.IObserverGame;
import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements IObserverGame {

  private Game o_game;
  private IView o_view;

  public PlayGame(Game a_game, IView a_view){
    o_game = a_game;
    o_view = a_view;
    a_view.addCommand(this);
    a_game.addGameObserver(this); //Adding Playgame (who implements IobserverGame) to the GameObservable.
  }

  public void Play() {
    o_view.DisplayWelcomeMessage();
    showPlayerDealerHand();
    isGameOver();
  }

  public void showPlayerDealerHand(){
    o_view.DisplayDealerHand(o_game.GetDealerHand(), o_game.GetDealerScore());
    o_view.DisplayPlayerHand(o_game.GetPlayerHand(), o_game.GetPlayerScore());
  }

  public void isGameOver(){
    if (o_game.IsGameOver()) {
      o_view.DisplayGameOver(o_game.IsDealerWinner());
    }
  }

  @Override
  public void setNewGame(){
    o_game.NewGame();
    showPlayerDealerHand();
    isGameOver();
  }
  @Override
  public void setHit(){
    o_game.Hit();
    showPlayerDealerHand();
    isGameOver();
  }
  @Override
  public void setStand(){
    o_game.Stand();
    showPlayerDealerHand();
    isGameOver();
  }
  @Override
  public void setExit(){
    System.exit(0);
  }

  
  @Override
  public void updateObservers() {
    try{
      Thread.sleep(3000);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    o_view.DisplayDealerHand(o_game.GetDealerHand(), o_game.GetDealerScore());
    o_view.DisplayPlayerHand(o_game.GetPlayerHand(), o_game.GetPlayerScore());

  }


}