package uk.ac.sheffield.aca14sk;
import java.util.*;

/**
 * Bishop.java
 * Concrete class to represent a Bishop, contains method to add legal moves
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class Bishop extends ContinuousMovesPiece { 
  //constructor
  public Bishop (int ix, int iy, int c, Board b) {
    super(PieceCode.BISHOP, ix, iy, c, b);
  }

	/**
	 * Method returns all legal moves of the piece to an ArrayList
	 * @return ArrayList<Move>  The ArrayList of all legal moves
	 */
	public ArrayList<Move> availableMoves() {
		//creates a new vector to store legal moves
		ArrayList<Move> v = new ArrayList<Move>();
		
		//adds legal moves in diagonal directions
		for(int i = -1; i < 2; i++)
			for(int j = -1; j < 2; j++){
				if(i!=0 && j!=0) // eliminates current square and adjacent squares
					addMoves(i,j,v);
			}
		
		if (v.isEmpty()) return null ;
		return v;
	 }

}

