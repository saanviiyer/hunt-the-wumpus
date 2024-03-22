// Saanvi Subramanian
// Period 5
// 3/15/24
// saanvi doing pavan's work
package GameLocations;
import java.util.ArrayList;
import java.util.Random;

public class GameLocations {

    // PROPERTIES
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    ArrayList<Integer> fallenArrows;
    int shopPos;

    // CONSTRUCTOR
    public GameLocations() {
        this.wumpusPos = 0;
        this.playerPos = 15;
        this.pitPos = new int[0];
        this.batPos = new int[0];
        this.fallenArrows = new ArrayList<Integer>();
        this.shopPos = 2;
    }

    // METHOD (add getters and setters)

    public int getWumpusPos() {
        return this.wumpusPos;
    }

    public void setWumpusPos(int pos) {
        this.wumpusPos = pos;
    }

    public int getPlayerPos() {
        return this.playerPos;
    }

    public void setPlayerPos(int pos) {
        this.playerPos = pos;
    }

    public int[] getPitPos() {
        return this.pitPos;
    }

    public void setPitPos(int[] pos) {
        this.pitPos = pos;
    }

    public int[] getBatPos() {
        return this.batPos;
    }

    public void setBatPos(int[] pos) {
        this.batPos = pos;
    }



    public int getShopPos() {
        return this.shopPos;
    }

    public ArrayList<Integer> getFallenArrows() {
        return this.fallenArrows;
    }
}
