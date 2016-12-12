package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public abstract class AbstractPiece {

	/**
	 * An L-Shape piece in the Tetris Game.
	 * 
	 * This piece is made up of 4 squares in the following configuration:
	 * 
	 * Sq <br>
	 * Sq <br>
	 * Sq Sq <br>
	 * 
	 * The game piece "floats above" the Grid. The (row, col) coordinates are
	 * the location of the middle Square on the side within the Grid
	 * 
	 * 
	 */
	private boolean ableToMove; // can this piece move

	protected Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	private Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	private static final int PIECE_COUNT = 4;

	/**
	 * Creates an L-Shape piece. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */
	public AbstractPiece(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
	}

	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}

	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}

	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}

	/**
	 * Rotate the Piece
	 */
	public void rotate() {
		// all new coords in array at once
		int allCoords[] = new int[6];

		allCoords[0] = rotatedCoords(0)[0];
		allCoords[1] = rotatedCoords(0)[1];
		allCoords[2] = rotatedCoords(2)[0];
		allCoords[3] = rotatedCoords(2)[1];
		allCoords[4] = rotatedCoords(3)[0];
		allCoords[5] = rotatedCoords(3)[1];

		if (!invalidCoords(allCoords)) {
			// if not invalid, rotate
			square[0].setCol(allCoords[0]);
			square[0].setRow(allCoords[1]);
			square[2].setCol(allCoords[2]);
			square[2].setRow(allCoords[3]);
			square[3].setCol(allCoords[4]);
			square[3].setRow(allCoords[5]);
		}

	}

	private boolean invalidCoords(int input[]) {
		// check for the edges of the map
		for (int i = 0; i < 6; i += 2) {
			if (input[i] > Grid.WIDTH - 1 || input[i] < 1) {
				return true;
			}
			// check if there's pieces in the space
			try {
				if (grid.isSet(input[i + 1], input[i])) {
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("the problem was when we checked ("
						+ input[i] + ", " + input[i + 1] + ")");
			}
		}
		return false;
	}

	private int[] rotatedCoords(int i) {
		int myCoords[] = new int[2];
		int newCoords[] = new int[2];
		// int rotationMatrix[][] = {{0, 1}, {-1, 0}};
		// This math is basically just the result of the matrix multiplication
		// from the rotation matrix above
		myCoords[0] = square[i].getCol() - square[1].getCol();
		myCoords[1] = square[i].getRow() - square[1].getRow();
		newCoords[0] = myCoords[1] + square[1].getCol();
		newCoords[1] = (-1 * myCoords[0]) + square[1].getRow();
		return newCoords;
	};

}
