// Saanvi Subramanian
// Game Control

// new change
// Handles user input (except for High Score and Trivia), coordinates all the other parts of the game.
package GameControl;
import Player.*;
import Trivia.*;
import UI.*;

import java.util.Random;

import Cave.*;
import GameLocations.GameLocations;

public class GameControl {

    // PROPERTIES
    Player player;
    GameLocations gl = new GameLocations();
    Cave cave;
    UI ui;

// work with UI object to start the game and display the current room.

    public GameControl() {

    }

//method

// initialize board when game starts
    public void setPlayer(Player p){
        this.player = p;
    }
    public void setCave(Cave c){
        this.cave = c;
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
            this.player.incrementTurns();
            int n = this.gl.getFallenArrows();
            for (int i = 0; i < n; i++) this.player.addArrows();
            if (!this.gl.visited(this.gl.getPlayerPos())) {
                this.player.addGoldCoins(); 
                this.gl.setVisited(this.gl.getPlayerPos());
            }
            
            if (this.gl.atBats()){
                this.cave.setPlayerPos((int)(Math.random() * 30));
            } else if (this.gl.atPit()){
                // game over?
                if (false) this.endGame();
            } else if (this.gl.atWumpus()){
                // game over
                this.endGame();
            }
        }
    }

    public void endGame(){

    }

    public void shoot(int dir){
        if (this.cave.shoot(dir, 1) == gl.getWumpusPos()){
            this.endGame();
        }
    }
    public boolean checkWumpusNearby(Player player) {
        return false;
    }

    public void chooseCave() {
        System.out.println("choose cave");
        // change player's cave
    }

    public void startTrivia() {
        System.out.println("start trivia");
        // tell UI to open trivia view
    }

    public void playSound() {
        System.out.println("playing sound");
    }

    public Question[] runTrivia() {
        String[] answers = {"A","B","C","D"};

        //TODO logic for getting the questions should be in the questions class - new method that returns an array of random questions
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
        return questions;
    }

    // hazards:
    // bottomless pit
    // super bat

    public boolean checkBottomlessPit(Player player) {
        if (gl.atPit() == true) {
            // bottomless pit function
            System.out.println("in a pit");
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkSuperBat(Player player) {
        if (gl.atBats() == true) {
            System.out.println("in a bat");
            return true;
        }
        else {
            return false;
        }
    }

    public void checkHazard(Player player) {
        if (checkBottomlessPit(player)) {
            startTrivia();
        } 
        
        if (checkSuperBat(player)) {
            // change player position to a random position
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
    public String[] getHazards(){
        String[] s = new String[3];
        if (this.gl.nextToBats()) s[0] = "I hear flapping";
        if (this.gl.nextToPit()) s[1] = "I feel a breeze";
        if (this.gl.nextToWumpus()) s[2] = "You hear a wumpus";
        return s;
    }
}