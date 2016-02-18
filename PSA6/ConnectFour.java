/**
 * A class that visually represents & controls the play of the Connect Four Game
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 6
 * Date: May 14, 2014
 *
 * You should answer these questions BEFORE you start coding:
 * 
 *   1. To what type of object will you add an instance of a PlayListener 
 *      object (as a MouseListener)?
 *           A BoardCell object
 * 
 *   2. If your board has row r and column c , how many BoardCell objects do 
 *      you need to create? 
 *      To which component will you add these BoardCell objects?
 *           We will need to create r * c BoardCell objects
 *           We will need to add these objects to the JPanel displayBoard
 * 
 *   3. Why is the JLabel status an instance variable (as opposed to just a 
 *      local variable in the constructor)?
 *           It is an instance variable because it is continuously changing
 *           throughout the game. If it were just a local variable, other methods
 *           would not have access to it, and therefore cannot change it.
 * 
 *   4. Where is the information about what contents are stored in each cell 
 *      located?  
 *      What method must the BoardCell call in its paintComponent method to 
 *      determine what color to paint the "checker"?
 *           Information about what contents are stored in each cell is located
 *           in theBoard, which is a ConnectFourBoard object.
 *           The method that BoardCell's paintComponenet must call is getContents(),
 *           which checks the contents of the BoardCell and then repaints() it.
 * 
 *   5. Which method will determine when the game is over (by calling methods 
 *      on the ConnectFourBoard object theBoard)?  
 *      Which method will detect illegal moves (again by calling methods on the 
 *      ConnectFourBoard object theBoard)?
 *           winsFor in makeMove() will determine when the game is over.
 *           allowsMove will detect whether or not move is illegal.
 * 
 *   6. Will you need to create a separate listener to handle clicks on the New
 *      Game button, or will you use another instance of the PlayListener class?
 *           We will need to create a separate listener to handle clicks on the
 *           New Game button, not using another instance of PlayListener.
 * 
 *   7. How do you run the game?
 *           By running a new ConnectFour object in the main method.
 * 
 */


// Import all the necessary built-in java stuff
import javax.swing.*;      // For swing classes (the "J" classes)
import java.awt.*;         // For awt classes (e.g., Dimension)
import java.awt.event.*;   // For events (which you will implement)

/** A class that implements a graphical version of Connect 4. */
public class ConnectFour extends JFrame
{
  
  /** The underlying board that will hold the state of the game */
  private ConnectFourBoard theBoard;
  
  /** Whose turn it is.  We use 'X' and 'O', but we will translate 'X's and 
    * 'O's into colors to display them
    */
  private char turn;
  
  /** The status message at the top of the window */
  private JLabel status;
  
  /** Create a new Connect 4 game that is 7x6.  */
  public ConnectFour()
  {
    this( 6, 7 );
  }
  
  /** Create a new ConnectFour game with the specified row and column
    * @param row The specified row
    * @param column The specified column
    */
  public ConnectFour( int row, int column )
  {
    // X starts
    this.turn = 'X';
    
    // Make a new underlying board.
    this.theBoard = new ConnectFourBoard(row, column);
    
    // The reset button.
    JButton jbtReset = new JButton( "New Game" );
    jbtReset.addActionListener(new resetListener());
    
    // This message will always display the current status
    // of the game (e.g., whose turn it is, whether the game is 
    // over, who won, etc).
    this.status = new JLabel( "<html>Welcome to Connect 4! Enjoy your game (:<br>"
                               + this.turn + "'s turn</html>" );
    
    // This is the panel that will hold the BoardCells. 
    // You will need to populate it.  I suggest writing a helper
    // method to create the BoardCell objects and add them to
    // the JPanel.  You will want to use a GridLayout on the displayBoard
    // to lay out the BoardCell objects.
    JPanel displayBoard = new JPanel();
    displayBoard.setLayout( new GridLayout( row, column ) );   // Set layout
    
    for ( int i = 0; i < row; i++ )
    {
      for ( int j = 0; j < column; j++ )
      {
        JPanel p = new BoardCell( i, j );   // Creating neessary number of BoardCell objects
        displayBoard.add( p );   // Adding objects to displayBoard
      }
    }
    
    // Use a BorderLayout to lay out the game board
    setLayout( new BorderLayout() );
    add( status, BorderLayout.NORTH );
    add( displayBoard, BorderLayout.CENTER );
    add( jbtReset, BorderLayout.SOUTH );
    
    // Size and show the board
    pack();
    setVisible( true );
  }
  
  
  /** This method is called when the BoardCell is clicked on
    * Checks to make sure the game is not over, otherwise do nothing
    * Determines if move is allowed, then makes the move in specified column
    * Changes the players' turns
    * Rechecks if game is over. If so, update status message to reflect whose
    * turn it is
    * @param col The specified column
    */
  private void makeMove( int col )
  {
    // Checks to make sure neither player has won & board is not full
    if ( !theBoard.winsFor('X') && !theBoard.winsFor('O') && !theBoard.isFull() )
    {
      // If move is allowed, add move
      if ( theBoard.allowsMove( col ) )
      {
        theBoard.addMove( col, turn );
        
        // Switch turn to O if currently X & set status
        if ( this.turn == 'X' )
        {
          this.turn = 'O';
          this.status.setText( "It is " + turn + "'s turn. Make your play!");
        }
        
        // Switch turn to X if currently O & set status
        else if ( this.turn == 'O' )
        {
          this.turn = 'X';
          this.status.setText( "It is " + turn + "'s turn. Make your play!");
        }
      }
            
      // If move is not allowed, display message
      else if ( !theBoard.allowsMove( col ) )   // If move is not allowed, display message
        this.status.setText( "Illegal move! Click again" );
    }
    
    // Check for any wins or tie
    if ( theBoard.winsFor('X') )
      this.status.setText("<html>GAME OVER!<br> Congratulations!! Player X wins!</html>");
    else if ( theBoard.winsFor('O') )
      this.status.setText("<html>GAME OVER!<br> Congratulations!! Player O wins!</html>");
    else if ( !theBoard.winsFor('O') && !theBoard.winsFor('X') && theBoard.isFull() )
      this.status.setText("<html>GAME OVER!<br> There was a tie. Play again!</html>");
    
    repaint();
  }
  
  
  /** An inner class that represents one graphical cell in the connect 4 board.
    * Each cell keeps track of what row and column it is in.
    * These are the objects that will listen for mouse clicks.
    * Because they are an inner class, they have access to all of
    * the methods in the ConnectFour outer class.  
    * 
    * Notice that a BoardCell object IS A JPanel, so it can be added directly
    * to a ConnectFour object (which IS A JFrame).  You can also add listeners
    * to JPanels (and BoardCells, since they are JPanels), which will be useful
    * to detect where the user wants to play.  */
  class BoardCell extends JPanel
  {
    /** The row in which this BoardCell appears in the board. */
    private int row;
    
    /** The column in which this BoardCell appears in the board. */
    private int column;
    
    /** Create a new BoardCell object at row r and column c.
      * @param r The specified row
      * @param c The specified column
      */
    BoardCell( int r, int c )
    {
      JPanel panel = new JPanel();
      this.row = r;
      this.column = c;
      this.addMouseListener( new PlayListener() );   // Add new listener to theBoard
    }
    
    /** Return the preferred size for this BoardCell */
    public Dimension getPreferredSize()
    {
      // Just a suggestion. Feel free to change it if you want.  
      return new Dimension( 50, 50 );
    }
    
    // Paint the BoardCell.  Note that this method should paint empty cells 
    // in one color, cells filled with an 'X' with another color, and cells 
    // filled with an 'O' in a third color.  
    /** This method overrides the JPanel's paintComponent method
      * It will first set the background to be gray
      * Also, it will set the color of the specified checkers
      * and draw a filled in oval
      * Also, it will check if the board is empty, and if so will
      * change the status
      * @param g refers to the graphic object
      */   
    protected void paintComponent( Graphics g )
    { 
      super.paintComponent( g );
      this.setBackground( Color.GRAY );

      // Set BoardCell color if cell is filled with an 'X'
      if ( theBoard.getContents( this.row, this.column ) == 'X' )
      {
        g.setColor( new Color(229, 5, 124));   // Set color to magenta
      }
      
      // Set BoardCell color if cell is filled with an 'O'
      else if ( theBoard.getContents( this.row, this.column ) == 'O' )
      {
        g.setColor( new Color(0, 236, 236));   // Set color to turquoise
      }
      
      // Set BoardCell color if cell is empty
      else
        g.setColor( Color.WHITE );
      
      g.fillOval(0,0,(int)(.9*getWidth()),(int)(.9*getHeight()));
    }
    
    /** This is the listener that will handle clicks on the board cell.
      * You will not need to change this code at all, but you should understand 
      * what is going on.
      */
    class PlayListener implements MouseListener
    {
      /** Inform the ConnectFour object that the corresponding column has been clicked
        * @param e Refers to an event
        */
      public void mouseClicked( MouseEvent e ) 
      {
        // We just need to tell the board to play a checker in the appropriate
        // column.  column refers to the instance variable in the BoardCell
        // object.  This method calls the makeMove method in the ConnectFour class. 
        makeMove( column );
      }
      
      public void mousePressed( MouseEvent e ) {}
      public void mouseReleased( MouseEvent e ) {}
      public void mouseEntered( MouseEvent e ) {}
      public void mouseExited( MouseEvent e ) {}
    }
  }
  
  /** This is the listener for the reset button */
  class resetListener implements ActionListener
  {
    /** Resets the game
      * Clears the ConnectFourBoard object
      * @param e Refers to action event
      */
    public void actionPerformed( ActionEvent e )
    {
      theBoard.clear();
      repaint();
    }
  }
  
  /** Main method */
  public static void main( String[] args )
  {
    new ConnectFour();
  }
}
