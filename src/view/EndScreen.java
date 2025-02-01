package view;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    Window window;
    JLabel mainTitle, gameOver;
    JButton btnNewGame, btnMenu;
    JTextField enterWinner;

    public EndScreen (int width, int height, Window window){
        super(null);
        this.window = window;
        this.setSize(width, height);
        this.setBackground(Color.black);

        // Titel
        mainTitle = new JLabel("SKATTJAKT!");
        mainTitle.setSize(300,50);
        mainTitle.setLocation(110,0);
        mainTitle.setForeground(Color.yellow.darker());
        mainTitle.setFont(window.getTileFont());
        this.add(mainTitle);

        // Game Over
        gameOver = new JLabel("GAME OVER");
        gameOver.setSize(300,50);
        gameOver.setLocation(150,50);
        gameOver.setForeground(Color.red.darker());
        gameOver.setFont(window.getSubTitleFont());
        this.add(gameOver);

        // Enter winner name
        enterWinner = new JTextField("Enter winner");
        enterWinner.setSize(200,100);
        enterWinner.setLocation(150,100);
        enterWinner.setFont(window.getBtnFont());
        enterWinner.setBackground(Color.DARK_GRAY.darker());
        enterWinner.setForeground(Color.yellow.darker());
        this.add(enterWinner);

        //Main menu button
        btnMenu = new JButton("HUVUDMENY");
        btnMenu.setEnabled(true);
        btnMenu.setSize(200,20);
        btnMenu.setLocation(150,280);
        btnMenu.setFont(window.getBtnFont());
        btnMenu.setBackground(Color.RED.darker());
        btnMenu.setForeground(Color.BLACK);
        btnMenu.addActionListener(l -> window.btnPressed(ButtonType.MENUBUTTON));
        this.add(btnMenu);

        //New game button
        btnNewGame = new JButton("NYTT SPEL");
        btnNewGame.setEnabled(true);
        btnNewGame.setSize(200,20);
        btnNewGame.setLocation(150,250);
        btnNewGame.setFont(window.getBtnFont());
        btnNewGame.setBackground(Color.green.darker());
        btnNewGame.setForeground(Color.BLACK);
        btnNewGame.addActionListener(l -> window.btnPressed(ButtonType.NEWGAMEBUTTON));
        this.add(btnNewGame);
    }
}
