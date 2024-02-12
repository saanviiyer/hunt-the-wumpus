/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 1:14 pm
 */


package CaveTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Cave {
    public Cave() {

    }

    public int DoStuff(int param) {
        return param;
    }
}

class Test {
    private static Cave myCave;

    public static void TestConstructor() {
        myCave = new Cave();
        System.out.println("Constructor called");
    }

    public static void TestCaveDoStuff() {
        int param = 5;
        int result = myCave.DoStuff(param);
        System.out.println("DoStuff called with " + param + " and returned " + result);
    }


    public static void main(String args[])
    {
        JFrame frame = new JFrame("My Wumpus GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Create a basic menu and add it to the top
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItem);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);

        // Add a constructor test button to content pane and make it visible
        {
            JButton button = new JButton("Test Constructor");
            button.setSize(frame.getWidth(), 50);
            button.setLocation(0, 0);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TestConstructor();
                }
            });
            frame.getContentPane().add(button);
        }

        // Add a constructor test button to content pane and make it visible
        {
            JButton button = new JButton("Test DoStuff");
            button.setSize(frame.getWidth(), 50);
            button.setLocation(0, 50);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TestCaveDoStuff();
                }
            });
            frame.getContentPane().add(button);
        }

        frame.setVisible(true);
    }
}