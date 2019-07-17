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

public class Pawn extends Pieces {
    //Constructors
    public Pawn(String i,String p,int c)
    {
        setId(i);
        setPath(p);
        setColour(c);
    }

    //Move Function Overridden
    public ArrayList<Cell> move(Cell[][] state, int x, int y)
    {
        //Pawn can move only one step except the first chance when it may move 2 steps
        //It can move in a diagonal fashion only for attacking a piece of opposite color
        //It cannot move backward or move forward to attack a piece

        possibleMoves.clear();
        if(getColour()==0)
        {
            if(x==0)
                return possibleMoves;
            if(state[x-1][y].getPiece()==null)
            {
                possibleMoves.add(state[x-1][y]);
                if(x==9)
                {
                    if(state[7][y].getPiece()==null)
                        possibleMoves.add(state[7][y]);
                }
            }
            if((y>0)&&(state[x-1][y-1].getPiece()!=null)&&(state[x-1][y-1].getPiece().getColour()!=this.getColour()))
                possibleMoves.add(state[x-1][y-1]);
            if((y<10)&&(state[x-1][y+1].getPiece()!=null)&&(state[x-1][y+1].getPiece().getColour()!=this.getColour()))
                possibleMoves.add(state[x-1][y+1]);
        }
        else
        {
            if(x==11)
                return possibleMoves;
            if(state[x+1][y].getPiece()==null)
            {
                possibleMoves.add(state[x+1][y]);
                if(x==1)
                {
                    if(state[3][y].getPiece()==null)
                        possibleMoves.add(state[3][y]);
                }
            }
            if((y>0)&&(state[x+1][y-1].getPiece()!=null)&&(state[x+1][y-1].getPiece().getColour()!=this.getColour()))
                possibleMoves.add(state[x+1][y-1]);
            if((y<10)&&(state[x+1][y+1].getPiece()!=null)&&(state[x+1][y+1].getPiece().getColour()!=this.getColour()))
                possibleMoves.add(state[x+1][y+1]);
        }
        return possibleMoves;
    }
}
