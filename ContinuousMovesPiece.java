package uk.ac.sheffield.aca14sk;

import java.util.ArrayList;
/**
 * ContinuousMovesPiece.java
 * Abstract class to represent Pieces with continuous moves
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public abstract class ContinuousMovesPiece extends Piece {
	
	public ContinuousMovesPiece(int i, int ix, int iy, int c, Board b){
		super(i,ix,iy,c,b);
		
	}
	
	 /**
	  * Method finds all legal moves for the particular Piece and adds the to an ArrayList 
	  * @param dx  int  x-value to be modified
	  * @param dy  int  y-value to be modfied
	  * @param v   ArrayList<Move>  ArrayList containing legal moves
	  */
	  public void addMoves(int dx, int dy, ArrayList<Move> v) {

	    int x = getX(); /*gets x and y coordinates of Piece selected*/
	    int y = getY();

	    int y1 = getY() + dy;
	    int x1 = getX() + dx;
	    Boolean occupied = false; /*occupied = true when a particular position is available but is occupied
	    and hence there can the no further moves available in that direction */

	    // set up m to refer to a Move object  
	    Move m = null;

	    while(!getBoard().outOfRange(x1,y1) && !occupied) {
	      if (!getBoard().occupied(x1,y1)) { /*executed if a position on the board is not out of range and is unoccupied */
	        m = new Move(this,x,y,x1,y1,false);
	        v.add(m);
	      }

	      else { /*executed if a position on the board is not out of range and is occupied by an opponent's piece */
	        if (getBoard().getPiece(x1,y1).getColour()!=this.getColour()) {
	          m = new Move(this,x,y,x1,y1,true);
	          v.add(m);
	        }
	        occupied = true; /*serves the purpose of breaking out of the loop*/
	      }
	      y1=y1+dy; /*updates x and y coordinates*/
	      x1=x1+dx;
	    }
	  }

}
