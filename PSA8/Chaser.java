import java.awt.*;
import javax.swing.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

/** Class that creates the Chaser critter */
public class Chaser extends Critter
{
  public Chaser ( int xInit, int yInit )
  {
    super ( xInit, yInit );
  }
  
  /** Paint the Critter appropriately using the graphic object g */
  public void paint( Graphics g )
  {
    // Setting shape color
    g.setColor( Color.RED );
    // Making an oval 15 pixels in diameter with 
    // its center at the Chaser's location
    g.fillOval( getX() - (15/2), getY() - (15/2), 15, 15 );
  }
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r 
    * @param c The Critter to react to
    * @param r The rectangle whose bounds will the the boundaries to stay in
    */
  public void reactTo( Critter c, Rectangle r )
  {
    // If the x coordinate of this critter is greater than that of Critter c
    // and is less than the width of Rectangle, decrease
    if( x > c.getX() && x < (r.getWidth() - 7) )
      x--;    
    // If the x coordinate of this critter is less than that of Critter c
    // and is greater than 7.5, increase
    else if( x < c.getX() && x > 7 )
      x++;
    // If the y coordinate of this critter is greater than that of Critter c
    // and is less than the height of Rectangle, decrease
    if( y > c.getY() && y < (r.getHeight() - 7) )
      y--;
    // If the y coordinate of this critter is less than that of Critter c
    // and is greater than 7.5, increase
    else if( y < c.getY() && y > 7 )
      y++;
  }
}