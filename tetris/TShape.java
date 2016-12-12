package tetris;

import java.awt.Color;

public class TShape extends AbstractPiece {
	public TShape(int r, int c, Grid g) {
		super(r, c, g);

		// Create the squares
		square[0] = new Square(g, r, c - 1, new Color(0xf9f38e), true);
		square[1] = new Square(g, r, c, new Color(0xf9f38e), true);
		square[2] = new Square(g, r, c + 1, new Color(0xf9f38e), true);
		square[3] = new Square(g, r + 1, c, new Color(0xf9f38e), true);
	}
}
