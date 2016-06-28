package uk.ac.sheffield.aca14sk;

import java.util.ArrayList;
/**
* AIPlayer.java 
*
*Abstract class to represent AI Players like Random Player and Aggressive Player
*
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/

public abstract class AIPlayer extends Player {
	public static final int TIME_BETWEEN_MOVES = 1000;
	
	//Constructor
	public AIPlayer(String n, Pieces p, Board b, Player o) {
	 	super(n,p,b,o);
	 }
	
	/**
	  * Finds all available moves for the player and adds them to an ArrayList
	  * @return ArrayList<Move> The ArrayList containing all available moves   
	  */
	public ArrayList<Move> getAllAvailableMoves(){
			int numPieces = getPieces().getNumPieces();
			ArrayList<Piece> allPieces = new ArrayList<Piece>();
			ArrayList<Move> allAvailableMoves = new ArrayList<Move>();
			
			//adds all legal moves of all own-pieces of the Player to ArrayList
			for(int i = 0 ; i<numPieces; i++){
				allPieces.add(getPieces().getData().get(i)); //adds all pieces of the Player to ArrayList
				if(allPieces.get(i).availableMoves()!=null)
					allAvailableMoves.addAll(allPieces.get(i).availableMoves()); 
			}
			return allAvailableMoves;
		}
	
	/**
	  * Finds a random move from all available moves for the player and returns it
	  * @param  allAvailableMoves  ArrayList<Moves>  ArrayList containing all available moves
	  * @return  Move  The random move selected  
	  */
	  public Move getRandomMove(ArrayList<Move> allAvailableMoves){
		  int numLegalMoves = allAvailableMoves.size();
		  double num = Math.random();
		  return allAvailableMoves.get((int)(num*numLegalMoves));
	  }
	  
	  /**
		* Pauses thread for specified time 
		*/
		public void delayMove(){
			try {
				Thread.sleep(TIME_BETWEEN_MOVES);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
}
