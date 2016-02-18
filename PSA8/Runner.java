import java.awt.*;
import javax.swing.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

/** Class that creates the Runner critter */
public class Runner extends Critter
{
  public Runner ( int xInit, int yInit )
  {
    super ( xInit, yInit );
  }
  
  /** Paint the Critter appropriately using the graphic object g */
  public void paint( Graphics g )
  {
    // Setting shape color
    g.setColor( Color.GREEN );
    // Making a square 15 pixels in length for a side with 
    // its center at the Runner's location
    g.fillRect( getX() - (15/2), getY() - (15/2), 15, 15 );
  }
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r */
  public void reactTo( Critter c, Rectangle r )
  {
    // If the x coordinate of this critter is greater than that of Critter c
    // and is less than the width of Rectangle, increase
    if( x > c.getX() && x < (r.getWidth() - 7) )
      x++;
    // If the x coordinate of this critter is less than that of Critter c
    // and is greater than 7, decrease
    else if( x < c.getX() && x > 7 )
      x--;
    // If the y coordinate of this critter is greater than that of Critter c
    // and is less than the height of Rectangle, increase
    if( y > c.getY() && y < (r.getHeight() - 7) )
      y++;
    // If the y coordinate of this critter is less than that of Critter c
    // and is greater than 7, decrease
    else if( y < c.getY() && y > 7 )
      y--;
  }
}
