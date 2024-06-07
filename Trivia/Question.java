// Jack Gillis
// Trivia Game (Hunt the Wumpus)
// 2/12/2024
// Period 5

/*
 * Last Editor(s): Jack Gillis
 * Last Edit @ 06-3-2024
 */

/*
 * Info:
 * Testing Area for ./Cave
 */

package Trivia;
public class Question {
    // variables
    private String questionText;
    private String[] options;
    private int correctAnswer;

    // constructor
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    //  methods

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}
