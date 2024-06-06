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
    //-----------------------PROPERTIES----------------------
    public static final String IDENTIFIER = "game";

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    
    private JLabel scoreLabel = new JLabel("Score: 0");
    private JLabel highScoreLabel = new JLabel("High Score: ");
    private JLabel goldCoinsLabel = new JLabel("Gold Coins: 0");
    private JLabel currentPlayerLabel = new JLabel("Player: ");
    private JLabel arrowLabel = new JLabel("Arrows: 3");

    private JButton shootOrMove = new JButton("Move");
    private JButton buyArrows = new JButton("Purchase Arrows");
    private JButton buySecrets = new JButton("Purchase Secrets");
    private JEditorPane hazards = new JEditorPane();
    private JEditorPane secrets = new JEditorPane();

    JPanel miniMap;

    //-----------------------CONSTRUCTOR----------------------
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
        
        //add menuitems to menu and menu to menubar
        {
            JMenuItem startNewGame = new JMenuItem("New Game");
            startNewGame.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            startNewGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), PlayerNamePanel.IDENTIFIER);
                }
            }); 
            menu.add(startNewGame);

            JMenuItem tutorial = new JMenuItem("Tutorial");
            tutorial.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            tutorial.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), "tutorial");
                    UI.setTutorialLastCard(IDENTIFIER);
                }
            });
            menu.add(tutorial);

            JMenuItem exitToTitle = new JMenuItem("Exit to Title Screen");
            exitToTitle.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 10));
            exitToTitle.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    crd.show(UI.getContentPane(), "start");
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
            Panel.add(arrowLabel);
            Panel.add(currentPlayerLabel);
            Panel.setVisible(true);
            add(Panel, "north");
            
        }

        //adding movement buttons
        {
            ImageIcon[] movementIcons = {new ImageIcon("UI\\movementImages\\left_top.png"),new ImageIcon("UI\\movementImages\\top_mid.png"),new ImageIcon("UI\\movementImages\\right_top.png"),new ImageIcon("UI\\movementImages\\left_bottom.png"),new ImageIcon("UI\\movementImages\\bottom_mid.png"),new ImageIcon("UI\\movementImages\\right_bottom.png")};

            
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
                        UI.updateGameLabels();
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
            add(buyArrows, "cell 3 0,flowy, w 500px, h 112px, gapy 0");
            
            buySecrets.setBackground(Color.WHITE);
            buySecrets.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                        UI.purchaseSecrets();
                }
            });
            add(buySecrets, "cell 3 0, w 500px, h 112px, gapy 0");

            shootOrMove.setBackground(Color.WHITE);
            shootOrMove.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(shootOrMove.getText().equals("Shoot")) shootOrMove.setText("Move");
                    else shootOrMove.setText("Shoot");
                }
            });
            add(shootOrMove, "cell 3 0, w 500px, h 112px, gapy 0");
            
            hazards.setText("Hazards:");
            hazards.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            hazards.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
            hazards.setEditable(false);
            hazards.setFocusable(false);
            hazards.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(hazards, "cell 3 0, w 500px, h 70px, gapy 0");
            
            secrets.setText("Secret:");
            secrets.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
            secrets.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 15));
            secrets.setEditable(false);
            secrets.setFocusable(false);
            secrets.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(secrets, "cell 3 0, w 500px, h 42px, gapy 0");
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

    //-----------------------METHODS----------------------

    public void setArrows(int i){
        arrowLabel.setText("Arrows: " + i);
    }

    public void setGold(int i){
        goldCoinsLabel.setText("Gold Coins: " + i);
    }

    public void setHazards(String[] strings){
        String print = "Hazards:\n";
        for(String s : strings){
            if(s != null) {
                print += s + "\n";
            }
        }
        hazards.setText(print);
    }

    public void setSecret(String s){
        secrets.setText("Secret:\n" + s);
    }

    public String getShootOrMove(){
        return shootOrMove.getText();
    }

    public void setPlayer(String player){
        currentPlayerLabel.setText("Player: " + player);
    }

    public void setHighScore(int i){
        highScoreLabel.setText("High Score: " + i);
    }

    public void setScore(int i){
        scoreLabel.setText("Score: " + i);
    }

}
