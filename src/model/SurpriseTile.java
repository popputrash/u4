package model;
import controller.Controller;

import java.awt.*;
import java.util.Random;

public class SurpriseTile extends GameTile{
    public SurpriseTile(){
        super(Color.blue.darker(), "SURPRISE");
    }

    @Override
    public void dig(Controller controller, Player currentPlayer) {
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                currentPlayer.addCrew();
                break;
            case 1:
                int extra = currentPlayer.getCrew();
                controller.handleExtraTurns(currentPlayer, extra);
                break;
            case 2:
                controller.digRandomTreasureTile(currentPlayer);
                break;
            case 3:
                controller.digRandomTile(currentPlayer);
                break;
        }
    }
}
