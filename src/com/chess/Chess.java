//***********************************************************************************
// Authors:
// MUHAMMAD ZAHID BIN MUHAMAD YEW
// NUR SYAZWANI ATIFAH BINTI MUHAMMAD FAROUK
// NUR HIDAYAH BINTI BADRUN HISYAM
// NURUL ABIDAH BINTI HYDRUS
//***********************************************************************************

package com.chess;

import com.chess.pieces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


// This is the Chess Class of our project.
// All GUI Elements are declared, initialized and used in this class.
public class Chess extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;
    private static final int Height=700;	
    private static final int Width=1000;
    private static Rook wr01,br01;
    private static Knight wk01,bk01;
    private static Bishop wb01,bb01;
    private static Pawn wp,bp;
    private static Queen wq,bq;
    private static King wk,bk;
    private Cell c,previous;
    private Cell[][] boardState;
    private ArrayList<Cell> destinationlist = new ArrayList<Cell>();
    public JPanel board = null;
    private boolean selected=false,end=false;
    static String move;
    private int chance=0;
    private JPanel controlPanel,WhitePlayer,BlackPlayer;
    private String wname=null,bname=null,winner=null;
    private String[] WNames={},BNames={};
    private JSplitPane split;
    private Button start,WNewPlayer,BNewPlayer,reset;
    private JLabel imageLabel = new JLabel();
    private JLabel headerLabel = new JLabel();
    private String fileName;
    private FileWriter record;   
    public static Chess Mainboard;
 
    //constructor
    private Chess() {
        setSize(Width,Height);
	setTitle("Java Chess");
        move="White";
	wname=null;
	bname=null;
        winner=null;
        board = new JPanel(new GridLayout(11,11));
        board.setMinimumSize(new Dimension(800,700));
        fileName = "winners.txt";
        try {
             record = new FileWriter(fileName,true);
        } catch (IOException ex) {
            Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
        }   
        controlPanel=new JPanel();
        WNewPlayer=new Button("Add White Player");
        BNewPlayer=new Button("Add Black Player");
        WNewPlayer.setPreferredSize(new Dimension(175,50));
        BNewPlayer.setPreferredSize(new Dimension(175,50));
        WNewPlayer.addActionListener(new Handler(0));
        BNewPlayer.addActionListener(new Handler(1));    
        start=new Button("Start");
        reset=new Button("Restart");
        start.setPreferredSize(new Dimension(175,50));
        reset.setPreferredSize(new Dimension(175,50));
        start.addActionListener(new START());
        reset.addActionListener(new RESET());
        /*ImageIcon ii = new ImageIcon(this.getClass().getResource("graphics/menu.gif"));
        imageLabel.setIcon(ii);*/
        controlPanel.add(imageLabel, BorderLayout.NORTH);
        controlPanel.add(WNewPlayer, BorderLayout.PAGE_END);
        controlPanel.add(BNewPlayer, BorderLayout.PAGE_END);
        controlPanel.add(reset,BorderLayout.PAGE_END);
        controlPanel.add(start, BorderLayout.PAGE_END);
        controlPanel.setMinimumSize(new Dimension(300,700));
        headerLabel.setText("Please click Start button");        
        controlPanel.add(headerLabel, BorderLayout.PAGE_END);
        
        /*InputStream in;
        try {
            // Open an audio input stream.
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
            (this.getClass().getResource("audio/background.wav"));
	    // Get a sound clip resource.
	    Clip clip = AudioSystem.getClip();
	    // Open audio clip and load samples from the audio input stream.
	    clip.open(audioInputStream);
	    clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
        
        //Defining all the Cells
        buildBoard();
           
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,board,controlPanel);      
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem WinnerBoard = new JMenuItem(new AbstractAction("Winner Board"){
            public void actionPerformed(ActionEvent ae) {
                JFrame hello = new JFrame("Winner Board");
                hello.setSize(250,250);
                JTextArea textArea = new JTextArea();
                hello.add(textArea);
                textArea.setEditable(false);
                File file = new File("winners.txt");
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    String text = null;
                    String savetext=null;
                    String newline = "\n";
		            		
                while ((text = reader.readLine()) != null) {
                    savetext = text;
                    textArea.append(savetext + newline);
                }
	
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                }
                hello.setVisible(true);
            }
        });      
        //Create the menu bar.
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        menu.add(WinnerBoard);
        this.setJMenuBar(menuBar);
        this.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // A function to change turn from White Player to Black Player and vice versa
    public void changeTurn() {
        if (boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
        {
            chance^=1;
            gameend();
        }
        if(destinationlist.isEmpty()==false)
            cleandestinations(destinationlist);
        if(previous!=null)
            previous.deselect();
        previous=null;
        chance^=1;      
    }
    
    //A function to retrieve the Black King or White King
    private King getKing(int color)
    {
        if (color==0)
            return wk;
        else
            return bk;
    }
    
    //A function to clean the highlights of possible destination cells
    //Function to clear the last move's destinations
    private void cleandestinations(ArrayList<Cell> destlist)      
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
            it.next().removePossibleDestination();
    }
    
    //A function that indicates the possible moves by highlighting the Cells
    private void highlightdestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
            it.next().setPossibleDestination();
    }
    
    //Function to check if the king will be in danger if the given move is made
    private boolean willkingbeindanger(Cell fromcell,Cell tocell)
    {
    	Cell[][] newboardstate = new Cell[11][11];
    	for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
            {	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}
    	
    	if(newboardstate[tocell.xCoordinate][tocell.yCoordinate].getPiece()!=null)
            newboardstate[tocell.xCoordinate][tocell.yCoordinate].removePiece();
    	
        newboardstate[tocell.xCoordinate][tocell.yCoordinate].setPiece(newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].getPiece());
        if(newboardstate[tocell.xCoordinate][tocell.yCoordinate].getPiece() instanceof King)
        {
            ((King)(newboardstate[tocell.xCoordinate][tocell.yCoordinate].getPiece())).setX(tocell.xCoordinate);
            ((King)(newboardstate[tocell.xCoordinate][tocell.yCoordinate].getPiece())).setY(tocell.yCoordinate);
        }
        newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].removePiece();
        if (((King)(newboardstate[getKing(chance).getX()][getKing(chance).getY()].getPiece())).isindanger(newboardstate)==true)
            return true;
        else
        return false;
    }
    
    
    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> filterdestination (ArrayList<Cell> destlist, Cell fromcell)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell[][] newboardstate = new Cell[11][11];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
            for(int i=0;i<11;i++)
                for(int j=0;j<11;j++)
                {   try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}

                    Cell tempc = it.next();
                    if(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece()!=null)
    			newboardstate[tempc.xCoordinate][tempc.yCoordinate].removePiece();
                    newboardstate[tempc.xCoordinate][tempc.yCoordinate].setPiece(newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].getPiece());
                    x=getKing(chance).getX();
                    y=getKing(chance).getY();
                    if(newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].getPiece() instanceof King)
                    {
    			((King)(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece())).setX(tempc.xCoordinate);
    			((King)(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece())).setY(tempc.yCoordinate);
    			x=tempc.xCoordinate;
    			y=tempc.yCoordinate;
                    }
                    newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].removePiece();
                    if ((((King)(newboardstate[x][y].getPiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    //A Function to filter the possible moves when the king of the current player is under Check 
    private ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell[][] newboardstate = new Cell[11][11];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
            for(int i=0;i<11;i++)
                for(int j=0;j<11;j++)
                {   try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
                    Cell tempc = it.next();
                    if(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece()!=null)
    			newboardstate[tempc.xCoordinate][tempc.yCoordinate].removePiece();
                    newboardstate[tempc.xCoordinate][tempc.yCoordinate].setPiece(newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].getPiece());
                    x=getKing(color).getX();
                    y=getKing(color).getY();
                    if(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece() instanceof King)
                    {
    			((King)(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece())).setX(tempc.xCoordinate);
    			((King)(newboardstate[tempc.xCoordinate][tempc.yCoordinate].getPiece())).setY(tempc.yCoordinate);
    			x=tempc.xCoordinate;
    			y=tempc.yCoordinate;
                    }
                    newboardstate[fromcell.xCoordinate][fromcell.yCoordinate].removePiece();
                    if ((((King)(newboardstate[x][y].getPiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
        }
    	return newlist;
    }
 
    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int colour)
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<11;i++)
    	{
            for(int j=0;j<11;j++)
            {
                if (boardState[i][j].getPiece()!=null && boardState[i][j].getPiece().getColour()==colour)
                {
                    dlist.clear();
                    dlist=boardState[i][j].getPiece().move(boardState, i, j);
                    dlist=incheckfilter(dlist,boardState[i][j],colour);
                    if(dlist.size()!=0)
                    return false;
                }
            }
    	}
    	return true;
    }
    
    @SuppressWarnings("deprecation")
    private void gameend()
    {
        ImageIcon gif = new ImageIcon(this.getClass().getResource("graphics/win.gif"));
    	cleandestinations(destinationlist);
    	if(previous!=null)
            previous.removePiece();
    	if(chance==0)
        { 
            winner=wname;
        }
        else
        {
            winner=bname;
        }
        try {
            BufferedWriter recordWriter = new BufferedWriter(record);
            recordWriter.newLine();
            recordWriter.write(winner);
            recordWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Open an audio input stream.
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
            (this.getClass().getResource("audio/youwin.wav"));
	    // Get a sound clip resource.
	    Clip clip = AudioSystem.getClip();
	    // Open audio clip and load samples from the audio input stream.
	    clip.open(audioInputStream);
	    clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(board,"Checkmate!!!\n"+winner+" wins","CheckMate", JOptionPane.INFORMATION_MESSAGE,gif);
        end=true;
    }
    
    
    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
    @Override
    public void mouseClicked(MouseEvent arg0){
        c=(Cell)arg0.getSource();
        if (previous==null)
        {
            if(c.getPiece()!=null)
            {
                if(c.getPiece().getColour()!=chance)
                    return;
                c.select();
                previous=c;
                destinationlist.clear();
                destinationlist=c.getPiece().move(boardState, c.xCoordinate, c.yCoordinate);
                if(c.getPiece() instanceof King)
                    destinationlist=filterdestination(destinationlist,c);
                else
                {
                    if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
                        destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
                    else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
                        destinationlist.clear();
                }
                highlightdestinations(destinationlist);
            }
        }
        else
        {
            if(c.xCoordinate==previous.xCoordinate && c.yCoordinate==previous.yCoordinate)
            {
                c.deselect();
                cleandestinations(destinationlist);
                destinationlist.clear();
                previous=null;
            }
            else if(c.getPiece()==null||previous.getPiece().getColour()!=c.getPiece().getColour())
            {
                if(c.isPossibleDestination())
                {
                    if(c.getPiece()!=null)
                        c.removePiece();
                    c.setPiece(previous.getPiece());
                    if (previous.isCheck())
                        previous.removeCheck();
                    previous.removePiece();
                    if(getKing(chance^1).isindanger(boardState))
                    {
                        boardState[getKing(chance^1).getX()][getKing(chance^1).getY()].setCheck();
                        if (checkmate(getKing(chance^1).getColour()))
                        {
                            previous.deselect();
                            if(previous.getPiece()!=null)
                                previous.removePiece();
                            gameend();
                        }
                    }
                    if(getKing(chance).isindanger(boardState)==false)
                        boardState[getKing(chance).getX()][getKing(chance).getY()].removeCheck();
                    if(c.getPiece() instanceof King)
                    {
                        ((King)c.getPiece()).setX(c.xCoordinate);
                        ((King)c.getPiece()).setY(c.yCoordinate);
                    }
                    changeTurn();
                }
                if(previous!=null)
                {
                    previous.deselect();
                    previous=null;
                }
                cleandestinations(destinationlist);
                destinationlist.clear();
            }
            else if(previous.getPiece().getColour()==c.getPiece().getColour())
            {
                previous.deselect();
                cleandestinations(destinationlist);
                destinationlist.clear();
                c.select();
                previous=c;
                destinationlist=c.getPiece().move(boardState, c.xCoordinate, c.yCoordinate);
                if(c.getPiece() instanceof King)
                    destinationlist=filterdestination(destinationlist,c);
                else
                {
                    if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
                        destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
                    else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
                        destinationlist.clear();
                }
                highlightdestinations(destinationlist);
            }
        }
        if(c.getPiece()!=null && c.getPiece() instanceof King)
        {
            ((King)c.getPiece()).setX(c.xCoordinate);
            ((King)c.getPiece()).setY(c.yCoordinate);
        }
        /*try {
            // Open an audio input stream.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
                    (this.getClass().getResource("audio/walk.wav"));
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
    }
    
     //Other Irrelevant abstract function. Only the Click Event is captured.
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub		
    }
    
    class START implements ActionListener
    {
        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(wname==null||bname==null)
                {JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
                return;}
          WNewPlayer.disable();
          BNewPlayer.disable();
          start.disable();
        }
    }

    class Handler implements ActionListener{
        private int color;
        Handler(int i)
        {
            color=i;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String n=(color==0)?wname:bname;
            JPanel j=(color==0)?WhitePlayer:BlackPlayer;
            n=JOptionPane.showInputDialog(j,"Enter your name");
            if(n!=null)
            {
                if(n.length()!=0)
                    {   
                        if(color==0)
                            wname=n;
                        else
                            bname=n;
                    }
                else return;
            }
        }
    }
    
    class RESET implements ActionListener {
        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(JOptionPane.showConfirmDialog(null, "Restart the game?", "Restart",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            
            Mainboard.dispose();
            Mainboard = new Chess();
            Mainboard.setVisible(true);
            }
        }
            
    }
       
    private void buildBoard() {
         com.chess.pieces.Pieces P;
         Cell cell;
         boardState=new Cell[11][11];
         for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
            {	
                P=null;
                if(i==0&&j==7)
                    P=br01;
                else if(i==10&&j==3)
                    P=wr01;
                else if(i==0&&j==6)
                    P=bk01;
                else if(i==10&&j==4)
                    P=wk01;
                else if(i==0&&j==5)
                    P=bb01;
                else if(i==10&&j==5)
                    P=wb01;
                else if(i==0&&j==4)
                    P=bk;
                else if(i==0&&j==3)
                    P=bq;
                else if(i==10&&j==7)
                    P=wk;
                else if(i==10&&j==6)
                    P=wq;
                else if(i==1&&j==3)
                    P=bp;
                else if(i==9&&j==7)
                    P=wp;
                    
                cell=new Cell(i,j,P);
                board.add(cell);
                boardState[i][j]=cell;
                
                if (i==0&&j<3||i==0&&j>7){
                    
                }else if (i==1&&j<2||i==1&&j>8){
                    
                }else if (i==2&&j==0||i==2&&j==10){
                    
                }else if (i==8&&j==0||i==8&&j==10){
                    
                }else if (i==9&&j<2||i==9&&j>8){
                    
                }else if (i==10&&j<3||i==10&&j>7){
                }
                else {
                cell.addMouseListener(this);
                }
            }
    }
    
   
    public static void main(String[] args) {
        wr01=new Rook("WR01","images/White_Rook.png",0);
        br01=new Rook("BR01","images/Black_Rook.png",1);
        wk01=new Knight("WK01","images/White_Knight.png",0);
        bk01=new Knight("BK01","images/Black_Knight.png",1);
        wb01=new Bishop("WB01","images/White_Bishop.png",0);
        bb01=new Bishop("BB01","images/Black_Bishop.png",1);
        wq=new Queen("WQ","images/White_Queen.png",0);
        bq=new Queen("BQ","images/Black_Queen.png",1);
        wk=new King("WK","images/White_King.png",0,10,7);
        bk=new King("BK","images/Black_King.png",1,0,4);
        wp=new Pawn("WP0","images/White_Pawn.png",0);
        bp=new Pawn("BP0","images/Black_Pawn.png",1);
        
        Mainboard = new Chess();
        Mainboard.setResizable(false);
        Mainboard.setVisible(true);
    }
}
