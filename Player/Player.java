//Pavan Anoop

/*
 * Last Editor(s): Pavan Anoop
 * Last Edit @ 05-19-2024
 */



// THINGS TO DO:
//
// Turn = Move, shoot, purchase arrows or secret
// Move - 1 at a time
//
// Shoot an arrow 
// - Start with 3
// - shoot into adjacent room + win condition
// - Wumpus move after shoot
// - no arrows = lose condition
//
// Purchase Arrows:
// - buy 2 arrows by answering 2/3 trivia
//
// Purchase Secret:
// - 2/3 trivia
// - Possible secrets: Where bat lives, where pit is, wumpus in 2 rooms of you?, 
// where the wumpus is, room you are in, answer to other trivia questions
//

package Player;

import java.util.Random;

public class Player {

// PROPERTIES ------------------------------------------------------------------------
    String name = new String("Player");
    private int arrows;
    private int goldCoins;
    private int turns;
    private int wumpusScore;
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    Random rand = new Random();
    int int_random = rand.nextInt(5);

// CONSTRUCTOR -------------------------------------------------------------------------------
    public Player(String name) {
        this.arrows = 3; // Initial number of arrows
        this.goldCoins = 0;
        this.turns = 0;
        this.name = name;
    }

// METHODS ------------------------------------------------------------------------------------
    public int getArrows() {
        return arrows;
    }

    public String getName() {
        return name;
    }
    public void setName(String s){
        this.name = s;
    }

    public void decrementArrows() {
        arrows--;
    }

    public void addArrows() {
        arrows++;
    }

    public String haveArrows(){
        if (arrows == 0){ 
            this.turns = 0;
            return "GAME OVER";
        }
        return "The player has " + arrows + " arrows";
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void addGoldCoins() {
        this.goldCoins++;
    }

    public void decrementGoldCoins() {
        this.goldCoins--;
    }

    public int getTurns() {
        return turns;
    }

    public void incrementTurns() {
        turns++;
        //addGoldCoins();
    }

    public String getSecret(int int_rand) {
        String[] secret = new String[5];
        secret[0] = "There is a bat in room " + batPos;
        secret[1] = "There is a pit in room " + pitPos;
        secret[2] = "There is a wumpus in room " + wumpusPos;
        secret[3] = "You are in room " + playerPos;
        secret[4] = "Trivia answer: ";
        return secret[int_rand];
    }

    public int getWumpusScore() {
        return wumpusScore;
    }

    public void addWumpusScore(int w) {
        wumpusScore += w;
    }

    public int calculateScore() {
        return (100 - getTurns() + getGoldCoins() * (5*getArrows()) + getWumpusScore());
    }
}