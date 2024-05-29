package UI;

import javax.swing.*;

public class circlepanel extends JPanel{
@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;  // Adjust x and y for positioning
        int y = 50;
        int diameter = 100;  // Adjust diameter for circle size
        g.drawOval(x, y, diameter, diameter);
    }
}
