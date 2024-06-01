// Nathan Chiu

// IMPORTANT TO ADD:
// PURCHASE ARROWS: YOU CAN PURCHASE 2 MORE ARROWS BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT
// PURCHASE SECRET: PURCHASE SECRET BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT



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
    JMenu menu = new JMenu("Menu");
    JMenuItem exit = new JMenuItem("Exit to Title Screen");
    JMenuItem startNewGame = new JMenuItem("New Game");
    JMenuItem exitToDesktop = new JMenuItem("Exit to Desktop");

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
    JPanel howToScreen = new JPanel();
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

        //creates new font to be derived
        Font legendOfZeldaFont = null;
            try{
                legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
            } catch(Exception e){}

        
        
        startScreen = new StartPanel(this, crd);
        startScreen.setVisible(true);
        add(startScreen,"startScreen");

        // How to play screen
        {
            
            howToScreen.setSize(1920,1080);
            howToScreen.setVisible(true);
            add(howToScreen, "tutorial");
        }

        game = new GamePanel(this, crd);
        
        //change font of game panel
        changeFont(game, legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
        

        game.setVisible(true);
        add(game, "game");

        setResizable(false);
        setVisible(true);
    }
    
    //////////////////////
    //////// METHODS /////
    //////////////////////

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
        alerts.setText(ctrl.getHazards()[0]);
    }

    public void purchaseArrows(){
        System.out.println("buy arrows");
        p.addArrows();
        arrowLabel.setText("Arrows: " + p.getArrows());
        p.decrementGoldCoins();

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

    public GameControl getGameControl(){
        return ctrl;
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
