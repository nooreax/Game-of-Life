import java.awt.*;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {

        int buttonSize = 20;

        int fieldWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / buttonSize;
        int fieldHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() -125) / buttonSize;

        StateManager stateManager = new StateManager(fieldWidth, fieldHeight);
        Backend backend = new Backend(stateManager);
        Frontend frontend = new Frontend(buttonSize, stateManager);

        Thread backendThread = new Thread(new Runner(backend));
        backendThread.start();
    }
}
