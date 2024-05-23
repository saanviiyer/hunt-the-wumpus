package Cave;

import javax.swing.*;
import java.awt.*;


public class Hex extends JButton{
  public static int LENGTH = 70; // side length, in pixels
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

  static final int nRows = 3;
  static final int nCols = 3;
  String label = "";
  int id;
  double x, y;
  Color color = new Color(255,255,255);
  Polygon hex;

  public Hex(int i){
    this.id = i;
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);
    this.hex = new Polygon();
    double[][] pts = this.getPoints();
    for (double[] d: pts){
      this.hex.addPoint((int)(d[0]), (int)(d[1]));
    }
    if (i != 6) {
      this.x = pts[5-this.id][1]*1.732 + offsetX;
      this.y = pts[5-this.id][0]*1.732 + offsetY;
    } else {    
      this.x = pts[2][1]*1.732 + offsetX;
      this.y = (1)*LENGTH*1.732+offsetY;
    }
  
    //if (i == 0 || i==3) this.y += LENGTH*1.732/2;

    //if ((i%nCols)%2 == 1) this.y += LENGTH*1.732/2;
    //if (i == 1) this.y -=1.732*LENGTH;
    setBounds((int)this.x, (int)this.y, (int)LENGTH*2, (int)(LENGTH*1.732+2));

  }

  public void changeLabel(String s){this.label = s; this.repaint();}
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
    g.drawString(this.label, (int) (LENGTH), (int)(LENGTH*1.732/2));
  }

  @Override
  public boolean contains(int x, int y){
    return this.hex.contains(x, y);
  }
}