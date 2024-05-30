//Pavan Anoop

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
    public static int name = 0;
    private int arrows;
    private int goldCoins;
    private int turns;
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    Random rand = new Random();
    int int_random = rand.nextInt(5);

// CONSTRUCTOR -------------------------------------------------------------------------------
    public Player() {
        this.arrows = 3; // Initial number of arrows
        this.goldCoins = 100;
        this.turns = 0;
        this.name++;
    }

// METHODS ------------------------------------------------------------------------------------
    public int getArrows() {
        return arrows;
    }

    public int getName() {
        return name;
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

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }

    public int getTurns() {
        return turns;
    }

    public void incrementTurns() {
        turns++;
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

}