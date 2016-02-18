// Don't remove these lines.  They tell java where to find various classes
// you will use.
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 3
 * Date: April 21, 2014
 */

public class WordCloud
{
  // These are the two instance variables that you will use to keep 
  // track of the unique words in the file and their counts
  
  
  /** An array of unique strings, to appear in the word cloud */
  public String[] uniqueWords;
  
  
  /** Counts corresponding to the number of times each word in the 
    * uniqueStrings array appeard in the original file.  These counts
    * will be used to size the words in the word cloud.  */
  public int[] counts;
    
  
  /** Read all words in given file and return a sorted array
    * @param filename File from which words are read
    * @return A sorted array of Strings with one entry for each word in the file
    */
  public String[] getWordsFromFile( String filename ) throws IOException
  {
    // Create a Scanner for input
    File sourceFile = new File( filename );  // Get filename
    Scanner input = new Scanner( sourceFile );  // Read from filename
    
    // Reads a line from the source file and sorts array
    String text = "";
    
    // Reads a line from the source file and concatenates the lines to a 
    // single string, adding a space in between each line from original file
    while ( input.hasNext() )
    { 
      String s1 = input.nextLine();
      text = text.concat( s1 + " " );
    }
    
    // Splitting the concatenated string by spaces and making a string array
    String[] allWords = text.split( " " );
    
    // Sorting the string array
    Arrays.sort( allWords );

    System.out.println(Arrays.toString(allWords));
    input.close();
    
    return allWords;
  }
  
  /** Takes a sorted array of Strings and modifies the instance variables
    * uniqueWords and counts. Does not return anything
    * @param words The array to input
    */
  public void setUniqueAndCounts( String[] words )
  {
    int size = sizeOfArray( words );  //Getting size of array
//    System.out.print( size );

    uniqueWords = new String[ size ];  //Instantiate new array
    String prev = words[0];  //Setting two temp strings
    String curr = words[0];     
    int a = 0;
    int j = 0;
    for( int i = 0; i < words.length; i++ )  //Looping through input string
    {
      curr = words[j];
      if( !prev.equals(curr) )  //Condition
      {
        uniqueWords[a] = prev;  //Fill in array
        a++;
        prev = curr;
      }
      j++;
    } 
    uniqueWords[a] = prev; //Set last index of uniqueWords to last word of input
//    System.out.print(Arrays.toString(uniqueWords));    
    
    counts = new int[ size ];  //Instantiate new array
    String previous = words[0];  //Set temp strings
    String current = words[0];
    int u = 0;
    int counter = 0;  //Instantiate counter
    for( int i = 0; i < words.length; i++ )  //Looping through input array
    {
      current = words[i];
      if( previous.equals(current))
      {
        counter++;
      }
      else
      {
        counts[u] = counter;  //Fill in array
        counter = 1;
        u++;
        previous = current;
      }
    }
    counts[u] = counter;
    System.out.print(Arrays.toString(counts));
  }
  
  /** HELPER METHOD to count number of unique words in sorted string array
    * @param array The string array to input
    * @return The number of unique words
    */
  private int sizeOfArray( String[] array )
  {
    int number = 0;  //Initializing a counter for unique words to zero
    
    if ( array != null && array.length > 0 )  //Check for empty array
    {
      for ( int i = 0; i < array.length - 1; i++ )  //Looping through array
      {
        if ( !array[i].equals( array[i+1] ) )  //Loop condition
          number = number + 1;  //Increment counter per different word
      }
    }
    else
      System.out.println( "Array is not initialized or is empty" );
    
    number = number + 1;  //Add one for first, uncounted word
//    System.out.print(number);
    return number;
  }
  
  /**Creates a GUI with all of the words in it, sized based on their counts
    */
  public void displayWords()
  {
    // Create the JFrame
    JFrame myWindow = new JFrame("WordCloud");
    // Create the layout
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
    myWindow.setLayout(flow);
    // Create the label and set the font; set the size based on counts
    for (int i = 0; i <uniqueWords.length; i++) {
      JLabel label = new JLabel(uniqueWords[i]);
      label.setFont(new Font("Serif", Font.BOLD, counts[i]*15));
      myWindow.add(label);
    }
    myWindow.pack();
    // Set size of the window
    myWindow.setSize(400, 400);
    // So that it is visible
    myWindow.setVisible(true);
  }
  
  /** Makes the cloud 
   * @param filename the file to be used to create the cloud
   */
  public void makeCloud( String filename ) throws IOException
  {    
    setUniqueAndCounts(getWordsFromFile(filename));
    displayWords();
  }
  
  // You can (and should) add a main method here for testing
  public static void main( String[] args ) throws IOException
  {
    //Part 1. Scrabble Scoring Tests
    WordCloud f = new WordCloud();
//    String[] test = f.getWordsFromFile("KnockKnock.txt");
//    f.sizeOfArray(test);
//    f.count(test);
    f.makeCloud("KnockKnock.txt");
  }
}
