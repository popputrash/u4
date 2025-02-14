package model;
import controller.Controller;

import java.awt.*;

/**
 * Klass för treasures
 * @author Maximilian Andersen & Elias Brännström
 */
public class TreasureTile extends GameTile {
    TreasureGroup group;

    /**
     * Konstruktor, tar in en den treasuregrupp den tillhör
     * @param group
     * @author Maximilian Andersen & Elias Brännström
     */
    public TreasureTile(TreasureGroup group) {
        super("TREASURE");
        this.group = group;
    }

    /**
     * Overriden dig metod som hanterar ifall hela treasuren är uppgrävd
     * @param controller
     * @param player
     * @author Maximilian Andersen & Elias Brännström
     */
    @Override
    public void dig(Controller controller, Player player) {
        setFound(true);
        group.decrementTilesRemaining();
        if(group.isFullyFound()){
            player.addScore(group.getScore());
            controller.notifyTreasure(this.getFullyFoundMessage());

        }
    }

    /**
     * Metod som returnerar meddelande för när spelare grävt upp en hel treasure.
     * @return
     * @author Maximilian Andersen & Elias Brännström
     */
    public String getFullyFoundMessage(){
        return "You found a the last piece of a treasure and was awarded " + group.getScore() + "points!";
    }

}
