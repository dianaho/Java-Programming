import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/** A class that will create an Applet that adds letters from the GraphicLetterFactory
  * class onto a Panel.
  * Author: Diana Ho
  * PID: A10573610
  * Login: cs8sbbg
  * PSA: 7
  * Date: May 19, 2014
  */

public class LetterGenerator extends JApplet
{
  // You'll want to create one or more additional instance variables here
  // See the example from class (posted on the website) for some ideas
  
  /** The panel that will hold the graphical letters */
  private JPanel canvas; 
  
  /** The letter factory that generates the graphical letters */
  private GraphicLetterFactory factory;
  
  /** The text field at the bottom of the applet */
  private JTextField textField;
    
  /** The button to clear the canvas & text */
  private JButton clear;
    
    
  /** Initialize the applet. Instantiate all the class variables.
    * This is like the constructor for a normal class.
    * You will want to add to this method.
    */
  public void init() {
    System.out.println( "Initializing the applet" );
    canvas = new JPanel();
    
    // Initialize the letter factory.
    factory = new GraphicLetterFactory();
    
    canvas.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    // A Panel to hold a button and a text field
    JPanel input = new JPanel();
    input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
    
    // Create the text field at the bottom
    textField = new JTextField();
    textField.addKeyListener(new MessageListener());
    
    // Create button to clear the textfield
    clear = new JButton("Clear Text");
    clear.addActionListener(new ResetListener());
    
    // add everything, set the starting size.  This size will change when we set
    // the size in the index.html file.
    setLayout( new BorderLayout() );
    this.setSize(new Dimension(700,500));
    add( canvas, BorderLayout.CENTER );
    // Add textField and the clear button to the input panel
    input.add( textField );
    input.add( clear );
    // Add input panel to the bottom of the canvas
    add(input, BorderLayout.SOUTH);
    
    setVisible( true );
  }
  
  /** This class will be used to add letters to the message JPanel */
  class MessageListener implements KeyListener
  {
    public void keyPressed( KeyEvent e ) {}
    public void keyReleased( KeyEvent e ) {}
    
    /** This class will add new GraphicLetters to the JPanel 
      * @param e The key event
      */
    public void keyTyped( KeyEvent e )
    {
      canvas.add( factory.getRandomCharacterIgnoreCase( e.getKeyChar() ) );
      repaint();
      canvas.revalidate();
    }
  }
  
  /** This class will be used to reset the textField when the clear button is
    * clicked by the user 
    */
  class ResetListener implements ActionListener
  {
    /** Method to reset the board with a call to removeAll() which will remove
      * all of the components the JPanel object contains
      * @param e The action event
      */
    public void actionPerformed( ActionEvent e )
    {
      canvas.removeAll();
      textField.setText("");
      repaint();
    }
  }
  
}
