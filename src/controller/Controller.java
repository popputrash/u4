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
    private ArrayList<TreasureGroup> treasureGroups;
    private int remainingTurns;
    private String[] map;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new ScoreItem[10];
        playerList = new ArrayList<Player>();
        playerList.add(new Player());
        playerList.add(new Player());
        treasureGroups = new ArrayList<TreasureGroup>();
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
                setupNewGame();
                window.setGameScreen(gameTiles);
                updateView();
                break;
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
        if(currentPlayer.isRandomTurn()){
            digRandomTile(currentPlayer);
        }else{
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
        }
        updateView();

        if(checkEndGame()){
            window.setEndScreen();
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
        gameTiles = generateMap();

        playerList.clear();
        playerList.add(new Player());
        playerList.add(new Player());

        for(GameTile tile : gameTiles){
            tile.preview();
        }
        currentPlayer = playerList.get(0);

    }

    public void handleExtraTurns(Player currentPlayer, int extraTurns) {
        currentPlayer.setTurns(currentPlayer.getTurns() + extraTurns);
    }

    public void digRandomTreasureTile(Player currentPlayer){
        Random rand = new Random();
        int index = rand.nextInt(treasureGroups.size());
        TreasureGroup tg = treasureGroups.get(index);
        while(tg.isFullyFound()){
            index = rand.nextInt(treasureGroups.size());
            tg = treasureGroups.get(index);
        }
        TreasureTile temp = tg.getRandomTile();
        temp.dig(this, currentPlayer);
        temp.reveal();
    }

    public void digRandomTile(Player currentPlayer) {
        Random rand = new Random();
        int index = rand.nextInt(gameTiles.length);
        gameTiles[index].dig(this, currentPlayer);
        gameTiles[index].reveal();
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
        GameTile[][] temp = new GameTile[10][10];
        Random rand = new Random();
        for(int i = 1; i <= 5; i++){
            TreasureGroup tempG = new TreasureGroup(i);
            treasureGroups.add(tempG);
            int posX = rand.nextInt(9);
            int posY = rand.nextInt(9);
            boolean spotFound = false;
            while(!spotFound){
                spotFound = true;
                for(int x = 0; x < tempG.getShape().length; x++) {
                    for (int y = 0; y < tempG.getShape()[x].length; y++) {
                        //if (tempG.getShape()[x][y] == 0)break;
                        if (posX + x > 9 || posY + y > 9){
                            spotFound = false;
                            break;
                        }
                        try{
                            if (temp[posX + x + 1][posY + y] != null) spotFound = false;
                            if (temp[posX + x - 1][posY + y] != null) spotFound = false;
                            if (temp[posX + x][posY + y + 1] != null) spotFound = false;
                            if (temp[posX + x][posY + y - 1] != null) spotFound = false;
                        }catch (ArrayIndexOutOfBoundsException _){

                        }

                        if (temp[posX + x][posY + y] != null)spotFound = false;
                    }
                }
                if(!spotFound){
                    posX = rand.nextInt(9);
                    posY = rand.nextInt(9);
                    System.out.println("trying new pos at: posX: " + posX + " posY: " + posY);
                }

            }

            for (int x = 0; x < tempG.getShape().length; x++) {
                for (int y = 0; y < tempG.getShape()[x].length; y++) {
                    if(posX + x > 9 || posY + y > 9)break;
                    if(tempG.getShape()[x][y] == 1){
                        TreasureTile tempTile = new TreasureTile(tempG);
                        temp[posX + x][posY + y] = tempTile;
                        tempG.addTreasure(tempTile);
                    }
                }
            }
        }

        for(int i = 0; i < 5; i++){
            int posX = 0;
            int posY = 0;
            boolean spotFound = false;
            while (!spotFound){
                spotFound = true;
                posX = rand.nextInt(9);
                posY = rand.nextInt(9);
                if(temp[posX][posY] != null)spotFound = false;

            }
            temp[posX][posY] = new TrapTile(rand.nextInt(4));


        }

        for(int i = 0; i <= rand.nextInt(3); i++){
            int posX = 0;
            int posY = 0;
            boolean spotFound = false;
            while (!spotFound){
                spotFound = true;
                posX = rand.nextInt(9);
                posY = rand.nextInt(9);
                if(temp[posX][posY] != null)spotFound = false;

            }
            temp[posX][posY] = new SurpriseTile(rand.nextInt(5));

        }

        for(int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j] == null) {
                    temp[i][j] = new EmptyTile();
                }
            }
        }

        GameTile[] returnArray = new GameTile[100];
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                returnArray[i + j*10] = temp[i][j];
            }
        }
        return returnArray;
    }

    public boolean checkEndGame(){
        boolean endGame = true;
        for(TreasureGroup treasureGroup : treasureGroups){
            if(!treasureGroup.isFullyFound())endGame = false;
        }

        return currentPlayer.getCrew() < 1 || endGame;
    }

    public Player getWinner(){
        if(playerList.get(0).getScore() > playerList.get(1).getScore()){
            return playerList.get(0);
        }
        return playerList.get(1);
    }

    public void notifySuprise(SurpriseTile surpriseTile){
        window.displayMessage(surpriseTile.getSupriseMessage());
    }

    public void notifyTrap(TrapTile trapTile){
        window.displayMessage(trapTile.getTrapMessage());
    }

    public void notifyTreasure(TreasureTile treasureTile){
        window.displayMessage(treasureTile.getFullyFoundMessage());
    }

}
