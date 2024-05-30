/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 05-30-2024
 */

/*
 * Info:
 * Testing Area for ./Cave
 */

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
        JPanel controls = myCave.drawControls(70);

        frame.getContentPane().add(controls, BorderLayout.WEST);

        frame.setVisible(true);

        JPanel mini = myCave.drawMiniMap(20);
        mini.setLocation(0,0);
        frame.getContentPane().add(mini, BorderLayout.CENTER);
        frame.setVisible(true);
        System.out.println(controls.getSize().getWidth());
        System.out.println(controls.getSize().getHeight());

    }
}