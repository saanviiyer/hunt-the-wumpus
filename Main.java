import Player.*;
// import Trivia.*;
import UI.*;
import Cave.*;
import javax.swing.UIManager;

public class Main{
    public static void main(String[] args){
        System.out.println("Hello Bumpell");

        Cave cave = new Cave();
        GameControl curGame = new GameControl();
        HighScore highScore = new HighScore();
        Player player = new Player();
        Sound sound = new Sound();

        Question[] questions = new Question[1];
        String[] answers = {"2034","4859","2024","2023"};
        questions[0] = new Question("what is the year", answers, 2);
        TriviaGameGUI trivia = new TriviaGameGUI(questions);
        
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            System.out.println("laf changed to " + UIManager.getLookAndFeel());
        } catch(Exception ignored){}
        UI ui = new UI();
        
        Wumpus wumpus = new Wumpus();
        curGame.initBoard();

        //cave.draw(ui);

        
    }
}