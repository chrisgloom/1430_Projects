package tetris;

import java.awt.Color;

public class SquareShape extends AbstractPiece {
	public SquareShape(int r, int c, Grid g) {
		super(r, c, g);

		// Create the squares
		square[0] = new Square(g, r - 1, c, new Color(0x8ef9f3), true);
		square[1] = new Square(g, r - 1, c + 1, new Color(0x8ef9f3), true);
		square[2] = new Square(g, r, c, new Color(0x8ef9f3), true);
		square[3] = new Square(g, r, c + 1, new Color(0x8ef9f3), true);
	}
	public void rotate(){
		
	}
}
