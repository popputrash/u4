package view;

import controller.Controller;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameScreen extends JPanel {
    Window window;
    JLabel mainTitle, pOne, pTwo;
    JPanel gamePanel, pOnePanel,pTwoPanel;
    JButton btnMenu, btnNewGame;
    GameTile[] gameTiles = new GameTile[100];
    private Controller controller;


    public GameScreen(int width, int height, Window window, Controller controller, GameTile[] gameTiles) {

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
        btnNewGame = new JButton("NYTT SPEL");
        btnNewGame.setEnabled(true);
        btnNewGame.setSize(150,20);
        btnNewGame.setLocation(790,700);
        btnNewGame.setFont(window.getBtnFont());
        btnNewGame.setBackground(Color.green.darker());
        btnNewGame.setForeground(Color.BLACK);
        btnNewGame.addActionListener(l -> window.btnPressed(ButtonType.NEWGAMEBUTTON));
        this.add(btnNewGame);

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
        pOnePanel.add(pOne);

        pTwo = new JLabel("Player 2:");
        pTwo.setSize(100,10);
        pTwo.setFont(window.getBtnFont());
        pTwo.setForeground(Color.yellow.darker());
        pTwo.setLocation(35,2);
        pTwoPanel.add(pTwo);


        this.add(pOnePanel);
        this.add(pTwoPanel);

        addTiles(gameTiles);

        gamePanel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                controller.handleMouseClick(e);
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

    public void addTiles(GameTile[] gameTiles){
        for(GameTile gameTile : gameTiles){
            gamePanel.add(gameTile);
        }
        gamePanel.repaint();
    }

    public void clearGamePanel(){
        for(GameTile gametile : gameTiles){
            gamePanel.remove(gametile);
        }

    }
    public boolean isGameFinished(){
        for (GameTile gameTile : gameTiles){
            if (!gameTile.isFound()){
                return false;
            }
        }
        return true;
    }

    public void gameOver(){
        window.setEndScreen();
    }

}
