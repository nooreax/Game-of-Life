import javax.swing.*;
import java.awt.*;

public class ZellButton extends JButton{

    private boolean zustand;


    //beim Erstellen eines Zell-Buttons wird der Zustand gesetzt und ein dazugehöriger JButton erstellt
    public ZellButton(boolean zustand){
        super();
        setZustand(zustand);
    }


    //je nach Zustand wird der Hintergrund des Buttons auf gelb bzw. grau gesetzt (true = gelb; false = grau)
    public void setZustand(boolean zustand){

        this.zustand = zustand;

        if(zustand){
            setBackground(Color.yellow);
        }
        else{
            setBackground(Color.gray);
        }
    }


    //der Zustand des Buttons wird auf den anderen Zustand gesetzt  (von true zu false; von false zu true)
    public void changeZustand(){

        setZustand(!zustand);
    }


    //der Zustand des Buttons wird ausgegeben
    public boolean getZustand(){

        return zustand;
    }
}
