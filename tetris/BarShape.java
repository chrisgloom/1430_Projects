package tetris;

import java.awt.Color;

public class BarShape extends AbstractPiece {
	public BarShape(int r, int c, Grid g) {
		super(r, c, g);
		Color myColor = new Color(0x8eacf9);
		// Create the squares
		square[0] = new Square(g, r, c, myColor, true);
		square[1] = new Square(g, r, c + 1, myColor, true);
		square[2] = new Square(g, r, c + 2, myColor, true);
		square[3] = new Square(g, r, c + 3, myColor, true);
	}
}
