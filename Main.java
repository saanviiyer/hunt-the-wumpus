import Player.*;
import Trivia.*;
import UI.*;
import javax.swing.UIManager;
import GameControl.*;
import HighScore.*;
import Sound.*;
import Wumpus.*;

public class Main{
    public static void main(String[] args){
        System.out.println("Hello Bumpell");

        //Cave cave = new Cave();
        HighScore highScore = new HighScore();
        Player player = new Player();
        Sound sound = new Sound();

        
        UI ui = new UI();
        
        Wumpus wumpus = new Wumpus();

        //cave.draw(ui);

        
    }
}