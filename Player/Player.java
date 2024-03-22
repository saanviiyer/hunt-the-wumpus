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
    
    public Player() {
        // Initialize player inventory and turns
        this.arrows = 5; // Initial number of arrows
        this.goldCoins = 0;
        this.turns = 0;
    }

    public int getArrows() {
        return arrows;
    }

    public void decrementArrows() {
        // Decrease the arrow count when the player shoots an arrow
        arrows--;
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

    // Additional methods and attributes as needed
}