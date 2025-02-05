package model;


import javax.swing.*;
import java.awt.*;

/**
 * Tänker att varje möjlig spelruta ärver denna klass.
 *
 */
public abstract class GameTile extends JPanel {
    Color color;
    JLabel text;
    int height = 70, width = 70;

    public GameTile(Color color, String text){
        this.color = color;
        this.text = new JLabel(text);
        this.text.setSize(width,height);
        this.text.setVisible(false);
        add(this.text);
    }

    public void reveal(){
        text.setVisible(true);
        setBackground(color);

    }
}
