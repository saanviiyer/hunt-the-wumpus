//Nathan Chiu

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;
import javax.swing.text.Document;

import net.miginfocom.swing.MigLayout;

public class TutorialPanel extends JPanel{
    public TutorialPanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}

        setSize(1920,1080);
        setLayout(new MigLayout());

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
        tutorial.setBackground(new Color(0,0,0,0));
        add(tutorial, "center, push, flowy");

        JButton backToHome = new JButton("Back to title screen");
        backToHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                crd.show(UI.getContentPane(), "start");
            }
        });
        backToHome.setBackground(Color.WHITE);
        backToHome.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        add(backToHome, "center, pushx, cell 0 0");
    }
}
