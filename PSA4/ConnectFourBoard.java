/**
 * A class that represents the board for the game of Connect Four
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 4
 * Date: April 28, 2014
 */

import java.util.*;

public class ConnectFourBoard
{
  ///////////////FIELDS///////////////
  
  private char[][] board;  //A two-dimensional list of characters
  private int numRows;  //An integer storing the number of rows on the game board
  private int numColumns; //An integer storing the number of columns
  
  /** Constructor for the ConnectFourBoard
    * @param rows The number of rows to set
    * @param columns The number of rows to set
    */
  public ConnectFourBoard( int rows, int columns )
  {
    this.numRows = rows;
    this.numColumns = columns;
    this.board = new char[numRows][numColumns];
    
    //Setting each position in array to ' '
    for( int i = 0; i < this.numRows; i++ )
    {
      for( int j = 0; j < this.numColumns; j++ )
        board[i][j] = ' ';
    }
  }
  
  /** Default constructor which initializes numRows to 6, numColumns to 7,
    * and initialize the board as a 6-rows by 7-columns  array of spaces
    */
  public ConnectFourBoard()
  {
    this( 6, 7 );
  }
  
  /** Returns a string representing the ConnectFourBoard object that calls it.
    * Each "checker" takes up one space
    */
  public String toString()
  {
    String toReturn = new String();
    
    // Nested loop to create the vertical columns here
    for ( int row = 0; row < this.numRows; row++ )
    {
      for ( int col = 0; col < this.numColumns; col++ )
      {        
        toReturn += "|" + this.board[row][col];
      }
      toReturn += "|\n";
    }
    
    // Create the line at the bottom of the board
    for ( int rnum = 0; rnum < this.numColumns; rnum++ )
    {
      toReturn += "--"; 
    }
    toReturn += "-\n";
    
    // Create the column numbers   
    for ( int rnum = 0; rnum < this.numColumns; rnum++ )
    {
      toReturn += " " + rnum % 10; 
    }
    toReturn += "\n";
    
    return toReturn;
  }
  
  /** Adds a checker to the board
    * @param columns The index of the column to which the checker will be added
    * @param checker The character representing the checker to add to the board
    */
  public void addMove(int columns, char checker)
  {
    for ( int row = this.numRows-1; row >= 0; row--)
    {
      if ( this.board[row][columns] == ' ')
      {
        this.board[row][columns] = checker;
        break;
      }
    }
  }
  
  /** Clears the board that calls it by setting each position in the board
    * 2D array to ' ' (space).
    */
  public void clear()
  {
    for ( int row = 0; row < this.numRows; row++ )
    {
      for ( int column = 0; column < this.numColumns; column++ )
        this.board[row][column] = ' ';
    }
  }
  
  /** Takes in a string of columns and places
    * alternating checkers in those columns,
    * starting with 'X'
    * 
    * For example, call b.setBoard("012345")
    * to see 'X's and 'O's alternate on the
    * bottom row, or b.setBoard("000000") to
    * see them alternate in the left column.
    * 
    * @param moveString A string of integers. 
    *    Note that this method will only play 
    *    in the first 10 columns of a board.
    */   
  public void setBoard( String moveString )
  {
    char nextCh = 'X';   // start by playing 'X'
    for ( int i = 0; i < moveString.length(); i++ )
    {
      int col = Character.getNumericValue( moveString.charAt( i ) );
      if ( 0 <= col && col < numColumns )
        addMove(col, nextCh);
      if ( nextCh == 'X' )
        nextCh = 'O';
      else
        nextCh = 'X';
    }
  }
  
  /** Checks that input column is within the range from 0 to the last column
    * Also makes sure that there is still room left in the column
    * @param column The input column
    * @return true if the calling object DOES allow a move into inputted column
    */
  public boolean allowsMove( int column )
  {
    if ( column >= 0 
          && column < this.numColumns 
          && this.board[0][column] == ' ')
      return true;
    
    else
      return false;
  }
  
  /** Checks to see if the board is completely full of checkers
    * @return true if the calling object is full
    */
  public boolean isFull()
  {
    int counter = 0;
    for ( int col = 0; col < this.numColumns; col++ )  // Loop though all the columns
    {
      if ( this.allowsMove( col ) == false )  // Implement allowsMove
       counter++;  // Increase counter if a move is not allowed in given column
    }
    
    if ( counter == this.numColumns )
      return true;
    
    else
      return false;
  }
  
  /** Takes a character input and check if that checker won
    * @return true if there are 4 checkers of type checker in a row on the board
    */
  public boolean winsFor( char checker )
  {
    if ( winHorizontal( checker ) == true )
      return true;
    
    else if ( winVertical( checker ) == true )
      return true;
    
    else if ( winDiagonal1( checker ) == true )
      return true;
    
    else if ( winDiagonal2( checker ) == true )
      return true;
    
    else
      return false;
  }
  
  /** Check for horizontal wins
    * @return true if there exists a horizontal four-in-a-row of checker
    */
  private boolean winHorizontal( char checker )
  {
    for ( int r = 0; r < numRows; r++)
    {
      for ( int c = 0; c < numColumns-3; c++)
      {
        if ( board[r][c] == checker 
              && board[r][c+1] == checker 
              && board[r][c+2] == checker
              && board[r][c+3] == checker )
          return true;
      }
    }
    return false;
  }
  
  /** Check for vertical wins
    * @return true if there exists a vertical four-in-a-row of checker
    */
  private boolean winVertical( char checker )
  {
    for ( int r = 0; r < numRows-3; r++)
    {
      for ( int c = 0; c < numColumns; c++)
      {
        if ( board[r][c] == checker 
              && board[r+1][c] == checker 
              && board[r+2][c] == checker
              && board[r+3][c] == checker )
          return true;
      }
    }
    return false;
  }
  
  /** Check for backward diagonal wins
    * @return true if there exists a backwards diagonal four-in-a-row of checker
    */
  private boolean winDiagonal1( char checker )
  {
    for ( int r = 0; r < numRows-3; r++)
    {
      for ( int c = 0; c < numColumns-3; c++)
      {
        if ( board[r][c] == checker 
              && board[r+1][c+1] == checker 
              && board[r+2][c+2] == checker
              && board[r+3][c+3] == checker )
          return true;
      }
    }
    return false;
  }
  
  /** Check for forward diagonal wins
    * @return true if there exists a forward diagonal four-in-a-row of checker
    */
  private boolean winDiagonal2( char checker )
  {
    for ( int r = 3; r < numRows; r++)
    {
      for ( int c = 0; c < numColumns-3; c++)
      {
        if ( board[r][c] == checker 
              && board[r-1][c+1] == checker 
              && board[r-2][c+2] == checker
              && board[r-3][c+3] == checker )
          return true;
      }
    }
    return false;
  }
  
  /** Hosts a new game of Connect Four between two people
    * Alternate turns between 'X' (who will always go first) 'O' (who will
    * always go second).
    * Will ask the user (with the input function) to select a column number for
    * move
    */
  public void hostGame()
  {
    System.out.println( "Welcome to Connect Four! :)");
    this.clear();
    char currPlayer = 'X';
    
    Scanner input = new Scanner(System.in);
    while( !isFull() )
    {
      System.out.println( this );
      int column = -1;
      System.out.println( "Player " + currPlayer + "'s turn.");
      do 
      {
        System.out.print( "Which column? " );
        column = input.nextInt();
        if ( !this.allowsMove( column ))
          System.out.println( "Illegal move! Choose again." );
        else
          System.out.println( currPlayer + "'s choice: " + column );
      } while ( !this.allowsMove( column ));
      
      this.addMove( column, currPlayer );
      if ( this.winsFor('X') == true )
      {
        System.out.println( this ); 
        System.out.println( "X wins!" );
        break;
      }
      else if ( this.winsFor('O') == true )
      {
        System.out.println( this );
        System.out.println( "O wins!" );
        break;  
      }
      else if ( this.isFull() == true )
      {
        System.out.println( this );
        System.out.println( "This is a tie!" );
        break;
      }
      
      if ( currPlayer == 'X' )
        currPlayer = 'O';
      else
        currPlayer = 'X';
    }
  }
  
  public static void main( String[] args )
  {
    // Testing toString method
//    ConnectFourBoard game1 = new ConnectFourBoard(5,15);
//    System.out.print( game1 );
    
    // Testing addMove
//    ConnectFourBoard b = new ConnectFourBoard(7,7);
//    b.addMove(0, 'X');
//    b.addMove(1, 'O');
//    b.addMove(2, 'X');
//    b.addMove(3, 'O');
//    b.addMove(4, 'O');  // cheating by letting O go again!
//    b.addMove(5, 'O');
//    b.addMove(6, 'O');
//    System.out.println( b );
//    
//    b.clear(); // Testing clear
//    System.out.println( b );
    
    // Testing allowsMove
//    ConnectFourBoard b = new ConnectFourBoard(5,5);
//    System.out.println( b );
//    b.addMove(0, 'X');
//    b.addMove(0, 'O');
//    b.addMove(0, 'X');
//    b.addMove(0, 'O');
//    b.addMove(0, 'O');
//    System.out.println( b );
//    System.out.println( "Is Column 3 allowed? " + b.allowsMove(3) );
//    System.out.println( "Is Column 2 allowed? " + b.allowsMove(2) );
//    System.out.println( "Is Column 0 allowed? " + b.allowsMove(0) );
    
    // Testing isFull
//    ConnectFourBoard b = new ConnectFourBoard(5,5);
//    System.out.println( b );
//    b.setBoard( "1401234124012340220" );
//    System.out.println( b );
//    System.out.println( "Is board full? " + b.isFull() );
    
    // Testing winsFor
//    ConnectFourBoard b = new ConnectFourBoard();
//    System.out.println( b );
//    b.setBoard( "01023002134345253" );
//    System.out.println( b );
//    System.out.println( "Did X win? " + b.winsFor('X') );
//    System.out.println( "Did O win? " + b.winsFor('O') );
    
    // Testing hostGame
    ConnectFourBoard b = new ConnectFourBoard();
    b.hostGame();
    
  }
}