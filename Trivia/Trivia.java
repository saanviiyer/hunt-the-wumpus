// Saanvi Subramanian

package Trivia;
import java.util.Random;
import java.util.ArrayList;

public class Trivia {

    Random r = new Random();

    ArrayList<Question> questions = new ArrayList<>();

    public Trivia() {
    }

    public Question getQuestion() {
        int rand = r.nextInt(questions.size());
        return questions.get(rand);
    }
}