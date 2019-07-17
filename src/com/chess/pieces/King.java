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

public class King extends Pieces {
    private int xCoordinate, yCoordinate;
    
    public King(String i, String p, int c, int x, int y) {
        setX(x);
	setY(y);
	setId(i);
        setPath(p);
	setColour(c); 
    }
    public void setX(int x) {
        this.xCoordinate = x;
    }
    public void setY(int y) {
        this.yCoordinate = y;
    }
    public int getX() {
        return xCoordinate;
    }
    public int getY() {
        return yCoordinate;
    }
    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        //King can move only one step. So all the adjacent 8 cells have been considered.
	possibleMoves.clear();
	int[] posx = {x, x, x + 1, x + 1, x + 1, x - 1, x - 1, x - 1, -1, -1, -1};
	int[] posy = {y - 1, y + 1, y - 1, y, y + 1, y - 1, y, y + 1, -1, -1, -1};
	for(int i = 0; i < 11; i++)
            if((posx[i] >= 0 && posx[i] < 11 && posy[i] >= 0 && posy[i] < 11))
		if((state[posx[i]][posy[i]].getPiece() == null || state[posx[i]][posy[i]].getPiece().getColour() != this.getColour()))
                    possibleMoves.add(state[posx[i]][posy[i]]);
	return possibleMoves;
    }
    
    //Function to check if king is under threat
    //It checks whether there is any piece of opposite color that can attack king for a given board state
    public boolean isindanger(Cell[][] state) {
    //Checking for attack from left,right,up and down
    for(int i = xCoordinate + 1; i < 11; i++) {
    	if(state[i][yCoordinate].getPiece() == null)
            continue;
    	else if(state[i][yCoordinate].getPiece().getColour() == this.getColour())
            break;
        else {
            if ((state[i][yCoordinate].getPiece() instanceof Rook) || (state[i][yCoordinate].getPiece() instanceof Queen))
                return true;
            else
                break;
    	}
    }
    for(int i = xCoordinate - 1; i >= 0; i--) {
    	if(state[i][yCoordinate].getPiece() == null)
            continue;
    	else if(state[i][yCoordinate].getPiece().getColour() == this.getColour())
            break;
        else {
            if ((state[i][yCoordinate].getPiece() instanceof Rook) || (state[i][yCoordinate].getPiece() instanceof Queen))
    		return true;
            else
    		break;
    	}
    }
    for(int i = yCoordinate+ 1; i < 11; i++) {
    	if(state[xCoordinate][i].getPiece()==null)
            continue;
    	else if(state[xCoordinate][i].getPiece().getColour()==this.getColour())
            break;
        else {
            if ((state[xCoordinate][i].getPiece() instanceof Rook) || (state[xCoordinate][i].getPiece() instanceof Queen))
    		return true;
            else
    		break;
    	}
    	}
    for(int i = yCoordinate - 1; i >= 0; i--) {
    	if(state[xCoordinate][i].getPiece() == null)
            continue;
    	else if(state[xCoordinate][i].getPiece().getColour() == this.getColour())
            break;
        else {
            if ((state[xCoordinate][i].getPiece() instanceof Rook) || (state[xCoordinate][i].getPiece() instanceof Queen))
                return true;
            else
                break;
        }
    }

    //checking for attack from diagonal direction
    int tempx = xCoordinate + 1, tempy = yCoordinate - 1;
        while(tempx < 11 && tempy >= 0) {
            if(state[tempx][tempy].getPiece() == null) {
                tempx++;
                tempy--;
            }
            else if(state[tempx][tempy].getPiece().getColour() == this.getColour())
                break;
            else
            {
                if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
                return true;
            else
                break;
            }
    }
    tempx = xCoordinate - 1; tempy = yCoordinate + 1;
    while(tempx >= 0 && tempy < 11)
    {
        if(state[tempx][tempy].getPiece() == null)
        {
                tempx--;
                tempy++;
        }
        else if(state[tempx][tempy].getPiece().getColour() == this.getColour())
                break;
        else
        {
                if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
                return true;
        else
                break;
        }
    }
    tempx = xCoordinate - 1; tempy = yCoordinate - 1;
    while(tempx >= 0 && tempy >= 0)
    {
        if(state[tempx][tempy].getPiece() == null)
        {
                tempx--;
                tempy--;
        }
        else if(state[tempx][tempy].getPiece().getColour() == this.getColour())
                break;
        else
        {
                if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
                return true;
        else
                break;
        }
    }
    tempx = xCoordinate + 1; tempy = yCoordinate + 1;
    while(tempx < 11 && tempy < 11)
    {
        if(state[tempx][tempy].getPiece()==null)
        {
                tempx++;
                tempy++;
        }
        else if(state[tempx][tempy].getPiece().getColour()==this.getColour())
                break;
        else
        {
                if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
                return true;
        else
                break;
        }
    }

    //Checking for attack from the Knight of opposite color
    int[] posx = {xCoordinate + 1, xCoordinate + 1, xCoordinate + 2, xCoordinate + 2, xCoordinate - 1, xCoordinate - 1, xCoordinate - 2, xCoordinate - 2,0,0,0};
    int[] posy = {yCoordinate - 2, yCoordinate + 2, yCoordinate - 1, yCoordinate + 1, yCoordinate - 2, yCoordinate + 2, yCoordinate - 1, yCoordinate + 1,0,0,0};
    for(int i = 0; i < 11; i++)
        if((posx[i] >= 0 && posx[i] < 11 &&posy[i] >= 0 &&posy[i] < 11))
            if(state[posx[i]][posy[i]].getPiece()!=null && state[posx[i]][posy[i]].getPiece().getColour()!=this.getColour() && (state[posx[i]][posy[i]].getPiece() instanceof Knight))
            {
                return true;
            }
    
    //Checking for attack from the Pawn of opposite color
    int[] pox = {xCoordinate + 1, xCoordinate + 1, xCoordinate + 1, xCoordinate, xCoordinate, xCoordinate - 1, xCoordinate - 1, xCoordinate - 1,0,0,0};
    int[] poy = {yCoordinate - 1, yCoordinate + 1, yCoordinate, yCoordinate + 1, yCoordinate - 1, yCoordinate + 1, yCoordinate - 1, yCoordinate,0,0,0};
    {
        for(int i = 0; i < 11; i++)
            if((pox[i] >= 0 && pox[i] < 11 &&poy[i] >= 0 && poy[i] < 11))
                if(state[pox[i]][poy[i]].getPiece() != null && state[pox[i]][poy[i]].getPiece().getColour() != this.getColour() && (state[pox[i]][poy[i]].getPiece() instanceof King))
                {
                    return true;
                }
    }
    if(getColour() == 0)
    {
        if(xCoordinate > 0 && yCoordinate > 0 && state[xCoordinate - 1][yCoordinate - 1].getPiece() != null && state[xCoordinate - 1][yCoordinate - 1].getPiece().getColour() == 1 && (state[xCoordinate - 1][yCoordinate - 1].getPiece() instanceof Pawn))
            return true;
        if(xCoordinate > 0 && yCoordinate < 10 && state[xCoordinate - 1][yCoordinate + 1].getPiece() != null && state[xCoordinate - 1][yCoordinate + 1].getPiece().getColour() == 1 && (state[xCoordinate - 1][yCoordinate + 1].getPiece() instanceof Pawn))
            return true;
    }
    else
    {
        if(xCoordinate < 10 && yCoordinate > 0 && state[xCoordinate + 1][yCoordinate - 1].getPiece() != null && state[xCoordinate + 1][yCoordinate - 1].getPiece().getColour() == 0 &&(state[xCoordinate + 1][yCoordinate - 1].getPiece() instanceof Pawn))
            return true;
        if(xCoordinate < 10 && yCoordinate < 10 && state[xCoordinate + 1][yCoordinate + 1].getPiece() != null && state[xCoordinate + 1][yCoordinate + 1].getPiece().getColour() == 0 &&(state[xCoordinate + 1][yCoordinate + 1].getPiece() instanceof Pawn))
            return true;
    }
    return false;
    }
}
