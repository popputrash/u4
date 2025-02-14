package view;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private GameScreen gameScreen;
    private final StartScreen startScreen;
    private final EndScreen EndScreen;
    private JPanel currentScreen;
    private final Controller controller;

    private final Font titleFont = new Font("Castellar", Font.BOLD, 40),
                 btnFont = new Font("Algerian", Font.PLAIN, 14),
                 subTitleFont = new Font("Castellar", Font.BOLD, 30);

    public Window(String title, Controller controller){
        super(title);
        this.controller = controller;

        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startScreen = new StartScreen(500,500, this);
        EndScreen = new EndScreen(500,500, this);

        currentScreen = startScreen;
        add(currentScreen);
        setVisible(true);
    }

    public Font getTileFont(){return titleFont;}
    public Font getBtnFont(){return btnFont;}
    public Font getSubTitleFont(){return subTitleFont;}

    public void btnPressed(ButtonType button){
        controller.btnPressed(button);
    }

    public void setGameScreen(){
        this.remove(currentScreen);
        this.setWindowSize(1000,830);
        gameScreen = new GameScreen(500,500, this,  controller);
        setCurrentScreen(gameScreen);
        this.add(currentScreen);
        this.repaint();
    }
    public void setMenuScreen(){
        this.remove(currentScreen);
        this.setWindowSize(500,500);
        setCurrentScreen(startScreen);
        this.add(currentScreen);
        this.repaint();
    }
    public void setEndScreen(int score){
        this.remove(currentScreen);
        this.setWindowSize(500,500);
        setCurrentScreen(EndScreen);
        EndScreen.setScore(score);
        this.add(currentScreen);
        this.repaint();
    }

    private void setCurrentScreen(JPanel screen){
        currentScreen = screen;
    }
    public void setWindowSize(int width, int height){
        this.setSize(width,height);
    }

    public void setHighScores (String[] highScores){
        startScreen.setHighScores(highScores);
    }

    public void updatePlayerInfo(int score1, int score2, int crew1, int crew2){
        gameScreen.updatePlayerInfo(score1, score2, crew1, crew2);
    }

    public void updateCurrentPlayer(int index){
        gameScreen.setCurrentPlayer(index);
    }

    public void displayMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public String getUsername(){
        return EndScreen.getUsername();
    }

    public void revealTile(int index, String type){
        gameScreen.revealTile(index, type);
    }

}
