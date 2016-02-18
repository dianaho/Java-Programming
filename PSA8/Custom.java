import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

/** Class that creates the Custom critter */
public class Custom extends Critter
{
  public Custom ( int xInit, int yInit )
  {
    super ( xInit, yInit );
  }
  
  /** Paint the Critter appropriately using the graphic object g */
  public void paint( Graphics g )
  {
    // Setting shape color
    g.setColor( Color.MAGENTA );
    // Making a triangle 15 pixels in diameter with 
    // its center at the Custom's location
    g.drawLine( getX(), getY()-(15/2), getX()+(15/2), getY()+(15/2) );
    g.drawLine( getX(), getY()-(15/2), getX()-(15/2), getY()+(15/2) );
    g.drawLine( getX()-(15/2), getY()+(15/2), getX()+(15/2), getY()+(15/2) );
  }
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r */
  public void reactTo( Critter c, Rectangle r )
  {
//    // If the x coordinate of this critter is greater than that of Critter c
//    // and is less than the width of Rectangle
//    if( x > c.getX() && x < (r.getWidth() - 7) )
//      x = c.getX() + 30 ;
//    // If the x coordinate of this critter is less than that of Critter c
//    // and is greater than 7.5
//    else if( x < c.getX() && x > 7 )
//      x = c.getX() - 30;
//    // If the y coordinate of this critter is greater than that of Critter c
//    // and is less than the height of Rectangle
//    if( y > c.getY() && y < (r.getHeight() - 7) )
//      y = c.getY() + 30;
//    // If the y coordinate of this critter is less than that of Critter c
//    // and is greater than 7.5
//    else if( y < c.getY() && y > 7 )
//      y = c.getY() - 30;
//    
//    // A random number generator
//    java.util.Random random = new java.util.Random();
//    
//    x = x + random.nextInt(50) - 20;
//    y = y + random.nextInt(50) - 20;
//    
//    // If coordinates become out of bounds, set to bounds
//    if( x < 7 )
//      x = 7;
//    if( x > r.getWidth()-7 )
//      x = r.width-7;
//    if( y < 7 )
//      y = 7;
//    if( y > r.getHeight()-7 )
//      y = r.height-7;
    
    // Move x left at all times
    if(x > 7 && x < r.getWidth() && y > 7 && y < r.getHeight())
    {
      x--;
      // If x hits left boundary
      if (x == 7)
      {
        x = (int)r.getWidth() - 8;
        x--;
      }
    }
  }
}