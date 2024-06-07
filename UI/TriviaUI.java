package UI;

/*
 * Last Editor(s): Nathan Chiu,Pavan Anoop
 * Last Edit @ 05-30-2024
 */



import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.*;

import Trivia.Question;
import net.miginfocom.swing.MigLayout;

public class TriviaUI extends JDialog{
    //////////////////////
    //// PROPERTIES  /////
    //////////////////////
    JLabel heading = new JLabel("TRIVIA TIME:");

    CardLayout crd = new CardLayout();

    JPanel trivia = new JPanel();

    ButtonGroup answers = new ButtonGroup();
    int diameter = 75;
    circlepanel[] indicators;

    JEditorPane question = new JEditorPane();

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


    public TriviaUI(Question[] questions, JFrame frame, boolean inPit){
        super(frame, true);
        
        //set jdialog behavior
        setTitle("Trivia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setLayout(crd);


        this.questions = questions;



        //create font
        Font legendOfZeldaFont = null;
        try{
            legendOfZeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\LoZ_Font\\the-legend-of-zelda-nes.ttf"));
        } catch(Exception e){}

        //setting trivia panel behavior
        {
            trivia.setSize(800, 800);
            trivia.setLayout(new MigLayout("","",""));
            trivia.setBackground(Color.GRAY);
        }

        //add heading
        {
            Font font = legendOfZeldaFont.deriveFont(Font.PLAIN, 60);
            heading.setOpaque(true);
            heading.setHorizontalAlignment(JLabel.CENTER);
            heading.setFont(font);
            heading.setBackground(Color.GRAY);
            heading.setForeground(Color.BLACK);
            if(inPit) heading.setText("Pit Time:");
            trivia.add(heading, "north, align center, push, wrap, grow, h 100px");
        }
        
        //add circle indicators
        {
            JPanel circles = new JPanel();
            circles.setLayout(new GridLayout(1,0));

            indicators = new circlepanel[questions.length];

            for(int i = 0; i < questions.length; i++){
                indicators[i] = new circlepanel(75);
                indicators[i].setPreferredSize(new Dimension(75, 75));
                circles.add(indicators[i]);
                            
            }

            circles.setBackground(Color.WHITE);
            trivia.add(circles, "wrap, push, grow");
        }

        //adding question, buttons
        {
            Font font = legendOfZeldaFont.deriveFont(Font.PLAIN, 30);
            question.setFont(font);
            question.setForeground(Color.BLACK);
            question.setEditable(false);
            question.setFocusable(false);
            question.setBackground(Color.GRAY);
            trivia.add(question, "wrap, pushx, growx");

        

            for(int i = 0; i < 4; i++){
                JRadioButton button = answerButtons[i];
                answers.add(button);
                button.setBackground(Color.GRAY);
                button.setForeground(Color.BLACK);
                button.setFont(font);
                trivia.add(button, "wrap, push");
            }
        }

        //add submit button
        {
            submit.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 30));
            submit.setBackground(Color.WHITE);
            submit.setBorder(null);
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
            endScreen.setBackground(Color.GRAY);
        }

        //add components to endscreen
        {
            endScreenText.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 20));
            endScreenText.setHorizontalAlignment(JLabel.CENTER);
            endScreenText.setForeground(Color.BLACK);
            endScreen.add(endScreenText, "center, push, flowy");

            continueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    endTrivia();
                }
            });
            continueButton.setFont(legendOfZeldaFont.deriveFont(Font.PLAIN, 30));
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
        if(currentQuestion >= questions.length) {
            endScreenText.setText("You got " + numCorrectAnswers + "/" + questions.length + " answers correct.");
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
        setVisible(false);
        dispose();
        return numCorrectAnswers;
    }

    public int getNumCorrectAnswers(){
        return numCorrectAnswers;
    }
}
