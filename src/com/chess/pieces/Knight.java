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

public class Knight extends Pieces {
    
    //Constructor
    public Knight(String i,String p,int c)
    {
        setId(i);
        setPath(p);
        setColour(c);
    }

    //Move Function overridden
    //There are at max 8 possible moves for a knight at any point of time.
    //Knight moves only 2(1/2) steps
    public ArrayList<Cell> move(Cell[][] state, int x, int y)
    {
        possibleMoves.clear();
        int[] posx = {x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2, -1, -1, -1};
        int[] posy = {y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1, -1, -1, -1};
        for(int i = 0; i < 11; i++)
            if((posx[i] >= 0 && posx[i] < 11 && posy[i] >= 0 && posy[i] < 11))
                if((state[posx[i]][posy[i]].getPiece()==null||state[posx[i]][posy[i]].getPiece().getColour()!=this.getColour()))
                {
                    possibleMoves.add(state[posx[i]][posy[i]]);
                }
        return possibleMoves;
    }
}
