// Pavan

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class StoryPanel extends JPanel{
     //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "story";


    //-----------------------CONSTRUCTOR----------------------
    public StoryPanel(UI UI, CardLayout crd){
       Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}

        setSize(1920,1080);
        setLayout(new MigLayout());
        setBackground(Color.GRAY);

        //creates a textbox containing text from an html file
        JEditorPane story = new JEditorPane();
        URL text = UI.class.getResource("story.html");
        try {
            story.setPage(text);
        } catch (Exception e) {
            System.out.println("html file not found");
        }
        story.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        story.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        story.setEditable(false);
        story.setFocusable(false);
        story.setOpaque(false);
        JScrollPane tutorialScrollPane = new JScrollPane(story);
        tutorialScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(tutorialScrollPane, "center, h 500px, w 1000px, push, flowy");

        JButton back = new JButton("Continue");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UI.resetAndShowGame();
            }
        });
        back.setBackground(Color.WHITE);
        back.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        add(back, "center, pushx, cell 0 0");

        
    }

    //-----------------------METHODS----------------------
}
