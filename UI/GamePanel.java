//Nathan Chiu

package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import Cave.Cave;
import net.miginfocom.swing.MigLayout;

public class GamePanel extends JPanel{
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    
    int score = 0;
    int highScore = 0;
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel highScoreLabel = new JLabel("High Score: ");
    JLabel goldCoinsLabel = new JLabel("Gold Coins: 0");
    JLabel currentPlayerLabel = new JLabel("Player: ");
    JLabel currentCaveLabel = new JLabel("Cave: ");
    JLabel arrowLabel = new JLabel("Arrows: 0");

    JButton shoot = new JButton("Shoot");
    JButton buyArrows = new JButton("Purchase Arrows");
    JButton buySecrets = new JButton("Purchase Secrets");
    JLabel alerts = new JLabel("Alerts");

    JPanel miniMap;


    public GamePanel(UI UI, CardLayout crd){
        //creates new font to be derived
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}
        
        
        // Setting game panel behavior
        {
            setSize(1920,1080);
            setLayout(new MigLayout(
            "",
            "[]0[]0[]",
            "[]0[]0[]0"
        ));


        }  
        
        //add menu and menuitems to menubar
        {
            JMenuItem startNewGame = new JMenuItem("New Game");
            startNewGame.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            startNewGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){

                }
            }); 
            menu.add(startNewGame);

            JMenuItem exitToTitle = new JMenuItem("Exit to Title Screen");
            exitToTitle.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            exitToTitle.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), "startScreen");
                }
            }); 
            menu.add(exitToTitle);

            JMenuItem exitToDesktop = new JMenuItem("Exit to Desktop");
            exitToDesktop.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            exitToDesktop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            });
            menu.add(exitToDesktop);

            menuBar.setBackground(new Color(0, 0, 0, 0)); 
            menuBar.setOpaque(true);
            menuBar.setBorder(null);
            menuBar.add(menu);
        }

        //adding labels
        {
            JPanel Panel = new JPanel();
            Panel.setLayout(new GridLayout(1,0));
            Panel.add(menuBar);
            Panel.add(scoreLabel);
            Panel.add(highScoreLabel);
            Panel.add(goldCoinsLabel);
            Panel.add(currentCaveLabel);
            Panel.add(arrowLabel);
            Panel.add(currentPlayerLabel);
            Panel.setVisible(true);
            add(Panel, "north");
            
        }

        //adding movement buttons
        {
            ImageIcon[] movementIcons = {new ImageIcon("UI/left_top.png"),new ImageIcon("UI/top_mid.png"),new ImageIcon("UI/right_top.png"),new ImageIcon("UI/left_bottom.png"),new ImageIcon("UI/bottom_mid.png"),new ImageIcon("UI/right_bottom.png")};

            
            int height = 450;

            for(int i = 0; i < 6; i++){
                JButton cur = new JButton();
                
                //sets icon of buttons and make them not change when pressed
                cur.setIcon(movementIcons[i]);
                cur.setBorder(null);
                cur.setContentAreaFilled(false);
                cur.setFocusPainted(false);
                cur.setBackground(Color.WHITE);

                final int dir = i;
                cur.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        UI.move(dir);
                        UI.displayHazards();
                    }
                });

                
                if(i == 2) add(cur, "wrap,grow, h " + height + "px," + "w " + height + "px");
                else add(cur, "grow, h " + height + "px," + "w " + height + "px");
            }
        }

        //adding purchasing, alerts, and shooting
        {
            
            buyArrows.setBackground(Color.WHITE);
            buyArrows.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    UI.purchaseArrows();
                }
            });
            add(buyArrows, "cell 3 0,flowy, w 500px, growy");
            
            buySecrets.setBackground(Color.WHITE);
            buySecrets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    UI.purchaseSecrets();
                }
            });
            add(buySecrets, "cell 3 0, w 500px, growy");

            shoot.setBackground(Color.WHITE);
            shoot.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(shoot.getText().equals("Shoot")) shoot.setText("Move");
                    else shoot.setText("Shoot");
                }
            });
            add(shoot, "cell 3 0, w 500px, growy");
            
            alerts.setBorder(BorderFactory.createLineBorder(Color.black));
            alerts.setHorizontalAlignment(JLabel.CENTER);
            add(alerts, "cell 3 0, w 500px, growy");

        }

        //add minimap
        {
            Cave cave = new Cave();
            miniMap = cave.drawMiniMap(40);
            miniMap.setMinimumSize(new Dimension(540,300));
            add(miniMap, "cell 3 1, grow");
            UI.getGameControl().setCave(cave);
        }
       
    }
}
