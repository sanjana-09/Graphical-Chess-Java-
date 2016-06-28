package uk.ac.sheffield.aca14sk;
import java.util.*;
/**
 * Queen.java
 * Concrete class to represent a Queen, contains method to add legal moves
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class Queen extends ContinuousMovesPiece {

  //constructor
  public Queen (int ix, int iy, int c, Board b) {
    super(PieceCode.QUEEN, ix, iy, c, b);
  }
  
  /**
   * Method returns all legal moves of the piece to an ArrayList
   * @return ArrayList<Move>  The ArrayList of all legal moves
   */
  public ArrayList<Move> availableMoves() {
	//creates a new vector to store legal moves
	    ArrayList<Move> v = new ArrayList<Move>();
	    
	    //adds legal moves in the direction of adjacent and diagonal squares
	    for(int i = -1; i < 2; i++)
	    	for(int j = -1; j < 2; j++){
	    		if(!(i==0 && j==0))//eliminates current square
	    			addMoves(i,j,v);
	    }

	    if (v.isEmpty()) return null ;
	    return v;
  }
}