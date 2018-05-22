package coen275TeamProjectGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuBoard extends JPanel implements MouseListener {

    SudokuCell[][] sudokuCells ;
    int [][] maps = new int[9][9];
    private SelectNumFrame selectNumFrame;

    public SudokuBoard(){
        //set up the board
        initMap(maps);
        this.setLayout(null);
        sudokuCells  = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                sudokuCells [i][j] = new SudokuCell();
                sudokuCells [i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50
                        + (j / 3) * 5);
                //set cell row and col.
                sudokuCells[i][j].setPosition(i,j);
                if(maps[i][j]!= 0){
                    sudokuCells[i][j].setText("" + maps[i][j]);
                }else{
                    sudokuCells [i][j].addMouseListener(this);
                }
                this.add(sudokuCells [i][j]);
            }
        }
        this.setVisible(true);

    }

    //used to test
    public void initMap(int[][] maps) {
        String[][] map = {
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"},

        };

        for(int i = 0 ; i< 9; i++){
            for(int j =0; j < 9; j++){
                if(!map[i][j].equals(".")){
                    maps[i][j] = Integer.parseInt(map[i][j]);
                }else{
                    maps[i][j] = 0;
                }
            }
        }


    }

    public void reloadBoardToInitState(){
        int[][] tmps = new int[9][9];
        initMap(tmps);
        this.setLayout(null);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.remove(sudokuCells[i][j]);
                sudokuCells [i][j] = new SudokuCell();
                sudokuCells [i][j].setLocation(20 + i * 50 + (i / 3) * 5, 20 + j * 50
                        + (j / 3) * 5);
                //set cell row and col.
                sudokuCells[i][j].setPosition(i,j);
                if(tmps[i][j]!= 0){
                    sudokuCells[i][j].setText("" + tmps[i][j]);
                }else{
                    sudokuCells [i][j].addMouseListener(this);
                }
                this.add(sudokuCells [i][j]);
            }
        }
        this.repaint();
    }

    //check is the user finishe the game(use to stop the timer)
    public boolean IsFinishedGame(){
        return false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int modes = e.getModifiers();
        if ((modes & InputEvent.BUTTON3_MASK) != 0) {// click the right.
            // clean the cell
            ((SudokuCell) e.getSource()).setText("");
        } else if ((modes & InputEvent.BUTTON1_MASK) != 0) {// click the left
            if (selectNumFrame != null) {
                selectNumFrame.dispose();
            }
            selectNumFrame = new SelectNumFrame();
            selectNumFrame.setModal(true);

            // set the location where ths selectNumframe is.
            selectNumFrame.setLocation(e.getLocationOnScreen().x,
                    e.getLocationOnScreen().y);
            selectNumFrame.setCell((SudokuCell) e.getSource());
            ((SudokuCell) e.getSource()).setForeground(Color.RED);
            // show the numbers
            if(selectNumFrame.getcell().getPosition()!= null & ((SudokuCell) e.getSource()).getText()!=""){
            maps[selectNumFrame.getcell().getPosition()[0]][selectNumFrame.getcell().getPosition()[1]] =
                    Integer.parseInt(((SudokuCell) e.getSource()).getText());
            }
            selectNumFrame.setVisible(true);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}