package controller;
import view.*;
import model.*;

import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Klass för controller, hanterar all spellogik och medlar information mellan view och model packages.
 */
public class Controller {
    final private Window window;
    private ArrayList<ScoreItem> highScores;
    private GameTile[] gameTiles = new GameTile[100];
    private ArrayList<Player> playerList;
    private Player currentPlayer;
    private ArrayList<TreasureGroup> treasureGroups;
    private final String scoreFile = "src/controller/scores.txt";

    /**
     * Konstruktor för controllern, sätter upp allt som behövs för spelet.
     * @author Maximilian Andersen & Elias Brännström
     */
    public Controller(){
        highScores = new ArrayList<>();
        readHighScores();
        window = new Window("Skattjakt", this);
        setHighScores();
        playerList = new ArrayList<Player>();
        playerList.add(new Player());
        playerList.add(new Player());
        treasureGroups = new ArrayList<TreasureGroup>();
    }


    /**
     * Metod för att hantera knapptryckningar i view klasserna.
     * @param button
     * @author Maximilian Andersen & Elias Brännström
     */
    public void btnPressed(ButtonType button){

        switch(button){
            case STARTBUTTON:
                System.out.println("start");
                setupNewGame();
                window.setGameScreen();
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
                window.setGameScreen();
                updateView();
                break;
            case SAVE:
                addHighScore(window.getUsername(), getWinner().getScore());
                window.setMenuScreen();
                setHighScores();
        }
    }

    /**
     * Metod för att addera ett nytt highscore till highscore listan.
     * @param name
     * @param score
     */
    public void addHighScore(String name, int score){
        ScoreItem s = new ScoreItem(score, name);
        highScores.add(s);
        saveHighScores();
    }

    /**
     * Metod för att hantera musklick i gamescreen, egenligen en gameloop
     * @param e, information från musen.
     * @author Maximilian Andersen & Elias Brännström
     */
    public void handleMouseClick(int index){
        if(currentPlayer.isRandomTurn()) {
            index = digRandomTile();
            currentPlayer.setRandomTurn(false);
        }

        if(!gameTiles[index].isFound()){
            window.revealTile(index, gameTiles[index].getTypeString());
            gameTiles[index].dig(this, currentPlayer);
            currentPlayer.setTurns(currentPlayer.getTurns() - 1);
        }

        if(currentPlayer.getTurns() <= 0){
            switchPlayer();
        }

        updateView();

        if(checkEndGame()){
            window.setEndScreen(getWinner().getScore());
        }

    }

    /**
     * Metod för att förmedla highscore lista till view klassen och visa denna.
     * @author Maximilian Andersen & Elias Brännström
     */
    public void setHighScores(){
        readHighScores();
        String[] temp = new String[highScores.size()];
        for (int i = 0; i < highScores.size(); i++) {
            if(highScores.get(i) != null && i < 10){
                temp[i] = highScores.get(i).toString();
            }
        }
        window.setHighScores(temp);
    }

    /**
     * Metod för att läsa av sparade highscores.
     * @author Maximilian Andersen & Elias Brännström
     */
    public void readHighScores(){
        highScores.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(scoreFile))){
            for(String line = br.readLine(); line != null; line = br.readLine()){
                String[] temp = line.split(" ");
                ScoreItem s = new ScoreItem(Integer.parseInt(temp[1]), temp[0]);
                highScores.add(s);
            }

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
        sortHighscores();
    }

    /**
     * Metod för att spara ner highscores till textfil-
     * @author Maximilian Andersen & Elias Brännström
     */
    public void saveHighScores() {
        sortHighscores();

        if(highScores.size() > 10){
            highScores.subList(0,10);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile))){
            for(int i = 0; i < highScores.size(); i++){
                writer.write(highScores.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metod för att starta ett nytt spel.
     * @author Maximilian Andersen & Elias Brännström
     */
    public void setupNewGame(){
        gameTiles = generateMap();

        playerList.clear();
        playerList.add(new Player());
        playerList.add(new Player());

        currentPlayer = playerList.get(0);

    }

    /**
     * Metod för att hantera ifall en får extra drag.
     * @param currentPlayer
     * @param extraTurns
     * @author Maximilian Andersen & Elias Brännström
     */
    public void handleExtraTurns(Player currentPlayer, int extraTurns) {
        currentPlayer.setTurns(currentPlayer.getTurns() + extraTurns);
    }

    /**
     * Metod för att grava upp en random treasure till currentPlayer.
     * @param currentPlayer
     * @author Maximilian Andersen & Elias Brännström
     */
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
        window.revealTile(index, temp.getTypeString());
        if(checkEndGame()){
            window.setEndScreen(getWinner().getScore());
        }

    }

    /**
     * Metod för att gräva upp en random tile åt currentPlayer
     * @author Maximilian Andersen & Elias Brännström
     */
    public int digRandomTile() {
        Random rand = new Random();
        int i = rand.nextInt(gameTiles.length);
        while (gameTiles[i].isFound()) {
            i = rand.nextInt(gameTiles.length);
        }
        return i;
    }

    /**
     * Metod för att få motståndaren
     * @param currentPlayer
     * @return Player objektet som är motståndaren
     * @author Maximilian Andersen & Elias Brännström
     */
    public Player getOpponent(Player currentPlayer) {
        return playerList.get(1 - playerList.indexOf(currentPlayer));
    }

    /**
     * Metod för att byta currentplayer till nästa spelare
     * @author Maximilian Andersen & Elias Brännström
     */
    public void switchPlayer(){
        currentPlayer = playerList.get(1 - playerList.indexOf(currentPlayer));
        currentPlayer.setTurns(1);
    }

    /**
     * Metod för att förmedla information till view klasser under spelets gång
     * @author Maximilian Andersen & Elias Brännström
     */
    public void updateView(){
        window.updateCurrentPlayer(playerList.indexOf(currentPlayer));
        window.updatePlayerInfo(playerList.get(0).getScore(), playerList.get(1).getScore(),
                playerList.get(0).getCrew(), playerList.get(1).getCrew());

    }

    /**
     * Metod för att generera en slumpmässig karta
     * @return en slumpmässig GameTile array
     * @author Maximilian Andersen & Elias Brännström
     */
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

    /**
     * Metod för att kontrollera ifall spelet är slut
     * @return boolean ifall spelet är slut
     * @author Maximilian Andersen & Elias Brännström
     */
    public boolean checkEndGame(){
        boolean endGame = true;
        for(TreasureGroup treasureGroup : treasureGroups){
            if(!treasureGroup.isFullyFound())endGame = false;
        }

        return currentPlayer.getCrew() < 1 || endGame;
    }

    /**
     * Metod för att få vinnaren
     * @return Spelar objektet som vann
     * @author Maximilian Andersen & Elias Brännström
     */
    public Player getWinner(){
        if(playerList.get(0).getScore() > playerList.get(1).getScore()){
            return playerList.get(0);
        }
        return playerList.get(1);
    }

    /**
     * Metod för att notifera spelare om en Suprise
     * @param surpriseTile
     * @author Maximilian Andersen & Elias Brännström
     */
    public void notifySuprise(SurpriseTile surpriseTile){
        window.displayMessage(surpriseTile.getSupriseMessage());
    }

    /**
     * Metod för att notifiera spelare om en Trap
     * @param trapTile
     * @author Maximilian Andersen & Elias Brännström
     */
    public void notifyTrap(TrapTile trapTile){
        window.displayMessage(trapTile.getTrapMessage());
    }

    /**
     * Metod för att notifiera spelare om en Treasure
     * @param treasureTile
     * @author Maximilian Andersen & Elias Brännström
     */
    public void notifyTreasure(TreasureTile treasureTile){
        window.displayMessage(treasureTile.getFullyFoundMessage());
    }

    /**
     * Metod för att sortera Highscore listan.
     * @author Maximilian Andersen & Elias Brännström
     */
    public void sortHighscores(){
        for(int i = 0; i < highScores.size(); i++){
            int min = i;
            for(int j = i + 1; j < highScores.size(); j++){
                if(highScores.get(min).getScore() < highScores.get(j).getScore()){
                    min = j;
                }
            }
            ScoreItem temp = highScores.get(min);
            highScores.set(min, highScores.get(i));
            highScores.set(i, temp);
        }
    }

}
