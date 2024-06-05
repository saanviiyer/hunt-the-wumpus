package HighScore;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
// Yana Ivanov
// Period 5
// Feb 2024
// High Score Management Object



// order: coins wumpus turns arrows name

public class HighScore {
    private ArrayList<Score> scores = new ArrayList<Score>();
    private ScoreComparator comp = new ScoreComparator();
    public HighScore() throws FileNotFoundException{
        Scanner fin = new Scanner(new File("HighScores.csv"));
        //fin.useDelimiter(",|\n");
        while (fin.hasNextLine()){
            scores.add(new Score(fin.nextLine().split(",")));
        }
        scores.sort(comp);
        fin.close();
    }

    

    public void save() throws IOException{
        PrintWriter fout = new PrintWriter(new File("HighScores.csv"));
        for (int i = 0; i < Math.min(10,this.scores.size()); i++){
            fout.write(this.scores.get(i).toString());
        }
        fout.close();
    }

    public void add(Score s){
        int i = this.find(s.getPlayer());
        if (i != -1 && this.comp.compare(s, this.scores.get(i)) > 0) this.scores.set(i, s);
        else if (i == -1) this.scores.add(s);
        this.scores.sort(this.comp);
    }

    public int find(String name){
        for (int i = 0; i < this.scores.size(); i++){
            if (this.scores.get(i).getPlayer().equals(name)) return i;
        }
        return -1;
    }

    public void resetScores() {
        System.out.println("Resetting scores.");
        this.scores = new ArrayList<Score>();
    }



    // For Trivia, have public void streakScore() where
    // correctStreak is initialized and then incremented by Trivia 
    // depending on how many Q's have been answered correct in a row, 
    // then make that the exponent for Math.pow(score, correctStreak)
    // If streak is broken in Trivia, it is set to 0 so score won't benefit
    // from method when user get question right again after

    public ArrayList<Score> getScores() {
        return this.scores;
    }

}