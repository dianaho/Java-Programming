import java.awt.*;
import javax.swing.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

/** Class that creates the Random critter */
public class Random extends Critter
{
  public Random ( int xInit, int yInit )
  {
    super ( xInit, yInit );
  }
  
  /** Paint the Critter appropriately using the graphic object g */
  public void paint( Graphics g )
  {
    // Setting shape color
    g.setColor( Color.BLUE );
    // Making the X shape, where the center is at the Random critter's location
    g.drawLine( getX()-(15/2), getY()-(15/2), getX()+(15/2), getY()+(15/2) );
    g.drawLine( getX()-(15/2), getY()+(15/2), getX()+(15/2), getY()-(15/2) );
  }
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r */
  public void reactTo( Critter c, Rectangle r )
  {
    // A random number generator
    java.util.Random random = new java.util.Random();
    
    x = x + random.nextInt(21) - 10;
    y = y + random.nextInt(21) - 10;
    
    // If coordinates become out of bounds, set to bounds
    if( x < 7 )
      x = 7;
    if( x > r.getWidth()-7 )
      x = r.width-7;
    if( y < 7 )
      y = 7;
    if( y > r.getHeight()-7 )
      y = r.height-7;
  }
}