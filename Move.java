package uk.ac.sheffield.aca14sk;
import java.util.*;
/**
* Move.java 
* Class that stores information about a move such as
* the from and to coordinates, the piece to be moved,
* whether the to-position is occupied, and the opponent's piece that might be taken
*
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/
public class Move {
	//instance variables
	Piece piece;
	int fx,fy,tx,ty ; // to and from coordinates
	boolean occupied;
	/**
	 * Constructor
	 * @param p  Piece  The Piece to be moved
	 * @param x1  int  the from-X coordinate of the Move
	 * @param y1  int  the from-Y coordinate of the Move
	 * @param x2  int  the to-X coordinate of the Move
	 * @param y2  int  the to-Y coordinate of the Move
	 * @param occ  boolean  True if to-position is occupied
	 */
	public Move(Piece p, int x1, int y1, int x2, int y2, boolean occ){
		piece = p;
		fx = x1;
		fy = y1;
		tx = x2;
		ty = y2;
		occupied = occ;
	}
	
	//Accessors
	public Piece getPiece(){
		return piece;
	}
	
	public Piece getTakenPiece(int x, int y){
		return piece.getBoard().getPiece(x,y);
	}
	
	public int getFromX(){
		return fx;
	}
	
	public int getFromY(){
		return fy;
	}
	
	public int getToX(){
		return tx;
	}
	
	public int getToY(){
		return ty;
	}
	
	public boolean isOccupied(){
		return occupied;
	}

	//returns true if both the objects have identical references
	public boolean equals(Object obj) {
		if (this==obj) 
			return true;
		/*returns false if the reference of the object passed points to nothing or if both the objects 
		are of different classes*/
		if (obj==null||this.getClass()!=obj.getClass()) 
			return false;
		Move i = (Move)obj;
		//returns true if the values of the instance variables of both the objects have the same value
		return this.piece.equals(i.piece) && (fx==i.fx) && (fy==i.fy) && (tx==i.tx) 
			&& (ty==i.ty) && (occupied==i.occupied);
	}
	
	public String toString(){
		String s;
		s= "Piece selected : " +piece+ ", fromX = "+fx+", fromY = "+fy+",toX = "+tx+", toY = "+ty+",next position occ="+occupied;
		return s;
	}
	/**
	 * Executes move
	 * @param player  Player  The instance of the Player currently making a move
	 * @return  boolean  True if opponent's king is taken
	 */
	public boolean executeMove(Player player){
		boolean kingDead = false; //true when the opponent's King has been taken
		int toX = getToX(); //gets x and y coordinates for move
		int toY = getToY();
		int fromX = getFromX();
		int fromY = getFromY();
		Piece pieceSelected = getPiece();
		
		//if occupied by opponent's piece
		if (pieceSelected.getBoard().occupied(toX,toY)) { 
			Piece pieceTaken = pieceSelected.getBoard().getPiece(toX,toY);//Opponent's piece taken
			
			player.getOpponent().deletePiece(pieceTaken); //opponent's piece deleted 
			
			if(pieceTaken.getChar()==PieceCode.KINGBLACK || pieceTaken.getChar()==PieceCode.KINGWHITE)
			 	kingDead = true; //boolean value to be returned set to true if opponent's King is taken
		}

		pieceSelected.getBoard().setPosition(toX,toY,pieceSelected); //board updated
		pieceSelected.setPosition(toX,toY); //Piece position updated
		pieceSelected.getBoard().remove(fromX,fromY);
		return kingDead;
	}
	
}