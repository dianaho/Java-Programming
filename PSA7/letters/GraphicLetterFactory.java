import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * A GraphicLetter factory that returns random letters. Each letter is mapped to
 * a List<GraphicLetter>, given a .jar file containing many GraphicLetter
 * classes.
 * 
 * @author Christine Luu
 */
public class GraphicLetterFactory {
  // Contains a mapping of letters to lists of GraphicLetters
  private HashMap<Character, ArrayList<GraphicLetter>> graphicLetterMap;

  /**
   * Creates a new GraphicLetterFactoy, and constructs a map of Characters to
   * Lists of GraphicLetters using the letterLibrary.jar file.
   * <p>
   * This constructor searches for the letter library jar file in two ways. This
   * is to enable two different ways of testing/running the main application.
   * <p>
   * Method 1: Search for the jar file as a resource. This is for the case where
   * the letter library is packaged in a jar file for execution as a web applet.
   * <p>
   * Method 2: Search for the jar file on the file system. This is for local
   * testing only.
   */
  public GraphicLetterFactory() {
    // Load the letterLibrary.jar -- if don't have GraphicLetter.java in dev
    // directory, should add this jar to the build path
    ZipInputStream zStream = null;
    InputStream iStream = this.getClass().getResourceAsStream(
        "letterLibrary.jar");
    if (iStream == null) {
      try {
        zStream = new ZipInputStream(new FileInputStream("letterLibrary.jar"));
      } catch (FileNotFoundException e) {
        System.out.println("The letter library cannot be found!");
        System.exit(0);
      } catch (Exception e) {
        System.exit(0);
      }
    } else {
      zStream = new ZipInputStream(iStream);
    }

    try {
      this.graphicLetterMap = createLetterMap(zStream);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  /**
   * Creates a map of letter lists.
   * 
   * @param file
   *          is the JAR file to search.
   * @return the HashMap of letter lists.
   * @throws Exception
   */
  private HashMap<Character, ArrayList<GraphicLetter>> createLetterMap(
      ZipInputStream stream) throws Exception {
    if (stream == null)
      return null;

    HashMap<Character, ArrayList<GraphicLetter>> graphicLetterMap = 
        new HashMap<Character, ArrayList<GraphicLetter>>();

    // get all files from the JAR
    ZipEntry currFile;
    while ((currFile = stream.getNextEntry()) != null) {
      String name = currFile.getName(); // should be something like
                                        // "ClassName.class"

      // skip the MANIFEST and anything that's not a class
      // also skip if it's the superclass
      if (name.endsWith(".class") && !name.equals("GraphicLetter.class")) {
        // Create the object
        name = name.replace(".class", "");
        Class<?> className = Class.forName(name);
        Constructor<?> constructor = className.getConstructor();
        Object obj = constructor.newInstance();

        // make sure it's a GraphicLetter
        if (obj instanceof GraphicLetter) {
          GraphicLetter letter = (GraphicLetter) obj;
          char letterChar = letter.whichChar();

          // add the letter to the letter list
          ArrayList<GraphicLetter> letterList = getLetterList(graphicLetterMap,
              letterChar, letter);

          // put the list back in the map
          graphicLetterMap.put(letterChar, letterList);
        }
      }
    }
    return graphicLetterMap;
  }

  /**
   * Returns the new list of letters for the param, letter.
   * 
   * @param graphicLetterMap
   *          is the graphicLetterMap being built.
   * @param letterChar
   *          is the char representation of the current letter.
   * @param letter
   *          is the GraphicLetter object to add to the list.
   * @return the new letter list to be added to the HashMap.
   */
  private ArrayList<GraphicLetter> getLetterList(
      HashMap<Character, ArrayList<GraphicLetter>> graphicLetterMap,
      char letterChar, GraphicLetter letter) {

    ArrayList<GraphicLetter> letterList = graphicLetterMap.get(letterChar);
    if (letterList == null) {
      // if it's null, this letter hasn't been added to the map yet
      letterList = new ArrayList<GraphicLetter>();
      letterList.add(letter);
    } else {
      letterList.add(letter);
    }
    return letterList;
  }

  /**
   * Returns a random character, respecting upper and lower case letters. Any
   * character that does not have a GraphicLetter representation will just
   * return a default character (this includes letters that do not have a
   * GraphicLetter representation, e.g. some lower case letters don't have any
   * corresponding GraphicLetters).
   * 
   * @param c
   *          is the character to get a random character for.
   * @return a copy of a random GraphicLetter for the given letter or a default
   *         character if it is not represented in the GraphicLetter library.
   */
  public GraphicLetter getRandomCharacter(char c) {
    if (this.graphicLetterMap == null)
      return null;

    ArrayList<GraphicLetter> letterList = this.graphicLetterMap.get(c);

    if (letterList != null) {
      // get a random index into the letter list
      Random randGen = new Random();
      int randIndex = randGen.nextInt(letterList.size());
      return letterList.get(randIndex).makeCopy();
    } else {
      // return default
      return new DefaultCharacter(c);
    }
  }

  /**
   * Returns a random character, ignoring case for all alphabetic letters. Any
   * letter that does not have a GraphicLetter representation will just return
   * the opposite case's representation.
   * 
   * @param c
   *          is the character to get a random character for.
   * @return a copy of a random GraphicLetter for the given letter or a default
   *         character.
   */
  public GraphicLetter getRandomCharacterIgnoreCase(char c) {
    if (this.graphicLetterMap == null)
      return null;

    ArrayList<GraphicLetter> letterList = this.graphicLetterMap.get(c);
    ArrayList<GraphicLetter> letterListInverted = this.graphicLetterMap
        .get(invertCase(c));

    // combine them if both != null
    if (letterList != null && letterListInverted != null)
      letterList.addAll(letterListInverted);
    // set letterList so that we only deal with letterList later
    else if (letterList == null && letterListInverted != null)
      letterList = letterListInverted;
    // both null, return default
    else if (letterList == null && letterListInverted == null)
      return new DefaultCharacter(c);
    // otherwise just use letterList

    // get random letter
    Random randGen = new Random();
    int randIndex = randGen.nextInt(letterList.size());
    return letterList.get(randIndex).makeCopy();
  }

  private char invertCase(char c) {
    return (Character.isUpperCase(c)) ? Character.toLowerCase(c) : 
      Character.toUpperCase(c);
  }

  /**
   * Prints out all letters in the HashMap and the number of items in their
   * corresponding lists. Used for debugging purposes only.
   */
  protected void printLetterStats() {
    for (char c = 'a'; c <= 'z'; c++) {
      ArrayList<GraphicLetter> list = this.graphicLetterMap
          .get(new Character(c));
      if (list != null)
        System.out.println(c + " : " + list.size());
    }
    for (char c = 'A'; c <= 'Z'; c++) {
      ArrayList<GraphicLetter> list = this.graphicLetterMap
          .get(new Character(c));
      if (list != null)
        System.out.println(c + " : " + list.size());
    }
  }
}
