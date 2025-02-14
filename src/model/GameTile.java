package model;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Superklass för Tiles i spelet
 * @author Maximilian Andersen & Elias Brännström
 */
public abstract class GameTile {
    private String typeString;
    private boolean found;

    /**
     * Konstruktor för GameTile
     * @param text
     * @author Maximilian Andersen & Elias Brännström
     */
    public GameTile(String text) {
        typeString = text;
        found = false;
    }

    /**
     * Getter för typeString
     * @return
     */
    public String getTypeString() {
        return typeString;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    /**
     * Metod som returnera ifall tile är uppgrävd
     * @return
     */
    public boolean isFound() {
        return found;
    }

    /**
     * Abstrakt klass
     * @param controller
     * @param player
     */
    public abstract void dig(Controller controller, Player player);



}
