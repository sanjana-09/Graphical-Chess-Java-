package uk.ac.sheffield.aca14sk;
import java.awt.GridLayout;

import javax.swing.*;
/**
* GraphcalPlayerSelection.java 
* Class which implements a GUI for selecting player type and for constructing the players 
*
* @author Sanjana Khot (skhot1@sheffield.ac.uk)
*/

public class GraphicalPlayerSelection extends JFrame {
	//instance variables
	private String name;
	private Player player;
	private String colour;
	private String[] possibleValues = { "Human", "Random", "Aggressive" };
	private JPanel panel;
	private JLabel label;
	private String[] options = {"OK"};
	private JComboBox<String> selectionList;
	
	/**
	* Constructor; constructs dialog boxes for player selection
	*
	* @param  c  int  The colour of the Player, 1 for white, 0 for black
	*/
	
	public GraphicalPlayerSelection(int c) {
		if (c == PieceCode.WHITE)
			colour = "White"; //setting colour for constructing Players
		else
			colour = "Black";
		panel = new JPanel(new GridLayout(2, 1)); 
		label = new JLabel("Select a type of player");
		selectionList = new JComboBox<String>(possibleValues);
		panel.add(label); 
		panel.add(selectionList);
	}
	/**
	* Displays dialog box for Player selection, prompts user to input name if
	* Human Player is selected, and returns selected PlayerType
	*
	* @return  PlayerType  The Player type selected by the user   
	*/
	public PlayerType getPlayerType(){
		//displays dialog box for selecting player type
		int selection = JOptionPane.showOptionDialog(null, panel,"Select Player - "+colour,
			     JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
			     null, options, options[0]);
		if(selection == JOptionPane.CLOSED_OPTION)
			return null;
		
		String selectedValue = (String)selectionList.getSelectedItem();
		
		//returns type of Player selected from the given String
		switch (selectedValue) {
		case "Random": return PlayerType.RANDOM;
		case "Aggressive": return PlayerType.AGGRESSIVE;
		case "Human":
		default:
			//default selects Human Player, prompts user for name
			do 
			 name = JOptionPane.showInputDialog("Name for human player: ");
			while(name != null && name.equals(""));
			
			if (name == null)
				return null;
			
			return PlayerType.HUMAN;
		}
	}
	
	//Accessor, returns name of Player
	private String getPlayerName(){
		return name;
	}
	
	//sets name of Player depending on type of Player selected
	private void makeName(PlayerType playerType, PlayerType opponentType){
		if(playerType.equals(opponentType))
			if(colour.equals("White"))
				name = playerType+" Player white";
			else
				name = playerType+" Player black";
		else
			name = playerType+ " Player";
	}
	/**
	* Constructs Players based on selected player type, calls methods to set name in case of AI Players
	* @param  type  PlayerType  Type of Player selected
	* @param  opponentType  PlayerType  Type of opponent of Player selected
	* @param  pieces  Pieces  pieces of the Player selected
	* @param  board  Board  The Chess board being used
	* @param  display  GraphicalDisplay  The GUI displaying the chess board, pieces, and indicating which player's turn it is 
	*
	* @return  Player  The instance of Player being constructed
	*/
	public Player constructPlayer(PlayerType type, PlayerType opponentType, Pieces pieces, Board b, GraphicalDisplay display){
		switch (type) {
		case HUMAN: String humanName = getPlayerName();
					return new HumanPlayer(humanName, pieces, b, null,display);
					
		case RANDOM: makeName(type,opponentType);
					 return new RandomPlayer(name, pieces, b, null);
					
		case AGGRESSIVE: makeName(type,opponentType);
						return new AggressivePlayer(name, pieces, b, null);
					
		}
		
		return player;
	}
	
	
}
