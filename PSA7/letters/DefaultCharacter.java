import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class DefaultCharacter extends GraphicLetter {
	private char character;

	public DefaultCharacter(char character) {
		super();
		this.character = character;
	}

	@Override
	public char whichChar() {
		return character;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(60,60);
	}

	@Override
	public GraphicLetter makeCopy() {
		return new DefaultCharacter(character);
	}
	
	protected void paintComponent( Graphics g ) {
		super.paintComponent(g);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 55));
		g.drawString(Character.toString(character), 15, 50);
	}
}
