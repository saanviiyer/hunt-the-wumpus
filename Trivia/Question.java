public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options; 
        this.correctAnswer = correctAnswer; 
    }

    public String getQuestionText() {
        return questionText; 
    }

    public String getOptions() {
        return options;    
    }

    public Int getCorrectAnswer() {
        return correctAnswer;
    }

    public Boolean isCorrect(int answer) {
        return (answer = correctAnswer);
    }
}