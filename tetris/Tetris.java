package tetris;

/**

 * Create and control the game Tetris.
 * 
 *
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Tetris extends JPanel {

	private Game game;

	/**
	 * Sets up the parts for the Tetris game, display and user control
	 */
	public Tetris() {
		game = new Game(this);
		JFrame f = new JFrame("The Tetris Game");
		f.getContentPane().add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(800, 550);
		f.setVisible(true);
		EventController ec = new EventController(game);
		f.addKeyListener(ec);

		// background color gets set here
		setBackground(new Color(0x4e598c));
		setLayout(null);
	}

	/**
	 * Updates the display
	 */
	public void update() {
		repaint();
	}

	/**
	 * Paint the current state of the game
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g);
		g.setFont(new Font("Monaco", Font.BOLD, 20));
		g.drawString("space to rotate", 400, 140);
		g.drawString("arrow keys to move left, right,", 400, 180);
		g.drawString("down", 400, 200);
		g.drawString("'q' to quit", 400, 300);
		if (game.isGameOver()) {
			g.setFont(new Font("Helvetica", Font.BOLD, 40));
			g.setColor(new Color(0x4bcc72));
			g.drawString("GAME OVER", 80, 303);
			g.setColor(new Color(0x5efc8d));
			g.drawString("GAME OVER", 80, 300);
		}
	}

	public static void main(String[] args) {
		new Tetris();
	}
}
