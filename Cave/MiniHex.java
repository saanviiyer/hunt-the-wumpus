/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 06-07-2024
 */

/*
 * Info:
 * Class for drawing a hex on the minimap.
 */

package Cave;

import javax.swing.*;
import java.awt.*;

public class MiniHex extends JButton{
  private  static int LENGTH = 40; // side length, in pixels
  // radius to an edge is sqrt3 * length/2
  // radius to a vertex is length
  private static int offsetX = LENGTH;
  private static int offsetY = LENGTH;
  public static final Color WHITE = new Color(255,255,255);
  public static final Color BLACK = new Color(0,0,0);
  public static final Color RED = new Color(255,0,0);
  public static final Color GREEN = new Color(0,255,0);
  public static final Color BLUE = new Color(0,0,255);
  public static final Color GRAY = new Color(120,120,120);
  public static final Color YELLOW = new Color(255,255,0);
  private Color def = WHITE;
  private static final int nRows = 5;
  private static final int nCols = 6;
  private int row;
  private int col;
  private int id;
  private double x, y;
  private Color color = new Color(255,255,255);
  private Polygon hex;

  public MiniHex(int r, int c){
    super(""+(r*nCols+c));
    this.id = r*nCols+c;
    this.row = r;
    this.col = c;
    setContentAreaFilled(false);
    setFocusPainted(true);
    setBorderPainted(false);
    double[][] d = this.getPoints();
    this.hex = new Polygon();
    for (double[] i: d){
      this.hex.addPoint((int)(i[0]), (int)(i[1]));
    }
    this.hex.addPoint((int)d[0][0], (int)d[0][1]);
    
    this.x = this.col*1.5*LENGTH+offsetX;
    this.y = this.row*LENGTH*1.732+offsetY;
    if (this.col%2 == 1) this.y += LENGTH*1.732/2;
    setBounds((int)this.x, (int)this.y, (int)LENGTH*2, (int)(LENGTH*1.732));


  }

  public MiniHex(int i){
    this(i/nCols, i%nCols);
  }

  public void visit(){this.def = GRAY;}
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

  public void reset(){this.setColor(this.def);}

  public static void setOffset(int x, int y){
    offsetX = x;
    offsetY = y;
  }

  public static void setLength(int l){LENGTH=l;}

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    if (this.color == GREEN && this.def == GRAY) g.setColor(new Color(255,255,0));
    else g.setColor(this.color);
    g.fillPolygon(this.hex);

    g.setColor(BLACK);
    Graphics2D g2 = (Graphics2D) g;
    int thickness = 1;
    Stroke oldStroke = g2.getStroke();
    g2.setStroke(new BasicStroke(thickness));
    setBounds((int)x, (int)y, (int)LENGTH*2, (int)(LENGTH*1.732+2));
    g.drawPolygon(this.hex);
    g2.setStroke(oldStroke);

    
    
    g.setColor(new Color(0,0,0));
    String s = ""+(this.row*6+this.col);
    if (s.length() == 2) g.drawString(s, (int) (LENGTH-10), (int)(LENGTH*1.732/2+7));
    else  g.drawString(s, (int) (LENGTH-6), (int)(LENGTH*1.732/2+7));
  }

  @Override
  public boolean contains(int x, int y){
    return this.hex.contains(x, y);
  }
}