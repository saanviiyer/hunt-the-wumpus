import Player.*;
import Trivia.*;
import UI.*;

public class Main{
    public static void main(String[] args){
        System.out.println("Hello Bumpell");

        Cave cave = new Cave();
        GameControl curGame = new GameControl();
        HighScore highScore = new HighScore();
        Player player = new Player();
        Sound sound = new Sound();
        Trivia trivia = new Trivia();
        UI ui = new UI();
        Wumpus wumpus = new Wumpus();
        curGame.initBoard();
    }
}