package view;

import controller.Controller;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends JPanel {
    private Window window;
    private JLabel mainTitle, pOne, pTwo, pOneScore, pOneCrew, pTwoCrew, pTwoScore;
    private JPanel gamePanel, pOnePanel,pTwoPanel;
    private JButton btnMenu, btnNewGame;
    private JPanel[] gamePanels = new JPanel[100];
    private Controller controller;


    public GameScreen(int width, int height, Window window, Controller controller) {

        super(null);
        this.setSize(width, height);
        this.window = window;
        this.setBackground(Color.black);
        this.setLocation(0,0);
        this.controller = controller;

        // Titel
        mainTitle = new JLabel("SKATTJAKT!");
        mainTitle.setSize(300,50);
        mainTitle.setLocation(270,5);
        mainTitle.setForeground(Color.yellow.darker());
        mainTitle.setFont(window.getTileFont());
        this.add(mainTitle);

        //Game Board Panel
        gamePanel = new JPanel(new GridLayout(10,10));
        gamePanel.setSize(700,700);
        gamePanel.setLocation(45,55);
        gamePanel.setBackground(Color.DARK_GRAY.darker());
        //gamePanel.setBorder(new LineBorder(Color.yellow));
        this.add(gamePanel);


        //Main menu button
        btnMenu = new JButton("HUVUDMENY");
        btnMenu.setEnabled(true);
        btnMenu.setSize(150,20);
        btnMenu.setLocation(790,735);
        btnMenu.setFont(window.getBtnFont());
        btnMenu.setBackground(Color.RED.darker());
        btnMenu.setForeground(Color.BLACK);
        btnMenu.addActionListener(l -> window.btnPressed(ButtonType.MENUBUTTON));
        this.add(btnMenu);

        //New game button

        //Stat panel player 1
        pOnePanel = new JPanel(null);
        pOnePanel.setSize(150,100);
        pOnePanel.setLocation(790,55);
        pOnePanel.setBackground(Color.DARK_GRAY.darker());

        //Stat panel player 2
        pTwoPanel = new JPanel(null);
        pTwoPanel.setSize(150,100);
        pTwoPanel.setLocation(790,170);
        pTwoPanel.setBackground(Color.DARK_GRAY.darker());

        //Player set (temporary)
        pOne = new JLabel("Player 1:");
        pOne.setSize(100,10);
        pOne.setFont(window.getBtnFont());
        pOne.setForeground(Color.yellow.darker());
        pOne.setLocation(35,2);

        pOneScore = new JLabel("Score: 0");
        pOneScore.setSize(100,10);
        pOneScore.setFont(window.getBtnFont());
        pOneScore.setForeground(Color.yellow.darker());
        pOneScore.setLocation(35,18);

        pOneCrew = new JLabel("Crew: 3");
        pOneCrew.setSize(100,10);
        pOneCrew.setFont(window.getBtnFont());
        pOneCrew.setForeground(Color.yellow.darker());
        pOneCrew.setLocation(35,34);

        pOnePanel.add(pOne);
        pOnePanel.add(pOneScore);
        pOnePanel.add(pOneCrew);


        pTwo = new JLabel("Player 2:");
        pTwo.setSize(100,10);
        pTwo.setFont(window.getBtnFont());
        pTwo.setForeground(Color.yellow.darker());
        pTwo.setLocation(35,2);

        pTwoScore = new JLabel("Score: 0");
        pTwoScore.setSize(100,10);
        pTwoScore.setFont(window.getBtnFont());
        pTwoScore.setForeground(Color.yellow.darker());
        pTwoScore.setLocation(35,18);

        pTwoCrew = new JLabel("Crew: 3");
        pTwoCrew.setSize(100,10);
        pTwoCrew.setFont(window.getBtnFont());
        pTwoCrew.setForeground(Color.yellow.darker());
        pTwoCrew.setLocation(35,34);

        pTwoPanel.add(pTwo);
        pTwoPanel.add(pTwoScore);
        pTwoPanel.add(pTwoCrew);


        this.add(pOnePanel);
        this.add(pTwoPanel);

        addTiles();

        gamePanel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                controller.handleMouseClick((10*(e.getY() / 70) + (e.getX()/70)));
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * @param ska vara en färdig array som bara adderas
     */

    public void addTiles(){
        for(int i = 0; i < gamePanels.length; i++){
            gamePanels[i] = new JPanel();
            gamePanels[i].setBackground(Color.GREEN.darker());
            gamePanels[i].setBorder(new LineBorder(Color.yellow));
            gamePanel.add(gamePanels[i]);
        }
    }

    public void setCurrentPlayer(int index){
        if(index == 0){
            pOnePanel.setBackground(Color.PINK.darker());
            pTwoPanel.setBackground(Color.DARK_GRAY.darker());
        }else{
            pOnePanel.setBackground(Color.DARK_GRAY.darker());
            pTwoPanel.setBackground(Color.PINK.darker());

        }
    }

    public void updatePlayerInfo(int score1, int score2, int crew1, int crew2){
        pOneScore.setText("Score: " + score1);
        pTwoScore.setText("Score: " + score2);
        pOneCrew.setText("Crew: " + crew1);
        pTwoCrew.setText("Crew: " + crew2);
        repaint();
    }

    public void revealTile(int index, String type){
        switch(type){
            case "EMPTY":
                gamePanels[index].setBorder(new LineBorder(Color.gray));
                gamePanels[index].setBackground(Color.gray.darker());
                break;
            case "SUPRISE":
                gamePanels[index].setBorder(new LineBorder(Color.gray));
                gamePanels[index].setBackground(Color.blue.darker());
                break;
            case "TRAP":
                gamePanels[index].setBorder(new LineBorder(Color.gray));
                gamePanels[index].setBackground(Color.red);
                break;
            case "TREASURE":
                gamePanels[index].setBorder(new LineBorder(Color.gray));
                gamePanels[index].setBackground(Color.yellow);

                break;
        }

        gamePanels[index].repaint();
    }

}
