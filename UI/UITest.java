//Nathan Chiu
//UI test
//Per. 5 
//Reiber

package UI;

import Trivia.Question;

class Test {

    public static void main(String[] args){
        String[] answers = {"1","2","3","4"};
        Question[] questions = {new Question("What is the year0???",answers , 0),
                                new Question("What is the year1???",answers , 1),
                                new Question("What is the year2???",answers , 2),
                                new Question("What is the year3???",answers , 3),
                                new Question("What is the year5???",answers , 0)};
        UI myUI = new UI();
        // TriviaUI trivia = new TriviaUI(questions);
    }

}