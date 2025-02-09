package model;
import controller.Controller;

import java.awt.*;
import java.util.Random;

public class TrapTile extends GameTile{

    public TrapTile(){
        super(Color.RED.darker(), "TRAP");
    }

    @Override
    public void dig(Controller controller, Player currentPlayer) {
        Random rand = new Random();
        switch (rand.nextInt(4)){
            // blir av med upp till 100p
            case 1:
                currentPlayer.decScore(100);
                break;
            //motståndaren får en andel av poängen
            case 2:
                int p = (int)(currentPlayer.getScore() * 0.1);
                currentPlayer.decScore(p);
                controller.getOpponent(currentPlayer).addScore(p);
                break;
            //förlora en crew mate
            case 3:
                currentPlayer.decCrew();
                break;
        }
    }
}
