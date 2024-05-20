// Saanvi Subramanian

package Trivia;
import java.util.Random;
import java.util.ArrayList;

public class Trivia {

    Random r = new Random();

    Question[] questions = new Question[5];

    public Trivia() {
        questions[0] = new Question("How many teachers are there at BHS?", "102");
        questions[1] = new Question("What does CTE stand for in BHS?", "Career and Technical Education");
        questions[2] = new Question("Who is the principal of BHS?", "Juan Price");
        questions[3] = new Question("When was BHS built?", "1953");
        questions[4] = new Question("How many times has BHS Science Olympiad won the state championships?", "5");
    }

    public Question getQuestion() {
        int rand = r.nextInt(questions.size());
        return questions.get(rand);
    }
}