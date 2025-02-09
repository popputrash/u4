package model;
import controller.Controller;

import java.awt.*;
import java.util.Random;

/**
 * Subklass för SupriseTiles, hanterar implementering av 4 olika suprises.
 */
public class SurpriseTile extends GameTile{
    private int type;

    /**
     * Konstruktor
     * @param type
     */
    public SurpriseTile(int type){
        super(Color.blue.darker(), "SURPRISE");
        if( type > 4 || type < 1 ){
            this.type = 2;
        }else
            this.type = type;
    }

    /**
     * overridden dig metod som hanterar olika typer av suprises.
     * @param controller
     * @param currentPlayer
     * @author Maximilian Andersen & Elias Bränsström
     */
    @Override
    public void dig(Controller controller, Player currentPlayer) {
        controller.notifySuprise(this);
        switch(type){
            case 1:
                currentPlayer.addCrew();
                break;
            case 2:
                int extra = currentPlayer.getCrew();
                controller.handleExtraTurns(currentPlayer, extra);
                break;
            case 3:
                controller.digRandomTreasureTile(currentPlayer);
                break;
            case 4:
                currentPlayer.setRandomTurn(true);
                break;
        }

    }

    /**
     * Metod för att returnera meddelande för given typ av suprise.
     * @return
     * @author Maximilian Andersen & Elias Bränsström
     */
    public String getSupriseMessage(){
        switch(type){
            case 1:
                return "You found a crewmember!";
            case 2:
                return "You got 1 extra turn for each crewmember!";
            case 3:
                return "We dug up a random treasure for u!";
            case 4:
                return "Your next turn will be random!";
            default:
                return "Default surprise message!";
        }
    }

}
