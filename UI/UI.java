//Nathan Chiu
//UI object
//Per. 5 
//Reiber

package UI;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class UI extends JFrame implements ActionListener{
    int var = 0;
    String permString = "Var equals: ";
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JButton button = new JButton("Increase var");
    JLabel label = new JLabel(permString + var);
    JMenuItem exit = new JMenuItem("Exit");

    JTextField textField = new JTextField(10);
    JButton submitText = new JButton("Submit Text");

    JMenuItem startNewGame = new JMenuItem("New Game");

    JButton shoot = new JButton("Shoot");

    JButton buyArrows = new JButton("Purchase Arrows");
    JButton buySecrets = new JButton("Purchase Secrets");

    int arrows = 0;
    JLabel arrowLabel = new JLabel("Arrows: " + arrows);

    int score = 0;
    int highScore = 0;
    JLabel scoreLabel = new JLabel("Score: " + score);
    JLabel highScoreLabel = new JLabel("High Score: " + highScore);

    JLabel currentPlayerLabel = new JLabel("Player: ");
    JLabel currentCaveLabel = new JLabel("Cave: ");

    JButton goN = new JButton("N");
    JButton goNE = new JButton("NE");
    JButton goE = new JButton("NE");
    JButton goSE = new JButton("SE");
    JButton goS = new JButton("S");
    JButton goSW = new JButton("SW");
    JButton goW = new JButton("W");
    JButton goNW = new JButton("NW");

   
    ////////////////////////
    ////   CONSTRUCTOR  ////
    ////////////////////////

    public UI(){
        var = 1;
        
        //set frame behavior
        setTitle("Hunt the Wumpus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(new GridBagLayout());
        
        //change icon of frame
        ImageIcon icon = new ImageIcon("wumpus4.png");
        setIconImage(icon.getImage());


        //set default constraints of gridbag
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        //add menu and menuitems
        {
            //adding items to menu
            menuBar.add(menu);
            

            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            }); 
            menu.add(exit);

            startNewGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    startNewGame();
                }
            }); 
            menu.add(startNewGame);

            //adding menubar to frame
            setJMenuBar(menuBar);
        }
        
        //add score, high score, player, cave, arrows labels
        {
            c.gridx = 0;
            c.gridwidth = 1;
            c.gridy = 0;
            // c.anchor = GridBagConstraints.FIRST_LINE_START;
            // scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
            // c.insets = new Insets(0, 0, 300, 250);
            add(scoreLabel, c);
            
            c.gridx = 1;
            c.gridwidth = 1;
            c.gridy = 0;
            c.weightx = 1;
            // highScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(highScoreLabel, c);

            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = 1;
            // c.anchor = GridBagConstraints.FIRST_LINE_END;
            // currentCaveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(currentCaveLabel, c);

            c.gridx = 3;
            c.gridy = 0;
            c.gridwidth = 1;
            // arrowLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(arrowLabel, c);

            c.gridy = 1;
            c.gridx = 0;
            c.gridwidth = 1;
            // c.anchor = GridBagConstraints.FIRST_LINE_START;
            // c.insets = new Insets(0, 0, 310, 310);
            add(currentPlayerLabel, c);


        }

        //add movement buttons
        {
            c.gridx = 0;
            c.gridy = 2;
            c.weightx = 1;
            c.weighty = 1;
            add(goNW, c);

            c.gridx = 1;
            c.gridy = 2;
            add(goN, c);

            c.gridx = 2;
            c.gridy = 2;
            add(goNE, c);

            c.gridx = 0;
            c.gridy = 3;
            add(goSW, c);

            c.gridx = 1;
            c.gridy = 3;
            add(goS, c);

            c.gridx = 2;
            c.gridy = 3;
            add(goSE, c);
        }
    
        //add new font
        {
            Font montserratBold = null;
            try{
                montserratBold = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Montserrat\\Montserrat-Bold.ttf"));
            } catch(Exception e){}

            Font size10bold = montserratBold.deriveFont(Font.PLAIN, 10);
            changeFont(this, size10bold);
        }

        //set frame to visible
        
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

    public void move(int room){
        System.out.println("player moving to " + room);
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

    public void shoot(int room){
        System.out.println("shooting to " + room);
    }

    public void purchaseArrows(){
        System.out.println("buy arrows");
        arrows++;
        arrowLabel.setText("Arrows: " + arrows);
    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");
    }

    public static void changeFont ( Component component, Font font ){
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            
            }
        }
    }
}
