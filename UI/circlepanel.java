package UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;

public class circlepanel extends JPanel{
    Color color = Color.GRAY;
    int diameter;

    public circlepanel(int diam){
        setBackground(Color.WHITE);
        setSize(75,75);
        diameter = diam;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //centers circle
        int x = getWidth()/2 - diameter/2;  
        int y = getHeight()/2 - diameter/2;
         
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void setCircleColor(Color c){
        color = c;
        repaint();
    }
}
