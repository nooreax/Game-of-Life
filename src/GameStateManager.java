import java.util.ArrayList;
import java.util.List;

public class GameStateManager implements Observable<GameState, Observer<GameState>>{

    private GameState gameState = GameState.WAIT;

    private List<Observer<GameState>> observers = new ArrayList<>();

    @Override
    public GameState subscribe(Observer<GameState> observer){

        observers.add(observer);

        return gameState;
    }


    public void notice(){

        for(Observer<GameState> observer: observers){

            observer.update(gameState);
        }
    }


    public void setGameState(GameState gameState){

        this.gameState = gameState;

        notice();
    }
}
