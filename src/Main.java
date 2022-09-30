import java.awt.*;
import java.io.IOException;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws IOException {

        int buttonSize = 20;

        int fieldWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / buttonSize;
        int fieldHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() -125) / buttonSize;

        StateManager stateManager = new StateManager(fieldWidth, fieldHeight);
        Backend backend = new Backend(stateManager);
        Frontend frontend = new Frontend(buttonSize, stateManager);

        Thread backendThread = new Thread(new Runner(backend, stateManager));
        backendThread.start();
    }
}
