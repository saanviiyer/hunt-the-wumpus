import javax.swing.*;


public class Hex extends JButton{
  static final int LENGTH = 100; // side length, in pixels
  // radius to an edge is sqrt3 * length/2
  // radius to a vertex is 100
  
  static int nRows, nCols;
  int row;
  int col;
  public Hex(int r, int c){
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);
    setPreferredSize(new Dimension(LENGTH*2, LENGTH*1.732));
    this.row = r;
    this.col = c;
  }
  public static void setDim(int nr, int nc){
    nRows = nr;
    nCols = nc;
  }

  public int[][] getPoints(){
    int x = col*1.5*LENGTH;
    int y = row*L*1.732;
    if (col%2)
    int[][] points = new int[6][2];
    points[0][0] = x + LENGTH/2;
    points[0][1] = y - LENGTH*1.732/2;
    
    points[1][0] = x + LENGTH;
    points[1][1] = y;
    
    points[2][0] = x + LENGTH/2;
    points[2][1] = y + LENGTH*1.732/2;
    
    points[3][0] = x - LENGTH/2;
    points[3][1] = y + LENGTH*1.732/2;
    
    points[4][0] = x - LENGTH;
    points[4][1] = y;
    
    points[5][0] = x - LENGTH/2;
    points[5][1] = y - LENGTH*1.732/2;
    return points;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Polygon hex = new Polygon();
    for (int[] i: this.getPoints()){
      hex.addPoint(i[0], i[1]);
    }
    g.drawPolygon(hex);
  }
}