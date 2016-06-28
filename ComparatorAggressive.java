package uk.ac.sheffield.aca14sk;

import java.util.Comparator;
/**
* ComparatorAggressive.java 
* Returns positive if first move takes higher piece, and negative otherwise
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/

public class ComparatorAggressive implements Comparator<Move> {
	/**
	* Compares two moves based on the value of piece taken
	* @return  int  positive int if first move takes higher piece, and negative int otherwise
	*/
	public int compare(Move a, Move b){
		int pieceA = PieceCode.charToInt(a.getTakenPiece(a.getToX(),a.getToY()).getChar()); //value of piece taken
		int pieceB = PieceCode.charToInt(b.getTakenPiece(b.getToX(),b.getToY()).getChar());
		return pieceA - pieceB;
	}

}