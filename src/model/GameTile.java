package model;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Tänker att varje möjlig spelruta ärver denna klass.
 *
 */
public abstract class GameTile {
    Color color;
    JLabel text;
    JPanel panel;
    boolean found;

    public GameTile(Color color, String text) {
        this.panel = new JPanel();
        this.color = color;
        this.text = new JLabel(text);
        this.text.setVisible(false);
        panel.add(this.text);
        found = false;
        panel.setBackground(Color.GREEN.darker());
        panel.setBorder(new LineBorder(Color.yellow));
    }

    public void reveal() {
        found = true;
        text.setVisible(true);
        panel.setBorder(new LineBorder(Color.gray));
        panel.setBackground(color);
    }

    public void preview(){
        text.setVisible(true);
        panel.setBorder(new LineBorder(Color.gray));
        panel.setBackground(color);
    }

    public void reset(){
        found = false;
        text.setVisible(false);
        panel.setBackground(Color.GREEN.darker());
        panel.setBorder(new LineBorder(Color.yellow));
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isFound() {
        return found;
    }

    public abstract void dig(Controller controller, Player player);

}
