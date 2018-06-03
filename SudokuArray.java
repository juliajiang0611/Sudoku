package coen275TeamProjectGUI;

import java.security.SecureRandom;

public class SudokuArray {
	private int array[][];
	protected final int ARRAYLENGTH = 9;
	protected final int SUBARRAYLENGTH = 3;
	private int difficultyFlag = 0;
	private SecureRandom randomCellCount = new SecureRandom();
	private int cellCount;
	
	public SudokuArray(int flag){
		this.difficultyFlag = flag;
		this.array = new int[][]{ 	//initialize array
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
		};
		
		fillPartial();										// Fill the diagonal of SRN x SRN matrices
		fillRemaining(0, SUBARRAYLENGTH);
		//printArray(); //TODO testing only to check if array stays the same
		if (flag==1)
		{
			cellCount = 46 + randomCellCount.nextInt(5);
			generatePuzzle(cellCount);
		}
		else if (flag==2)
		{
			cellCount = 56 + randomCellCount.nextInt(5);
			generatePuzzle(cellCount);
		}
		//printArray(); //TODO testing only to check if the digits stayed in the same position
	}//end SudokuArray constructor
	
	//this method will fill the middle-sub-array portion of the grid 
	//first for optimal puzzle generation
	public void fillPartial()	
	{
		for (int i = 0; i<ARRAYLENGTH; i=i+SUBARRAYLENGTH)
			fillSubArray(i, i);	
	}//end fillpartial method
	
	//this method will fill a 3x3 sub-array to be used in fillPartial method
	public void fillSubArray(int row,int col) {
		SecureRandom randomDigit = new SecureRandom();
		int temp;
		for (int i=0; i<SUBARRAYLENGTH; i++) {
			for (int j=0; j<SUBARRAYLENGTH; j++) {
				do {
					temp = 1 + randomDigit.nextInt(ARRAYLENGTH);
				}
				while (!checkSubArray(row, col, temp));

				this.array[row+i][col+j] = temp;
			}
		}
	}//end fillBox method
	
	// Returns false if given 3 x 3 block contains num.
	private boolean checkSubArray(int rowStart, int colStart, int num) {
		for (int i = 0; i<SUBARRAYLENGTH; i++)
			for (int j = 0; j<SUBARRAYLENGTH; j++)
				if (this.array[rowStart+i][colStart+j]==num)
					return false;
		return true;
	}//end checkBox method
	
	// A recursive function to fill remaining matrix
	private boolean fillRemaining(int i, int j) {
		if (j>=ARRAYLENGTH && i<ARRAYLENGTH-1) {
			i = i + 1;
			j = 0;
		}
		if (i>=ARRAYLENGTH && j>=ARRAYLENGTH)
			return true;

		if (i < SUBARRAYLENGTH) {
			if (j < SUBARRAYLENGTH)
				j = SUBARRAYLENGTH;
		}
		else if (i < ARRAYLENGTH-SUBARRAYLENGTH) {
			if (j==(int)(i/SUBARRAYLENGTH)*SUBARRAYLENGTH)
				j = j + SUBARRAYLENGTH;
		}
		else {
			if (j == ARRAYLENGTH-SUBARRAYLENGTH) {
				i = i + 1;
				j = 0;
				if (i>=ARRAYLENGTH)
					return true;
			}
		}

		for (int num = 1; num<=ARRAYLENGTH; num++) {
			if (isValid(i, j, num)) {
				this.array[i][j] = num;
				if (fillRemaining(i, j+1))
					return true;

				this.array[i][j] = 0;
			}
		}
		return false;
	}//end fillRemaining method
	
	// this method will check if random digit is valid
	private boolean isValid(int i,int j,int digit) {
		return (checkRow(i, digit) &&
				checkCol(j, digit) &&
				checkSubArray(i-i%SUBARRAYLENGTH, j-j%SUBARRAYLENGTH, digit));
	}//end isValid method
	
	// this method will check if the digit has duplicate in the same row
	private boolean checkRow(int i,int digit) {
		for (int j = 0; j<ARRAYLENGTH; j++){
			if (this.array[i][j] == digit)
					return false;
		}
		return true;
	}//end checkRow method
	
	// check in the row for existence
	private boolean checkCol(int j,int digit) {
		for (int i = 0; i<ARRAYLENGTH; i++){
			if (this.array[i][j] == digit)
				return false;
		}
		return true;
	}//end checkCol method
	
	// Remove the K no. of digits to complete game
	private void generatePuzzle(int n) {
		SecureRandom randomCell = new SecureRandom();
		int count = n;
		while (count != 0) {
			int cellId = randomCell.nextInt(ARRAYLENGTH*ARRAYLENGTH);
			int i = (cellId/ARRAYLENGTH);
			int j = cellId%9;
			if (j != 0)
				j = j - 1;

			if (this.array[i][j] != 0) {
				count--;
				this.array[i][j] = 0;
			}//end if
		}//end while
	}//end generatePuzzle
	
	public int[][] getArray()
	{
		return this.array;
	}
	
	public void updateArray(int[][] n)
	{
		this.array = n.clone();
	}
	
	public void printArray()
	{
		System.out.println("Updated array is: \n");
		for (int i = 0; i<ARRAYLENGTH; i++) {
			System.out.print("\n");
			for (int j = 0; j<ARRAYLENGTH; j++){
				System.out.print("\t" + this.array[i][j]);
			}
		}
	}
	
}//end SudokuArray class
