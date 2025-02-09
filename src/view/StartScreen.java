/**
 * Panel som används som startskärm.
 * @author Elias Brännström
 */

package view;
import javax.swing.*;
import java.awt.*;


public class StartScreen extends JPanel {

    Window window;

    JButton btnQuit;
    JButton btnStart;
    JLabel mainTitle;
    JPanel highScorePanel;
    JList<String> highScores = new JList<>();
    JLabel highScoreTile;

    /**
     * Constructor för startpanelen
     * @author Elias Brännström
     * @param width bredd på panel
     * @param height höjd på panel
     * @param window Fönstret panel sitter på
     */
    public StartScreen(int width, int height, Window window) {

        super(null);
        this.setSize(width, height);
        this.window = window;
        this.setBackground(Color.black);

        String[] test = {"1.","2.","3.","4.","5.","6.","7.","8.","9.","10."};
        highScores.setListData(test);

        btnSetUp();
        setUp();
    }
    /**
     * Metod för att skapa diverse knappar som finns på startskärmen
     * @author Elias Brännström
     */
    private void btnSetUp(){
        btnQuit = new JButton("AVSLUTA");
        btnQuit.setEnabled(true);
        btnQuit.setSize(150,20);
        btnQuit.setLocation(95,400);
        btnQuit.setBackground(Color.RED.darker());
        btnQuit.setForeground(Color.BLACK);
        btnQuit.setFont(window.getBtnFont());
        btnQuit.addActionListener(l -> window.btnPressed(ButtonType.EXITBUTTON));

        btnStart = new JButton("STARTA SPEL");
        btnStart.setEnabled(true);
        btnStart.setSize(150,20);
        btnStart.setLocation(255,400);
        btnStart.setFont(window.getBtnFont());
        btnStart.setBackground(Color.GREEN.darker());
        btnStart.setForeground(Color.BLACK);
        btnStart.addActionListener(l -> window.btnPressed(ButtonType.STARTBUTTON));

        this.add(btnQuit);
        this.add(btnStart);
    }

    /**
     * Metod för att skapa diverse element som finns på startskärmen
     * @author Elias Brännström
     */
    public void setUp(){

        // Titel
        mainTitle = new JLabel("SKATTJAKT!");
        mainTitle.setSize(300,70);
        mainTitle.setLocation(110,50);
        mainTitle.setForeground(Color.yellow.darker());
        mainTitle.setFont(window.getTileFont());

        // High score screen background
        highScorePanel = new JPanel(null);
        highScorePanel.setSize(200,250);
        highScorePanel.setLocation(150,130);
        highScorePanel.setBackground(Color.DARK_GRAY.darker());

        // High score screen label
        highScoreTile = new JLabel("HIGH SCORES");
        highScoreTile.setFont(window.getBtnFont());
        highScoreTile.setForeground(Color.yellow.darker());
        highScoreTile.setSize(90,20);
        highScoreTile.setLocation(55,0);

        // High score list
        highScores.setFont(window.getBtnFont());
        highScores.setForeground(Color.yellow.darker());
        highScores.setBackground(Color.DARK_GRAY.darker());
        highScores.setSize(200,220);
        highScores.setLocation(20,20);
        highScores.setEnabled(false);

        // Add all elements to StartScreen panel
        highScorePanel.add(highScoreTile);
        highScorePanel.add(highScores);
        this.add(highScorePanel);
        this.add(mainTitle);
    }

    public void setHighScores(String[] highScores){
        this.highScores.setListData(highScores);
        this.highScores.repaint();
    }

}
