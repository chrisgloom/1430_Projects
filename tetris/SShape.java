package tetris;

import java.awt.Color;

public class SShape extends AbstractPiece {
	public SShape(int r, int c, Grid g) {
		super(r, c, g);
		Color myColor = new Color(0xf9c88e);
		// Create the squares
		square[0] = new Square(g, r - 1, c + 1, myColor, true);
		square[1] = new Square(g, r - 1, c, myColor, true);
		square[2] = new Square(g, r, c, myColor, true);
		square[3] = new Square(g, r, c - 1, myColor, true);
	}
}
