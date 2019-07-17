//***********************************************************************************
// Authors:
// MUHAMMAD ZAHID BIN MUHAMAD YEW
// NUR SYAZWANI ATIFAH BINTI MUHAMMAD FAROUK
// NUR HIDAYAH BINTI BADRUN HISYAM
// NURUL ABIDAH BINTI HYDRUS
//***********************************************************************************


package com.chess.pieces;

import com.chess.Cell;
import java.util.ArrayList;

public class Bishop extends Pieces {
    //Constructor
    public Bishop(String i,String p,int c)
    {
        setId(i);
        setPath(p);
        setColour(c);
    }
	
    //move function defined. It returns a list of all the possible destinations of a Bishop
    //The basic principle of Bishop Movement on chess board has been implemented
    public ArrayList<Cell> move(Cell[][] state, int x, int y)
    {
        //Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
        //This function defines that logic
        possibleMoves.clear();
        int tempx=x+1,tempy=y-1;
        while(tempx<11&&tempy>=0)
        {
            if(state[tempx][tempy].getPiece()==null)
            {
                    possibleMoves.add(state[tempx][tempy]);
            }
            else if(state[tempx][tempy].getPiece().getColour()==this.getColour())
                    break;
            else
            {
                    possibleMoves.add(state[tempx][tempy]);
                    break;
            }
            tempx++;
            tempy--;
        }
        tempx=x-1;tempy=y+1;
        while(tempx>=0&&tempy<11)
        {
            if(state[tempx][tempy].getPiece()==null)
                    possibleMoves.add(state[tempx][tempy]);
            else if(state[tempx][tempy].getPiece().getColour()==this.getColour())
                    break;
            else
            {
                    possibleMoves.add(state[tempx][tempy]);
                    break;
            }
            tempx--;
            tempy++;
        }
        tempx=x-1;tempy=y-1;
        while(tempx>=0&&tempy>=0)
        {
            if(state[tempx][tempy].getPiece()==null)
                    possibleMoves.add(state[tempx][tempy]);
            else if(state[tempx][tempy].getPiece().getColour()==this.getColour())
                    break;
            else
            {
                    possibleMoves.add(state[tempx][tempy]);
                    break;
            }
            tempx--;
            tempy--;
        }
        tempx=x+1;tempy=y+1;
        while(tempx<11&&tempy<11)
        {
            if(state[tempx][tempy].getPiece()==null)
                    possibleMoves.add(state[tempx][tempy]);
            else if(state[tempx][tempy].getPiece().getColour()==this.getColour())
                    break;
            else
            {
                    possibleMoves.add(state[tempx][tempy]);
                    break;
            }
            tempx++;
            tempy++;
        }
        return possibleMoves;
    }
}

