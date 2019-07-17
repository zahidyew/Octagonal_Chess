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

public class Queen extends Pieces {
    //Constructors
    public Queen(String i,String p,int c)
    {
        setId(i);
        setPath(p);
        setColour(c);
    }

    //Move Function Defined
    public ArrayList<Cell> move(Cell[][] state, int x, int y)
    {
        //Queen can move any number of steps in all 8 direction
        //The possible moves of queen is a combination of Rook and Bishop
        possibleMoves.clear();

        //Checking possible moves in vertical direction
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

        //Checking possible moves in horizontal Direction
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

        //Checking for possible moves in diagonal direction
        tempx=x+1;tempy=y-1;
        while(tempx<11&&tempy>=0)
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
