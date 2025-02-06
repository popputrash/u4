package model;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Tänker att varje möjlig spelruta ärver denna klass.
 *
 */
public abstract class GameTile extends JPanel {
    Color color;
    JLabel text;
    boolean found;
    public GameTile(Color color, String text){
        this.color = color;
        this.text = new JLabel(text);
        this.text.setVisible(false);
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
