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
    private int remainingTurns;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[10];
        playerList = new ArrayList<Player>();
        playerList.add(new Player());
        playerList.add(new Player());
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
                window.setGameScreen(gameTiles);
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
        int index;
        if(currentPlayer.isRandomTurn()){
            index = new Random().nextInt(100);
            while (gameTiles[index].isFound())index=new Random().nextInt(100);
        }else{
            index = (10*(e.getY() / 70) + (e.getX()/70));
        }
        System.out.print("x: " + e.getX()/70);
        System.out.print(", y: " + e.getY()/70);
        System.out.println(", Index: " + index);

        if(!gameTiles[index].isFound()){
            gameTiles[index].reveal();
            switch(gameTiles[index].getTileType()){
                case EMPTY:
                    break;
                case TREASURE:
                    handleTreasureTile(currentPlayer);
                    break;
                case TRAP:
                    handleTrapTile(currentPlayer);
                    break;
                case SUPRISE:
                    handleSupriseTile(currentPlayer);
                    break;
            }
            if(remainingTurns <= 0){
                currentPlayer = playerList.get(1 - playerList.indexOf(currentPlayer));
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

    public void setupNewGame(){
        gameTiles = new GameTile[100];
        for (int i = 0; i < 100; i++) {
            if(i%4==0){
                gameTiles[i] = new GameTile(Color.yellow, "TREASURE", TileType.TREASURE);
            } else if (i%15 == 0) {
                gameTiles[i] = new GameTile(Color.RED.darker(), "TRAP", TileType.TRAP);
            } else if (i%15 == 1) {
                gameTiles[i] = new GameTile(Color.BLUE.darker(), "SUPRISE", TileType.SUPRISE);
            }
            else{
                gameTiles[i] = new GameTile(Color.GRAY, "", TileType.EMPTY );
            }
        }
       currentPlayer = playerList.get(0);
    }

    public void handleTrapTile(Player currentPlayer) {
    }

    public void handleTreasureTile(Player currentPlayer) {

    }

    public void handleSupriseTile(Player currentPlayer) {
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                currentPlayer.setCrew(currentPlayer.getCrew() + 1);
                break;
            case 1:
                remainingTurns = currentPlayer.getCrew();
                break;
            case 2:
                digRandomTreasureTile(currentPlayer);
                break;
            case 3:
                currentPlayer.setRandomTurn(true);
                break;
        }
    }

    public void digRandomTreasureTile(Player currentPlayer) {

    }

}
