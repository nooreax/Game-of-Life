import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend {

    private final int buttonSize;

    private final int feldbreite;
    private final int feldhoehe;

    public boolean startButtonPressed;

    //Array mit den Buttons wird definiert
    private  final ZellButton[][] zellButtons;

    //ein Window, bestehend aus zwei JPanels, wird erstellt
    JFrame window = new JFrame("Conway's Game of Life");
    JPanel grid = new JPanel();
    JPanel steuerungsleiste = new JPanel();

    //Frontend wird erstellt
    public Frontend(int buttonSize, int feldbreite, int feldhoehe, Backend backend1){

        this.buttonSize = buttonSize;
        this.feldbreite = feldbreite;
        this.feldhoehe = feldhoehe;

        //das Window bekommt ein BorderLayout
        window.setLayout(new BorderLayout());


    //Grid

        //das Grid bekommt ein GridLayout mit gewisser Größe
        grid.setLayout(new GridLayout(feldhoehe, feldbreite));
        grid.setPreferredSize(new Dimension(feldbreite * buttonSize, feldhoehe * buttonSize));


    //Grid: Zell-Buttons

        //das Array, welches die Buttons beinhaltet, wird erstellt
        zellButtons = new ZellButton[feldbreite][feldhoehe];


        for(int i = 0; i < feldhoehe; i++){
            for(int j = 0; j < feldbreite; j++){

                //Buttons werden mit Zustand "false" erstellt und die Größe wird festgelegt
                zellButtons[j][i] = new ZellButton(false);
                zellButtons[j][i].setPreferredSize(new Dimension(buttonSize, buttonSize));

                final int final_i = i;
                final int final_j = j;

                //jeder Button bekommt einen ActionListener
                zellButtons[j][i].addActionListener(new ActionListener() {

                    //alle Buttons ändern ihren Zustand und alle Zellen werden auf den entsprechenden Zustand gesetzt
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        zellButtons[final_j][final_i].changeZustand();

                        backend1.setZelle(final_j, final_i, zellButtons[final_j][final_i].getZustand());

                        //das Window wird erneuert
                        window.repaint();
                    }
                });
                //jeder Button wird dem Grid hinzugefügt
                grid.add(zellButtons[j][i]);
            }
        }


    //Steuerungsleiste

        //die Größe der Steuerungsleiste wird festgelegt
        steuerungsleiste.setPreferredSize(new Dimension(feldbreite * buttonSize, 70));


    //Steuerungsleiste: Start-Button

        //der Start-Button wird erstellt und seine Größe wird festgelegt
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));

        //ActionListener wird hinzugefügt
        startButton.addActionListener(new ActionListener(){

            //Wenn der Start-Button gedrückt wird, werden alle Zellen wiederholend nach ihrem Zustand gefragt, woraufhin
            // alle Zellen auf ihren neuen Zustand gesetzt werden und das Window aktualisiert wird
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        //der Start-Button wird zur Steuerungsleiste hinzugefügt
        steuerungsleiste.add(startButton);


    //Steuerungsleiste: Next-Button

        //der Next-Button wird erstellt und seine Größe wird gesetzt
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 50));

        //ActionListener wird hinzugefügt
        nextButton.addActionListener(new ActionListener(){

            //alle Zellen werden nach ihrem Zustand gefragt, woraufhin alle Zellen auf ihren neuen Zustand gesetzt
            //werden und das Window aktualisiert wird
            @Override
            public void actionPerformed(ActionEvent e) {

                backend1.zellCheck();

                refresh(backend1.getFeld());
            }
        });
        //der Next-Button wird zur Steuerungsleiste hinzugefügt
        steuerungsleiste.add(nextButton);



        //das Grid und die Steuerungsleiste werden dem window hinzugefügt
        window.add(grid, BorderLayout.CENTER);
        window.add(steuerungsleiste, BorderLayout.PAGE_END);

        //Window-Einstellungen: gepackt, unveränderbare Größe und Sichbarkeit
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }


    //für jede Zelle wird der neue Zustand gesetzt und das Window wird daraufhin erneuert
    public void refresh(boolean[][] feld){

        for(int i = 0; i < feldhoehe; i++) {
            for (int j = 0; j < feldbreite; j++) {

                zellButtons[j][i].setZustand(feld [j][i]);
            }
        }
        window.repaint();
    }
}
