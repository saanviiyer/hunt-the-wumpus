//Nathan Chiu

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class StartPanel extends JPanel{
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
        JButton howToPlay = new JButton("How to Play!");
       
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                crd.show(UI.getContentPane(), "game");
            }
        });
        startGame.setForeground(Color.BLACK);
        startGame.setBorder(null);
        startGame.setContentAreaFilled(false);
        startGame.setFocusPainted(false);
        startGame.setHorizontalAlignment(JButton.CENTER);
        startGame.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(startGame, "center, wrap, cell 0 1, h 30px");
        
        
        
        howToPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), "tutorial");
            }
        });
        howToPlay.setForeground(Color.BLACK);
        howToPlay.setBorder(null);
        howToPlay.setContentAreaFilled(false);
        howToPlay.setFocusPainted(false);
        howToPlay.setHorizontalAlignment(JButton.CENTER);
        howToPlay.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(howToPlay, "center, cell 0 2, h 30px");
        
    }
}