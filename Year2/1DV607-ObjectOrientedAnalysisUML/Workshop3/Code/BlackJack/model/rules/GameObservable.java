package BlackJack.model.rules;

import java.util.ArrayList;

public abstract class GameObservable {

    private ArrayList<IObserverGame> gameObservers = new ArrayList<IObserverGame>();

    public void addGameObserver(IObserverGame gameObserver){
        gameObservers.add(gameObserver);
    }

    public void removeGameObserver(IObserverGame gameObserver){
        gameObservers.remove(gameObserver);
    }

    public void notifyGameObserver(){
        /*Loop through the arraylist and call the method "updateObservers" from PlayGame which implements IObserverGame*/
        for(int i=0;i<gameObservers.size();i++){
            gameObservers.get(i).updateObservers();
        }
    }
}
