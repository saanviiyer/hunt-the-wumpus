// Nathan Chiu

package UI;
import java.util.Random;

import javax.swing.*;

import Cave.Cave;
import GameControl.GameControl;
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
    GameControl ctrl = new GameControl();
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


    JPanel miniMap;

    JLabel alerts = new JLabel("Alerts");

    JPanel game = new JPanel();
    JPanel startScreen = new JPanel();
    JPanel endscreen = new JPanel();
    CardLayout crd = new CardLayout();

    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI(){
        //set frame behavior
        setTitle("Hunt the Wumpus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(crd);
        
        //change icon of frame
        ImageIcon icon = new ImageIcon("UI\\bumpell_icon.jpg");
        setIconImage(icon.getImage());

        Font legendOfZeldaFont = null;
            try{
                legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
            } catch(Exception e){}

        //create start menu
        {
            startScreen.setSize(1920, 1080);
            startScreen.setLayout(new MigLayout());
            startScreen.setBackground(Color.GRAY);

            JLabel title = new JLabel("Hunt the Wumpus");
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,30));
            startScreen.add(title, "center, push, wrap");

            JButton startGame = new JButton("Start New Game");
            startGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    crd.next(getContentPane());
                }
            });
            startGame.setBorder(null);
            startGame.setContentAreaFilled(false);
            startGame.setFocusPainted(false);
            startGame.setHorizontalAlignment(JButton.CENTER);
            startGame.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN,15));
            startScreen.add(startGame, "center, push");
        }
        add(startScreen);

        // Setting game panel behavior
        {
            game.setSize(1920,1080);
            game.setLayout(new MigLayout(
            "",
            "[]0[]0[]",
            "[]0[]0[]0"
        ));


        }  
        
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
            game.add(menuBar,"north, pushx, growx");
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
            game.add(Panel, "north");
            Panel.setVisible(true);
        }

        //adding movement buttons
        {
            ImageIcon[] movementIcons = {new ImageIcon("UI/left_top.png"),new ImageIcon("UI/top_mid.png"),new ImageIcon("UI/right_top.png"),new ImageIcon("UI/left_bottom.png"),new ImageIcon("UI/bottom_mid.png"),new ImageIcon("UI/right_bottom.png")};

            
            int height = 450;

            for(int i = 0; i < 6; i++){
                JButton cur = new JButton();
                
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

                
                if(i == 2) game.add(cur, "wrap,grow, h " + height + "px," + "w " + height + "px");
                else game.add(cur, "grow, h " + height + "px," + "w " + height + "px");
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
            game.add(buyArrows, "cell 3 0,flowy, w 500px, growy");
            
            buySecrets.setBackground(Color.WHITE);
            buySecrets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    purchaseSecrets();
                }
            });
            game.add(buySecrets, "cell 3 0, w 500px, growy");

            shoot.setBackground(Color.WHITE);
            shoot.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(shoot.getText().equals("Shoot")) shoot.setText("Move");
                    else shoot.setText("Shoot");
                }
            });
            game.add(shoot, "cell 3 0, w 500px, growy");
            
            alerts.setBorder(BorderFactory.createLineBorder(Color.black));
            alerts.setHorizontalAlignment(JLabel.CENTER);
            game.add(alerts, "cell 3 0, w 500px, growy");

        }

        //add minimap
        {
            Cave cave = new Cave();
            miniMap = cave.drawMiniMap(40);
            miniMap.setMinimumSize(new Dimension(540,300));
            game.add(miniMap, "cell 3 1, grow");
            ctrl.setCave(cave);
        }

        add(game);

        //change font of game panel
        {
            changeFont(game, legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        }


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
        int[] dirs = {5,0,1,4,3,2};
        ctrl.movePlayer(dirs[direction]);
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

        String[] answers = {"A","B","C","D"};

        // CHANGE TO BE ACTUAL LENGTH OF QUESTIONS FILE
        int q = 5;

        Random r = new Random();
        int a = r.nextInt(q);
        int b = r.nextInt(q);
        int c = r.nextInt(q);
        int d = r.nextInt(q);
        int e = r.nextInt(q);

        // ADD CODE TO READ QUESTIONS AND ANSWERS FROM A, B, C, D, E FOR THE FIVE QUESTIONS
        
        // aQ should be the question from the line number of a
        String aQ = "";
        String bQ = "";
        String cQ = "";
        String dQ = "";
        String eQ = "";

        // aA should be the ANSWER CHOICES from the line number of a
        String[] aA = {"","","",""};
        String[] bA = {"","","",""};
        String[] cA = {"","","",""};
        String[] dA = {"","","",""};
        String[] eA = {"","","",""};

        //aI should be the NUMBER at the end of answers from line a to indicate the correct answer
        int aI = 0;
        int bI = 0;
        int cI = 0;
        int dI = 0;
        int eI = 0;

        Question[] questions = {new Question("What is the year0?",answers , 0),
                                new Question(bQ,bA, bI),
                                new Question(cQ,cA ,cI),
                                new Question(dQ,dA , dI),
                                new Question(eQ,eA , eI)};
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
