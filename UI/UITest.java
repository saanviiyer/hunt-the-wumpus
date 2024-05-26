//Nathan Chiu
//UI test
//Per. 5 
//Reiber

package UI;

import javax.swing.*;

class Test {

    public static void main(String[] args){
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            System.out.println("laf changed to " + UIManager.getLookAndFeel());
        } catch(Exception ignored){}
        // UI myUI = new UI();
        UI2 myUi2 = new UI2();
    }

}