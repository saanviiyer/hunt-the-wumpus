package Cave;

import javax.swing.*;
import java.awt.*;


public class MiniHex extends JButton{
  static int LENGTH = 20; // side length, in pixels
  // radius to an edge is sqrt3 * length/2
  // radius to a vertex is length
  static int offsetX = LENGTH;
  static int offsetY = LENGTH;
  static final Color WHITE = new Color(255,255,255);
  static final Color BLACK = new Color(0,0,0);
  static final Color RED = new Color(255,0,0);
  static final Color GREEN = new Color(0,255,0);
  static final Color BLUE = new Color(0,0,255);
  //static 

  static final int nRows = 5;
  static final int nCols = 6;
  int row;
  int col;
  int id;
  double x, y;
  Color color = new Color(255,255,255);
  Polygon hex;

  public MiniHex(int r, int c){
    super(""+(r*nCols+c));
    this.id = r*nCols+c;
    this.row = r;
    this.col = c;
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);

    this.hex = new Polygon();
    for (double[] i: this.getPoints()){
      this.hex.addPoint((int)(i[0]), (int)(i[1]));
    }
    
    this.x = this.col*1.5*LENGTH+offsetX;
    this.y = this.row*LENGTH*1.732+offsetY;
    if (this.col%2 == 1) this.y += LENGTH*1.732/2;
    setBounds((int)this.x, (int)this.y, (int)LENGTH*2, (int)(LENGTH*1.732+2));


  }

  public MiniHex(int i){
    this(i/nCols, i%nCols);
  }


  public double[][] getPoints(){

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

  public void reset(){this.setColor(WHITE);}

  public static void setOffset(int x, int y){
    offsetX = x;
    offsetY = y;
  }

  public static void setLength(int l){LENGTH=l;}

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
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