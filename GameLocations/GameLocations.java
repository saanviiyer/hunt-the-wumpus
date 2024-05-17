// Saanvi Subramanian
// Period 5
// 3/15/24

package GameLocations;
import Cave.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


// two pits, two bats, arraylist of arrows, one shop
public class GameLocations {

    // PROPERTIES
    static Cave cave;
    static int wumpusPos;
    static int playerPos;
    static int[] pitPos;
    static int[] batPos;
    static ArrayList<Integer> fallenArrows;
    static int shopPos;
    static final Random RAND = new Random();
    static Set<Integer> taken = new HashSet<Integer>();

    // CONSTRUCTOR
    public GameLocations() {
        wumpusPos = RAND.nextInt(0,30);
        taken.add(wumpusPos);

        playerPos = RAND.nextInt(0,30);
        while (taken.contains(playerPos)) playerPos = RAND.nextInt(0,30);
        taken.add(playerPos);

        pitPos = new int[2];
        pitPos[0] = RAND.nextInt(0,30);
        while (taken.contains(pitPos[0])) pitPos[0] = RAND.nextInt(0,30);
        taken.add(pitPos[0]);

        pitPos[1] = RAND.nextInt(0,30);
        while (taken.contains(pitPos[1])) pitPos[1] = RAND.nextInt(0,30);
        taken.add(pitPos[1]);

        batPos = new int[2];
        batPos[0] = RAND.nextInt(0,30);
        while (taken.contains(batPos[0])) batPos[0] = RAND.nextInt(0,30);
        taken.add(batPos[0]);

        batPos[1] = RAND.nextInt(0,30);
        while (taken.contains(batPos[1])) batPos[1] = RAND.nextInt(0,30);
        taken.add(batPos[1]);

        fallenArrows = new ArrayList<Integer>();

        shopPos = RAND.nextInt(0,30);
        while (taken.contains(shopPos)) shopPos = RAND.nextInt(0,30);
        taken.add(shopPos);
    }

    // METHOD (add getters and setters)

    public int getWumpusPos() {
        return wumpusPos;
    }

    public void setWumpusPos(int pos) {
        wumpusPos = pos;
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public boolean canMoveTo(int id){
        return cave.isNextTo(playerPos, id);
    }
    public boolean nextToWumpus(){
        return canMoveTo(wumpusPos);
    }
    public boolean nextToPit(){
        return (canMoveTo(pitPos[0]) || canMoveTo(pitPos[1]));
    }
    public boolean nextToBats(){
        return (canMoveTo(batPos[0]) || canMoveTo(batPos[1]));
    }


    public void setPlayerPos(int pos) {
        playerPos = pos;
        if (pos == batPos[0] || pos == batPos[1]){
            System.out.println("bats!");
        } else if (pos == pitPos[0] || pos == pitPos[1]){
            System.out.println("pit!");
        } else if (pos == wumpusPos){
            System.out.println("wumpus!");
        } else if (pos == shopPos){
            System.out.println("a shop!");
        }
        if (fallenArrows.contains(pos)){
            System.out.println("a dropped arrow!");
        } 
    }

    public int[] getPitPos() {
        return pitPos;
    }

    public void setPitPos(int[] pos) {
        pitPos = pos;
    }

    public int[] getBatPos() {
        return batPos;
    }

    public void setBatPos(int[] pos) {
        batPos = pos;
    }


    public int getShopPos() {
        return shopPos;
    }

    public ArrayList<Integer> getFallenArrows() {
        return fallenArrows;
    }
    
    public void addFallenArrow(int id){
        fallenArrows.add(id);
    }
}
