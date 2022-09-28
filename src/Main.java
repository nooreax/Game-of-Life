import java.awt.*;

public class Main {

    public Main() {
    }

    //wird beim Start des Programms ausgeführt
    public static void main(String[] args) {

        //Button-Größe wird gesetzt
        int buttonSize = 20;

        //Feldbreite und Feldhöhe werden in Abhängikeit der Bildschirmgröße gesetzt
        int feldbreite = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / buttonSize;
        int feldhoehe = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() -125) / buttonSize;

        //neues Backend, Frontend und StateManager werden erstellt
        Backend backend = new Backend(feldbreite, feldhoehe);
        Frontend frontend = new Frontend(buttonSize, feldbreite, feldhoehe, backend);
        StateManager stateManager = new StateManager();

        //bei jeder Zelle wird der Zustand aktualisiert
        frontend.refresh(backend.getFeld());
    }
}
