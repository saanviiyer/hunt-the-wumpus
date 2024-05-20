package Trivia;

public class Question {
    private String question;
    private String answer;
    int points;
    boolean correct;

    public Question(String q, String a) {
        this.correct = false;
        this.points = 100;
        this.question = q;
        this.answer = a;
    }

    // public void setQuestion(String q) {
    //     this.question = q;
    // }

    // public void setAnswer(String a) {
    //     this.answer = a;
    // }

    public void answerQ(String a) {
        if (a == answer) {
            this.correct = true;
        }
    }
}
