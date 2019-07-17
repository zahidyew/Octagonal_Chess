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

public class Rook extends Pieces {
    //Constructor
    public Rook(String i,String p,int c)
    {
            setId(i);
            setPath(p);
            setColour(c);
    }

    //Move function defined
    public ArrayList<Cell> move(Cell[][] state, int x, int y)
    {
        //Rook can move only horizontally or vertically
        possibleMoves.clear();
        int tempx=x-1;
        while(tempx>=0)
        {
            if(state[tempx][y].getPiece()==null)
                possibleMoves.add(state[tempx][y]);
            else if(state[tempx][y].getPiece().getColour()==this.getColour())
                break;
            else
            {
                possibleMoves.add(state[tempx][y]);
                break;
            }
            tempx--;
        }
        tempx=x+1;
        while(tempx<11)
        {
            if(state[tempx][y].getPiece()==null)
                possibleMoves.add(state[tempx][y]);
            else if(state[tempx][y].getPiece().getColour()==this.getColour())
                break;
            else
            {
                possibleMoves.add(state[tempx][y]);
                break;
            }
            tempx++;
        }
        int tempy=y-1;
        while(tempy>=0)
        {
            if(state[x][tempy].getPiece()==null)
                possibleMoves.add(state[x][tempy]);
            else if(state[x][tempy].getPiece().getColour()==this.getColour())
                break;
            else
            {
                possibleMoves.add(state[x][tempy]);
                break;
            }
            tempy--;
        }
        tempy=y+1;
        while(tempy<11)
        {
            if(state[x][tempy].getPiece()==null)
                possibleMoves.add(state[x][tempy]);
            else if(state[x][tempy].getPiece().getColour()==this.getColour())
                break;
            else
            {
                possibleMoves.add(state[x][tempy]);
                break;
            }
            tempy++;
        }
        return possibleMoves;
    }
}
