// Pavan Anoop

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class VictoryPanel extends JPanel{
     //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "start";

    //-----------------------CONSTRUCTOR----------------------
    public VictoryPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
            
        setSize(1920, 1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("YOU ARE VICTORIOUS!!");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,40));
        title.setForeground(Color.BLACK);
        add(title, "center, pushx, wrap, h 700px");





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
