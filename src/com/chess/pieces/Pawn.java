//***********************************************************************************
// Project: Group TMFG2_34
//
// Authors and matric number: 
// MUHAMMAD ZAHID BIN MUHAMAD YEW               56869
// NUR SYAZWANI ATIFAH BINTI MUHAMMAD FAROUK    57374
// NUR HIDAYAH BINTI BADRUN HISYAM              58973
// NURUL ABIDAH BINTI HYDRUS                    57472
//
// Honour code: We pledge that this program represents our own program code. We received help from
// Ashish Kedia and Adarsh Mohata for their work on the chess program called ChessOOP, which is 
// our main reference in doing this project. 
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
