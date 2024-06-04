package Cave;

/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 05-30-2024
 */

/*
 * Guide:
 * move(int dir), moves player in a direction
 * setPlayerPos(int id), teleports player to a hex
 */


import GameLocations.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;


public class Cave {

    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};
    static Random RAND = new Random();
    GameLocations loc = new GameLocations();
    // rooms are represented by ints [0,29]
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];
    MiniHex[] hexes = new MiniHex[30]; // minimap, row = i/6, col = i%6
    Hex[] view = new Hex[6]; // controls view
    Hex current;// middle control hex
    JPanel mini = new JPanel(); // minimap
    JPanel controls = new JPanel(); // controls



    boolean[][] paths = new boolean[30][6]; // for each hex, if that path is open

    // Cave is made up of hexagonal rooms with staggered columns
    //   6 cols, 5 rows
    public Cave() {
        //sets adjacencies
        loc.setCave(this);
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
        this.openPaths(); // randomizes paths
    }

    public void setLoc(GameLocations gl){
      this.loc = gl;
    }
    public GameLocations getLoc(){return this.loc;}

    public boolean[] getOpenings(){return this.paths[this.loc.getPlayerPos()];}


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
    }

    // generates random openings. Mostly 3 per hex.
    // If it fails to open all hexes, tries again.
    public void openPaths(){
      System.out.println("opening paths...");
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

      System.out.println("closed: " + closed.size());
      if (!closed.isEmpty()) {
        this.paths = new boolean[30][6];
        System.out.println("Trying again");
        this.openPaths();
      }
    }

    //returns the amount of openings the hex has
    public int count(int id){
      int c = 0;
      for (boolean b: this.paths[id]) if (b) c++;
      
      return c;
    }

    // if Hex tar is open to cur
    public boolean isNextTo(int cur, int tar){
      for (int i = 0; i < 6; i++)
        if (this.paths[cur][i] && this.adj[cur][i] == tar) return true;
      return false;
    }

    // Same thing, except only with player location
    public boolean isNextTo(int id){ // if there is a path there
      return this.isNextTo(loc.getPlayerPos(), id);
    }

    // wiping and drawing
    public void wipe(){
      for(int i: this.adj[loc.getPlayerPos()]) this.hexes[i].reset();
      this.hexes[loc.getPlayerPos()].reset();
    }

    public void color(){
      if (this.view[0] != null) {
        this.current.changeLabel("" + loc.getPlayerPos());
        for (int i = 0; i < 6; i++){
          this.view[i].changeLabel(""+adj[loc.getPlayerPos()][i]);
          if (this.paths[loc.getPlayerPos()][i]) {
            if (loc.visited(this.adj[loc.getPlayerPos()][i])) this.view[i].setColor(Hex.YELLOW);
            else this.view[i].setColor(Hex.GREEN);
          }
          else this.view[i].reset();
        }
      }
      
      for(int i = 0; i < 6; i++) if (this.paths[loc.getPlayerPos()][i]) 
        this.hexes[this.adj[loc.getPlayerPos()][i]].setColor(MiniHex.GREEN);
      this.hexes[loc.getPlayerPos()].setColor(MiniHex.RED);
    }

    // sets the player position. (Teleports)
    public void setPlayerPos(int id){
      this.wipe();
      loc.setPlayerPos(id);
      this.color();
    }

    // moves the player to an adjacent open hex and redraws hexes
    public boolean goTo(int id){
      boolean b = false;
      this.wipe();
      if (this.isNextTo(id)) {
        loc.setPlayerPos(id);
        this.hexes[id].visit();
        b = true;
      }
      this.color();
      return b;
    }

    // moves the player in a direction, if possible.
    public boolean move(int dir){
      return this.goTo(this.adj[loc.getPlayerPos()][dir]);
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


    // adds controls to this.view, do not use more than once
    // sets control side length to l
    public JPanel drawControls(int l){
        //int l = 50;
        //this.controls.setLayout(null);
        //this.controls.setSize(Hex.LENGTH, Hex.LENGTH);
        this.controls.setPreferredSize(new Dimension(l*5,l*5));

        //this.controls.setLayout(null);
        Hex.setLength(l);
        Hex.setOffset(0,0);
        this.current = new Hex(6);
        this.current.changeLabel("" + loc.getPlayerPos());
        this.current.setColor(Hex.RED);
        for(int i = 0; i < 6; i++){
            int id = 5-i;//(6-i)%6;
            this.view[id] = new Hex(id);
            if (paths[loc.getPlayerPos()][id]) this.view[id].setColor(Hex.GREEN);
            this.view[id].changeLabel(""+adj[loc.getPlayerPos()][id]);
            this.view[id].addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    move(id);
                }
            });
            this.controls.add(this.view[id]);
        }
        this.controls.add(this.current);
        return this.controls;
    }

    // draws onto panel mini
    // sets side lengths to l
    // DO NOT USE MORE THAN ONCE
    // See Hex.java for more information on how hexes are drawn.
    public JPanel drawMiniMap(int l){
        //int l = 50;
        //this.mini.setLayout(null);
        this.mini.setPreferredSize(new Dimension(l*2, l*6));
        MiniHex.setLength(l);
        MiniHex.setOffset(0,0);
        for(int row = 0; row < 5; row++){
            for (int col = 0; col < 6; col++){
                int id = row*6+col;
                this.hexes[id] = new MiniHex(row, col);
                if (isNextTo(id)) this.hexes[id].setColor(MiniHex.GREEN);
                else if (id == loc.getPlayerPos()) this.hexes[id].setColor(MiniHex.RED);
                    this.hexes[id].addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        //goTo(id);
                    }
                });
                this.mini.add(this.hexes[id]);
            }
        }
        this.hexes[this.loc.getPlayerPos()].visit();
        return this.mini;
    }
}

