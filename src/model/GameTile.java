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
    Color color;
    JLabel text;
    JPanel panel;
    boolean found;

    /**
     * Konstruktor för GameTile
     * @param color
     * @param text
     * @author Maximilian Andersen & Elias Brännström
     */
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

    /**
     * Reveal metod som visar en tile efter den blivit uppgrävd
     * @author Maximilian Andersen & Elias Brännström
     */
    public void reveal() {
        found = true;
        text.setVisible(true);
        panel.setBorder(new LineBorder(Color.gray));
        panel.setBackground(color);
    }

    /**
     * Metod för att underlätta felsökning, visar alla tiles som uppgrävda under spelets gång
     * @author Maximilian Andersen & Elias Brännström
     */
    public void preview(){
        text.setVisible(true);
        panel.setBorder(new LineBorder(Color.gray));
        panel.setBackground(color);
    }

    /**
     * Metod för att resetta en tile till ordinarie tillstånd
     */
    public void reset(){
        found = false;
        text.setVisible(false);
        panel.setBackground(Color.GREEN.darker());
        panel.setBorder(new LineBorder(Color.yellow));
    }

    /**
     * Getter för tilens panel
     * @return
     */
    public JPanel getPanel() {
        return panel;
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
