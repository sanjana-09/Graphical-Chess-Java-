package uk.ac.sheffield.aca14sk;
import java.util.*;
/*
 * Display.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Display.java 
*
* An interface to specify what a text or graphical display needs to do
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public interface Display {
  public void showPiecesOnBoard(Piece[][] data);
  
  //New methods added for aiding functionality of Graphical Display
  public int[][] getBothCoords(Player p);
  public void indicatePlayerTurn(Player player);
}

