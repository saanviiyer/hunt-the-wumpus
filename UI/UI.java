// Nathan Chiu

// IMPORTANT TO ADD:
// PURCHASE ARROWS: YOU CAN PURCHASE 2 MORE ARROWS BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT
// PURCHASE SECRET: PURCHASE SECRET BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT



package UI;
import java.util.Random;

import javax.swing.*;

import GameControl.GameControl;
import Player.Player;
import Trivia.Question;
import java.awt.*;
import java.io.File;

public class UI extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    //TODO shouldn't gamecontrol handle interactions with player? UI just needs the different values of the player (coins, score, name), not everything else
    Player p = new Player();
    GameControl ctrl = new GameControl();

    GamePanel gamePanel;
    StartPanel startPanel;
    TutorialPanel tutorialPanel;
    CardLayout crd = new CardLayout();

    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI(){
        ctrl.setUI(this);
        ctrl.setPlayer(this.p);
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

        
        startPanel = new StartPanel(this, crd);
        startPanel.setVisible(true);
        add(startPanel, StartPanel.IDENTIFIER);


        gamePanel = new GamePanel(this, crd);
        changeFont(gamePanel, legendOfZeldaFont.deriveFont(Font.PLAIN, 15)); 
        gamePanel.setVisible(true);
        add(gamePanel, GamePanel.IDENTIFIER);

        tutorialPanel = new TutorialPanel(this, crd);
        tutorialPanel.setVisible(true);
        add(tutorialPanel, TutorialPanel.IDENTIFIER);

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
        gamePanel.setGold("Gold Coins: "+ p.getGoldCoins());

    }

    public void updateHighScore(){
        System.out.println("updating high score");
    }

    public void displayHazards(){
        System.out.println("Displaying hazards");
        gamePanel.setAlerts(ctrl.getHazards());
    }

    public void purchaseArrows(){
        System.out.println("buy arrows");

        p.decrementGoldCoins();
        gamePanel.setGold("Gold Coins: "+ p.getGoldCoins());

        Question[] questions = ctrl.runTrivia();
        TriviaUI triviaUI = new TriviaUI(questions, this);
        int numQCorrect = triviaUI.getNumCorrectAnswers();
        System.out.println("You got " + numQCorrect + " questions right");

        if (numQCorrect >= 2) {
            // p.addArrows();
            gamePanel.setArrows("Arrows: " + p.getArrows());
        }


    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");

        p.decrementGoldCoins();
        gamePanel.setGold("Gold Coins: " + p.getGoldCoins());

        Question[] questions = ctrl.runTrivia();
        TriviaUI triviaUI = new TriviaUI(questions, this);

        int numQCorrect = triviaUI.getNumCorrectAnswers();
        System.out.println("You got " + numQCorrect + " questions right");

        if (numQCorrect >= 2) {
            Random rand = new Random();
            int r = rand.nextInt(5);
            gamePanel.setAlerts(p.getSecret(r));
        }



    }

    public GameControl getGameControl(){
        return ctrl;
    }

    public void setTutorialLastCard(String s){
        tutorialPanel.setPreviouslyDisplayedCard(s);
    }

    public static void changeFont(Component component, Font font ){
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
