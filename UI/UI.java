//Nathan Chiu
//UI object
//Per. 5 
//Reiber

package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI extends JFrame implements ActionListener{
    int var;
    String permString = "Var equals: ";
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenu menu2 = new JMenu("hello");
    JButton button = new JButton("Increase var");
    JLabel label = new JLabel(permString);
    JMenuItem exit = new JMenuItem("Exit");

    JTextField textField = new JTextField(10);
    JButton submitText = new JButton("Submit Text");

    JMenuItem startNewGame = new JMenuItem("New Game");
    JButton move = new JButton("Move");
    JButton shoot = new JButton("shoot");
    JButton buyArrows = new JButton("Purchase Arrows");
    JButton buySecrets = new JButton("Purchase Secrets");
   
    ////////////////////////
    ////   CONSTRUCTOR  ////
    ////////////////////////

    public UI(){
        var = 1;
        
        //set frame behavior
        setTitle("Hunt the Wumpus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(new FlowLayout());
  
        //adding items to menu
        menuBar.add(menu);
        menuBar.add(menu2);

        menu.add(exit);
        menu.add(startNewGame);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }); 
        
        //adding menubar to frame
        setJMenuBar(menuBar);
        
        //adding ticker
        add(label);
 
        button.addActionListener(this);
        add(button);

        add(textField);

        submitText.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                var = Integer.parseInt(textField.getText());
                label.setText(permString + var);
            }
        });
        add(submitText);

        move.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                move();
            }
        });
        add(move);

        shoot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                shoot();
            }
        });
        add(shoot);

        buyArrows.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                purchaseArrows();
            }
        });
        add(buyArrows);

        buySecrets.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                purchaseSecrets();
            }
        });
        add(buySecrets);

        setVisible(true);


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

    public void displayHazards(){
        System.out.println("Displaying hazards");
    }

    public void shoot(){
        System.out.println("shooting");
    }

    public void purchaseArrows(){
        System.out.println("buy arrows");
    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");
    }
}
