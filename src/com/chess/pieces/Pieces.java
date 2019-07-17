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

 // This is the Pieces Class. It is an abstract class from which all the actual pieces are inherited.
 // It defines all the function common to all the pieces
public abstract class Pieces implements Cloneable {
    private int colour;
    private String id = null;
    private String path;
    protected ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public abstract ArrayList<Cell> move(Cell[][] pos, int x, int y );
    
    public void setId(String id) {
        this.id = id;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setColour(int colour) {
        this.colour = colour;
    }
    public String getId() {
        return id;
    }
    public String getPath() {
        return path;
    }
    public int getColour() {
        return this.colour;
    }
    public Pieces getCopy() throws CloneNotSupportedException {
        return (Pieces) this.clone();
    }  
}
