package tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * 
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	private AbstractPiece piece; // the current piece that is dropping

	private boolean isOver; // has the game finished?

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		randomPiece();
		isOver = false;
	}

	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			piece.move(direction);
		}
		updatePiece();
        grid.checkRows();
        display.update();
		
                
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			randomPiece();
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

	}

            /** Rotate the piece*/
        public void rotatePiece()
        {
            if (piece != null) {
                            piece.rotate();
                    }
                    updatePiece();
            grid.checkRows();
            display.update();
        }
        public void randomPiece(){
        	//new piece goes hereeeee
			Random rand = new Random();
			
			ShapeList whichShape=ShapeList.values()[rand.nextInt(7)];
			switch (whichShape){
			case LSHAPE:
				piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case SSHAPE:
				piece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case TSHAPE:
				piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case ZSHAPE:
				piece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case SQUARESHAPE:
				piece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case JSHAPE:
				piece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case BARSHAPE:
				piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
				default:
					break;
			
			}
        }


}
