package coen275TeamProjectGUI;

import javax.swing.*;
import java.awt.color.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SudokuBoard extends JPanel implements MouseListener {

    SudokuCell[][] sudokuCells ;
    int [][] maps = new int[9][9];
    int [][] origMaps = new int[9][9];
    boolean [][] flagArray = new boolean[9][9];
    private SelectNumFrame selectNumFrame;
    private SudokuArray sudokuArray;
    private boolean isFinshed = false;

    public boolean getIsfinshed(){
        return this.isFinshed;
    }

    public SudokuBoard(){
        //set up the board
    	sudokuArray = new SudokuArray(1);
    	maps = sudokuArray.getArray().clone();
    	copyArray(origMaps,sudokuArray.getArray());
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
                    flagArray[i][j] = true;
                }else{
                    sudokuCells [i][j].addMouseListener(this);
                    flagArray[i][j] = false;
                }
                this.add(sudokuCells [i][j]);
            }
        }
        this.setVisible(true);

    }
    
    public SudokuBoard(int n){
        //set up the board
    	System.out.println("constructor n worked");
        sudokuArray = new SudokuArray(n);
    	maps = sudokuArray.getArray().clone();
    	copyArray(maps,sudokuArray.getArray());
    	copyArray(origMaps, sudokuArray.getArray());
//    	System.out.println("\n Cloned array: ");
//    	
//    	//TODO testing only to print the array, see if they're the same
//    	for (int i=0; i<9; i++){
//    		System.out.print("\n");
//    		for (int j=0; j<9; j++){
//    			System.out.print("\t" + maps[i][j]);
//    		}
//    	}
    	//origMaps = sudokuArray.getArray().clone();	//keep original puzzle for when "Clear" button is clicked
        //initMap(maps);
        this.setLayout(null);
        sudokuCells  = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                sudokuCells [i][j] = new SudokuCell();
                sudokuCells [i][j].setLocation(20 + j * 50 + (j / 3) * 5, 20 + i * 50
                        + (i / 3) * 5);
                //set cell row and col.
                sudokuCells[i][j].setPosition(i,j);
                if(maps[i][j]!= 0){
                    sudokuCells[i][j].setText("" + maps[i][j]);
                    flagArray[i][j] = true;
                }else{
                	//sudokuCells[i][j].setBackground(Color.LIGHT_GRAY);	//set background color of empty cells to light gray
                	sudokuCells[i][j].setBackground(new Color(240, 125, 125));
                	sudokuCells[i][j].setOpaque(true);
                	sudokuCells [i][j].addMouseListener(this);
                	flagArray[i][j] = false;
                }
                this.add(sudokuCells [i][j]);
            }
        }
        this.setVisible(true);

    }

//    //used to test
//    public void initMap(int[][] maps) {
//        String[][] map = {
//                {"5","3",".",".","7",".",".",".","."},
//                {"6",".",".","1","9","5",".",".","."},
//                {".","9","8",".",".",".",".","6","."},
//                {"8",".",".",".","6",".",".",".","3"},
//                {"4",".",".","8",".","3",".",".","1"},
//                {"7",".",".",".","2",".",".",".","6"},
//                {".","6",".",".",".",".","2","8","."},
//                {".",".",".","4","1","9",".",".","5"},
//                {".",".",".",".","8",".",".","7","9"},
//
//        };
//
//        for(int i = 0 ; i< 9; i++){
//            for(int j =0; j < 9; j++){
//                if(!map[i][j].equals(".")){
//                    maps[i][j] = Integer.parseInt(map[i][j]);
//                }else{
//                    maps[i][j] = 0;
//                }
//            }
//        }
//
//
//    }

    public void reloadBoardPrevious(int[][] origMaps){
        //int[][] tmps = new int[9][9];
        //initMap(tmps);
        this.setLayout(null);
        maps = sudokuArray.getArray().clone(); //reset maps to delete all existing values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.remove(sudokuCells[i][j]);
                sudokuCells [i][j] = new SudokuCell();
                sudokuCells [i][j].setLocation(20 + j * 50 + (j / 3) * 5, 20 + i * 50
                        + (i / 3) * 5);
                //set cell row and col.
                sudokuCells[i][j].setPosition(i,j);
                if(origMaps[i][j]!= 0){
                    sudokuCells[i][j].setText("" + origMaps[i][j]);
                }else{
                	sudokuCells[i][j].setBackground(new Color(240, 125, 125));
                	sudokuCells[i][j].setOpaque(true);
                	sudokuCells [i][j].addMouseListener(this);
                }
                this.add(sudokuCells [i][j]);
            }
        }
        this.repaint();
    }
    
    
    public void reloadBoardToInitState(){
        int[][] tmps = new int[9][9];
        //initMap(tmps);
        this.setLayout(null);
        maps = sudokuArray.getArray().clone(); //reset maps to delete all existing values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.remove(sudokuCells[i][j]);
                sudokuCells [i][j] = new SudokuCell();
                sudokuCells [i][j].setLocation(20 + j * 50 + (j / 3) * 5, 20 + i * 50
                        + (i / 3) * 5);
                //set cell row and col.
                sudokuCells[i][j].setPosition(i,j);
                if(origMaps[i][j]!= 0){
                    sudokuCells[i][j].setText("" + origMaps[i][j]);
                }else{
                	sudokuCells[i][j].setBackground(new Color(240, 125, 125));
                	sudokuCells[i][j].setOpaque(true);
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
        int[][] previous = new int[9][9];
        copyArray(previous, maps);
        if ((modes & InputEvent.BUTTON3_MASK) != 0) {// click the right.
            // clean the cell
            ((SudokuCell) e.getSource()).setText("");
            maps[((SudokuCell) e.getSource()).getPosition()[0]][((SudokuCell) e.getSource()).getPosition()[1]] = 0;
            //CheckSolution.isValid(maps,((SudokuCell) e.getSource()).getPosition()[0],((SudokuCell) e.getSource()).getPosition()[1],)
            //previous[((SudokuCell) e.getSource()).getPosition()[0]][((SudokuCell) e.getSource()).getPosition()[1]]=0;
            //reloadBoardPrevious(maps);
            CheckSolution.checkDuplicated(maps,sudokuCells);
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
            ((SudokuCell) e.getSource()).setForeground(Color.DARK_GRAY);
            // show the numbers
            selectNumFrame.setVisible(true);
            if(selectNumFrame.getcell().getPosition()!= null & ((SudokuCell) e.getSource()).getText()!=""){

            maps[selectNumFrame.getcell().getPosition()[0]][selectNumFrame.getcell().getPosition()[1]] =
                    Integer.parseInt(((SudokuCell) e.getSource()).getText());
           // sudokuArray.updateArray(maps);
           //testing only
            System.out.println("You entered: " + maps[selectNumFrame.getcell().getPosition()[0]][selectNumFrame.getcell().getPosition()[1]]);
            CheckSolution.checkDuplicated(maps,sudokuCells);
            if (!(CheckSolution.isValid(maps, selectNumFrame.getcell().getPosition()[0], selectNumFrame.getcell().getPosition()[1],
            			maps[selectNumFrame.getcell().getPosition()[0]][selectNumFrame.getcell().getPosition()[1]],sudokuCells))){
            	    
            		System.out.println("Duplicate somewhere");
            		//CheckSolution.isSolutionComplete(flagArray);
       
            }//end if subGrid
            else
            {
            	flagArray[selectNumFrame.getcell().getPosition()[0]][selectNumFrame.getcell().getPosition()[1]] = true;
            	//CheckSolution.isSolutionComplete(flagArray);
            } //end else
//             if(CheckSolution.isSolutionComplete(flagArray)){
//                isFinshed = true;
//             }
            
            //end testing
            }
            
            //System.out.println("Element 1, 1 is: " + maps[1][1]);
        	

        }
        //sudokuArray.printArray();
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
    
    public int[][] getUpdatedArray()
    {
    	return maps;
    }

    public int[][] getOrigArray()
    {
    	return origMaps;
    }
    
    public void printUpdatedArray()
    {
    	System.out.println("\nSolution button clicked\nUpdated array is: \n");
		for (int i = 0; i<9; i++) {
			System.out.print("\n");
			for (int j = 0; j<9; j++){
				System.out.print("\t" + maps[i][j]);
			}
		}
    }
    
    public void copyArray(int[][] origMaps, int[][] maps)
    {
    	for(int i = 0; i < maps.length; ++i)
    	{
    		for(int j = 0; j < maps.length; ++j)
    		{
    			origMaps[i][j] = maps[i][j];
    		}
    	}
    }
   
    
}