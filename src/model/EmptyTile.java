package model;

import controller.Controller;

import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Subklass för en tom tile.
 */
public class EmptyTile extends GameTile{

    public EmptyTile() {
        super("EMPTY");
    }

    @Override
    public void dig(Controller controller, Player player) {
        setFound(true);

    }

}
