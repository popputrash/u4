package view;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {

    Window window;

    JButton btnQuit;
    JButton btnStart;
    JLabel mainTitle;
    JPanel highScrScrn;
    JList<String> highScores = new JList<>();;
    JLabel highScoreTile;

    public StartScreen(int width, int height, Window window){

        super(null);
        this.setSize(width, height);
        this.window = window;
        this.setBackground(Color.black);

        String[] test = {"1.","2.","3.","4.","5.","6.","7.","8.","9.","10."};
        highScores.setListData(test);

        btnSetUp();
        setUp();
    }
    private void btnSetUp(){
        btnQuit = new JButton("AVSLUTA SPEL");
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

    public void setUp(){


        // Titel
        mainTitle = new JLabel("SKATTJAKT!");
        mainTitle.setSize(300,70);
        mainTitle.setLocation(110,50);
        mainTitle.setForeground(Color.yellow.darker());
        mainTitle.setFont(window.getTileFont());


        // High score screen background
        highScrScrn = new JPanel(null);
        highScrScrn.setSize(200,250);
        highScrScrn.setLocation(150,130);
        highScrScrn.setBackground(Color.DARK_GRAY.darker());


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
        highScores.setSize(50,220);
        highScores.setLocation(20,20);
        highScores.setEnabled(false);

        // Add all elements
        highScrScrn.add(highScoreTile);
        highScrScrn.add(highScores);
        this.add(highScrScrn);
        this.add(mainTitle);
    }

}
