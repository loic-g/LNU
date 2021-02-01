package BlackJack.model.rules;

public interface IObserverGame {

    void updateObservers();
    void setNewGame();
    void setHit();
    void setStand();
    void setExit();
}
