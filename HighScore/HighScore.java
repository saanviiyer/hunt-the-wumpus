package HighScore;

// Yana Ivanov
// Period 5
// Feb 2024
// High Score Management Object

public class HighScore {
    private int score;
    private int correctStreak;

    public HighScore() {
        this.score = 0;    
    }

    public void resetScores() {
        System.out.println("Resetting scores.");
        this.score = 0;
    }

    public void incrementScore(int incrementScoreBy) {
        System.out.println("Incrementing score by " + incrementScoreBy); 
        this.score += incrementScoreBy;
    }
    
    public void decrementScore(int decrementScoreBy) {
        System.out.println("Decrementing score by " + decrementScoreBy); 
        this.score -= decrementScoreBy;
        if (this.score  < 0) {
            resetScores();
        }
    }

    // For Trivia, have public void streakScore() where
    // correctStreak is initialized and then incremented by Trivia 
    // depending on how many Q's have been answered correct in a row, 
    // then make that the exponent for Math.pow(score, correctStreak)
    // If streak is broken in Trivia, it is set to 0 so score won't benefit
    // from method when user get question right again after

    public int getScore() {
        return this.score;
    }

}