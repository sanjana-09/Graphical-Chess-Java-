package uk.ac.sheffield.aca14sk;
import java.util.*;

/**
 * King.java
 * Concrete class to represent a King, contains method to add legal moves
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class King extends Piece {

  //constructor
  public King (int ix, int iy, int c, Board b) {
    super(PieceCode.KING, ix, iy, c, b);
  }

  /**
   * Method returns all legal moves of the piece to an ArrayList
   * @return ArrayList<Move>  The ArrayList of all legal moves
   */
  public ArrayList<Move> availableMoves() {
	  ArrayList<Move> v = new ArrayList<Move>();
	    
	    //adds legal moves from adjacent and diagonal squares
	    for(int i = -1; i < 2; i++)
	    	for(int j = -1; j < 2; j++){
	    		if(!(i==0 && j==0))//eliminates current square
	    			addMove(i,j,v);
	    	}

	    if (v.isEmpty()) return null ;
	    return v;
  }

  /*method to generate legal moves*/
  private ArrayList<Move> addMove(int dx, int dy, ArrayList<Move> v) {

    int x = getX(); /*gets x and y coordinates of Piece selected*/
    int y = getY();

    int y1 = getY() + dy; /*dx and dy increment the x and y coordinate in a specific direction*/
    int x1 = getX() + dx;

    // set up m to refer to a Move object  
    Move m = null;

    if(!getBoard().outOfRange(x1,y1))
      if (!getBoard().occupied(x1,y1)) { /*executed if a position on the board is not out of range and is unoccupied */
        m = new Move(this,x,y,x1,y1,false);
        v.add(m);
      }

      else  /*executed if a position on the board is not out of range and is occupied by an opponent's piece */
        if (getBoard().getPiece(x1,y1).getColour()!=this.getColour()) {
          m = new Move(this,x,y,x1,y1,true);
          v.add(m);
        }

    if (v.isEmpty()) return null ;
    return v; /*returns ArrayList of available moves*/
  }
}