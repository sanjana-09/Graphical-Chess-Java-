package uk.ac.sheffield.aca14sk;

import java.util.ArrayList;

import java.util.Collections;

/**
* AggressivePlayer.java 
*
*Concrete class to represent Aggressive player
*
*@author Sanjana Khot (skhot1@sheffield.ac.uk)
*/
public class AggressivePlayer extends AIPlayer {

	public AggressivePlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	/**
	  * Selects a move to take the highest value piece of the opponent,
	  * or selects a random move no piece  can be taken
	  * @return  boolean  True if the opponent's King is taken 
	  */
	public boolean makeMove() {
		//makes moves slower so they can be easily watched 
		delayMove();
		
		ArrayList<Move> allAvailableMoves = getAllAvailableMoves();
		
		//ArrayList to contain moves that take an opponent's piece
		ArrayList<Move> movesTakePiece = new ArrayList<Move>();
		int numLegalMoves = allAvailableMoves.size();
		
		//calls method to add moves that take a piece
		addMovesTakePiece(allAvailableMoves, movesTakePiece, numLegalMoves); 
		Move m = null;
		
		if (movesTakePiece.isEmpty())
			m = getRandomMove(allAvailableMoves); //gets random move
	
		else {
			m = Collections.max(movesTakePiece, new ComparatorAggressive());//finds move that takes highest value piece
		}
		return m.executeMove(this);
	}
	
	//Method picks moves that take an opponent's piece from all available moves and adds them to the ArrayList
	private void addMovesTakePiece(ArrayList<Move> allAvailableMoves,ArrayList<Move> movesTakePiece, int numLegalMoves) {
		for (int j = 0; j < numLegalMoves; j++) { 
			if (!allAvailableMoves.isEmpty()) {
				Move m = allAvailableMoves.get(j);
				if (m.isOccupied())
					movesTakePiece.add(m);
			}
		}
	}
}
