import java.awt.*;
import javax.swing.*;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

// This is the base class for the Critter assignment
public abstract class Critter
{
  /** The x location of the MIDDLE of the Critter */
  protected int x;
  /** The y location of the MIDDLE of the Critter */
  protected int y;
  
  /**  This is if you would like to use an image for your Critter */
  protected BufferedImage img;
  
  /** Create a new Critter at position xInit, yInit 
    * @param xInit The starting x location
    * @param yInit The starting y location
    */
  public Critter( int xInit, int yInit )
  {
    x = xInit;
    y = yInit;
  }
  
  /** Return the x location of the Critter. */
  public int getX()
  {
    return x;
  }
  
  /** Return the y location of the Critter */
  public int getY()
  {
    return y;
  }
  
  /** Paint the Critter appropriately using the graphic object g */
  public abstract void paint( Graphics g );
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r */
  public abstract void reactTo( Critter c, Rectangle r );
  
  // You will probably want to add another method (or more than one) 
  // here.  For example, a very useful method calculates the distance
  // from this Critter to an x, y location (and/or to another Critter).
  
  /** Helper method to calculate the distance between two critters
    * @param c1 The first critter
    * @param c2 The second critter
    * @return A double value of the distance
    */
  public double calcDistance( Critter c1, Critter c2 )
  {
    // Getting coordinates of c1 and c2
    double x1 = c1.getX();
    double y1 = c1.getY();
    double x2 = c2.getX();
    double y2 = c2.getY();
    
    // Calculating distance using distance formula
    double distance = Math.sqrt( ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1)) );
    
    return distance;
  }
}