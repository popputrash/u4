package Controller;

import view.*;

import java.sql.SQLOutput;

public class Controller {
    final private Window window;
    private String[] highScores;

    public Controller(){
        window = new Window("Skattjakt", this);
        highScores = new String[5];

    }
    public void btnPressed(ButtonType button){

        switch(button){
            case STARTBUTTON:
                System.out.println("start");
                window.setGameScreen();
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
                //window.setEndScreen();
        }

    }

}
