package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TriviaPanel extends JPanel implements MouseListener {

    private Point initialClick;

    public TriviaPanel() {
        super();
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Optional: Handle click events if needed
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Optional: Handle release events if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optional
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - initialClick.x;
        int dy = e.getY() - initialClick.y;

        // Update the location of the panel based on drag distance
        setLocation(getLocation().x + dx, getLocation().y + dy);
    }
}

