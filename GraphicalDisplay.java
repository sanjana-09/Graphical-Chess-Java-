package uk.ac.sheffield.aca14sk;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * GraphicalDisplay.java
 * Concrete class for graphical display of Chess board and pieces
 * @author Sanjana Khot (skhot1@sheffield.ac.uk)
 *
 */
public class GraphicalDisplay extends JFrame implements Display,ActionListener {
	//constructor
	public GraphicalDisplay() {
		setSize(0,0); //size zero until players and pieces have been constructed
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//instance variables
	private JPanel gridButtons;
	private Piece[][] board;
	private JButton buttonPressed = null;
	private Container contentPane = getContentPane();
	private ButtonOperations buttonHandler;
	
	private static final String EMPTY_SQAURE = "";
	
	/**
	 * Method creates graphical chess board and displays board and pieces 
	 */
	public void showPiecesOnBoard(Piece[][] data) {
		setSize(700, 700);
		contentPane.removeAll();
		board = data; //array of Chess pieces
		gridButtons = new JPanel();
		gridButtons.setLayout(new GridLayout(8, 8));
		
		//looping through adding buttons to the Panel
		for (int i = 0; i < data.length; i++) { 
			for (int j = 0; j < data.length; j++) {
				JButton button = new JButton();
				storeCoordinates(j, i, button); //stores coordinates of buttons in String form
				button.setBorderPainted(false);
				button.setFocusPainted(false);
				
				if ((i + j) % 2 == 0) 
					button.setBackground(new Color(121,98,75));//dark squares
					
				else
					button.setBackground(new Color(201,170,103)); //light squares

				button.setFont((new Font("Arial Unicode MS",Font.PLAIN, 40)));
			
				button.addActionListener(this);

				if (data[j][i] == null)
					button.setText(EMPTY_SQAURE);
				else{
					//method for displaying Unicode symbols
					button.setText(data[j][i].toImage(data[j][i].toString())); 
					if(data[j][i].getColour() == PieceCode.WHITE)
						button.setForeground(Color.WHITE);
				}
				gridButtons.add(button);
			}
		}
		contentPane.add(gridButtons);
		contentPane.revalidate(); //redraws board 
		contentPane.repaint();
	}
	/**
	 * Method invoked when a button is pressed, calls method to handle button presses
	 * @param e  ActionEvent The event of a button press 
	 */
	public void actionPerformed(ActionEvent e) {
		buttonPressed = (JButton)e.getSource();
		buttonHandler.setButtonPressed(buttonPressed);
		contentPane.revalidate();
		contentPane.repaint();
	}
	/**
	 * Method called from Human Player, returns to and from coordinates
	 * @param p  Player  The instance of the Player making the move
	 * @return int[][]  Coordinates of the move
	 */
	public int[][] getBothCoords(Player p) {
		buttonHandler = new ButtonOperations(buttonPressed,board);
		return buttonHandler.getBothCoords(p);
	}

	//stores coordinates of a button after it is pressed
	private void storeCoordinates(int j, int i, JButton button) {
		String x = String.valueOf(j);
		String y = String.valueOf(i);
		button.setActionCommand(x + y);
	}

	/**
	 * Method indicates which player's turn it is graphically by setting title
	 * and colour of the border
	 * @param player  Player  The Player whose turn it is
	 */
	public void indicatePlayerTurn(Player player){
		setTitle(player + "'s turn.");
		
		int colour = player.getPieces().getPiece(0).getColour();
		if(colour == PieceCode.WHITE)
			getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE,20));
		else
			getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,20));
	}
}











