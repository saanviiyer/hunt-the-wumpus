// Nathan Chiu

// IMPORTANT TO ADD:
// PURCHASE ARROWS: YOU CAN PURCHASE 2 MORE ARROWS BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT
// PURCHASE SECRET: PURCHASE SECRET BY GETTING AT LEAST 2 OUT OF 3 TRIVIA QUESTIONS RIGHT



package UI;
import java.util.Random;

import javax.swing.*;

import Cave.Cave;
import GameControl.GameControl;
import GameLocations.GameLocations;
import Player.Player;
import Trivia.Question;
import java.awt.*;
import java.io.File;

public class UI extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    Player p;
    GameControl ctrl = new GameControl();

    GamePanel gamePanel;
    StartPanel startPanel;
    TutorialPanel tutorialPanel;
    EndPanel endPanel;
    PlayerNamePanel PlayerNameP;
    CardLayout crd = new CardLayout();

    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI(){
        ctrl.setUI(this);
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

        PlayerNameP = new PlayerNamePanel(this, crd);
        PlayerNameP.setVisible(true);
        add(PlayerNameP, PlayerNamePanel.IDENTIFIER);

        endPanel = new EndPanel(this, crd);
        endPanel.setVisible(true);
        add(endPanel, EndPanel.IDENTIFIER);

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
        updateGameLabels();

    }


    public void purchaseArrows(){
        System.out.println("buy arrows");

        p.decrementGoldCoins();

        Question[] questions = ctrl.runTrivia5();
        TriviaUI triviaUI = new TriviaUI(questions, this);
        int numQCorrect = triviaUI.getNumCorrectAnswers();
        System.out.println("You got " + numQCorrect + " questions right");

        if (numQCorrect >= 2) {
            p.addArrows();
            p.addArrows();

        }

        updateGameLabels();
    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");

        p.decrementGoldCoins();
        gamePanel.setGold(p.getGoldCoins());

        Question[] questions = ctrl.runTrivia5();
        TriviaUI triviaUI = new TriviaUI(questions, this);

        int numQCorrect = triviaUI.getNumCorrectAnswers();
        System.out.println("You got " + numQCorrect + " questions right");

        if (numQCorrect >= 2) {
            Random rand = new Random();
            int r = rand.nextInt(5);
            gamePanel.setSecret(p.getSecret(r));
        }



    }

    public GameControl getGameControl(){
        return ctrl;
    }

    public void setTutorialLastCard(String s){
        tutorialPanel.setPreviouslyDisplayedCard(s);
    }

    public void showGameEnd(boolean won){
        if(won) endPanel.won(p);
        else endPanel.lost("");
        crd.show(getContentPane(), EndPanel.IDENTIFIER);
    }

    public void setPlayer(){
        p = new Player(PlayerNameP.getPlayerName());
        ctrl.setPlayer(p);
        updateGameLabels();
        crd.show(getContentPane(), GamePanel.IDENTIFIER);
    }


    public void updateGameLabels(){
        gamePanel.setPlayer(p.getName());
        gamePanel.setArrows(p.getArrows());
        gamePanel.setGold(p.getGoldCoins());
        gamePanel.setHighScore(0);
        gamePanel.setScore(p.calculateScore());
        gamePanel.setHazards(ctrl.getHazards());
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

// Checks if game is over
    public void checkEnd(){
            if(this.ctrl.getGameLocations().atWumpus()){
                crd.show(getContentPane(), EndPanel.IDENTIFIER);   
            }
    }
}
