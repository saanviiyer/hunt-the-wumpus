package Cave;

/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 1:14 pm
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cave {
    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};

    // rooms are represented by ints [0,29]
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];

    int playerPos = 0;
    Hex[][] hexes = new Hex[5][6];
    /*
    boolean[][][] openings = { // [row][col][dir]
      {{true,true,true,true,true,true}, {true,true,true,true,true,true}},
      {{true,true,true,true,true,true}, {true,true,true,true,true,true}}
    };*/
    boolean[][][] openings = { // [row][col][dir]
      {{true,false,true,false,true,false}, {false,true,false,true,false,true}},
      {{false,false,true,true,true,false}, {true,true,false,false,false,true}}
    };
    // Cave is made up of hexagonal rooms with staggered columns
    //   6 cols, 5 rows
    public Cave() {
      //*
        //this.openings = {
        //  {{false,false,false,false,false,false}, {false,false,false,false,false,false}},
        //  {{false,false,false,false,false,false}, {false,false,false,false,false,false}}
        //};
//*/
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
    public boolean isNextTo(int id){
      int r = this.playerPos/6;
      int c = this.playerPos%6;
      for (int i = 0; i < 6; i++)
        if (this.openings[r%2][c%2][i] && this.adj[this.playerPos][i] == id) return true;
      return false;
      /*
      for (int i: this.adj[playerPos]) if (i == id) return true;
      return false;*/
    }

    public void goTo(int id){
      for(int i: this.adj[this.playerPos]) if (this.isNextTo(i)) this.hexes[i/6][i%6].reset();
      this.hexes[this.playerPos/6][this.playerPos%6].reset();
      if (this.isNextTo(id)) this.playerPos = id;
      
      for(int i = 0; i < 6; i++) if (this.openings[(id/6)%2][(id%6)%2][i]) 
        this.hexes[this.adj[id][i]/6][this.adj[id][i]%6].setColor(new Color(0,255,0));
      this.hexes[this.playerPos/6][this.playerPos%6].setColor(new Color(255,0,0));
    }

  
    public String DoStuff(int param) {
        return this.getPaths(param);
    }

    public void draw(JFrame frame){
        //int l = 50;
        for(int row = 0; row < 5; row++){
            for (int col = 0; col < 6; col++){
                this.hexes[row][col] = new Hex(row, col);
                int id = row*6+col;
                if (isNextTo(id)) this.hexes[row][col].setColor(new Color(0,255,0));
                else if (id == playerPos) this.hexes[row][col].setColor(new Color(255,0,0));
                    this.hexes[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.out.println(getPaths(id));
                        goTo(id);
                    }
                });
                frame.getContentPane().add(this.hexes[row][col]);
            }
        }
    }
}

