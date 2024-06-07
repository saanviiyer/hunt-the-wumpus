
/*
 * Last Editor(s): Shunzo Hida
 * Last Edit @ 06-07-2024
 */



package HighScore;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        return -1*Integer.compare(a.value(), b.value());
    }
}
