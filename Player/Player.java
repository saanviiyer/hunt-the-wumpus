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

public class Player {
    private int arrows;
    private int goldCoins;
    private int turns;
    private String[] secret;

    public Player() {
        this.arrows = 3; // Initial number of arrows
        this.goldCoins = 0;
        this.turns = 0;
    }

    public int getArrows() {
        return arrows;
    }

    public void decrementArrows() {
        arrows--;
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

    public void getSecret() {
        String[] secret = new String[6];
        secret[0] = "Their is a bat in room ";
        secret[1] = "Their is a pit in room ";
        secret[2] = "Their is a wumpus in room ";
        secret[3] = "You are in room ";
        secret[4] = "Trivia answer ##";
        secret[5] = "The Wumpus is two rooms away";
    }

}