package controller;
import view.*;
import model.*;

import java.awt.event.MouseEvent;

public class Controller {
    final private Window window;
    private ScoreItem[] highScores;
    private GameTile[] gameTiles = new GameTile[100];

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[10];
    }
    public void btnPressed(ButtonType button){

        switch(button){
            case STARTBUTTON:
                System.out.println("start");
                setupNewGame();
                window.setGameScreen(gameTiles);
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
                setupNewGame();
                window.setup(gameTiles);
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

    public void handleMouseClick(MouseEvent e){
        int index = (10*(e.getY() / 70) + (e.getX()/70));
        System.out.print("x: " + e.getX()/70);
        System.out.print(", y: " + e.getY()/70);
        System.out.println(", Index: " + index);
        gameTiles[index].reveal();

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

    public void setupNewGame(){
        gameTiles = new GameTile[100];
        for (int i = 0; i < 100; i++) {

            if(i%4==0){
                gameTiles[i] = new TreasureTile();
            } else if (i%15 == 0) {
                gameTiles[i] = new TrapTile();
            } else if (i%15 == 1) {
                gameTiles[i] = new SurpriseTile();
            }
            else{
                gameTiles[i] = new EmptyTile();
            }

        }

    }

}
