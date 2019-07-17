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
