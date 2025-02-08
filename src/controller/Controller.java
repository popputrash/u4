package controller;
import view.*;
import model.*;

public class Controller {
    final private Window window;
    private ScoreItem[] highScores;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[10];
    }
    public void btnPressed(ButtonType button){

        switch(button){
            case STARTBUTTON:
                System.out.println("start");
                window.setGameScreen();
                break;
            case EXITBUTTON:
                System.out.println("exit");
                window.dispose();
                break;
            case MENUBUTTON:
                System.out.println("menu");
                window.setMenuScreen();
                break;
            case NEWGAMEBUTTON:
                System.out.println("new game");
                window.clearGamePanel();
                window.setup();
        }
    }
    public void addHighScore(String name, int score){
        ScoreItem scoreA;
        ScoreItem scoreB;

        for(int i = 0; i < highScores.length; i++){
            if(highScores[i] != null && score > highScores[i].getScore()){
                scoreA = highScores[i];
                highScores[i] = new ScoreItem(score,name);

                highScores[i+i] = scoreA;

                return;
            }
        }
    }

    /**
     * sätter highScore skärmen till highscore arrayen
     */
    public void setHighScores(){
        String[] temp = new String[highScores.length];
        for (int i = 0; i < highScores.length; i++) {
            if(highScores[i] != null){
                temp[i] = highScores[i].toString();
            }
        }
        window.setHighScores(temp);
    }

    public void saveHighScores(){}

}
