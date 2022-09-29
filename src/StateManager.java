public class StateManager {

    public final FieldManager fieldManager;
    public final GameStateManager gameStateManager;

    public StateManager(int fieldWidth, int fieldHeight){

        fieldManager = new FieldManager(fieldWidth, fieldHeight);
        gameStateManager = new GameStateManager();
    }
}
