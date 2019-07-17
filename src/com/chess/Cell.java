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


package com.chess;

import com.chess.pieces.King;
import com.chess.pieces.Pieces;
import java.awt.*;
import javax.swing.*;

// There are total of 97 cells that together makes up the Chess Board
public class Cell extends JPanel implements Cloneable {
    private static final long serialVersionUID = 1L;
    private boolean possibleDestination;
    private JLabel content, effect1, effect2;
    private Pieces piece;
    public int xCoordinate, yCoordinate;
    private boolean isSelected = false;
    private boolean isCheck = false;
    
    public Cell (int x, int y, Pieces p) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        ImageIcon fire = new ImageIcon(this.getClass().getResource("graphics/checkfire.gif"));
        effect1 = new JLabel(fire);
        ImageIcon square = new ImageIcon(this.getClass().getResource("graphics/selected.gif"));
        effect2 = new JLabel(square);
        
        setLayout (new BorderLayout());
        
        if ((x + y) % 2 == 0)
            setBackground(Color.white);
        else 
            setBackground(new Color(139,69,19));
        if (p != null)
            setPiece(p);
        
        if (x==0&&y<3||x==0&&y>7)
            setOpaque(false);
        if (x==1&&y<2||x==1&&y>8)
            setOpaque(false);
        if (x==2&&y==0||x==2&&y==10)
            setOpaque(false);
        if (x==8&&y==0||x==8&&y==10)
            setOpaque(false);
        if (x==9&&y<2||x==9&&y>8)
            setOpaque(false);
        if (x==10&&y<3||x==10&&y>7)
            setOpaque(false);
    }
    public Cell (Cell cell) throws CloneNotSupportedException {
        this.xCoordinate = cell.xCoordinate;
        this.yCoordinate = cell.yCoordinate;
        
        setLayout(new BorderLayout());
        if ((xCoordinate + yCoordinate) % 2 == 0)
            setBackground(Color.white);
        else 
            setBackground(new Color(139,69,19));
        if (cell.getPiece() != null) {
            setPiece (cell.getPiece().getCopy());
        }
        else 
            piece = null;
    }
    public void setPiece (Pieces p) {
        piece = p;
        ImageIcon image = new ImageIcon(this.getClass().getResource(p.getPath()));
        content = new JLabel(image);
        this.add(content);
    }
    public void removePiece() {
        if (piece instanceof King) {
            piece = null;
            this.remove(content);
        }
        else {
            piece = null;
            this.remove(content);
        }
    }
    public Pieces getPiece() {
        return this.piece;
    }
    public void select() {
        this.setBorder(BorderFactory.createLineBorder(Color.red,6));
        this.isSelected = true;
    }
    public boolean is_selected() {
        return this.isSelected;
    }
    public void deselect() {
        this.setBorder(null);
        this.isSelected = false;
    }
    public void setPossibleDestination() {
        this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        this.possibleDestination = true;
    }
    public void removePossibleDestination() {
        this.setBorder(null);
        this.possibleDestination = false;
    }
    public boolean isPossibleDestination() {
        return this.possibleDestination;
    }
    public void setCheck() {
        this.add(effect1);
        this.isCheck = true;
    }
    public void removeCheck() {
        this.setBorder(null);
        if ((xCoordinate + yCoordinate) % 2 == 0) {
            setBackground(Color.white);
            this.remove(effect1);
        }
        else {
            setBackground(new Color(139,69,19));
            this.remove(effect1);
        }
        this.isCheck = false;
    }
   public boolean isCheck() {
       return isCheck;
   }
}