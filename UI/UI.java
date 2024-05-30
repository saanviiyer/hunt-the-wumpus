// Nathan Chiu

package UI;
import java.util.Random;

import javax.swing.*;

import Cave.Cave;
import Player.Player;
import Trivia.Question;
import net.miginfocom.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class UI extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    Player p = new Player();

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem startNewGame = new JMenuItem("New Game");

    JButton shoot = new JButton("Shoot");

    JButton buyArrows = new JButton("Purchase Arrows");

    JButton buySecrets = new JButton("Purchase Secrets");

    int arrows = p.getArrows();
    JLabel arrowLabel = new JLabel("Arrows: " + arrows);

    int score = 0;
    int highScore = 0;
    JLabel scoreLabel = new JLabel("Score: " + score);
    JLabel highScoreLabel = new JLabel("High Score: " + highScore);
    JLabel goldCoinsLabel = new JLabel("Gold Coins: " + p.getGoldCoins());
    JLabel currentPlayerLabel = new JLabel("Player: " + p.getName());
    JLabel currentCaveLabel = new JLabel("Cave: ");

    JButton goN = new JButton();
    JButton goNE = new JButton();
    JButton goE = new JButton();
    JButton goSE = new JButton();
    JButton goS = new JButton();
    JButton goSW = new JButton();
    JButton goW = new JButton();
    JButton goNW = new JButton();

    JPanel miniMap;

    JLabel alerts = new JLabel("Alerts");

    JPanel game = new JPanel();
    JPanel startscreen = new JPanel();
    JPanel endscreen = new JPanel();
    
    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI(){
        //set frame behavior
        setTitle("Bumpell");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(new MigLayout(
            "",
            "[]0[]0[]",
            "[]0[]0[]0"
        ));
        
        //change icon of frame
        ImageIcon icon = new ImageIcon("UI/wumpus4.png");
        setIconImage(icon.getImage());

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

        //adding labels
        {
            JPanel Panel = new JPanel();
            Panel.setLayout(new GridLayout(1,0));
            Panel.add(scoreLabel);
            Panel.add(highScoreLabel);
            Panel.add(goldCoinsLabel);
            Panel.add(currentCaveLabel);
            Panel.add(arrowLabel);
            Panel.add(currentPlayerLabel);
            add(Panel, "north");
            Panel.setVisible(true);
        }

        //adding movement buttons
        {
            JButton[] movementButtons = {goNW, goN, goNE, goSW, goS, goSE};
            ImageIcon[] movementIcons = {new ImageIcon("UI/left_top.png"),new ImageIcon("UI/top_mid.png"),new ImageIcon("UI/right_top.png"),new ImageIcon("UI/left_bottom.png"),new ImageIcon("UI/bottom_mid.png"),new ImageIcon("UI/right_bottom.png")};

            
            int height = 450;

            for(int i = 0; i < 6; i++){
                JButton cur = movementButtons[i];
                
                //sets icon of buttons and make them not change when pressed
                cur.setIcon(movementIcons[i]);
                cur.setBorder(null);
                cur.setContentAreaFilled(false);
                cur.setFocusPainted(false);
                cur.setBackground(Color.WHITE);

                final int dir = i;
                cur.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        move(dir);
                        
                    }
                });

                
                if(i == 2) add(cur, "wrap,grow, h " + height + "px," + "w " + height + "px");
                else add(cur, "grow, h " + height + "px," + "w " + height + "px");
            }
        }

        //adding purchasing, alerts, and shooting
        {
            
            buyArrows.setBackground(Color.WHITE);
            buyArrows.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    purchaseArrows();
                }
            });
            add(buyArrows, "cell 3 0,flowy, w 500px, growy");
            
            buySecrets.setBackground(Color.WHITE);
            buySecrets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    purchaseSecrets();
                }
            });
            add(buySecrets, "cell 3 0, w 500px, growy");

            shoot.setBackground(Color.WHITE);
            shoot.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(shoot.getText().equals("Shoot")) shoot.setText("Move");
                    else shoot.setText("Shoot");
                }
            });
            add(shoot, "cell 3 0, w 500px, growy");
            
            alerts.setBorder(BorderFactory.createLineBorder(Color.black));
            alerts.setHorizontalAlignment(JLabel.CENTER);
            add(alerts, "cell 3 0, w 500px, growy");

        }

        //add minimap
        {
            Cave cave = new Cave();
            miniMap = cave.drawMiniMap(40);
            miniMap.setMinimumSize(new Dimension(540,300));
            add(miniMap, "cell 3 1, grow");
        }

        //add new font
        {
            Font montserratBold = null;
            try{
                montserratBold = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Montserrat\\Montserrat-Bold.ttf"));
            } catch(Exception e){}

            Font size10bold = montserratBold.deriveFont(Font.PLAIN, 15);
            changeFont(this, size10bold);
        }

        repaint();
        revalidate();
        // setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }
    
    //////////////////////
    //////// METHODS /////
    //////////////////////

    public void startNewGame(){
        System.out.println("starting new game");
    }

    public void move(int direction){
        System.out.println("player moving to " + direction);
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

    public void purchaseArrows(){
        System.out.println("buy arrows");
        p.addArrows();
        arrowLabel.setText("Arrows: " + p.getArrows());
        String[] answers = {"1","2","3","4"};
        Question[] questions = {new Question("What is the year0???",answers , 0),
                                new Question("What is the year1???",answers , 1),
                                new Question("What is the year2???",answers , 2),
                                new Question("What is the year3???",answers , 3),
                                new Question("What is the year5???",answers , 0)};
        TriviaUI triviaUI = new TriviaUI(questions, this);
        System.out.println("You got " + triviaUI.getNumCorrectAnswers() + " questions right");
        p.decrementGoldCoins();
        goldCoinsLabel.setText("Gold Coins: "+p.getGoldCoins());
    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");
        Random rand = new Random();
        int r = rand.nextInt(5);
        alerts.setText(p.getSecret(r));
        p.decrementGoldCoins();
        goldCoinsLabel.setText("Gold Coins: " + p.getGoldCoins());
    }

    public static void changeFont (Component component, Font font ){
        component.setFont(font);
        if (component instanceof Container)
        {
            for (Component child : ((Container) component).getComponents())
            {
                changeFont (child, font);
            }
        }
    }
}
