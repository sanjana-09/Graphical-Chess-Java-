package uk.ac.sheffield.aca14sk;
/**
 * Class to represent types of Players
 * @author Sanjana Khot (skhot1@shefield.ac.uk)
 *
 */
public enum PlayerType {
	HUMAN, RANDOM, AGGRESSIVE;
	
	/**
	 * Converts enum to corresponding String value
	 * @return  String  The String representing type of Player
	 */
	public String toString(){
		switch (this){
		case HUMAN: return "Human";
		case RANDOM: return "Random";
		case AGGRESSIVE: return "Aggressive";
		default:
			return "";
		}
	
	}
}
