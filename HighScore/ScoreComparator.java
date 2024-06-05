package HighScore;
import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score a, Score b) {
        return Integer.compare(a.value(), b.value());
    }
}
