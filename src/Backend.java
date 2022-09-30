public class Backend implements Observer<Field>{

    private final StateManager stateManager;

    private Field field;

    private Observer<GameState> gameStateObserver;
    private GameState gameState;


    public Backend(StateManager stateManager) {

        this.stateManager = stateManager;

        field = this.stateManager.fieldManager.subscribe(this);

        gameStateObserver = createGameStateObserver();
    }


    @Override
    public void update(Field field) {

        this.field = field;
    }


    public boolean getLiveOrDie(int fieldWidthValue, int fieldHeightValue) {

        int anzahlNachbar = field.getNeighbor(fieldWidthValue, fieldHeightValue);

        boolean zustand = field.getZelle(fieldWidthValue, fieldHeightValue);

        if (!zustand && anzahlNachbar == 3) {
            return true;
        }

        if (zustand && (anzahlNachbar == 2 || anzahlNachbar == 3)) {
            return true;
        }

        return false;
    }


    public void zellCheck() {

        boolean[][] newField = new boolean[field.getFieldWidth()][field.getFieldHeight()];

        for (int i = 0; i < field.getFieldHeight(); i++) {
            for (int j = 0; j < field.getFieldWidth(); j++) {

                newField[j][i] = getLiveOrDie(j, i);
            }
        }

        stateManager.fieldManager.setField(newField);
    }


    public void clear() {

        boolean[][] newField = new boolean[field.getFieldWidth()][field.getFieldHeight()];

        for (int i = 0; i < field.getFieldHeight(); i++) {
            for (int j = 0; j < field.getFieldWidth(); j++) {

                newField[j][i] = false;
            }
        }

        stateManager.fieldManager.setField(newField);
    }


    private Observer<GameState> createGameStateObserver() {

        final Backend backend = this;
        Observer<GameState> observer = new Observer<GameState>() {

            @Override
            public void update(GameState newInformation) {

                backend.updateGameState(newInformation);
            }
        };
        gameState = stateManager.gameStateManager.subscribe(observer);

        return observer;
    }


    private void updateGameState(GameState gameState) {

        this.gameState = gameState;
    }


    public void execute(){

        if(gameState == GameState.RUN){

            zellCheck();

        }
        else if(gameState == GameState.STEP){

            zellCheck();

            stateManager.gameStateManager.setGameState(GameState.WAIT);
        }
        else if(gameState == GameState.CLEAR){

            clear();

            stateManager.gameStateManager.setGameState(GameState.WAIT);
        }
    }


    //zum Testen

    public void test() {

        for (int i = 0; i < field.getFieldHeight(); i++) {
            for (int j = 0; j < field.getFieldWidth(); j++) {

                if (field.getZelle(j, i)) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }
}
