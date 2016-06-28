package uk.ac.sheffield.aca14sk;
import java.awt.*;

import javax.swing.*;

/**
* ButtonHandler.java 
* Class which handles operations associated with a button after it has been pressed 
*
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/
public class ButtonOperations  {
	//instance variables
	private int[][] bothCoords = new int[2][2];
	private Piece[][] board;
	private JButton buttonPressed = null;
	
	public static final int TIME_BETWEEN_PRESSES = 100;
	
	/**
	 * Constructor
	 * @param  button  JButton  The button pressed
	 * @param  b  Piece[][]  Array of chess pieces on the board
	 */
	public ButtonOperations(JButton button, Piece[][] b){
		buttonPressed = button;
		board = b;
	}
	
	/**
	 * Returns the 'to' and 'from' coordinates of the button pressed
	 * @param  p  Player  The instance of Player making the move
	 * @return int[][]  The coordinates of the move
	 */
	public int[][] getBothCoords(Player p) {
		JButton b1 = waitUntilButtonPressed();//first button pressed
		bothCoords[0] = getCoords(b1);
		b1.setBackground(new Color(249,191,59));
		
		/*checks if invalid button is pressed, move coordinates returned early
		  with to-coordinates as (0,0) because, if an invalid button is pressed,
		  the to-coordinates are irrelevant */
		if (checkWrongButtonPressed(p)) {
			return bothCoords;
		}
		JButton b2 = waitUntilButtonPressed();//second button pressed
		bothCoords[1] = getCoords(b2);
		return bothCoords;
	}

	/*holds up the program until a button has been pressed,
	  returns the button pressed once it has*/
	private JButton waitUntilButtonPressed() { 
		buttonPressed = null;
		while (buttonPressed == null) {
			try {
				Thread.sleep(TIME_BETWEEN_PRESSES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return buttonPressed;
	}

	// converts coordinates from Strings to ints, returns int array of a pair of coordinates
	private int[] getCoords(JButton button) {
		String coords = button.getActionCommand(); //gets coordinates of button pressed
		String x = coords.substring(0, 1);
		String y = coords.substring(1);

		//String coords converted to ints
		int xCoord = Integer.valueOf(x);
		int yCoord = Integer.valueOf(y);
		int[] intCoords = new int[2];
		intCoords[0] = xCoord;
		intCoords[1] = yCoord;
		return intCoords;
	}

	/*Checks if an invalid button for that particular move has been pressed
	 and returns true if it has*/
	private boolean checkWrongButtonPressed(Player p) { 
		boolean wrongButtonPressed = false;
		int x = bothCoords[0][0]; //from coordinates
		int y = bothCoords[0][1];
		Piece pieceSelected = board[x][y];
		int playerColor = p.getPieces().getPiece(0).getColour();
		
		//checks if button pressed is invalid
		if (pieceSelected == null || pieceSelected.getColour() != playerColor 
			|| pieceSelected.availableMoves() == null) {
			bothCoords[1][0] = 0; //to coordinates
			bothCoords[1][1] = 0;
			wrongButtonPressed = true;
		}
		return wrongButtonPressed;
	}
	
	//Mutators
	public void setButtonPressed(JButton button){
		buttonPressed = button;
	}
	
	public void setBoard(Piece[][]  b){
		board = b;
	}

}
