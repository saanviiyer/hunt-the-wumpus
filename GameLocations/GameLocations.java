// Saanvi Subramanian
// Period 5
// 3/15/24
// saanvi doing pavan's work
package GameLocations;
import java.util.ArrayList;

public class GameLocations {

    // PROPERTIES
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    ArrayList<Integer> fallenArrows;
    int shopPos;

    // CONSTRUCTOR
    public GameLocation() {
        // assign each property to a random value
    }

    // METHOD (add getters and setters)

    public int getWumpusPos() {
        return wumpusPos;
    }

    public setWumpusPos(int pos) {
        wumpusPos = pos;
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public setPlayerPos(int pos) {
        playerPos = pos;
    }

    public int[] getPitPos() {
        return pitPos;
    }

    public setPitPos(int[] pos) {
        pitPos = pos;
    }

    public int[] getBatPos() {
        return batPos;
    }

    public setBatPos(int[] pos) {
        batPos = pos;
    }



    public int getShopPos() {
        return shopPos;
    }

    public ArrayList<Integer> getFallenArrows() {
        return fallenArrows;
    }
}
