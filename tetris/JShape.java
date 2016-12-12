package tetris;

import java.awt.Color;

public class JShape extends AbstractPiece {
	public JShape(int r, int c, Grid g) {
		super(r, c, g);

		// Create the squares
		square[0] = new Square(g, r - 1, c, new Color(0xc88ef9), true);
		square[1] = new Square(g, r, c, new Color(0xc88ef9), true);
		square[2] = new Square(g, r + 1, c, new Color(0xc88ef9), true);
		square[3] = new Square(g, r + 1, c - 1, new Color(0xc88ef9), true);
	}
}
