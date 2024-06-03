package UI;

import java.awt.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class GameOverPanel extends JPanel{
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "game over";

    //-----------------------CONSTRUCTOR----------------------
    public GameOverPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
        
        setSize(1980, 1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);


        JLabel text = new JLabel("You lost!");
        add(text, "center, push");

        JButton backToTitleScreen = new JButton("Return to Title Screen");
        backToTitleScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                crd.show(UI.getContentPane(), StartPanel.IDENTIFIER);
            }
        });
    }
    //-----------------------METHODS----------------------
}
