package Cave;

/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 03-22-2024
 */


import GameLocations.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;


public class Cave {
    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};
    static Random RAND = new Random();
    static GameLocations loc = new GameLocations();
    // rooms are represented by ints [0,29]
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];
    Hex[] hexes = new Hex[30]; // row = i/6, col = i%6

    /*
    boolean[][][] openings = { // [row][col][dir]
      //n, ne, se, s, sw, nw
      {{a,b,c,d,e,f}, {g,e,l,i,j,c}},
      {{d,j,k,a,h,l}, {i,h,f,g,b,k}}
    };*/
    



    boolean[][] paths = new boolean[30][6]; // for each hex, if that path is open
    // Cave is made up of hexagonal rooms with staggered columns
    //   6 cols, 5 rows
    public Cave() {
        //sets adjacencies
        for (int i = 0; i < 30; i++){
            int row = i/6; // [0,4]
            int col = i%6; // [0,5]

            // columns are aligned by even/oddness
            if (col%2 == 1){ // lower columns
                this.adj[i][0] = ( ((row-1+5)%5)*6 +  col        )%30; // previous row, same column
                this.adj[i][1] = (   row        *6 + (col+1)  %6 )%30; // same row, next column
                this.adj[i][2] = ( ((row+1)  %5)*6 + (col+1)  %6 )%30; // next row, next column
                this.adj[i][3] = ( ((row+1)  %5)*6 +  col        )%30; // next row, same column
                this.adj[i][4] = ( ((row+1)  %5)*6 + (col-1+6)%6 )%30; // next row, previous column
                this.adj[i][5] = (   row        *6 + (col-1+6)%6 )%30; // same row, previous column
            } else { // upper columns
                this.adj[i][0] = ( ((row-1+5)%5)*6 +  col        )%30; // previous row, same column
                this.adj[i][1] = ( ((row-1+5)%5)*6 +  (col+1)%6  )%30; // previous row, next column
                this.adj[i][2] = (   row        *6 + (col+1)  %6 )%30; // same row, next column
                this.adj[i][3] = ( ((row+1)  %5)*6 +  col        )%30; // next row, same column
                this.adj[i][4] = (   row        *6 + (col-1+6)%6 )%30; // same row, previous column
                this.adj[i][5] = ( ((row-1+5)%5)*6 + (col-1+6)%6 )%30; // previous row, previous column
            }
        }
        this.openPaths();
    }

    // returns an array of adjacencies
    public int[] getAdj(int id){
        return this.adj[id];
    }
    
    // returns a string displaying adjacencies
    public String printAdj(int id){
        return "{" + this.adj[id][0] + ", " + this.adj[id][1] + ", " + this.adj[id][2] + ", " 
                + this.adj[id][3] + ", " + this.adj[id][4] + ", " + this.adj[id][5] + "}";
    }

    // returns a string with adjacencies and directions
    public String getPaths(int id){
        String temp = new String();
        for (int i = 0; i < 6; i++){
            temp += dirs[i]+": " + this.adj[id][i] + "\n";
        }
        return temp;
    }


    // opens a wall between two adjacent hexes
    public void open(int id, int dir){
      this.paths[id][dir] = true;
      this.paths[this.adj[id][dir]][(dir+3)%6] = true;
      //System.out.println("opened path between " + id + " and " + this.adj[id][dir]);
      //System.out.println(id + ", dir" + dir);
    }

    // generates random openings. Mostly 3 per hex.
    public void openPaths(){
      int start = 0;
      ArrayList<Integer> open = new ArrayList<Integer>();
      open.add(start);
      ArrayList<Integer> finished = new ArrayList<Integer>();
      ArrayList<Integer> closed = new ArrayList<Integer>();
      for (int i = 0; i < 30; i++) if (i!=start) closed.add(i);
      while (!closed.isEmpty()){
        if (open.size() == 0){
          System.out.println("failed");
          break;
        }
        int curHex = open.get(0);
        open.remove(0);
        int nToOpen = 3-this.count(curHex);//Math.max(RAND.nextInt(1, 3)-this.count(curHex), 0);
        ArrayList<Integer> avail = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++){
          if (this.count(this.adj[curHex][i]) < 3 && !this.paths[curHex][i] && !finished.contains(this.adj[curHex][i])) avail.add(i);
        }
        ArrayList<Integer> pathsToOpen = new ArrayList<Integer>();
        for (int i = 0; i < nToOpen && !avail.isEmpty(); i++){
          int chI = RAND.nextInt(0,avail.size());
          pathsToOpen.add(avail.get(chI));
          avail.remove(chI);
        }
        for (int dir: pathsToOpen){
          open.add(this.adj[curHex][dir]);
          this.open(curHex, dir);
          closed.remove(Integer.valueOf(this.adj[curHex][dir]));
          //System.out.println("removed: " + this.adj[curHex][dir]);
        }
        finished.add(curHex);
      }
      //System.out.println("closed: " + closed.size());
    }

    //returns the amount of openings the hex has
    public int count(int id){
      int c = 0;
      for (boolean b: this.paths[id]) {
        if (b) c++;
      }
      return c;
    }

    // if Hex tar is open to cur
    public boolean isNextTo(int cur, int tar){
      for (int i = 0; i < 6; i++)
        //if (this.openings[r%2][c%2][i] && this.adj[cur][i] == tar) return true;
        if (this.paths[cur][i] && this.adj[cur][i] == tar) return true;
      return false;
    }
    // Same thing, except only with player location
    public boolean isNextTo(int id){ // if there is a path there
      return this.isNextTo(loc.getPlayerPos(), id);
    }

    // moves the player and redraws hexes
    public void goTo(int id){

      for(int i: this.adj[loc.getPlayerPos()]) this.hexes[i].reset();
      this.hexes[loc.getPlayerPos()].reset();
      if (this.isNextTo(id)) loc.setPlayerPos(id);
      
      for(int i = 0; i < 6; i++) if (this.paths[loc.getPlayerPos()][i]) 
        this.hexes[this.adj[loc.getPlayerPos()][i]].setColor(Hex.GREEN);
      this.hexes[loc.getPlayerPos()].setColor(Hex.RED);
    }

    // shoots an arrow in a direction dir, continues for len hexes or until it hits a wall
    public int shoot(int dir, int len){ // dir = [0,5]
      int cur = loc.getPlayerPos();
      for (int i = 0; i < len; i++){
        if (this.paths[cur][dir]) cur = this.adj[cur][dir];
        if (cur == loc.getWumpusPos()) System.out.println("Hit the wumpus!");
      }
      loc.addFallenArrow(cur);
      return cur;
    }


    // idk
    public String DoStuff(int param) {
        return this.getPaths(param);
    }

    // draws onto frame using frame.getContentPane().add(Hex)
    // See Hex.java for more information on how hexes are drawn.
    public void draw(JFrame frame){
        //int l = 50;
        Hex.setOffset(100,100);
        for(int row = 0; row < 5; row++){
            for (int col = 0; col < 6; col++){
                int id = row*6+col;
                this.hexes[id] = new Hex(row, col);
                if (isNextTo(id)) this.hexes[id].setColor(Hex.GREEN);
                else if (id == loc.getPlayerPos()) this.hexes[id].setColor(Hex.RED);
                    this.hexes[id].addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        //System.out.println(getPaths(id));
                        goTo(id);
                        //System.out.println(shoot(0, RAND.nextInt(5)));
                    }
                });
                frame.getContentPane().add(this.hexes[id]);
            }
        }
    }
}

