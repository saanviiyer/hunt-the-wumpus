// Pavan Anoop

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import Player.Player;
import net.miginfocom.swing.MigLayout;


public class EndPanel extends JPanel{
     //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "game done";
    private JLabel title = new JLabel();
    private Font legendOfZeldaFont;
    //-----------------------CONSTRUCTOR----------------------
    public EndPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
            
        setSize(1920, 1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,40));
        title.setForeground(Color.BLACK);
        add(title, "center, pushx, wrap, h 700px");



        JButton exit = new JButton("Exit to Title Screen");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), StartPanel.IDENTIFIER);
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

    public void lost(String cause){
        title.setText("You lost - womp womp");
    }

    public void won(Player p){
        title.setText("YOU WONNNN!!!!!!!");

        JLabel name = new JLabel("Player: " + p.getName());
        name.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        add(name, "cell 0 1");

        JLabel score = new JLabel("Score:" + p.calculateScore());
        name.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        add(name, "cell 0 1");


    }
}
