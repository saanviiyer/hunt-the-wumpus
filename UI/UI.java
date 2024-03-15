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
    JLabel label = new JLabel(permString);
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenu menu2 = new JMenu("hello");
    JButton button = new JButton("Increase var");
    ////////////////////////
    ////   CONSTRUCTOR  ////
    ////////////////////////

    public UI(){
        var = 1;

        JFrame frame = new JFrame("Wumpus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setLayout(new FlowLayout());


        menuBar.add(menu);
        menuBar.add(menu2);
        
        frame.setJMenuBar(menuBar);
        


 
        button.addActionListener(this);
        frame.add(label);
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
        
    }
}
