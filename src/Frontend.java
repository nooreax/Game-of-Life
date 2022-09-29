import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend implements Observer<Field>{

    private final int buttonSize;

    public boolean startButtonPressed;

    private  final ZellButton[][] zellButtons;

    private Field field;

    private final StateManager stateManager;


    JFrame window = new JFrame("Conway's Game of Life");
    JPanel grid = new JPanel();
    JPanel controllPanel = new JPanel();


    public Frontend(int buttonSize, StateManager stateManager){

        this.stateManager = stateManager;

        field = this.stateManager.fieldManager.subscribe(this);

        this.buttonSize = buttonSize;

        int fieldWidth = field.getFieldWidth();
        int fieldHeight = field.getFieldHeight();

        window.setLayout(new BorderLayout());


    //Grid

        grid.setLayout(new GridLayout(fieldHeight, fieldWidth));
        grid.setPreferredSize(new Dimension(fieldWidth * buttonSize, fieldHeight * buttonSize));


    //Grid: Zell-Buttons

        zellButtons = new ZellButton[fieldWidth][fieldHeight];


        for(int i = 0; i < fieldHeight; i++){
            for(int j = 0; j < fieldWidth; j++){

                zellButtons[j][i] = new ZellButton(false);
                zellButtons[j][i].setPreferredSize(new Dimension(buttonSize, buttonSize));

                final int final_i = i;
                final int final_j = j;

                zellButtons[j][i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        zellButtons[final_j][final_i].changeZustand();

                        stateManager.fieldManager.setZelle(final_j, final_i, zellButtons[final_j][final_i].getZustand());

                        window.repaint();
                    }
                });
                grid.add(zellButtons[j][i]);
            }
        }


    //Controll Panel

        controllPanel.setPreferredSize(new Dimension(fieldWidth * buttonSize, 70));


    //Controll Panel: Start-Button

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));

        startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                stateManager.gameStateManager.setGameState(GameState.RUN);

            }
        });
        controllPanel.add(startButton);


    //Controll Panel: Next-Button

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 50));

        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                stateManager.gameStateManager.setGameState(GameState.STEP);
            }
        });
        controllPanel.add(nextButton);



        window.add(grid, BorderLayout.CENTER);
        window.add(controllPanel, BorderLayout.PAGE_END);

        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }


    @Override
    public void update(Field field){

        this.field = field;

        refresh(field.getField());
    }


    public void refresh(boolean[][] feld){

        for(int i = 0; i < field.getFieldHeight(); i++) {
            for (int j = 0; j < field.getFieldWidth(); j++) {

                zellButtons[j][i].setZustand(feld [j][i]);
            }
        }
        window.repaint();
    }
}
