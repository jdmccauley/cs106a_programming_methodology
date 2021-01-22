/*
 * File: Breakout.java
 * -------------------
 * This purpose of this program is to implement the game of Breakout.
 */

/*
 * Decomposition:
 * 1. Make the bricks
 * 	Method
 * 	call in run() to initalize
 * 2. Make the paddle
 * 3. Make the ball
 * 4. Get the ball to bounce off walls
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	
	//For some reason these need to be public
	//If private, window is too large
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	public static final int WIDTH = APPLICATION_WIDTH;
	public static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	public static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/* My own constants. */
/** Separation between rows */
	private static final int ROW_SEP = 10;

/** Paddle X initial position */
	private static final int PADDLE_X_INIT = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;

/** Paddle Y initial position */
	private static final int PADDLE_Y_INIT = (APPLICATION_HEIGHT - (PADDLE_HEIGHT + PADDLE_Y_OFFSET));

/** Ball X initial position */
	private static final int BALL_X_INIT = (WIDTH / 2) - BALL_RADIUS;
		
/** Ball Y initial position */
	private static final int BALL_Y_INIT = (HEIGHT / 2) - BALL_RADIUS;

/** Time in milliseconds to delay ball movement */
	public static final int BALL_PAUSE = 10;

	/*
	 * Making the bricks
	 * 1. Make brick method
	 * 2. Make row method, fill bricks
	 */
	
	/**
	 * Method: makeRect
	 * Returns a fillable rectangle.
	 * @param width int width: Width of rectangle
	 * @param height int height: Height of rectangle
	 * @return GRect Grect that is fillable
	 */
	private GRect makeRect(int width, int height) {
		GRect rect = new GRect(width, height);
		rect.setFilled(true);
		return rect;
	}
	
	/**
	 * Method: makeColors
	 * Returns an array of colors.
	 * @return Color[] Array of Colors
	 */
	private Color[] makeColors() {
		Color[] brickColors = new Color[5];
		brickColors[0] = Color.RED;
		brickColors[1] = Color.ORANGE;
		brickColors[2] = Color.YELLOW;
		brickColors[3] = Color.GREEN;
		brickColors[4] = Color.CYAN;
		return brickColors;
	}
	
	/**
	 * Method: makeRow
	 * Makes a row of bricks for the specified color. 
	 * @param x int x: Horizontal position in pixels to put row
	 * @param y int y: Vertical position in pixels to put row
	 * @param color Color color: Color to fill bricks for the row
	 * @return void
	 */
	private void makeRow(int x, int y, Color color) {
		GRect brick;
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			brick = makeRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFillColor(color);
			brick.setLocation(x, y);
			add(brick);
			x += BRICK_WIDTH + BRICK_SEP;
		}
	}
	
	/**
	 * Method: addRows
	 * Makes the rows of bricks.
	 * @param x int x: Horizontal position in pixels to put row
	 * @param y int y: Vertical position in pixels to put row
	 * @param brickColors Color[] brickColors: Colors to fill bricks for the rows
	 * @return void 
	 */
	private void addRows(int x, int y, Color[] brickColors) {
		int colorCounter = 0;
		for (int i = 0; i < NBRICK_ROWS; i++) {
			if (i % 2 == 0 && i != 0) {
				colorCounter++;
			}
			makeRow(x, y, brickColors[colorCounter]);
			y += ROW_SEP;
		}
	}
	
	
	/* Instance variable */
	private GRect paddle = makeRect(PADDLE_WIDTH, PADDLE_HEIGHT);
	
	/* Instance variable */
//	private GOval ball = makeBall(BALL_RADIUS);
	private BreakoutBall ball = new BreakoutBall(BALL_RADIUS);
	
	/* Instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	

	/* Mouse events */
	/* From the book pg 204, add better citation later */
	/* NOTE THAT THE MOUSE MUST BE CLICKED TO BE DRAGGED */
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int minX = 0;
		int maxX = WIDTH - PADDLE_WIDTH;
		if (x < minX) x = minX;
		if (x > maxX) x = maxX;
		paddle.move(x - paddle.getX(), 0);
	}

/* Method: init() */
/** Initializes the Breakout program with the bricks and paddle. */
	public void init() {
		/* Build rows of bricks with brickColors*/
		Color[] brickColors = makeColors();
		addRows(0, BRICK_Y_OFFSET, brickColors);
		/* Add paddle */
		paddle.setFillColor(Color.BLACK);
		paddle.setLocation(PADDLE_X_INIT, PADDLE_Y_INIT);
		ball.setFillColor(Color.BLACK);
		ball.setLocation(BALL_X_INIT, BALL_Y_INIT);
		add(paddle);
		add(ball);
		ball.setYVelocity(3.0);
		ball.setXVelocity(rgen.nextDouble(1.0, 3.0));
		if (rgen.nextBoolean(0.5)) ball.setXVelocity(-ball.getXVelocity());
	}
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		// Play three times
		for (int i = 0; i < NTURNS; i++) {
			// Wait a couple of seconds for the player to get ready
			pause(2000);
			while (ball.getY() < HEIGHT) {
				ball.moveBall(paddle);
			}
			// Restart the game
			init();
		}
	}

}