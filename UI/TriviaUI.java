package UI;

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

    ButtonGroup answers = new ButtonGroup();
    int diameter = 75;
    CirclePanel[] indicators = {new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter),new CirclePanel(diameter)};

    JLabel question = new JLabel("Q1: blah blah blah");

    JRadioButton ans1 = new JRadioButton("A)");
    JRadioButton ans2 = new JRadioButton("B)");
    JRadioButton ans3 = new JRadioButton("C)");
    JRadioButton ans4 = new JRadioButton("D)");
    JRadioButton[] answerButtons = {ans1, ans2, ans3, ans4};

    JButton submit = new JButton("Submit");

    int currentQuestion = 0;

    Question[] questions;

    //////////////////////
    //// CONSTRUCTOR /////
    //////////////////////
    public TriviaUI(Question[] questions){
        setTitle("Trivia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setLayout(new MigLayout(
            "",
            "",
            ""
        ));
        getContentPane().setBackground(new Color(255, 222, 89));

        this.questions = questions;

        //create font
        Font montserratBold = null;
        try{
            montserratBold = Font.createFont(Font.TRUETYPE_FONT, new File("UI\\Montserrat\\Montserrat-Bold.ttf"));
        } catch(Exception e){}

        //add heading
        {
            Font font = montserratBold.deriveFont(Font.PLAIN, 60);
            heading.setOpaque(true);
            heading.setHorizontalAlignment(JLabel.CENTER);
            heading.setFont(font);
            heading.setBackground(new Color(0,191,99));
            add(heading, "north, align center, w 800px, wrap");
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
            add(circles, "wrap, growx, h 125px");
        }


        //adding question, buttons
        {
            Font font = montserratBold.deriveFont(Font.PLAIN, 30);
            question.setFont(font);
            // question.setHorizontalAlignment(JLabel.CENTER);
            add(question, "wrap, growx");

            


            for(int i = 0; i < 4; i++){
                JRadioButton button = answerButtons[i];
                answers.add(button);
                button.setBackground(new Color(255, 222, 89));
                button.setFont(font);
                add(button, "wrap");
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
                }
            });
            add(submit, "growx");
        }


        repaint();
        revalidate();
        // setUndecorated(true);
        // setResizable(true);
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
        if(getSelectedButton() == answerButtons[questions[currentQuestion].getCorrectAnswer()]){
            indicators[currentQuestion].setCircleColor(Color.GREEN);
        } else {
            indicators[currentQuestion].setCircleColor(Color.RED);
        }
    }
}
