package coen275TeamProjectGUI;

import javax.swing.*;
import java.awt.*;

public class SudokuCell extends JButton {
    private int[] position={0,0};
    public SudokuCell(){
        this.setSize(50,50);
        Font font = new Font("",2,24);
        this.setFont(font);
        this.setBackground(new Color(255,153,102));
        this.setForeground(Color.BLUE);
    }

    public SudokuCell setPosition(int row, int col){
        position[0] = row;
        position[1]= col;
        return this;
    }

    public int[] getPosition(){
        return position;
    }
}
