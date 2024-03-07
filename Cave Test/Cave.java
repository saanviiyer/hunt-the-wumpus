/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 1:14 pm
 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Cave {
    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};
    // rooms are represented by ints [0,29]
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];
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
        /* 
        // sets adjacencies: currently, each one is adjacent to the next three.
        for (int i = 0; i < 30; i++){
            for (int j = 1; j <= 3; j++)
                this.adj[i][j-1] = (j+i)%30;
        }
        */
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

    public String DoStuff(int param) {
        return this.getPaths(param);
    }
}

class Test {
    private static Cave myCave = new Cave();

    public static void TestConstructor() {
        myCave = new Cave();
        System.out.println("Constructor called");
    }

    public static void TestCaveDoStuff() {
        int param = 8;
        String result = myCave.DoStuff(param);
        System.out.println("DoStuff called with " + param + " and returned " + result);
    }


    public static void main(String args[])
    {
        JFrame frame = new JFrame("Cave");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);

        // Create a basic menu and add it to the top
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);


        Hex[][] hexes = new Hex[5][6];
        //int l = 50;
        for(int row = 0; row < 5; row++){
            for (int col = 0; col < 6; col++){
                //double x = col*1.5*l + l;
                //double y = row*l*1.732 + l;
                //if (col%2 == 1) y += l*1.732/2;
                System.out.println(row+", "+col);
                hexes[row][col] = new Hex(row, col);
                //hexes[row][col].setLocation((int)x, (int)y);
                //hexes[row][col].addActionListener(new ActionListener() {
                //    public void actionPerformed(java.awt.event.ActionEvent e) {
                //        TestCaveDoStuff();
                //    }
                //});
                //Hex h1 = new Hex(row,col);
                //h1.setSize(frame.getWidth(), 50);
                //h1.setLocation(300, 300);
                frame.getContentPane().add(hexes[row][col]);
            }
        }
        for (Hex[] hl: hexes) for (Hex h: hl) frame.getContentPane().add(h);

        /* 
        // Add a constructor test button to content pane and make it visible
        {
            JButton button = new JButton("Test Constructor");
            button.setSize(frame.getWidth(), 50);
            button.setLocation(0, 0);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TestConstructor();
                }
            });
            frame.getContentPane().add(button);
        }

        // Add a constructor test button to content pane and make it visible
        {
            JButton button = new JButton("Test DoStuff");
            button.setSize(frame.getWidth(), 50);
            button.setLocation(0, 50);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    TestCaveDoStuff();
                }
            });
            frame.getContentPane().add(button);
        }*/

        frame.setVisible(true);
    }
}