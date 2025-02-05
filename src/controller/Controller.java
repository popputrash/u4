package controller;
import view.*;
import model.*;

public class Controller {
    final private Window window;
    private ScoreItem[] highScores;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[5];

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
                window.populateGamePanel();

        }
    }
    public void addHighScore(String name, int score){
        ScoreItem tempHighScoreItem;
        ScoreItem tempsScore;
        for(int i = 0; 0 < highScores.length; i++){
            if(score > highScores[i].getScore()){
                tempHighScoreItem = highScores[i];
                highScores[i] = new ScoreItem(score,name);

                // lägg till lite mer loopar för att justera listan


                return;
            }
        }
    }

}
