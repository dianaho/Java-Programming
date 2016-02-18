import java.util.*;
import java.awt.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 8
 * Date: June 4, 2014
 */

/** Class that that holds the interact method,
  * which deals with the interactions between 
  * different critters in the world
  */
public class Interactor
{
  /** This method takes a Critter and a list of Critters.  It finds the 
    * Critter from the list of Critters that c will interact with.  Usually
    * this is the closest Critter in the list, but you might vary this slightly
    * (See the PSA instructions).  Then it asks the Critter c to reactTo
    * that Critter.  The Rectangle r is the bounds in which 
    * the Critters must stay, and should be passed to the reactTo method.
    * 
    * @param c A Critter whose turn it is to interact with
    * other Critters in the world
    * @cList An ArrayList of Critters
    * @r A Rectangle object, which specifies the current geometrical extent
    * of the world
    */
  // A reference to the closest Critter  
  int closeCritter;
  
  public void interact(Critter c, ArrayList<Critter> cList, Rectangle r)
  {    
    // A large distance to compare to
    double compareDistance = 999999999;
    
    // If there is only one Critter in the world, then there is no Critter
    // in the array that is not the critter itself, and so there is nothing
    // for that Critter to interact with
    if( cList.size() < 2 )
      return;
    
    // If there is 2 or more Critters in the world, find the one closest
    // to c and interact with it
    else
    {
      for( int i = 0; i < cList.size(); i++ )
      {
        // Check to make sure Critter is another critter than c
        if( cList.get(i) != c )
        {
          double distance = c.calcDistance( c, cList.get(i) );
          
          // If found distance is smaller than compareDistance, set new value
          if( distance < compareDistance )
          {
            compareDistance = distance;
            closeCritter = i;
          }
        }
      }
      c.reactTo( cList.get( closeCritter ), r );
    }
  }
}