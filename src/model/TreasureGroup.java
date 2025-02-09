package model;

import java.util.ArrayList;

public class TreasureGroup {
    ArrayList<TreasureTile> treasures;
    int id, tilesRemaining;

    public TreasureGroup(int id) {
        this.treasures = new ArrayList<>();
        this.id = id;
    }

    public void addTreasure(TreasureTile treasure) {
        this.treasures.add(treasure);
        tilesRemaining = this.treasures.size();
    }

    public boolean isFullyFound(){
        if(tilesRemaining == 0){
            System.out.println(tilesRemaining + " tiles remaining.");
            return true;
        }else {
            System.out.println(tilesRemaining + " tiles remaining.");
            return false;
        }
    }

    public void decrementTilesRemaining(){
        tilesRemaining--;
    }
}
