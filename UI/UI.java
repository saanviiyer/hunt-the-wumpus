//Nathan Chiu
//UI object
//Per. 5 
//Reiber

package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI implements ActionListener{
    int var;
    String permString = "Var equals: ";
    JFrame frame = new JFrame("Wumpus");
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenu menu2 = new JMenu("hello");
    JButton button = new JButton("Increase var");
    JLabel label = new JLabel(permString);
    JMenuItem exit = new JMenuItem("Exit");

    ////////////////////////
    ////   CONSTRUCTOR  ////
    ////////////////////////

    public UI(){
        var = 1;
        
        //set frame behavior
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLayout(new FlowLayout());
  
        //adding items to menu
        menuBar.add(menu);
        menuBar.add(menu2);

        menu.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }); 
        
        //adding menubar to frame
        frame.setJMenuBar(menuBar);
        
        //adding ticker
        frame.add(label);
 
        button.addActionListener(this);
        
        frame.add(button);
        frame.setVisible(true);


    }

    ////////////////////////
    ////   METHODS      ////
    ////////////////////////

    public void actionPerformed(ActionEvent e){
        DoStuff(1);
        label.setText(permString + var);
    }

    public int DoStuff(int i){
        var += i;
        return var;
    }

    public void startNewGame(){
        System.out.println("starting new game");
    }

    public void move(){
        System.out.println("player moving");
    }

    public void displayNearbyRooms(){
        System.out.println("showing nearby rooms");
    }

    public void updateHighScore(){
        System.out.println("updating high score");
    }
}
