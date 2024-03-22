package Cave;

/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 03-22-2024
 */


import GameLocations.*;
import Player.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class Cave {
    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};
    static Random RAND = new Random();
    static GameLocations loc = new GameLocations();
    // rooms are represented by ints [0,29]
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];
    Player player;
    Hex[] hexes = new Hex[30]; // row = i/6, col = i%6
    /*
    boolean[][][] openings = { // [row][col][dir]
      {{true,true,true,true,true,true}, {true,true,true,true,true,true}},
      {{true,true,true,true,true,true}, {true,true,true,true,true,true}}
    };*/
    /*
    boolean[][][] openings = { // [row][col][dir]
      //n, ne, se, s, sw, nw
      {{a,b,c,d,e,f}, {g,e,l,i,j,c}},
      {{d,j,k,a,h,l}, {i,h,f,g,b,k}}
    };*/
    
    boolean[][][] openings = { // [row][col][dir]
      {{true,false,true,false,true,false}, {false,true,false,true,false,true}},
      {{false,false,true,true,true,false}, {true,true,false,false,false,true}}
    }; // connected to row, alternating column


    boolean[][] paths = new boolean[30][6];
    // Cave is made up of hexagonal rooms with staggered columns
    //   6 cols, 5 rows
    public Cave() {
        //this.randomOpen();
        if (RAND.nextInt(3) != 0) this.connectByColumn();
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

    }

    public int[] getAdj(int id){
        return this.adj[id];
    }
    
    public String printAdj(int id){
        return "{" + this.adj[id][0] + ", " + this.adj[id][1] + ", " + this.adj[id][2] + ", " 
                + this.adj[id][3] + ", " + this.adj[id][4] + ", " + this.adj[id][5] + "}";
    }

    public String getPaths(int id){
        String temp = new String();
        for (int i = 0; i < 6; i++){
            temp += dirs[i]+": " + this.adj[id][i] + "\n";
        }
        return temp;
    }

    
    public void randomOpen(){
      // a-l
      // a, b, c, d, e, f = 3
      // g, e, l, i, j, c = 3
      // d, j, k, a, h, l = 3
      // i, h, f, g, b, k = 3
      boolean a = RAND.nextBoolean();
      boolean b = RAND.nextBoolean();
      boolean c = RAND.nextBoolean();
      boolean d = RAND.nextBoolean();
      boolean e = RAND.nextBoolean();
      boolean f = RAND.nextBoolean();
      boolean g = RAND.nextBoolean();
      boolean h = RAND.nextBoolean();
      boolean i = RAND.nextBoolean();
      boolean j = RAND.nextBoolean();
      boolean k = RAND.nextBoolean();
      boolean l = RAND.nextBoolean();

      this.openings[0][0][0] = a;
      this.openings[0][0][1] = b;
      this.openings[0][0][2] = c;
      this.openings[0][0][3] = d;
      this.openings[0][0][4] = e;
      this.openings[0][0][5] = f;

      this.openings[0][1][0] = g;
      this.openings[0][1][1] = e;
      this.openings[0][1][2] = l;
      this.openings[0][1][3] = i;
      this.openings[0][1][4] = j;
      this.openings[0][1][5] = c;

      this.openings[1][0][0] = d;
      this.openings[1][0][1] = j;
      this.openings[1][0][2] = k;
      this.openings[1][0][3] = a;
      this.openings[1][0][4] = h;
      this.openings[1][0][5] = l;

      this.openings[1][1][0] = i;
      this.openings[1][1][1] = h;
      this.openings[1][1][2] = f;
      this.openings[1][1][3] = g;
      this.openings[1][1][4] = b;
      this.openings[1][1][5] = k;
      /*
      this.openings = { // [row][col][dir]
        //n, ne, se, s, sw, nw
        {{a,b,c,d,e,f}, {g,e,l,i,j,c}},
        {{d,j,k,a,h,l}, {i,h,f,g,b,k}}
      }; */
    }
    public void connectByColumn(){
      // a-l
      // a, b, c, d, e, f = 3
      // g, e, l, i, j, c = 3
      // d, j, k, a, h, l = 3
      // i, h, f, g, b, k = 3

      boolean a = true;
      boolean b = false;
      boolean c = false;
      boolean d = true;
      boolean e = false;
      boolean f = false;
      boolean g = true;
      boolean h = false;
      boolean i = true;
      boolean j = false;
      boolean k = false;
      boolean l = false;

      // one of b, c, e, f is true;


      switch (RAND.nextInt(4)){
        case 0:
          b = true;
          break;
        case 1:
          c = true;
          break;
        case 2:
          e = true;
          break;
        case 3:
          f = true;
          break;
        default: break;
      }
      if (b){
        l = true;
      } else if (c){
        h = true;
      } else if (e) {
        k = true;
      } else if (f){
        j = true;
      }


      this.openings[0][0][0] = a;
      this.openings[0][0][1] = b;
      this.openings[0][0][2] = c;
      this.openings[0][0][3] = d;
      this.openings[0][0][4] = e;
      this.openings[0][0][5] = f;

      this.openings[0][1][0] = g;
      this.openings[0][1][1] = e;
      this.openings[0][1][2] = l;
      this.openings[0][1][3] = i;
      this.openings[0][1][4] = j;
      this.openings[0][1][5] = c;

      this.openings[1][0][0] = d;
      this.openings[1][0][1] = j;
      this.openings[1][0][2] = k;
      this.openings[1][0][3] = a;
      this.openings[1][0][4] = h;
      this.openings[1][0][5] = l;

      this.openings[1][1][0] = i;
      this.openings[1][1][1] = h;
      this.openings[1][1][2] = f;
      this.openings[1][1][3] = g;
      this.openings[1][1][4] = b;
      this.openings[1][1][5] = k;
      /*
      this.openings = { // [row][col][dir]
        //n, ne, se, s, sw, nw
        {{a,b,c,d,e,f}, {g,e,l,i,j,c}},
        {{d,j,k,a,h,l}, {i,h,f,g,b,k}}
      }; */
    }
    public boolean isNextTo(int id){
      int r = loc.getPlayerPos()/6;
      int c = loc.getPlayerPos()%6;
      for (int i = 0; i < 6; i++)
        if (this.openings[r%2][c%2][i] && this.adj[loc.getPlayerPos()][i] == id) return true;
      return false;
      /*
      for (int i: this.adj[loc.getPlayerPos()]) if (i == id) return true;
      return false;*/
    }
    public void goTo(int id){
      System.out.println(loc.getPlayerPos());
      for(int i: this.adj[loc.getPlayerPos()]) this.hexes[i].reset();
      this.hexes[loc.getPlayerPos()].reset();
      if (this.isNextTo(id)) loc.setPlayerPos(id);
      
      for(int i = 0; i < 6; i++) if (this.openings[(loc.getPlayerPos()/6)%2][(loc.getPlayerPos()%6)%2][i]) 
        this.hexes[this.adj[loc.getPlayerPos()][i]].setColor(Hex.GREEN);
      this.hexes[loc.getPlayerPos()].setColor(Hex.RED);
    }

    public int shoot(int dir, int len){ // dir = [0,5]
      int cur = loc.getPlayerPos();
      for (int i = 0; i < len; i++){
        if (this.openings[(cur/6)%2][(cur%6)%2][dir]) cur = this.adj[cur][dir];
      }
      return cur;
    }

    public int pathFind(){
      return 0;
    }
  
    public String DoStuff(int param) {
        return this.getPaths(param);
    }

    public void draw(JFrame frame){
        //int l = 50;
        for(int row = 0; row < 5; row++){
            for (int col = 0; col < 6; col++){
                int id = row*6+col;
                this.hexes[id] = new Hex(row, col);
                if (isNextTo(id)) this.hexes[id].setColor(new Color(0,255,0));
                else if (id == loc.getPlayerPos()) this.hexes[id].setColor(new Color(255,0,0));
                    this.hexes[id].addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.out.println(getPaths(id));
                        goTo(id);
                        System.out.println(shoot(0, RAND.nextInt(5)));
                    }
                });
                frame.getContentPane().add(this.hexes[id]);
            }
        }
    }
}

