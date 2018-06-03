package coen275TeamProjectGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class SudokuGridFrame extends JFrame implements ActionListener {

    private JButton solution;
    private JButton clear;
    private JButton newGame;
    private SudokuBoard sudokuBoard;
    public Timer userTimeAction;
    public long usedTime = 0;
    private int diffculty;

    public SudokuGridFrame(){

          init();
    }

    public SudokuGridFrame(int n){
       this.diffculty = n;
       init();
       
  }
    public void init(){
        JPanel TimerPanel = new JPanel();
        JLabel level = new JLabel();
        level.setFont(new Font("Arial",Font.PLAIN, 30));
        level.setBounds(300, 5, 200, 100);
        TimerPanel.setBackground(new Color(251, 244, 249));
        if(this.diffculty == 1)
        {
        	level.setText("Easy");
        }
        else
        {
        	level.setText("Hard");
        }
        
        addBoard();
        addButtons();
        TimerPanel.setBounds(600,425,300,200);
        addTimerPanel(TimerPanel);
        this.setLayout(null);
        this.add(level);
        this.add(TimerPanel);
        this.setVisible(true);
        this.setSize(900,700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(251, 244, 249));

    }


    public void addBoard(){
        sudokuBoard = new SudokuBoard(this.diffculty);
        sudokuBoard.setBounds(75,75,500,500);
        sudokuBoard.setBackground(Color.pink);
        sudokuBoard.setOpaque(true);
        this.add(sudokuBoard);
    }

    public void removeboard(){
        this.remove(sudokuBoard);
    }



    public void addButtons(){
        solution = new JButton("Solution");
        solution.setBounds(625,75,100,50);
        solution.addActionListener(this);
        clear = new JButton("Clear");
        clear.setBounds(625,200,100,50);
        clear.addActionListener(this);
        newGame = new JButton("New");
        newGame.setBounds(625,325,100,50);
        newGame.addActionListener(this);
        //this.add(solution);
        this.add(clear);
        this.add(newGame);
        this.setLocation(250, 70);
    }

    public int getElapsedTime(int startTime,int endTime){

        return endTime - startTime;
    }

    public void addTimerPanel(JPanel  panelComponent){

            JPanel panelTime = new JPanel();
            panelTime.setBackground(new Color(251, 244, 249));
            panelTime.setBorder(new TitledBorder("Timer"));
            panelTime.setLayout(new GridLayout(2, 1));

            final JLabel lbSysTime = new JLabel();
            final JLabel lbUserTime = new JLabel();

            panelTime.add(lbSysTime, BorderLayout.NORTH);
            panelTime.add(lbUserTime, BorderLayout.SOUTH);

            // sett up system time
            Timer sysTimeAction = new Timer(500, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    long timeMillis = System.currentTimeMillis();
                    SimpleDateFormat df = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    lbSysTime.setText("   SystemTime " + df.format(timeMillis));
                }
            });
            sysTimeAction.start();
            userTimeAction = new Timer(1000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    lbUserTime.setText("   TimeUsed  " + (++usedTime)+ " sec.");
                }
            });
            userTimeAction.start();

            panelComponent.add(panelTime, BorderLayout.SOUTH);


    }

    public boolean IsFinishFillCell(){
        /*
        * TODO
        * check board state.
        * if board state is that all cell has number that means finish the game.
        * then return true;*/
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == solution){
            /*TODO
            * show up up the solution*/
        	sudokuBoard.printUpdatedArray();
        }

        if(source == clear){
            /*
            * TODO
            * show initializedBoard*/
            sudokuBoard.reloadBoardToInitState();
        }

        if(source == newGame){
            this.dispose();
            new SelectGameFrame();
        }
        if(sudokuBoard.getIsfinshed()){
            this.dispose();
            new HighScoreFrame();
        }
    }

public static  void main(String[] args){
        SudokuGridFrame sudokuGridFrame =  new SudokuGridFrame();
}
}
