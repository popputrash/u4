package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TreasureGroup {
    private ArrayList<TreasureTile> treasures;
    private int tilesRemaining;
    private int shape[][];

    public TreasureGroup(int id) {
        this.treasures = new ArrayList<>();
        shape = generateShapeFromId(id);
        tilesRemaining = initialAmountOfTreasures();

    }

    public void addTreasure(TreasureTile treasure) {
        this.treasures.add(treasure);
        tilesRemaining = this.treasures.size();
    }

    public boolean isFullyFound(){
        if(tilesRemaining == 0){
            return true;
        }else {
            return false;
        }
    }

    public TreasureTile getRandomTile() {
        Random rand = new Random();
        int index = rand.nextInt(tilesRemaining);
        while(treasures.get(index).isFound()){
            index = rand.nextInt(tilesRemaining);
        }
        return treasures.get(index);

    }

    public void decrementTilesRemaining(){
        tilesRemaining--;
    }

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

    public int initialAmountOfTreasures(){
        int count = 0;
        for(int[] row : shape){
            for(int col : row){
                if(col == 1)count++;
            }
        }
        return count;
    }

    public int[][] getShape() {
        return shape;
    }
}
