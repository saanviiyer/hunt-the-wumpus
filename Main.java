/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 05-30-2024
 */



import Player.*;
import Trivia.*;
import UI.*;

import java.io.FileNotFoundException;

import javax.swing.UIManager;
import HighScore.*;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Hello Bumpell");

        //Cave cave = new Cave();
        HighScore highScore = new HighScore();
        Player player = new Player("");
        UI ui = new UI();

        //cave.draw(ui);

        
    }
}