package Cave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CaveTest {
    private static Cave myCave = new Cave();

    public static void TestConstructor() {
        myCave = new Cave();
        System.out.println("Constructor called");
    }

    public static void TestCaveDoStuff() {
        int param = 8;
        String result = myCave.DoStuff(param);
        System.out.println("DoStuff called with " + param + " and returned " + result);
    }


    public static void main(String args[])
    {
        JFrame frame = new JFrame("Cave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

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
        //JPanel p = new JPanel();
        JPanel controls = myCave.drawControls();
        controls.setLocation(30,30);
        frame.add(controls);
        frame.setVisible(true);
        JPanel mini = myCave.drawMiniMap();
        mini.setLocation(400,400);
        frame.add(mini);
        frame.setVisible(true);
    }
}