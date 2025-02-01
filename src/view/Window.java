package view;
import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    JPanel currentScreen;
    JPanel gameScreen, startScreen, EndScreen;
    Controller controller;

    Font titleFont = new Font("Castellar", Font.BOLD, 40);
    Font btnFont = new Font("Algerian", Font.PLAIN, 14);
    Font subTitleFont = new Font("Castellar", Font.BOLD, 30);

    public Window(String title, Controller controller){
        super(title);
        this.controller = controller;
        setVisible(true);
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startScreen = new StartScreen(500,500, this);
        gameScreen = new GameScreen(500,500, this);
        EndScreen = new EndScreen(500,500, this);

        currentScreen = startScreen;
        add(currentScreen);
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
    public void setEndScreen(){
        this.remove(currentScreen);
        this.setWindowSize(500,500);
        setCurrentScreen(EndScreen);
        this.add(currentScreen);
        this.repaint();
    }

    private void setCurrentScreen(JPanel screen){
        currentScreen = screen;
    }
    public void setWindowSize(int width, int height){
        this.setSize(width,height);
    }


}
