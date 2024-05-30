package UI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.*;

import Trivia.Question;
import net.miginfocom.swing.MigLayout;

public class TriviaUI extends JFrame{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    JLabel heading = new JLabel("TRIVIA TIME:");

    CardLayout crd = new CardLayout();

    JPanel trivia = new JPanel();

    ButtonGroup answers = new ButtonGroup();
    int diameter = 75;
    CirclePanel[] indicators = {new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter)};

    JLabel question = new JLabel("Q1: blah blah blah");

    JRadioButton[] answerButtons = {new JRadioButton(), new JRadioButton(), new JRadioButton(), new JRadioButton()};

    JButton submit = new JButton("Submit");

    int currentQuestion = 0;

    Question[] questions;

    int numCorrectAnswers = 0;

    JPanel endScreen = new JPanel();
    JLabel endScreenText = new JLabel();
    JButton continueButton = new JButton("Continue");

    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public TriviaUI(Question[] questions){
        //set jframe behavior
        setTitle("Trivia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setLayout(crd);

        this.questions = questions;



        //create font
        Font montserratBold = null;
        try{
            montserratBold = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Montserrat\\Montserrat-Bold.ttf"));
        } catch(Exception e){}

        //setting trivia panel behavior
        {
            trivia.setSize(800, 800);
            trivia.setLayout(new MigLayout("","",""));
            trivia.setBackground(new Color(255, 222, 89));
        }

        //add heading
        {
            Font font = montserratBold.deriveFont(Font.PLAIN, 60);
            heading.setOpaque(true);
            heading.setHorizontalAlignment(JLabel.CENTER);
            heading.setFont(font);
            heading.setBackground(new Color(0,191,99));
            trivia.add(heading, "north, align center, push, wrap");
        }
        
        //add question indicators
        {
            JPanel circles = new JPanel(new FlowLayout());
            circles.setLayout(new GridLayout(1,0));

            for(CirclePanel indicator : indicators){
                indicator.setPreferredSize(new Dimension(75, 75));
                circles.add(indicator);
            }

            circles.setBackground(Color.WHITE);
            trivia.add(circles, "wrap, push, grow");
        }

        //adding question, buttons
        {
            Font font = montserratBold.deriveFont(Font.PLAIN, 30);
            question.setFont(font);
            // question.setHorizontalAlignment(JLabel.CENTER);
            trivia.add(question, "wrap");

        

            for(int i = 0; i < 4; i++){
                JRadioButton button = answerButtons[i];
                answers.add(button);
                button.setBackground(new Color(255, 222, 89));
                button.setFont(font);
                trivia.add(button, "wrap, push");
            }
        }

        //add submit button
        {
            Font font = montserratBold.deriveFont(Font.PLAIN, 30);
            submit.setFont(font);
            submit.setBackground(Color.WHITE);
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    System.out.println(getSelectedButton().getText().substring(0,1));
                    checkAnswer();
                    answers.clearSelection();
                }
            });
            trivia.add(submit, "grow,push");
        }
        
        add(trivia); //trivia becomes first card

        //setting endscreen panel behavior
        {
            endScreen.setSize(800,800);
            endScreen.setLayout(new MigLayout());
            endScreen.setBackground(new Color(255, 222, 89));
        }

        //add components to endscreen
        {
            endScreenText.setFont(montserratBold.deriveFont(Font.PLAIN, 30));
            endScreenText.setHorizontalAlignment(JLabel.CENTER);
            endScreen.add(endScreenText, "center, push, flowy");

            continueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    endTrivia();
                }
            });
            continueButton.setFont(montserratBold.deriveFont(Font.PLAIN, 30));
            continueButton.setHorizontalAlignment(JButton.CENTER);
            continueButton.setBackground(Color.WHITE);
            endScreen.add(continueButton, "center, push, cell 0 0");
        }

        add(endScreen); //endscreen becomes second card

        loadQuestion(); //initialize first question

        // setUndecorated(true);
        // setResizable(false);
        setLocationRelativeTo(null); //centers jframe
        setVisible(true);
    }

    //////////////////////
    //////// METHODS /////
    //////////////////////
    public AbstractButton getSelectedButton(){
        for(Enumeration<AbstractButton> buttons = answers.getElements(); buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();

            if(button.isSelected()) return button;
        }
        return null;
    }

    public void checkAnswer(){
        AbstractButton selectedButton = getSelectedButton();

        if(selectedButton == null) return;
        else if(selectedButton == answerButtons[questions[currentQuestion].getCorrectAnswer()]){
            indicators[currentQuestion].setCircleColor(Color.GREEN);
            numCorrectAnswers++;
        } else {
            indicators[currentQuestion].setCircleColor(Color.RED);
        }
            
        currentQuestion++;
        if(currentQuestion >= 5) {
            endScreenText.setText("You got " + numCorrectAnswers + "/5 answers correct.");
            crd.next(getContentPane());
            return;
        } 

        loadQuestion();
    }

    public void loadQuestion(){
        Question q = questions[currentQuestion];
        question.setText("Q" + (currentQuestion+1) + ": " + q.getQuestionText());
        
        String[] possibleAnswers = q.getOptions();
        char[] letters = {'A', 'B', 'C', 'D'};
        for(int i = 0; i < 4; i++){
            answerButtons[i].setText(letters[i] + ") " + possibleAnswers[i]);
        }
    }

    private int endTrivia(){
        dispose();
        return numCorrectAnswers;
    }
}
