package controller;
import view.*;
import model.*;
import view.Window;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    final private Window window;
    private ScoreItem[] highScores;
    private GameTile[] gameTiles = new GameTile[100];
    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private TreasureGroup treasureGroup;
    private int remainingTurns;
    private String[] map;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[10];
        playerList = new ArrayList<Player>();
        playerList.add(new Player());
        playerList.add(new Player());
        treasureGroup = new TreasureGroup(1);
    }

    public void btnPressed(ButtonType button){

        switch(button){
            case STARTBUTTON:
                System.out.println("start");
                setupNewGame();
                window.setGameScreen(gameTiles);
                updateView();
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
                window.setGameScreen(gameTiles);
                updateView();
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
        if(!gameTiles[index].isFound()){
            gameTiles[index].dig(this, currentPlayer);
            gameTiles[index].reveal();
            currentPlayer.setTurns(currentPlayer.getTurns() - 1);
        }
        if(currentPlayer.getTurns() <= 0){
            switchPlayer();
        }

        updateView();

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
                TreasureTile tile = new TreasureTile(treasureGroup);
                treasureGroup.addTreasure(tile);
                gameTiles[i] = tile;
            } else if (i%15 == 0) {
                gameTiles[i] = new TrapTile();
            } else if (i%15 == 1) {
                gameTiles[i] = new SurpriseTile();
            }
            else{
                gameTiles[i] = new EmptyTile();
            }
        }
        currentPlayer = playerList.get(0);

    }

    public void handleExtraTurns(Player currentPlayer, int extraTurns) {
        currentPlayer.setTurns(currentPlayer.getTurns() + extraTurns);
    }

    public void digRandomTreasureTile(Player currentPlayer){
        //gräv upp random treasuretile
    }

    public void digRandomTile(Player currentPlayer) {
        //gräv upp random tile nästa drag.
    }

    public Player getOpponent(Player currentPlayer) {
        return playerList.get(1 - playerList.indexOf(currentPlayer));
    }

    public void switchPlayer(){
        currentPlayer = playerList.get(1 - playerList.indexOf(currentPlayer));
        currentPlayer.setTurns(1);
    }

    public void updateView(){
        window.updateCurrentPlayer(playerList.indexOf(currentPlayer));
        window.updatePlayerInfo(playerList.get(0).getScore(), playerList.get(1).getScore(),
                playerList.get(0).getCrew(), playerList.get(1).getCrew());

    }

    public GameTile[] generateMap(){
        //Generera random karta
        GameTile[] temp = new GameTile[100];


        return temp;
    }

}
