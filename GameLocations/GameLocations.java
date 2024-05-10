// Saanvi Subramanian
// Period 5
// 3/15/24

package GameLocations;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


// two pits, two bats, arraylist of arrows, one shop
public class GameLocations {

    // PROPERTIES
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    ArrayList<Integer> fallenArrows;
    int shopPos;
    static final Random RAND = new Random();
    Set<Integer> taken = new HashSet<Integer>();

    // CONSTRUCTOR
    public GameLocations() {
        this.wumpusPos = RAND.nextInt(0,30);
        taken.add(this.wumpusPos);

        this.playerPos = RAND.nextInt(0,30);
        while (taken.contains(this.playerPos)) this.playerPos = RAND.nextInt(0,30);
        taken.add(this.playerPos);

        this.pitPos = new int[2];
        this.pitPos[0] = RAND.nextInt(0,30);
        while (taken.contains(this.pitPos[0])) this.pitPos[0] = RAND.nextInt(0,30);
        taken.add(this.pitPos[0]);

        this.pitPos[1] = RAND.nextInt(0,30);
        while (taken.contains(this.pitPos[1])) this.pitPos[1] = RAND.nextInt(0,30);
        taken.add(this.pitPos[1]);

        this.batPos = new int[2];
        this.batPos[0] = RAND.nextInt(0,30);
        while (taken.contains(this.batPos[0])) this.batPos[0] = RAND.nextInt(0,30);
        taken.add(this.batPos[0]);

        this.batPos[1] = RAND.nextInt(0,30);
        while (taken.contains(this.batPos[1])) this.batPos[1] = RAND.nextInt(0,30);
        taken.add(this.batPos[1]);

        this.fallenArrows = new ArrayList<Integer>();

        this.shopPos = RAND.nextInt(0,30);
        while (taken.contains(this.shopPos)) this.shopPos = RAND.nextInt(0,30);
        taken.add(this.shopPos);
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
        if (pos == this.batPos[0] || pos == this.batPos[1]){
            System.out.println("bats!");
        } else if (pos == this.pitPos[0] || pos == this.pitPos[1]){
            System.out.println("pit!");
        } else if (pos == this.wumpusPos){
            System.out.println("wumpus!");
        } else if (pos == this.shopPos){
            System.out.println("a shop!");
        }

        if (this.fallenArrows.contains(pos)){
            System.out.println("a dropped arrow!");
        } 
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
    
    public void addFallenArrow(int id){
        this.fallenArrows.add(id);
    }
}
