package model;
import controller.Controller;

import java.awt.*;

public class TreasureTile extends GameTile {
    TreasureGroup group;

    public TreasureTile(TreasureGroup group) {
        super(Color.yellow, "TREASURE");
        this.group = group;
    }

    @Override
    public void dig(Controller controller, Player player) {
        group.decrementTilesRemaining();
        if(group.isFullyFound()){
            player.addScore(group.getSize() * 100);
            controller.notifyTreasure(this);

        }
    }

    public String getFullyFoundMessage(){
        return "You found a the last piece of a treasure and was awarded 100 points!";
    }

}
