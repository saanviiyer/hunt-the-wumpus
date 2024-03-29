// Saanvi Subramanian
// Game Control

// new change
// Handles user input (except for High Score and Trivia), coordinates all the other parts of the game.

import Player.*;
import Trivia.*;
import UI.*;
import Cave.*;

public class GameControl {

    // PROPERTIES
    Player player = new Player();
    UI ui = new UI();

// work with UI object to start the game and display the current room.

    public GameControl() {

    }
//method

// initialize board when game starts
    public void initBoard() {
        System.out.println("Initializing board");
        displayBoard();
    }

    public void displayBoard() {
        System.out.println("displaying board rn");
    }

    public void movePlayer(String direction) {
        System.out.println("moving player in direction " + direction);
        // will be updating value of player's current location
    }

    public boolean checkWumpusNearby(Player player) {

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
        return true;
    }

    public boolean checkSuperBat(Player player) {
        return true;
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

}