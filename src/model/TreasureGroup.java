package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Klass för att hantera grupper av Treasures.
 */
public class TreasureGroup {
    private ArrayList<TreasureTile> treasures;
    private int tilesRemaining;
    private int shape[][];
    private int size;

    /**
     * Konstruktor, tar in id för vilken form grouppen ska ha
     * @param id
     * @author Maximilian Andersen & Elias Brännström
     */
    public TreasureGroup(int id) {
        this.treasures = new ArrayList<>();
        shape = generateShapeFromId(id);
        tilesRemaining = initialAmountOfTreasures();
        this.size = initialAmountOfTreasures();

    }

    /**
     * Metod för att addera ett Treasure objekt.
     * @param treasure
     * @author Maximilian Andersen & Elias Brännström
     */
    public void addTreasure(TreasureTile treasure) {
        this.treasures.add(treasure);
        tilesRemaining = this.treasures.size();
    }

    /**
     * Metod för att kontrollera ifall hela gruppen är uppgrävd
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public boolean isFullyFound(){
        if(tilesRemaining == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Returnerar en slumpmässigt vald icke uppgrävd splumässigt treasure
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public TreasureTile getRandomTile() {
        Random rand = new Random();
        int index = rand.nextInt(tilesRemaining);
        while(treasures.get(index).isFound()){
            index = rand.nextInt(tilesRemaining);
        }
        return treasures.get(index);

    }

    /**
     * Minskar antal treasures som är kvar
     * @author Maximilian Andersen & Elias Brännström
     */
    public void decrementTilesRemaining(){
        tilesRemaining--;
    }

    public int getScore(){
        return size * 100;
    }

    /**
     * Genererar en shape från ett id
     * @param id
     * @return 2d array av shapen.
     * @author Maximilian Andersen & Elias Brännström
     */
    public int[][] generateShapeFromId(int id){
        int temp[][];
        switch(id){
            case 1:
                temp = new int[][] {{0, 0, 0},
                                    {0, 1, 0},
                                    {0, 0, 0}};
                break;
            case 2:
                temp = new int[][] {{0,0,0},
                                    {0,1,1},
                                    {0,0,0}};
                break;
            case 3:
                temp = new int[][] {{0,0,0},
                                    {1,1,1},
                                    {0,0,0}};
                break;
            case 4:
                temp = new int[][] {{1,1,0},
                                    {0,1,0},
                                    {0,0,0}};
                break;
            case 5:
                temp = new int[][] {{0,0,0},
                                    {1,1,1},
                                    {0,1,0}};
                break;
            case 6:
                temp = new int[][] {
                                    {0,1,1},
                                    {0,1,1},
                                    {0,0,0}};
                break;
            case 7:
                temp = new int[][] {{0,0,0},
                                    {1,1,1},
                                    {0,0,1}};
                break;
            case 8:
                temp = new int[][] {{0,0,0},
                                    {1,1,0},
                                    {0,1,1}};
                break;
            case 9:
                temp = new int[][] {{0,1,0},
                                    {1,1,1},
                                    {0,1,0}};
                break;
            default:
                temp = new int[][] {{0,0,0},
                                    {0,0,0},
                                      {0,0,0}};

        }
        return temp;
    }

    /**
     * Returnerar antalet treasures från början.
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public int initialAmountOfTreasures(){
        int count = 0;
        for(int[] row : shape){
            for(int col : row){
                if(col == 1)count++;
            }
        }
        return count;
    }

    /**
     * Getter för shape
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Getter för size
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public int getSize() {
        return size;
    }
}
