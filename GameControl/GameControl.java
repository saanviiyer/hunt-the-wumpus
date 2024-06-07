// Saanvi Subramanian
// Game Control

package GameControl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// new change
// Handles user input (except for High Score and Trivia), coordinates all the other parts of the game.
// UPDATE: trivia should now work correctly

import Player.*;
import HighScore.*;
import Trivia.*;
import UI.*;
import HighScore.HighScore;
import java.util.Random;
import java.util.ArrayList;
import Cave.*;
import GameLocations.GameLocations;

public class GameControl{

    // PROPERTIES
    Player player;
    GameLocations gl;
    Cave cave;
    UI ui;
    HighScore score;

// work with UI object to start the game and display the current room.

    public GameControl() {
        try{
            this.score = new HighScore();
            this.score.save();
        }
        catch (Exception e){
            System.out.println("Highscores are broken.");
        }
    }

//method

// initialize board when game starts
    public void setPlayer(Player p){
        this.player = p;
    }
    public void setCave(Cave c){
        this.cave = c;
        this.gl = c.getLoc();
    }
    public void setUI(UI u){
        this.ui = u;
    }

    public void initBoard() {
        System.out.println("Initializing board");
        displayBoard();
    }

    public void displayBoard() {
        System.out.println("displaying board rn");
    }

    public void movePlayer(int direction) {
        System.out.println("moving player in direction " + direction);
        if (this.cave.move(direction)) {
            ui.setNewImages();
            this.player.incrementTurns();
            int n = this.gl.getFallenArrows();
            for (int i = 0; i < n; i++) this.player.addArrows();
            if (!this.gl.visited(this.gl.getPlayerPos())) {
                this.player.addGoldCoins(); 
                this.gl.setVisited(this.gl.getPlayerPos());
            }
            
            if (this.gl.atBats()){
                System.out.println("GameControl says: Bats");
                this.cave.setPlayerPos((int)(Math.random() * 30));
            } else if (this.gl.atPit()){
                System.out.println("GameControl says: Pit");
                // game over?
                if (gl.atWumpus()) this.endGame(false);
            } else if (this.gl.atWumpus()){
                // game over
                this.endGame(false);
            }
        }
    }

    public int calcScore(boolean won){
        Score s = new Score(this.player.getGoldCoins(), Boolean.compare(won, false), this.player.getTurns(), this.player.getArrows(), this.player.getName());
        return s.value();
    }

    public void endGame(boolean won){
        Score s = new Score(this.player.getGoldCoins(), Boolean.compare(won, false), this.player.getTurns(), this.player.getArrows(), this.player.getName());
        this.score.add(s);
        leaderboard();
        if (won) ui.winGame();
    }

    public String leaderboard(){
        return this.score.print();
    }

    public void shoot(int dir){
        System.out.println("Attempting to shoot");
        if (player.getArrows() > 0){
            player.decrementArrows();
            if (this.cave.shoot(dir, 1) == gl.getWumpusPos()){
                player.addWumpusScore(50);
                this.endGame(true);
            } else {
                System.out.println("You missed.");
            }
        } else {
            System.out.println("Not enough arrows.");
        }
    }

    public GameLocations getGameLocations(){
        return this.gl;
    }

    public String getSecret(int r){
        String[] secrets = new String[5];
        secrets[0] = "There is a wumpus at " + gl.getWumpusPos();
        secrets[1] = "There are bats at " + gl.getBatPos()[0];
        secrets[2] = "There are bats at " + gl.getBatPos()[1];
        secrets[3] = "There is a pit at " + gl.getPitPos()[0];
        secrets[4] = "There is a pit at " + gl.getPitPos()[1];
        return secrets[r];
        //return secrets[(int)(Math.random()*5)];
    }

    public Question[] runTrivia3() {
        String[] answers = {"A","B","C","D"};

        //TODO logic for getting the questions should be in the questions class - new method that returns an array of random questions
        // CHANGE TO BE ACTUAL LENGTH OF QUESTIONS FILE
        int q = 15;

        Random r = new Random();
        int a = r.nextInt(q);
        int b = r.nextInt(q);
        while (b == a) {
          b = r.nextInt(q);  
        }
        int c = r.nextInt(q);
        while ((c == a) || (c == b)) {
          c = r.nextInt(q);
        }


        // ADD CODE TO READ QUESTIONS AND ANSWERS FROM A, B, C, D, E FOR THE FIVE QUESTIONS
        ArrayList<String> linesQ = new ArrayList<String>();
        ArrayList<String> linesA = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("GameControl\\Trivia-Q.csv"));
            BufferedReader readerA = new BufferedReader(new FileReader("GameControl\\Trivia-A.csv"));
            String line = null;
        while ((line=reader.readLine()) != null) {
            linesQ.add(line);
        }
        while ((line=readerA.readLine()) != null) {
            linesA.add(line);
        }
        System.out.println(linesQ.get(0));
        System.out.println(linesA.get(0));

        } catch (FileNotFoundException ex) {

        } catch (IOException ex2) {

        }


        // aQ should be the question from the line number of a
        String aQ = linesQ.get(a);
        String bQ = linesQ.get(b);
        String cQ = linesQ.get(c);

        String[] answersA = linesA.get(a).split(",");

        for (String i : answersA) {
            System.out.println(i);
        }

        String[] answersB = linesA.get(b).split(",");
        String[] answersC = linesA.get(c).split(",");


        // aA should be the ANSWER CHOICES from the line number of a

        String[] aA = {"","","",""};
        String[] bA = {"","","",""};
        String[] cA = {"","","",""};
        populateAnswers(aA, answersA);
        populateAnswers(bA, answersB);
        populateAnswers(cA, answersC);
        

        //aI should be the NUMBER at the end of answers from line a to indicate the correct answer
        int aI = Integer.parseInt(answersA[4]);
        int bI = Integer.parseInt(answersB[4]);
        int cI = Integer.parseInt(answersC[4]);

        Question[] questions = {new Question(aQ,aA ,aI),
                                new Question(bQ,bA, bI),
                                new Question(cQ,cA ,cI)};
        return questions;
    }


    public Question[] runTrivia5() {
        String[] answers = {"A","B","C","D"};

        //TODO logic for getting the questions should be in the questions class - new method that returns an array of random questions
        // CHANGE TO BE ACTUAL LENGTH OF QUESTIONS FILE
        int q = 15;

        Random r = new Random();
        int a = r.nextInt(q);
        int b = r.nextInt(q);
        while (b == a) {
          b = r.nextInt(q);  
        }
        int c = r.nextInt(q);
        while ((c == a) || (c == b)) {
          c = r.nextInt(q);
        }
        int d = r.nextInt(q);
        while ((d == c) || (d == b) || (d == a )) {
            d = r.nextInt(q);
        }
        int e = r.nextInt(q);
        while ((e == d) || (e == c) || (e == b) || (e == a))  {
            e = r.nextInt(q);
        }



        // ADD CODE TO READ QUESTIONS AND ANSWERS FROM A, B, C, D, E FOR THE FIVE QUESTIONS
        ArrayList<String> linesQ = new ArrayList<String>();
        ArrayList<String> linesA = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("GameControl\\Trivia-Q.csv"));
            BufferedReader readerA = new BufferedReader(new FileReader("GameControl\\Trivia-A.csv"));
            String line = null;
        while ((line=reader.readLine()) != null) {
            linesQ.add(line);
        }
        while ((line=readerA.readLine()) != null) {
            linesA.add(line);
        }
        System.out.println(linesQ.get(0));
        System.out.println(linesA.get(0));

        } catch (FileNotFoundException ex) {

        } catch (IOException ex2) {

        }


        // aQ should be the question from the line number of a
        String aQ = linesQ.get(a);
        String bQ = linesQ.get(b);
        String cQ = linesQ.get(c);
        String dQ = linesQ.get(d);
        String eQ = linesQ.get(e);

        String[] answersA = linesA.get(a).split(",");

        for (String i : answersA) {
            System.out.println(i);
        }

        String[] answersB = linesA.get(b).split(",");
        String[] answersC = linesA.get(c).split(",");
        String[] answersD = linesA.get(d).split(",");
        String[] answersE = linesA.get(e).split(",");


        // aA should be the ANSWER CHOICES from the line number of a

        String[] aA = {"","","",""};
        String[] bA = {"","","",""};
        String[] cA = {"","","",""};
        String[] dA = {"","","",""};
        String[] eA = {"","","",""};
        populateAnswers(aA, answersA);
        populateAnswers(bA, answersB);
        populateAnswers(cA, answersC);
        populateAnswers(dA, answersD);
        populateAnswers(eA, answersE);
        

        //aI should be the NUMBER at the end of answers from line a to indicate the correct answer
        int aI = Integer.parseInt(answersA[4]);
        int bI = Integer.parseInt(answersB[4]);
        int cI = Integer.parseInt(answersC[4]);
        int dI = Integer.parseInt(answersD[4]);
        int eI = Integer.parseInt(answersE[4]);

        Question[] questions = {new Question(aQ,aA ,aI),
                                new Question(bQ,bA, bI),
                                new Question(cQ,cA ,cI),
                                new Question(dQ,dA , dI),
                                new Question(eQ,eA , eI)};
        return questions;
    }

    // hazards:
    // bottomless pit
    // super bat

    public void populateAnswers(String[] original, String[] copyFrom) {
        for (int i = 0; i < 4; i++) {
            original[i] = copyFrom[i];
        }
    }






    // public void moveWumpus() {
    //     if player.shoot() {
    //         wumpus.runAway();
    //     }
    // }

    // method to check if player wants trivia
    // if player.wantsTrivia {
    //     UI.showTrivia;
    // }

    public boolean gameEnded() {
        if (player.getGoldCoins() <= -2) {
            return true;
        }
        return false;
    }

    public String[] getHazards(){
        String[] s = new String[3];
        if (this.gl.nextToBats()) s[0] = "I hear flapping";
        if (this.gl.nextToPit()) s[1] = "I feel a breeze";
        if (this.gl.nextToWumpus()) s[2] = "You hear a wumpus";
        return s;
    }
}