// Pavan Anoop

/*
 * Last Editor(s): Pavan
 * Last Edit @ 05-30-2024
 */



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
    private UI ui;
    //-----------------------CONSTRUCTOR----------------------
    public EndPanel(UI ui, CardLayout crd){
        //creates new font to be derived
        this.ui = ui;
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
                    crd.show(ui.getContentPane(), StartPanel.IDENTIFIER);
                    removeAll();
                    add(title, "center, pushx, wrap, h 700px");
                    add(exit, "center, cell 0 2");
            }
        });
        exit.setForeground(Color.BLACK);
        exit.setBorder(null);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.setHorizontalAlignment(JButton.CENTER);
        exit.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
        add(exit, "center, cell 0 2");
        
    }


    //-----------------------METHODS----------------------

    public void lost(String cause){
        title.setText("You lost - wump wump");

        JLabel causeOfLoss = new JLabel("You lost because of: " + cause);
        causeOfLoss.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        causeOfLoss.setHorizontalAlignment(JLabel.CENTER);
        causeOfLoss.setForeground(Color.BLACK);
        add(causeOfLoss, "cell 0 1, center, grow");
    }

    public void won(Player p){
        title.setText("YOU WONNNN!!!!!!!");

        JLabel[] labels = {new JLabel("Player: " + p.getName()), new JLabel("Score: " + ui.getGameControl().calcScore(true))};

        for(JLabel label : labels){
            label.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
            label.setForeground(Color.BLACK);
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label, "cell 0 1, grow, center");

        }


    }


}
