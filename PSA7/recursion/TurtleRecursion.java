/** A class that will use recursion to solve problems.
  * Author: Diana Ho
  * PID: A10573610
  * Login: cs8sbbg
  * PSA: 7
  * Date: May 19, 2014
  */

public class TurtleRecursion
{
  /** This method will create a spiral using recursion
    * @param turtle A drawing turtle to create lines
    * @param initialLength The initial length of the spiral's first line segment
    * @param angle An angle specifying the degree of the angle formed by 
    * neighboring segments
    * @param multiplier Indicates how much each segment changes in size from the
    * previous one
    */
  public void spiral(Turtle turtle, double initialLength, int angle,
                     double multiplier)
  {
    if ( initialLength >= 1 && initialLength <= 200 )
    {
      turtle.forward( (int) initialLength );
      turtle.turn( angle );
      initialLength = initialLength * multiplier;      
      spiral(turtle,initialLength,angle,multiplier);
    }
  }
  
  /** This method will create a tree using recursion
    * @param turtle A drawing turtle to create lines
    * @param trunkLength The length of the main trunk of the tree
    * @param height Indicates the number of levels of brancing of the tree
    */
  public void tree(Turtle turtle, int trunkLength, int height)
  {
    // Base case
    if( height <= 0 ) {}
    
    else 
    {
      turtle.forward( trunkLength );
      turtle.turn( -45 );
      // Recursive call
      tree( turtle, (int)(trunkLength * 0.5), height - 1);
      turtle.turn( 90 );
      // Recursive call for other half of tree
      tree( turtle, (int)(trunkLength * 0.5), height - 1);
      turtle.turn( -45 );
      turtle.backward( trunkLength );
    }
  }      
  
  /** This is the main method where we will test our recursion methods */
  public static void main(String[] args)
  {
    TurtleRecursion tr = new TurtleRecursion();
    
    // World for spiral
    World venus = new World();
    Turtle billyBarker = new Turtle( venus );
    tr.spiral( billyBarker, 1, -45, 1.1 );
    
    // World for tree
    World mars = new World();
    Turtle twerkz = new Turtle( mars );
    twerkz.penUp();
    twerkz.moveTo( 320, 480 );
    twerkz.penDown();
    tr.tree(twerkz, 256, 7);    
  }
}