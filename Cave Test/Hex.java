import javax.swing.*;
import java.awt.*;


public class Hex extends JButton{
  static final int LENGTH = 50; // side length, in pixels
  // radius to an edge is sqrt3 * length/2
  // radius to a vertex is 100
  
  static int nRows = 5;
  static int nCols = 6;
  int row;
  int col;
  public Hex(int r, int c){
    super(""+r+",");
    //super(""+(r*6+c));
    this.row = r;
    this.col = c;
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);
    setSize((int)LENGTH, (int)(LENGTH));
    double x = c*1.5*LENGTH;
    double y = r*LENGTH*1.732;
    if (c%2 == 1) y += LENGTH*1.732/2;
    setLocation((int)x, (int)y);
    //setBounds(0,0,(int)LENGTH, (int)(LENGTH));

  }
  public static void setDim(int nr, int nc){
    nRows = nr;
    nCols = nc;
  }

  public double[][] getPoints(){
    double[][] points = new double[4][2];
    points[0][0] = 0;
    points[0][1] = 0;

    points[1][0] = LENGTH;
    points[1][1] = 0;

    points[2][0] = LENGTH;
    points[2][1] = LENGTH;

    points[3][0] = 0;
    points[3][1] = LENGTH;
    /* 
    points[0][0] = LENGTH/2+LENGTH;
    points[0][1] = LENGTH*1.732/2+LENGTH*1.732/2;
    
    points[1][0] = LENGTH+LENGTH;
    points[1][1] = LENGTH*1.732/2;
    
    points[2][0] = LENGTH/2+LENGTH;
    points[2][1] = LENGTH*1.732/2+LENGTH*1.732/2;
    
    points[3][0] = LENGTH/2+LENGTH;
    points[3][1] = LENGTH*1.732/2+LENGTH*1.732/2;
    
    points[4][0] = LENGTH+LENGTH;
    points[4][1] = LENGTH*1.732/2;
    
    points[5][0] = LENGTH/2+LENGTH;
    points[5][1] = LENGTH*1.732/2+LENGTH*1.732/2;*/
    return points;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Polygon hex = new Polygon();
    for (double[] i: this.getPoints()){
      hex.addPoint((int)(i[0]), (int)(i[1]));
    }
    //g.fillPolygon(hex);
    g.drawPolygon(hex);

  }
}