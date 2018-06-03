package coen275TeamProjectGUI;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class CheckSolution 
{
	public CheckSolution()
	{
		
	}

	public static void checkDuplicated(int[][] sudukuArray,SudokuCell[][] sudokuCells){
		//Set<Integer> set = new HashSet<>();
		for(int i = 0 ; i < 9;i++){
            for(int j = 0 ; j < 9;j++){
            	if(isValid(sudukuArray,i,j,sudukuArray[i][j],sudokuCells)){
            		sudokuCells[i][j].setForeground(Color.BLUE);
				}
			}
		   }

	}
	//check if element entered has a duplicate on the same row
	public static boolean isValid(int[][] sudokuArray, int row, int col, int element, SudokuCell[][] sudokuCells){
		boolean result = true;
		//check if there is duplicate in row
		for (int j=0; j<9; j++){
			if (j==col)
				continue;
			else{
				if (element == sudokuArray[row][j])
				{
					sudokuCells[row][j].setForeground(new Color(200,25,85));
					result = false;
				}
			}
		}
		
		//check if there is duplicate in column
		for (int i=0; i<9; i++){
			if (i==row)
				continue;
			else{
				if (element == sudokuArray[i][col])
				{
					sudokuCells[i][col].setForeground(new Color(200,25,85));
					result = false;
				}
					
			}
		}
		
		//check if there is duplicate in 3x3 sub Matrix
		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++){
			for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++){
				if ((i==row) && (j==col))
					continue;
				else
				{
					if (element == sudokuArray[i][j])
					{
						sudokuCells[i][j].setForeground(new Color(200,25,85));
						result = false;
					}
						
				}//end else
			}//end for j
		}//end for i
		
		return result;
	}
	
	//check if all inputs are valid
	public static void isSolutionComplete(boolean[][] flag)
	{
		boolean indivFlag = true;
		
		for(int i = 0; i < 9; ++i)
		{
			for(int j = 0; j < 9; ++j)
			{
				if(flag[i][j] == false)
				{
					indivFlag = false;
				}
			}
		}
		
		if(indivFlag == true)
		{
			JOptionPane.showMessageDialog(null, "You finished the game");
		}
	}
	
}
