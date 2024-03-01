/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 1:14 pm
 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Cave {
    static final String[] dirs = {"North", "Northeast", "Southeast", "South", "Southwest", "Northwest"};
    // rooms are represented by ints
    // adjacency list is represented by ints, going from north and proceeding clockwise
    int[][] adj = new int[30][6];
    // Cave is made up of hexagonal rooms with staggered columns
    //   6 cols, 5 rows
    public Cave() {
        //sets adjacencies
        for (int i = 0; i < 30; i++){
            int row = i/6;
            int col = i%6;

            // columns are aligned by even/oddness
            if (col%2 == 1){
                this.adj[i][0] = (i-6+30)%30;
                this.adj[i][1] = (i + 1 )%30;
                this.adj[i][2] = (i + 7 )%30;
                this.adj[i][3] = (i + 6 )%30;
                this.adj[i][4] = (i + 5 )%30;
                this.adj[i][5] = (i-1+30)%30;
            } else {
                this.adj[i][0] = (i-6+30)%30;
                this.adj[i][1] = (i-5+30)%30;
                this.adj[i][2] = (i + 1 )%30;
                this.adj[i][3] = (i + 6 )%30;
                this.adj[i][4] = (i-1+30)%30;
                this.adj[i][5] = (i-7+30)%30;
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
    private static Cave myCave;

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
        JFrame frame = new JFrame("My Wumpus GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

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
        }

        frame.setVisible(true);
    }
}