package UI;

import javax.swing.*;

import Cave.Cave;
import Player.Player;
import net.miginfocom.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class UI2 extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    Player p = new Player();

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem startNewGame = new JMenuItem("New Game");

    
    JButton shoot = new JButton("Shoot");

    JButton buyArrows = new JButton("Purchase Arrows");



    JButton buySecrets = new JButton("Purchase Secrets");

    int arrows = p.getArrows();
    JLabel arrowLabel = new JLabel("Arrows: " + arrows);

    int score = 0;
    int highScore = 0;
    JLabel scoreLabel = new JLabel("Score: " + score);
    JLabel highScoreLabel = new JLabel("High Score: " + highScore);

    JLabel currentPlayerLabel = new JLabel("Player: ");
    JLabel currentCaveLabel = new JLabel("Cave: ");

    JButton goN = new JButton();
    JButton goNE = new JButton();
    JButton goE = new JButton();
    JButton goSE = new JButton();
    JButton goS = new JButton();
    JButton goSW = new JButton();
    JButton goW = new JButton();
    JButton goNW = new JButton();

    JPanel miniMap;

    JLabel alerts = new JLabel("Alerts");
    
    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public UI2(){
        //set frame behavior
        setTitle("UI2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setLayout(new MigLayout());
        
        //change icon of frame
        ImageIcon icon = new ImageIcon("wumpus4.png");
        setIconImage(icon.getImage());

        //add menu and menuitems
        {
            //adding items to menu
            menuBar.add(menu);
            

            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            }); 
            menu.add(exit);

            startNewGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    startNewGame();
                }
            }); 
            menu.add(startNewGame);

            //adding menubar to frame
            setJMenuBar(menuBar);
        }

        //adding labels
        {
            add(scoreLabel);
            add(highScoreLabel);
            add(currentCaveLabel);
            add(arrowLabel, "wrap");
            add(currentPlayerLabel, "wrap");
        }

        //adding movement buttons
        {
            JButton[] movementButtons = {goNW, goN, goNE, goSW, goS, goSE};
            ImageIcon[] movementIcons = {new ImageIcon("UI/left_top.png"),new ImageIcon("UI/top_mid.png"),new ImageIcon("UI/right_top.png"),
                                       new ImageIcon("UI/left_bottom.png"),new ImageIcon("UI/bottom_mid.png"),new ImageIcon("UI/right_bottom.png")};

            for(int i = 0; i < 6; i++){
                JButton cur = movementButtons[i];
                cur.setIcon(movementIcons[i]);
                if(i == 2) add(cur, "wrap,grow");
                else add(cur, "grow");
            }
        }

        //adding purchasing, alerts, and shooting
        {
            add(buyArrows, "cell 3 2,flowy, w 500px, growy");
            add(buySecrets, "cell 3 2, w 500px, growy");
            add(shoot, "cell 3 2, w 500px, growy");
            alerts.setHorizontalAlignment(JLabel.CENTER);
            add(alerts, "cell 3 2, w 500px, growy");

        }

        //add minimap
        {
            Cave cave = new Cave();
            miniMap = cave.drawMiniMap(34);
            miniMap.setSize(200, 1000);
            add(miniMap, "cell 3 3, grow");
        }

        //add new font
        {
            Font montserratBold = null;
            try{
                montserratBold = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Montserrat\\Montserrat-Bold.ttf"));
            } catch(Exception e){}

            Font size10bold = montserratBold.deriveFont(Font.PLAIN, 10);
            changeFont(this, size10bold);
        }

        repaint();
        revalidate();
        setResizable(false);
        setVisible(true);
    }

    private void goNW(){
        System.out.println("going NW");
    }

    private void goN(){
        System.out.println("going N");
    }

    private void goNE(){
        System.out.println("going NE");
    }

    private void goSW(){
        System.out.println("going SW");
    }

    private void goS(){
        System.out.println("going S");
    }

    private void goSE(){
        System.out.println("going SE");
    }

    public void startNewGame(){
        System.out.println("starting new game");
    }

    public void move(int room){
        System.out.println("player moving to " + room);
    }

    public void displayNearbyRooms(){
        System.out.println("showing nearby rooms");
    }

    public void updateHighScore(){
        System.out.println("updating high score");
    }

    public void displayHazards(){
        System.out.println("Displaying hazards");
    }

    public void shoot(int room){
        System.out.println("shooting to " + room);
    }

    public void purchaseArrows(){
        System.out.println("buy arrows");
        p.addArrows();
        arrowLabel.setText("Arrows: " + p.getArrows());
    }

    public void purchaseSecrets(){
        System.out.println("buy secrets");
    }

    public static void changeFont (Component component, Font font ){
        component.setFont(font);
        if (component instanceof Container)
        {
            for (Component child : ((Container) component).getComponents())
            {
                changeFont (child, font);
            }
        }
    }
}
