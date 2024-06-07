//Nathan Chiu

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class LeaderboardPanel extends JPanel{
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "leaderboard";
    private String previouslyDisplayedCard;

    //-----------------------CONSTRUCTOR----------------------
    public LeaderboardPanel(UI ui, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}

        setSize(1920,1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        JLabel heading = new JLabel("Leaderboard");
        heading.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 40));
        heading.setBackground(new Color(0,0,0,0));
        add(heading, "center, pushx, flowy");

        //creates a textbox containing text within a scrollpane
        JEditorPane leaderboard = new JEditorPane();
        leaderboard.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        leaderboard.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 20));
        leaderboard.setEditable(false);
        leaderboard.setFocusable(false);
        // leaderboard.setOpaque(false);
        leaderboard.setText(ui.getGameControl().leaderboard());
        JScrollPane tutorialScrollPane = new JScrollPane(leaderboard);
        tutorialScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(tutorialScrollPane, "center, h 400px, w 1750px, push, cell 0 0");

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                crd.show(ui.getContentPane(), previouslyDisplayedCard);
            }
        });
        back.setBackground(Color.WHITE);
        back.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        add(back, "center, pushx, cell 0 0");
    }

    //-----------------------METHODS----------------------
    public void setPreviouslyDisplayedCard(String s){
        previouslyDisplayedCard = s;
    }
}

