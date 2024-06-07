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


public class Player {

// PROPERTIES ------------------------------------------------------------------------
    String name = new String("Player");
    private int arrows;
    private int goldCoins;
    private int turns;
    

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


}