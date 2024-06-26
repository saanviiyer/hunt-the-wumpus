/*
 * Last Editor(s): Nathan Chiu
 * Last Edit @ 05-30-2024
 */



package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class StartPanel extends JPanel{
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "start";

    //-----------------------CONSTRUCTOR----------------------
    public StartPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
            
        setSize(1920, 1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Hunt the Wumpus");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,30));
        title.setForeground(Color.BLACK);
        add(title, "center, pushx, wrap, h 700px");

        JButton startGame = new JButton("Start New Game");
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                crd.show(UI.getContentPane(), PlayerNamePanel.IDENTIFIER);
            }
        });
        startGame.setForeground(Color.BLACK);
        startGame.setBorder(null);
        startGame.setContentAreaFilled(false);
        startGame.setFocusPainted(false);
        startGame.setHorizontalAlignment(JButton.CENTER);
        startGame.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(startGame, "center, wrap, h 30px");
        
        
        JButton howToPlay = new JButton("How to Play!");
        howToPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), TutorialPanel.IDENTIFIER);
                    UI.setTutorialLastCard(IDENTIFIER);
            }
        });
        howToPlay.setForeground(Color.BLACK);
        howToPlay.setBorder(null);
        howToPlay.setContentAreaFilled(false);
        howToPlay.setFocusPainted(false);
        howToPlay.setHorizontalAlignment(JButton.CENTER);
        howToPlay.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(howToPlay, "center, h 30px, wrap");

        JButton leaderboard = new JButton("Leaderboard");
        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    UI.showLeaderboard();
                    UI.setLeaderboardLastCard(IDENTIFIER);
            }
        });
        leaderboard.setForeground(Color.BLACK);
        leaderboard.setBorder(null);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setFocusPainted(false);
        leaderboard.setHorizontalAlignment(JButton.CENTER);
        leaderboard.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(leaderboard, "center, h 30px, wrap");

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    System.exit(0);
            }
        });
        exit.setForeground(Color.BLACK);
        exit.setBorder(null);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(exit, "center, h 30px");
        
    }

    //-----------------------METHODS----------------------
}
