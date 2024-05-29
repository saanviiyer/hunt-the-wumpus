package HighScore;

public class HighScoreTest {

    public static void testResetScores() {
        HighScore highScore = new HighScore();
        highScore.resetScores();
        if (highScore.getScore() == 0) {
            System.out.println("testResetScores: Success");
        } else {
            System.out.println("testResetScores: Failure");
        }
    }

    public static void testIncrementScore() {
        HighScore highScore = new HighScore();
        highScore.incrementScore(10);
        if (highScore.getScore() == 10) {
            System.out.println("testIncrementScore: Success");
        } else {
            System.out.println("testIncrementScore: Failure");
        }
    }

    public static void testDecrementScore() {
        HighScore highScore = new HighScore();
        highScore.incrementScore(10); // Increment first to avoid direct reset
        highScore.decrementScore(5);
        if (highScore.getScore() == 5) {
            System.out.println("testDecrementScore: Success");
        } else {
            System.out.println("testDecrementScore: Failure");
        }
    }

    public static void testDecrementScoreBelowZero() {
        HighScore highScore = new HighScore();
        highScore.decrementScore(1); // This should reset the score to 0 due to negative value
        if (highScore.getScore() == 0) {
            System.out.println("testDecrementScoreBelowZero: Success");
        } else {
            System.out.println("testDecrementScoreBelowZero: Failure");
        }
    }

    public static void testGetScore() {
        HighScore highScore = new HighScore();
        if (highScore.getScore() == 0) {
            System.out.println("testGetScore: Success");
        } else {
            System.out.println("testGetScore: Failure");
        }
    }

    public static void main(String[] args) {
        testResetScores();
        testIncrementScore();
        testDecrementScore();
        testDecrementScoreBelowZero();
        testGetScore();
    }
}
