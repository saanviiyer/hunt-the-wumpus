package Cave;

import javax.swing.*;
import java.awt.*;


public class Hex extends JButton{
  static final int LENGTH = 50; // side length, in pixels
  // radius to an edge is sqrt3 * length/2
  // radius to a vertex is length
  
  static int nRows = 5;
  static int nCols = 6;
  int row;
  int col;
  int id;
  Color color = new Color(255,255,255);
  Polygon hex;
  public Hex(int r, int c){
    //super(""+r+",");
    super(""+(r*6+c));
    this.id = r*6+c;
    this.row = r;
    this.col = c;
    double x = this.col*1.5*LENGTH+LENGTH;
    double y = this.row*LENGTH*1.732+LENGTH;
    if (this.col%2 == 1) y += LENGTH*1.732/2;
    setBounds((int)x, (int)y, (int)LENGTH*2, (int)(LENGTH*1.732+2));
    //setSize((int)LENGTH*2, (int)(LENGTH*1.732+2));
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);

    this.hex = new Polygon();
    for (double[] i: this.getPoints()){
      this.hex.addPoint((int)(i[0]), (int)(i[1]));
    }
    //setLocation((int)x, (int)y);
    //setSize((int)LENGTH, (int)(LENGTH));
  }
  public Hex(int i){
    this(i/6, i%6);
  }
  public static void setDim(int nr, int nc){
    nRows = nr;
    nCols = nc;
  }

  public double[][] getPoints(){
    /*
    double[][] points = new double[4][2];
    points[0][0] = 0;
    points[0][1] = 0;

    points[1][0] = LENGTH;
    points[1][1] = 0;

    points[2][0] = LENGTH;
    points[2][1] = LENGTH;

    points[3][0] = 0;
    points[3][1] = LENGTH;
    /*/
    double[][]points = new double[6][2];
    // x, y
    points[0][0] = LENGTH * 0.5;
    points[0][1] = 0;
    
    points[1][0] = LENGTH * 1.5;
    points[1][1] = 0;
    
    points[2][0] = LENGTH * 2;
    points[2][1] = LENGTH * 1.732/2;
    
    points[3][0] = LENGTH * 1.5;
    points[3][1] = LENGTH * 1.732;
    
    points[4][0] = LENGTH * 0.5;
    points[4][1] = LENGTH * 1.732;
    
    points[5][0] = 0;
    points[5][1] = LENGTH * 1.732/2;
    //*/
    return points;
  }

  public void setColor(Color c){this.color = c; this.repaint();}
  public void reset(){this.setColor(new Color(255,255,255));}
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    double x = this.col*1.5*LENGTH+LENGTH;
    double y = this.row*LENGTH*1.732+LENGTH;
    if (this.col%2 == 1) y += LENGTH*1.732/2;
    setBounds((int)x, (int)y, (int)LENGTH*2, (int)(LENGTH*1.732+2));
    g.drawPolygon(this.hex);
    
    g.setColor(this.color);
    g.fillPolygon(this.hex);
    
    g.setColor(new Color(0,0,0));
    g.drawString(""+(this.row*6+this.col), (int) (LENGTH), (int)(LENGTH*1.732/2));
  }
  @Override
  public boolean contains(int x, int y){
    return this.hex.contains(x, y);
  }
}