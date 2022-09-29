import javax.swing.*;
import java.awt.*;

public class ZellButton extends JButton{

    private boolean zustand;


    public ZellButton(boolean zustand){
        super();
        setZustand(zustand);
    }


    public void setZustand(boolean zustand){

        this.zustand = zustand;

        if(zustand){
            setBackground(Color.yellow);
        }
        else{
            setBackground(Color.gray);
        }
    }


    public void changeZustand(){

        setZustand(!zustand);
    }


    public boolean getZustand(){

        return zustand;
    }
}
