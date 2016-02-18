/**
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 5
 * Date: May 5, 2014
 */

import javax.swing.*;
import java.awt.*;

/** This class is used to test GraphicLetter_cs8sbbg1 and
  * GraphicLetter_cs8sbbg2 classes
  * It will create the frame and add the graphic letters,
  * and also our graphic art
  */
public class GraphicLetterTester
{
  public static void main( String[] args )
  {
    JFrame myFrame = new JFrame();
    
    // Setting layout to GridLayout with enough spaces to hold 2 copies of each letter
    myFrame.setLayout( new GridLayout( 2, 2 ) );
    myFrame.setSize( 600, 600 );
    
    // Making one copy of each letter and adding them to JFrame
    GraphicLetter_cs8sbbg1 a = new GraphicLetter_cs8sbbg1();
    myFrame.add( a );
    a.setBackground( new Color( 255, 75, 120 ));
    GraphicLetter_cs8sbbg2 b = new GraphicLetter_cs8sbbg2();
    myFrame.add( b );
    b.setBackground( new Color( 0, 0, 0 ));
    
    // Making another copy of each letters by calling makeCopy
    GraphicLetter a2 = a.makeCopy();
    myFrame.add( a2 );
    a2.setBackground( new Color( 0, 255, 255 ));
    GraphicLetter b2 = b.makeCopy();
    myFrame.add( b2 );
    b2.setBackground( new Color( 255, 245, 150));
    
    // Making the JFrame visible
    myFrame.setVisible( true );
  }
}