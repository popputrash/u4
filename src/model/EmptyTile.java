package model;

import controller.Controller;

import javax.swing.border.LineBorder;
import java.awt.*;

public class EmptyTile extends GameTile{

    public EmptyTile() {
        super(Color.gray, "");
    }

    @Override
    public void dig(Controller controller, Player player) {

    }

}
