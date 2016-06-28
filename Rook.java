package uk.ac.sheffield.aca14sk;
import java.util.*;

/**
 * Rook.java
 * Concrete class to represent a Rook, contains method to add legal moves
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class Rook extends ContinuousMovesPiece {

  //constructor
  public Rook (int ix, int iy, int c, Board b) {
    super(PieceCode.ROOK, ix, iy, c, b);
  }

  /**
   * Method returns all legal moves of the piece to an ArrayList
   * @return ArrayList<Move>  The ArrayList of all legal moves
   */
  public ArrayList<Move> availableMoves() {
	  //creates a new vector to store legal moves
	    ArrayList<Move> v = new ArrayList<Move>();
	    
	    //adds legal moves in direction of adjacent squares
	    for(int i = -1; i < 2; i++)
	    	for(int j = -1; j < 2; j++) {
	    		if(i+j==1 || i+j == -1)//eliminates squares in diagonal directions
	    			addMoves(i,j,v);
	    }

	    if (v.isEmpty()) return null ;
	    return v;

  }
}






