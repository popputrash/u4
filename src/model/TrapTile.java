package model;
import controller.Controller;

import java.awt.*;

/**
 * Class för Traps, hanterar 3 olika typer av traps
 * @author Maximilian Andersen & Elias Bränsström
 */
public class TrapTile extends GameTile{
    private int type;

    /**
     * Konstruktor
     * @param type
     * @author Maximilian Andersen & Elias Bränsström
     */
    public TrapTile(int type){
        super(Color.RED.darker(), "TRAP");
        if( type > 3 ){
            this.type = 3;
        }else if(type < 1){
            this.type = 1;
        } else
            this.type = type;
    }

    /**
     * Traps impolementation av dig, hanterar 3 olika typer av traps
     * @param controller
     * @param currentPlayer
     * @author Maximilian Andersen & Elias Bränsström
     */
    @Override
    public void dig(Controller controller, Player currentPlayer) {
        switch (type){
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

        controller.notifyTrap(this);
    }

    /**
     * Metod för att returnera meddelande för given typ av trap.
     * @return
     * @author Maximilian Andersen & Elias Bränsström
     */
    public String getTrapMessage(){
        switch(type){
            case 1:
                return "You lost 100 points!";
            case 2:
                return "Your opponent got 10% of you total points!";
            case 3:
                return "You lost a crewmember!";
            default:
                return "Default trap message!";
        }
    }
}
