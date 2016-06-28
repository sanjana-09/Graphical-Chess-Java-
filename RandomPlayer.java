package uk.ac.sheffield.aca14sk;
import java.util.*;
/**
* RandomPlayer.java 
*
*Concrete class to represent Random player
*
*@author Sanjana Khot (skhot1@sheffield.ac.uk)
*/

public class RandomPlayer extends AIPlayer {
	//constructor
	public RandomPlayer(String n, Pieces p, Board b, Player o) {
	 	super(n,p,b,o);
	 }
	 
	/**
	  * Selects a random move
	  * @return  boolean  True if the opponent's King is taken 
	  */
	public boolean makeMove() {
		//makes moves slower so they can be easily watched 
		delayMove();
		
		//gets all available moves
		ArrayList<Move> allAvailableMoves = getAllAvailableMoves();
		
		//gets random move
		Move m = getRandomMove(allAvailableMoves);
		
		return m.executeMove(this);
	}
}
	



