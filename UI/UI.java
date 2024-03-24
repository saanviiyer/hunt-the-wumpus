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

    JTextField roomInput = new JTextField("enter room number");
    JButton move = new JButton("Move");

    JButton shoot = new JButton("shoot");

    JButton buyArrows = new JButton("Purchase Arrows");
    JButton buySecrets = new JButton("Purchase Secrets");

    int arrows = 0;
    JLabel arrowLabel = new JLabel("Arrows: " + arrows);

    int score = 0;
    int highScore = 0;
    JLabel scoreLabel = new JLabel("Score: " + score);
    JLabel highScoreLabel = new JLabel("High Score: " + highScore);

    JLabel hazards = new JLabel("Hazards: ");

    Font serif = new Font(Font.SERIF, Font.BOLD, 20);

   
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
        changeFont(this, serif);
        hazards.setFont(serif);
        
        //change icon of frame
        ImageIcon icon = new ImageIcon("wumpus4.png");
        setIconImage(icon.getImage());


        //set default constraints of gridbag
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(10,10,10,10);

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
        
        //add score and high score labels
        {
            c.gridx = 0;
            c.gridwidth = 1;
            c.gridy = 0;
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            add(scoreLabel, c);

            c.gridx = 5;
            c.gridwidth = 1;
            c.gridy = 0;
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            highScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(highScoreLabel, c);
        }

        //int incrementer and label
        {
            //adding button incrementer
            button.addActionListener(this);
            c.gridx = 0;
            c.gridwidth = 3;
            c.gridy = 1;
            add(button, c);

            //adding var label
            c.gridx = 3;
            c.gridwidth = 3;
            // c.gridy = 1;
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label, c);

            
        }

        //adding input for changing int
        {
            c.gridx = 0;
            c.gridy = 2;
            add(textField, c);

            submitText.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    var = Integer.parseInt(textField.getText());
                    label.setText(permString + var);
                }
            });
            c.gridx = GridBagConstraints.RELATIVE;
            add(submitText, c);
        }

        //adds room input, moving, shooting
        {
            c.gridx = 0;
            c.gridwidth = 2;
            c.gridy = 3;
            add(roomInput, c);

            move.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    move(Integer.parseInt(roomInput.getText()));
                }
            });
            c.gridx = 2;
            add(move, c);

            shoot.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    shoot(Integer.parseInt(roomInput.getText()));
                }
            });
            c.gridx = 4;
            add(shoot, c);


        }

        //buying arrows and secrets
        {
            buyArrows.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    purchaseArrows();
                }
            });
            c.gridx = 0;
            c.gridwidth = 2;
            c.gridy = 4;
            // c.ipadx = 100;
            add(buyArrows, c);

            c.gridx = 2;
            c.gridwidth = 1;
            arrowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(arrowLabel, c);

            buySecrets.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    purchaseSecrets();
                }
            });
            c.gridx = 3;
            c.gridwidth = 3;
            add(buySecrets, c);

            
        }

        //adding hazards
        {
            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = 6;
            hazards.setHorizontalAlignment(SwingConstants.CENTER);
            add(hazards, c);
        }

        {
            Font starjedi = null;
            try{
                starjedi = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Starjedi.ttf"));
            } catch(Exception e){}

            Font size20 = starjedi.deriveFont(Font.PLAIN, 20);
            changeFont(this, size20);
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
