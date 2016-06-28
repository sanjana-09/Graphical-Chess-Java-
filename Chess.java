package uk.ac.sheffield.aca14sk;
import javax.swing.*;

/**
 * Chess.java
 * Implements a Graphical game of Chess
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class Chess{
	public static void main(String[] args) {

		Board board = new Board(); //creates a Board
		
		Pieces piecesWhite = new Pieces(board, PieceCode.WHITE); //Initialises Pieces
		Pieces piecesBlack = new Pieces(board, PieceCode.BLACK); 
		
		GraphicalDisplay display = new GraphicalDisplay();//Initialises GraphicalDisplay object
		
		//Displays dialog box for Player selection and returns Player type selected
		GraphicalPlayerSelection white = new GraphicalPlayerSelection(PieceCode.WHITE); 
		GraphicalPlayerSelection black = new GraphicalPlayerSelection(PieceCode.BLACK);
		
		PlayerType whitePlayerType =  white.getPlayerType();
		if(whitePlayerType == null)
			System.exit(0); //exits program if no player selected
			
		PlayerType blackPlayerType = black.getPlayerType(); //player type
		if(blackPlayerType == null)
			System.exit(0);
		
		//Constructs and returns Player
		Player whitePlayer = white.constructPlayer(whitePlayerType,blackPlayerType,piecesWhite,board,display); 
		
		Player blackPlayer = black.constructPlayer(blackPlayerType,whitePlayerType,piecesBlack,board,display);
		
		
		whitePlayer.setOpponent(blackPlayer);
		blackPlayer.setOpponent(whitePlayer);

		display.showPiecesOnBoard(board.getData());
		
		int c = 0; //counter to determine which player's turn it is
		boolean kingDead = false; //boolean value depending on whether a King is dead
		
		Player currentPlayer = whitePlayer;

		while(!kingDead) {
			if (c%2==0)  //if c is even, white player's turn, else black player's turn
				currentPlayer = whitePlayer;
			else 
				currentPlayer = blackPlayer;
			display.indicatePlayerTurn(currentPlayer);
			kingDead = currentPlayer.makeMove();
			display.showPiecesOnBoard(board.getData());
			c++;
		}

		if(kingDead)
			JOptionPane.showMessageDialog(null,"Game has ended! "+currentPlayer+" wins.");
			

	}
}


		
			