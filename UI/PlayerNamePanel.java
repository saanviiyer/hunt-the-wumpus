// Pavan Anoop

package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class PlayerNamePanel extends JPanel{
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "playername";
    private String playername;

    //-----------------------CONSTRUCTOR----------------------
    public PlayerNamePanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
            
        setSize(1920, 1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Enter your name:");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,40));
        title.setForeground(Color.BLACK);
        add(title, "center, pushx, wrap, h 700px");

        JTextField enterName = new JTextField("Enter player name here!");
        enterName.setHorizontalAlignment(JLabel.CENTER);
        enterName.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,18));
        enterName.setForeground(Color.BLACK);
        add(enterName, "center, pushx, wrap, h 100px, w 700px");

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                playername = enterName.getText();
                UI.setPlayer();
                enterName.setText("Enter player name here!");
            }
        });

        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(submit, "center, h 30px");
        
    }

    //-----------------------METHODS---------------------- 
    public String getPlayerName(){
        return playername;
    }   
}
