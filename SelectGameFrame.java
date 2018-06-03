package coen275TeamProjectGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameFrame extends JFrame implements ActionListener {
    private int difficulty;
    //buttons
    private JButton easy;
    private JButton hard;
    private JButton highScore;

    private JLabel welcom;


    public SelectGameFrame(){
        //new buttons and set up
        easy = new JButton("EASY");
        easy.setBounds(250,150,300,100);
        hard = new JButton("HARD");
        hard.setBounds(250,350,300,100);
        highScore = new JButton("HIGH SCORE");
        highScore.setBounds(250,550,300,100);

        setLayout(null);
        add(easy);
        add(hard);
        add(highScore);

        easy.setBackground(new Color(143, 214, 195));
        easy.setContentAreaFilled(false);
        easy.setBorderPainted(false);
        easy.setOpaque(true);
        easy.addActionListener(this);

        hard.setBackground(new Color(200, 84, 0));
        hard.setContentAreaFilled(false);
        hard.setBorderPainted(false);
        hard.setOpaque(true);
        hard.addActionListener(this);

        highScore.setBackground(new Color(242, 221, 169));
        highScore.setContentAreaFilled(false);
        highScore.setBorderPainted(false);
        highScore.setOpaque(true);
        highScore.addActionListener(this);

        welcom = new JLabel();
        welcom.setText(" WELCOME TO SUDOKU GAME");
        welcom.setFont(new java.awt.Font("Dialog",   1,   18));
        welcom.setLayout(null);
        welcom.setBounds(260,50,400,100);

        add(welcom);
        setLayout(null);
        setSize(800,800);
        getContentPane().setBackground(new Color(251, 244, 249));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(300, 10);

    }


    public SelectGameFrame setDifficulty(int difficulty){
        this.difficulty = difficulty;
        return this;
    }

    public int getDifficulty(){
        return difficulty;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if( source == easy ){

            setDifficulty(1);
            //close current GUI
            this.dispose();
            //new SudokuGridFrame(1);	//TODO change after generating array
            new SudokuGridFrame(1);
        }
        if( source == hard ){
            setDifficulty(2);
            this.dispose();
            //new SudokuGridFrame(2);	//TODO change after generating array
            new SudokuGridFrame(2);
        }

        if(source == highScore ){
            /*
            * TODO
            * show the high score panel*/
        }
    }



    public static void main(String[] args){

        SelectGameFrame selectGameFrame = new SelectGameFrame();

    }
}
