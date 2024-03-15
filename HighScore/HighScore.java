// Yana Ivanov
// Period 5
// Feb 2024
// High Score Management Object

public class HighScore {
    private int score;

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

    public int getScore() {
        return this.score;
    }

}