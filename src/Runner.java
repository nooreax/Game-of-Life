public class Runner implements Observer<GameState>, Runnable{

    private final Backend backend;
    private final StateManager stateManager;

    private GameState gameState;


    public Runner(Backend backend, StateManager stateManager) {

        this.backend = backend;
        this.stateManager = stateManager;

        gameState = stateManager.gameStateManager.subscribe(this);
    }


    @Override
    public void run() {

        while (true) {

            if(gameState == GameState.RUN){

                backend.execute();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(gameState == GameState.STEP || gameState == GameState.CLEAR){

                backend.execute();
            }
        }
    }


    @Override
    public void update(GameState gameState){

        this.gameState = gameState;
    }
}
