// Saanvi Subramanian
// Period 5
// 3/15/24

package GameLocations;
import Cave.*;
import GameControl.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;


// two pits, two bats, arraylist of arrows, one shop
public class GameLocations {

    // PROPERTIES
    Cave cave;
    GameControl ctrl;
    int wumpusPos;
    int playerPos;
    int[] pitPos;
    int[] batPos;
    int[] fallenArrows = new int[30];
    int shopPos;
    static final Random RAND = new Random();
    Set<Integer> taken = new HashSet<Integer>();
    boolean[] visited = new boolean[30];
    // CONSTRUCTOR
    public GameLocations(Cave c) {
        cave = c;
        if (batPos == null){

            playerPos = RAND.nextInt(0,30);
            taken.add(playerPos);

            wumpusPos = RAND.nextInt(0,30);
            while (taken.contains(wumpusPos) || canMoveTo(wumpusPos)) wumpusPos = RAND.nextInt(0,30);
            taken.add(wumpusPos);

            pitPos = new int[2];
            pitPos[0] = RAND.nextInt(0,30);
            while (taken.contains(pitPos[0]) || canMoveTo(pitPos[0])) pitPos[0] = RAND.nextInt(0,30);
            taken.add(pitPos[0]);

            pitPos[1] = RAND.nextInt(0,30);
            while (taken.contains(pitPos[1]) || canMoveTo(pitPos[1])) pitPos[1] = RAND.nextInt(0,30);
            taken.add(pitPos[1]);

            batPos = new int[2];
            batPos[0] = RAND.nextInt(0,30);
            while (taken.contains(batPos[0]) || canMoveTo(batPos[0])) batPos[0] = RAND.nextInt(0,30);
            taken.add(batPos[0]);

            batPos[1] = RAND.nextInt(0,30);
            while (taken.contains(batPos[1]) || canMoveTo(batPos[1])) batPos[1] = RAND.nextInt(0,30);
            taken.add(batPos[1]);

            shopPos = RAND.nextInt(0,30);
            while (taken.contains(shopPos)) shopPos = RAND.nextInt(0,30);
            taken.add(shopPos);
        }
    }

    // METHOD (add getters and setters)
    public void setControl(GameControl g){
        ctrl = g;
    }
    public void setCave(Cave c){
        cave = c;
    }

    public Cave getCave(){return cave;}

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

    public boolean atWumpus(){
        return (playerPos == wumpusPos);
    }
    public boolean atPit(){
        return (playerPos == pitPos[0] || playerPos == pitPos[1]);
    }
    public boolean atBats(){
        return (playerPos == batPos[0] || playerPos == batPos[1]);
    }
    public boolean atShop(){
        return (playerPos == shopPos);
    }

    public boolean visited(int id){
        return visited[id];
    }

    public void setVisited(int id){
        visited[id] = true;
    }

    public void setPlayerPos(int pos) {
        playerPos = pos;
        
        //cave.goTo(pos);
        if (this.atBats()){
            System.out.println("GameLocations says: bats!");
        } else if (this.atPit()){
            System.out.println("GameLocations says: pit!");
        } else if (this.atWumpus()){
            System.out.println("GameLocations says: wumpus!");
        } else if (this.atShop()){
            System.out.println("GameLocations says: a shop!");
        }
        if (fallenArrows[pos] > 0){
            System.out.println("GameLocations says: a dropped arrow!");
        } 
        if (this.nextToBats()){
            System.out.println("GameLocations says: bats nearby");
        } else if (this.nextToPit()){
            System.out.println("GameLocations says: a pit is near");
        } else if (this.nextToWumpus()){
            System.out.println("GameLocations says: the wumpus is near");
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

    public int getFallenArrows() {
        int a = fallenArrows[playerPos];
        fallenArrows[playerPos] = 0;
        return a;
    }
    public void addFallenArrow(int id){
        fallenArrows[id]++;
    }
}
