/* YOU SHOULD ALWAYS INCLUDE A COMMENT AT THE TOP WITH
 * YOUR NAME (INCLUDING YOUR PARTNER IF YOU HAVE ONE), 
 * THE PSA NUMBER AND THE DATE.
 * 
 * Author: Diana Ho
 * PSA: 1
 * Date: April 7, 2014
 * 
 */

/** ListMatch is a class that implements some 
  * basic matching functions on a list */
public class ArrayMatch
{
  private int[] storedArray;
  
  /** The constructor.  Takes an input array that
    * is stored in the object to match against.
    * Makes a copy of the input array. */
  public ArrayMatch( int[] inputArray )
  {
    storedArray = new int[inputArray.length];
    for ( int i = 0; i < inputArray.length; i++ )
    {
      storedArray[i] = inputArray[i];
    }
  }
  
  // Recall that the toString method for any class returns a nice (printable)
  // string representation of the object.  toString is automatically called
  // when the object is passed to System.out.println.
  public String toString()
  {
    String ret = "[";
    for (int i = 0; i < storedArray.length-1; i++)
    {
      ret += storedArray[i] + ", ";
    }
    return ret + storedArray[storedArray.length-1] + "]";
  }
  // Below is a Javadoc style comment (you can tell because it begins with /**)
  // You should include such a comment before every method you write. 
  
  /** Checks to see if the stored array contains a particular int.
    * @param toCheck The integer to check against the array
    * @return true if the stored array contains toCheck, false if not
    * */
  public boolean contains( int toCheck )
  {
    for ( int i = 0; i < storedArray.length; i++ )
    {
      if ( toCheck == storedArray[i] )
        return true;
    }
    return false;
  }
  
/** Checks to see if the stored array contains at least four of a particular
  * integer consecutively
  * @param toCheck The integer to check against the array
  * @return true if the stored array contains consecutively at least four of 
  * toCheck, false if not
  */
  public boolean fourInARow( int toCheck )
  {
    int inARow = 1;
    for ( int i = 0; i < storedArray.length-1; i++ )
    {
      if ( toCheck == storedArray[i] ) //Check if toCheck exists in array
      {
        if ( storedArray[i] == storedArray[i+1] )
          inARow = inARow + 1;
        
        if ( inARow >= 4 )
          return true;
      }
    }
    return false;
  }
  
  /* main is where you should put tests.  We've included a few.  You should add more.
   * You can also do your testing in the interaction pane in Dr. Java.
   * */
  public static void main( String[] args )
  {
    int[] myArray = {2, 1, 1, 4, 3, 3, 3, 3, 1, 1};
    ArrayMatch tester = new ArrayMatch( myArray );
    System.out.println( "test array is " + tester );
    System.out.print( "Checking whether 1 is in the array (should be true)..." );
    System.out.println( tester.contains( 1 ) );
    System.out.print( "Checking whether 2 is in the array (should be true)..." );
    System.out.println( tester.contains( 2 ) );
    System.out.print( "Checking whether 0 is in the array (should be false)..." );
    System.out.println( tester.contains( 0 ) );
    
    // Adding extra test cases to make sure method works correctly
    System.out.print( "Checking whether 3 is in the array (should be true)..." );
    System.out.println( tester.contains( 3 ) );
    System.out.print( "Checking whether 4 is in the array (should be true)..." );
    System.out.println( tester.contains( 4 ) );
    System.out.print( "Checking whether 5 is in the array (should be false)..." );
    System.out.println( tester.contains( 5 ) );
    System.out.print( "Checking whether 7 is in the array (should be false)..." );
    System.out.println( tester.contains( 7 ) );
    
    System.out.print( "Checking whether there are four 3's in a row " + 
                         "(should be true )..." );
    System.out.println( tester.fourInARow( 3 ) );
    System.out.print( "Checking whether there are four 1's in a row " +
                         "(should be false )..." );
    System.out.println( tester.fourInARow( 1 ) );
    
    // Adding extra test cases
    System.out.print( "Checking whether there are four 2's in a row " +
                     "(should be false )..." );
    System.out.println( tester.fourInARow( 2 ) );
    System.out.print( "Checking whether there are four 0's in a row " +
                     "(should be false )..." );
    System.out.println( tester.fourInARow( 0 ) );
  }
}
