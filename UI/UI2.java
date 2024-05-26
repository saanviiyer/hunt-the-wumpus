package UI;

import javax.swing.*;

import net.miginfocom.swing.*;



public class UI2 extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////

    
    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI2(){
        //set frame behavior
        setTitle("Hunt the Wumpus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(null);
        new MigLayout("wrap 3");
        //change icon of frame
        ImageIcon icon = new ImageIcon("wumpus4.png");
        setIconImage(icon.getImage());

        repaint();
        revalidate();
        setResizable(false);
        setVisible(true);
    }
}
