import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frontend implements Observer<Field>{

    private final StateManager stateManager;

    private final int buttonSize;

    private  final ZellButton[][] zellButtons;

    private final JButton resetButton;

    private Field field;

    private GameState gameState;

    private boolean inAnimation;
    private boolean inRules;


    JFrame window = new JFrame("Conway's Game of Life");
    JDialog rules = new JDialog(window, "Rules");
    JPanel grid = new JPanel();
    JPanel controllPanel = new JPanel();
    JPanel rulesIcons = new JPanel();
    JPanel rulesText = new JPanel();


    public Frontend(int buttonSize, StateManager stateManager) throws IOException {

        this.stateManager = stateManager;

        field = this.stateManager.fieldManager.subscribe(this);

        createGameStateObserver();

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

                        if(!inRules){

                            zellButtons[final_j][final_i].changeZustand();
                        }

                        stateManager.fieldManager.setZelle(final_j, final_i, zellButtons[final_j][final_i].getZustand());

                        if(gameState != GameState.RUN){
                            stateManager.fieldManager.safeOldField();
                        }

                        window.repaint();
                    }
                });
                grid.add(zellButtons[j][i]);
            }
        }


    //Controll Panel

        controllPanel.setPreferredSize(new Dimension(fieldWidth * buttonSize, 70));


    //Controll Panel: Rules-Button

        JButton rulesButton = new JButton("Rules");
        rulesButton.setPreferredSize(new Dimension(100, 50));

        rulesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if(!inRules){

                    rules.setVisible(true);

                    inRules = true;
                }
            }
        });
        controllPanel.add(rulesButton);


    //Controll Panel: Start-Button

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));

        startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!inRules) {

                    if (gameState == GameState.WAIT) {

                        startButton.setText("Stop");

                        stateManager.gameStateManager.setGameState(GameState.RUN);

                        changeResetButton(true);
                    }
                    else if (gameState == GameState.RUN) {

                        startButton.setText("Start");

                        stateManager.gameStateManager.setGameState(GameState.WAIT);
                    }
                }
            }
        });
        controllPanel.add(startButton);


    //Controll Panel: Next-Button

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 50));

        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                if(!inRules){

                    if(gameState == GameState.WAIT){

                        stateManager.gameStateManager.setGameState(GameState.STEP);

                        changeResetButton(true);
                    }
                }
            }
        });
        controllPanel.add(nextButton);


    //Controll Panel: Reset-Button

        JButton resetButton = new JButton("Clear");
        resetButton.setPreferredSize(new Dimension(100, 50));

        resetButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!inRules){

                    if(inAnimation){

                        stateManager.fieldManager.setOldField();

                        if(gameState != GameState.RUN){
                            changeResetButton(false);
                        }
                    }
                    else{

                        stateManager.gameStateManager.setGameState(GameState.CLEAR);
                    }
                }
            }
        });
        this.resetButton = resetButton;

        controllPanel.add(resetButton);



    //Rules

        rules.setLayout(new BorderLayout());
        rules.setBounds(400, 300, 800, 300);


    //Rules: Close-Button

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(80, 40));

        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rules.setVisible(false);

                inRules = false;
            }
        });


    //Rules: Rules-Text

        rulesText.setLayout(new GridLayout(4, 1));

        JLabel rule1 = new JLabel("A cell with no or only one neighbor will die in the following frame because it feels lonely");
        rulesText.add(rule1);

        JLabel rule2 = new JLabel("A cell with two or three neighbors will survive in the following frame.");
        rulesText.add(rule2);

        JLabel rule3 = new JLabel("A cell with more than three neighbors will die in the following frame because of overpopulation.");
        rulesText.add(rule3);

        JLabel rule4 = new JLabel("An empty field with exactly three neighbors will be populated in the following frame.");
        rulesText.add(rule4);


    //Rules: Rules_Icons

        rulesIcons.setLayout(new GridLayout(4,7));


        JLabel rule1Example1Icon1 = new JLabel();
        rule1Example1Icon1.setIcon(new ImageIcon("Icons/rule1Example1Icon1.png"));
        rule1Example1Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule1Example1Icon1);

        JLabel rule1Example1 = new JLabel("➔" );
        rulesIcons.add(rule1Example1);

        JLabel rule1Example1Icon2 = new JLabel();
        rule1Example1Icon2.setIcon(new ImageIcon("Icons/rule1Example1Icon2.png"));
        rule1Example1Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule1Example1Icon2);

        JLabel rule1Example2Icon1 = new JLabel();
        rule1Example2Icon1.setIcon(new ImageIcon("Icons/rule1Example2Icon1.png"));
        rule1Example2Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule1Example2Icon1);

        JLabel rule1Example2 = new JLabel("➔");
        rulesIcons.add(rule1Example2);

        JLabel rule1Example2Icon2 = new JLabel();
        rule1Example2Icon2.setIcon(new ImageIcon("Icons/rule1Example2Icon2.png"));
        rule1Example2Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule1Example2Icon2);


        JLabel rule2Example1Icon1 = new JLabel();
        rule2Example1Icon1.setIcon(new ImageIcon("Icons/rule2Example1Icon1.png"));
        rule2Example1Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule2Example1Icon1);

        JLabel rule2Example1 = new JLabel("➔");
        rulesIcons.add(rule2Example1);

        JLabel rule2Example1Icon2 = new JLabel();
        rule2Example1Icon2.setIcon(new ImageIcon("Icons/rule2Example1Icon2.png"));
        rule2Example1Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule2Example1Icon2);

        JLabel rule2Example2Icon1 = new JLabel();
        rule2Example2Icon1.setIcon(new ImageIcon("Icons/rule2Example2Icon1.png"));
        rule2Example2Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule2Example2Icon1);

        JLabel rule2Example2 = new JLabel("➔");
        rulesIcons.add(rule2Example2);

        JLabel rule2Example2Icon2 = new JLabel();
        rule2Example2Icon2.setIcon(new ImageIcon("Icons/rule2Example2Icon2.png"));
        rule2Example2Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule2Example2Icon2);


        JLabel rule3Example1Icon1 = new JLabel();
        rule3Example1Icon1.setIcon(new ImageIcon("Icons/rule3Example1Icon1.png"));
        rule3Example1Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule3Example1Icon1);

        JLabel rule3Example1 = new JLabel("➔");
        rulesIcons.add(rule3Example1);

        JLabel rule3Example1Icon2 = new JLabel();
        rule3Example1Icon2.setIcon(new ImageIcon("Icons/rule3Example1Icon2.png"));
        rule3Example1Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule3Example1Icon2);

        JLabel rule3Example2Icon1 = new JLabel();
        rule3Example2Icon1.setIcon(new ImageIcon("Icons/rule3Example2Icon1.png"));
        rule3Example2Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule3Example2Icon1);

        JLabel rule3Example2 = new JLabel("➔");
        rulesIcons.add(rule3Example2);

        JLabel rule3Example2Icon2 = new JLabel();
        rule3Example2Icon2.setIcon(new ImageIcon("Icons/rule3Example2Icon2.png"));
        rule3Example2Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule3Example2Icon2);


        JLabel rule4Example1Icon1 = new JLabel();
        rule4Example1Icon1.setIcon(new ImageIcon("Icons/rule4Example1Icon1.png"));
        rule4Example1Icon1.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule4Example1Icon1);

        JLabel rule4Example1 = new JLabel("➔");
        rulesIcons.add(rule4Example1);

        JLabel rule4Example1Icon2 = new JLabel();
        rule4Example1Icon2.setIcon(new ImageIcon("Icons/rule4Example1Icon2.png"));
        rule4Example1Icon2.setPreferredSize(new Dimension(24, 24));
        rulesIcons.add(rule4Example1Icon2);



        rules.add(rulesIcons, BorderLayout.CENTER);
        rules.add(closeButton, BorderLayout.PAGE_END);
        rules.add(rulesText, BorderLayout.LINE_START);



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


    private Observer<GameState> createGameStateObserver() {

        final Frontend frontend = this;
        Observer<GameState> observer = new Observer<GameState>() {

            @Override
            public void update(GameState newInformation) {

                frontend.updateGameState(newInformation);
            }
        };
        gameState = stateManager.gameStateManager.subscribe(observer);

        return observer;
    }


    private void updateGameState(GameState gameState) {

        this.gameState = gameState;
    }


    private void changeResetButton(boolean inAnimation){

        this.inAnimation = inAnimation;

        if(inAnimation){

            resetButton.setText("Reset");
        }
        else{

            resetButton.setText("Clear");
        }
    }
}
