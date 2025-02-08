package model;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Tänker att varje möjlig spelruta ärver denna klass.
 *
 */
public class GameTile extends JPanel {
    Color color;
    JLabel text;
    boolean found;
    TileType tileType;

    public GameTile(Color color, String text, TileType tileType) {
        this.color = color;
        this.text = new JLabel(text);
        this.text.setVisible(false);
        this.tileType = tileType;
        add(this.text);
        found = false;
        setBackground(Color.GREEN.darker());
        setBorder(new LineBorder(Color.yellow));
    }
    public void reveal() {
        found = true;
        text.setVisible(true);
        setBorder(new LineBorder(Color.gray));
        setBackground(color);
    }
    public void reset(){
        found = false;
        text.setVisible(false);
        setBackground(Color.GREEN.darker());
        setBorder(new LineBorder(Color.yellow));
    }

    public boolean isFound() {
        return found;
    }


}
