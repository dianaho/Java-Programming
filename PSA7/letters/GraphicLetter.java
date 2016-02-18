import javax.swing.JPanel;

/**
 * Class GraphicLetter.
 * Author: Christine Alvarado
 * Date: April 25, 2013
 * 
 * This class implements the base functionality for a graphic letter
 * implemented in a JPanel.  It doesn't do anything yet.  You will
 * extend this class (and in doing so, extend JPanel) and override
 * each method declared below so that your class accurately implements
 * the graphic letter that you choose to implement.  However,
 * YOU DO NOT NEED TO (AND SHOULD NOT) MODIFY THIS CLASS AT ALL.
 * 
 * In fact, this class should probably be abstract because it doesn't 
 * actually implement any functionality and must be overridden, but
 * since we haven't learned about abstract classes yet, I didn't make it
 * abstract.  
 * 
 * 
 * */
public abstract class GraphicLetter extends JPanel
{
  /** Returns the character that this class displays graphically.
    * The character returned should reflect the case as well as the 
    * letter that is displayed.
    * @return The character that this graphic letter displays.
    * */
  public abstract char whichChar();
  
  /** Return a new instance of the particular GraphicLetter object.
    * @return A new instance of the GraphicLetter in question.
    * */
  public abstract GraphicLetter makeCopy();
  
  /** Return a string representation of the GraphicLetter */
  @Override
  public String toString() {
   return this.getClass().getName() + " : " + this.whichChar();
  }
}