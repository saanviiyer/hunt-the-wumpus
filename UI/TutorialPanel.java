
/*
 * Last Editor(s): Nathan Chiu
 * Last Edit @ 05-30-2024
 */

/*
 * Info:
 * Testing Area for ./Cave
 */

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class TutorialPanel extends JPanel{
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "tutorial";
    private String previouslyDisplayedCard;

    //-----------------------CONSTRUCTOR----------------------
    public TutorialPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}

        setSize(1920,1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        //creates a textbox containing text from an html file
        JEditorPane tutorial = new JEditorPane();
        URL text = UI.class.getResource("tutorial.html");
        try {
            tutorial.setPage(text);
        } catch (Exception e) {
            System.out.println("html file not found");
        }
        tutorial.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        tutorial.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        tutorial.setEditable(false);
        tutorial.setFocusable(false);
        tutorial.setOpaque(false);
        JScrollPane tutorialScrollPane = new JScrollPane(tutorial);
        tutorialScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(tutorialScrollPane, "center, h 500px, w 1000px, push, flowy");

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                crd.show(UI.getContentPane(), previouslyDisplayedCard);
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
