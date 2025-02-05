package model;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Tänker att varje möjlig spelruta ärver denna klass.
 *
 */
public abstract class GameTile extends JPanel {
    Color color;
    JLabel text;

    public GameTile(Color color, String text){
        this.color = color;
        this.text = new JLabel(text);
        add(this.text);
        setBackground(Color.red.darker());
        setBorder(new LineBorder(Color.yellow));
    }
    public void reveal(){
        text.setVisible(true);
        setBackground(color);
    }

}
