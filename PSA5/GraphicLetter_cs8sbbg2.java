/**
 * A class that represents the initials of my name
 * Author: Diana Ho
 * PID: A10573610
 * Login: cs8sbbg
 * PSA: 5
 * Date: May 5, 2014
 */

import java.awt.*;
import javax.swing.JPanel;

/** This class extends GraphicLetter and overrides some of their methods
  * It will implement GraphicLetter and creates a GUI of a letter of 
  * our name.
  */

public class GraphicLetter_cs8sbbg2 extends GraphicLetter
{
  /** This method will return a character representation of the letter
    * that the graphic depicts    
    * @return a char representation of the letter 'H'
    */
  public char whichChar()
  {
    return 'H';
  }
  
  /** This method will return a new instance of the graphic character object
    * that is implemented in this class
    * @return a GraphicLetter object
    */
  public GraphicLetter makeCopy()
  {
    return new GraphicLetter_cs8sbbg2();
  }
  
  /** This method returns a dimension object that specifies the size
    * at which your graphic letter displays best
    * @return returns a dimension object
    */
  public Dimension getPreferredSize()
  {
    return new Dimension(80,80);
  }
  
  /** This method will draw the graphic letter
    * @param g A graphic object representation
    */
  protected void paintComponent( Graphics g )
  {
    // Call the paintComponent of the JPanel.  
    // Leave this line in when you add to this method.
    super.paintComponent(g);
    
    // Get the widths and heights
    int width = getWidth();
    int height = getHeight();
    
    // Create a Graphics2D g2 for changing the thickness of our line
    Graphics2D g2 = (Graphics2D)g;
    // Set color to black for line
    g2.setColor(Color.MAGENTA);
    // Set thickness of line
    g2.setStroke(new BasicStroke(30));
    // Draw our lines
    g2.drawLine((int)(width * 0.3), (int)(height * 0.2), (int)(width * 0.3),
   (int)(height * 0.8));
    g2.drawLine((int)(width * 0.7), (int)(height * 0.2), (int)(width * 0.7),
   (int)(height * 0.8));
    g2.drawLine((int)(width * 0.3), (int)(height * 0.5), (int)(width * 0.7),
   (int)(height * 0.5));
  }
}