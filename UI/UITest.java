//Nathan Chiu
//UI test
//Per. 5 
//Reiber

package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.Font;


class Test {
    private static UI myUI;


    // public static void main(String args[])
    // {
    //     JFrame frame = new JFrame("My Wumpus GUI");
    //     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //     frame.setSize(800, 400);
    //     frame.setLayout(new FlowLayout());

    //     // Create a basic menu and add it to the top
    //     JMenuBar menuBar = new JMenuBar();
    //     JMenu menu = new JMenu("File");
        

    //     JMenuItem menuItem = new JMenuItem("Exit");
    //     menuItem.addActionListener(new ActionListener() {
    //         public void actionPerformed(java.awt.event.ActionEvent e) {
    //             System.exit(0);
    //         }
    //     });
    //     menu.add(menuItem);
        
    //     menuBar.add(menu);
        
    //     frame.getContentPane().add(BorderLayout.NORTH, menuBar);

    //     // Add a constructor test button to content pane and make it visible
    //     {
    //         JButton button = new JButton("Test Constructor");
    //         button.setSize(frame.getWidth(), 50);
    //         button.setLocation(0, 50);

    //         button.addActionListener(new ActionListener() {
    //             public void actionPerformed(java.awt.event.ActionEvent e) {
    //                 TestConstructor();
    //             }
    //         });
    //         frame.getContentPane().add(button);
    //     }

    //     // Add a constructor test button to content pane and make it visible
    //     {
    //         JButton button = new JButton("Test DoStuff");
    //         button.setSize(frame.getWidth(), 50);
    //         button.setLocation(0, 10);

    //         button.addActionListener(new ActionListener() {
    //             public void actionPerformed(java.awt.event.ActionEvent e) {
    //                 TestUIDoStuff();
    //             }
    //         });
    //         frame.getContentPane().add(button);
    //     }

    //     frame.setVisible(true);
    // }

    public static void main(String[] args){
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            System.out.println("laf changed to " + UIManager.getLookAndFeel());
        } catch(Exception ignored){}
        // myUI = new UI();
        UI2 myUi2 = new UI2();
    }

}