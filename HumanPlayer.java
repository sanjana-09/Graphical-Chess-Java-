package uk.ac.sheffield.aca14sk;

import java.util.*;

/**
* HumanPlayer.java 
* Class to represent Human Player
*
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/

public class HumanPlayer extends Player {
	//instance variable
	private Display display;
	private Move m;
	private boolean kingDead;
	
	// constructor
	public HumanPlayer(String n, Pieces p, Board b, Player o, Display d) {
		super(n, p, b, o);
		display = d;
	}
	
	/**
	 * Interacts with the user and receives input for movement coordinates, checks
	 * if a move is legal and only executes a move if it is, returns
	 * true when the opponent's King is taken
	 * @return  boolean  True when King is taken 
	 */
	
	public boolean makeMove() {
	boolean played = false; // played = true when a move has been played successfully
	kingDead = false; // kingDead = true when a King has been taken, method returns this variable
		while (!played) {
			int[][] bothCoords = display.getBothCoords(this); //gets coordinates of button pressed
			int fromX = bothCoords[0][0];
			int fromY = bothCoords[0][1];
			int toX = bothCoords[1][0];
			int toY = bothCoords[1][1];
			
			//calls method to check if move is legal, played = true if it is
			played = isValidMove(fromX,fromY,toX,toY);
			
			if(played)
				kingDead = m.executeMove(this); //executes move, returns true if opponent's king has been taken
			
			//redraws board if move is invalid
			else
				display.showPiecesOnBoard(getBoard().getData());
		}
		return kingDead;
	}
	
	/*Method checks if move is legal, and executes it if it is. Returns true if move is successfully executed */
	private boolean isValidMove(int fromX,int fromY,int toX,int toY){
		
		// if no piece at the location corresponding to the from coordinates, returns false
		if (!getBoard().occupied(fromX, fromY)) { 
			System.out.println("There is no piece here.");
			return false;
		}
		
		Piece pieceSelected = getBoard().getPiece(fromX, fromY);
		
		//creates move object
		m = new Move(pieceSelected, fromX, fromY, toX, toY,getBoard().occupied(toX, toY));
	
		// move invalid if an opponent's piece is selected, returns false
		if (pieceSelected.getColour() != this.getPieces().getPiece(0).getColour()) {
			System.out.println("This isn't valid. Play with your own pieces");
			return false;
		}
		
		//if piece has no available moves, return false
		if (pieceSelected.availableMoves() == null){
			System.out.println("This piece cannot move right now.");
			return false;
		}
		
		// if move isn't legal, returns false
		if (!pieceSelected.availableMoves().contains(m)){
			System.out.println("This move isn't valid.");
			return false;
		}
		return true;
	}
}

