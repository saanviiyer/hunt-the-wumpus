// Saanvi Subramanian
// Game Control

// new change
// Handles user input (except for High Score and Trivia), coordinates all the other parts of the game.
package GameControl;
import Player.*;
// import Trivia.*;
import UI.*;
import Cave.*;
import GameLocations.GameLocations;

public class GameControl {

    // PROPERTIES
    Player player = new Player();
    GameLocations gl = new GameLocations();
    Cave cave;

// work with UI object to start the game and display the current room.

    public GameControl() {

    }

//method

// initialize board when game starts
    public void setCave(Cave c){
        this.cave = c;
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
        this.cave.move(direction);
        this.player.incrementTurns();
        // will be updating value of player's current location
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