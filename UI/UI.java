//Nathan Chiu
//UI object
//Per. 5 
//Reiber

package UI;
import javax.swing.*;

public class UI {
    int var;

    ////////////////////////
    ////   CONSTRUCTOR  ////
    ////////////////////////

    public UI(){
        var = 1;
        JFrame frame = new JFrame("Wumpus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        frame.setVisible(true);

/* 
        JButton button1 = new JButton(Integer.toString(var));
        frame.getContentPane().add(button1);
        frame.setVisible(true);*/

    }

    ////////////////////////
    ////   METHODS      ////
    ////////////////////////

    public int DoStuff(int i){
        var += i;
        return var;
    }
}
