import java.util.Random;
import java.util.Scanner;

/**
 * @author CarterWarren
 *
 */
public class Opoly {

	private int boardSize; // size of the board
	private Random rand; // random number generator used to simulate a spin
	private int roundsTotal; // number of rounds to play
	private int roundsPlayed; // number of rounds that have completed
	private int position = 0; // position on the board
	private int reward = 100; // current reward
	private int moveCounter = 0; // counts the moves made throughout the game
	private int nextNum; // describes the output of the simulated spin

	/**
	 * Constructed an Opoly game with the size of the board.
	 * 
	 * @param initBoardSize the size of the board.
	 */
	public Opoly(int initBoardSize) {
		boardSize = initBoardSize;
		rand = new Random();
	}

	/**
	 * Constructs an Opoly game with the size of the board and a seed value.
	 * 
	 * @param initBoardSize the size of the board.
	 * @param initSeed      the seed for generating random numbers.
	 */
	public Opoly(int initBoardSize, int initSeed) {
		boardSize = initBoardSize;
		int seed = initSeed;
		rand = new Random(seed);

	}

	/**
	 * Generates a number between 1 and 5.
	 * @return a number between 1 and 5. The returned value is based on a random selection.
	 */
	public int spin() {

		nextNum = rand.nextInt(5) + 1;
		moveCounter++;
		return nextNum;
	}

	/**
	 * Moves the player along the board according to the game's rules. 
	 * 
	 * @param spinResult result of the spin.
	 */
	public void move(int spinResult) {
		if (moveCounter % 10 == 0) {
			reward = reward - 50;
		}
		position = position + spinResult;
		if (position >= boardSize) {
			reward = reward + 100;
			position = position % boardSize;
		}
		if (position == boardSize - 1) {
			position = position - 3;
			if (position % 7 == 0) {
				reward = reward * 2;
			}
		} else {
			if (position % 7 == 0) {
				reward = reward * 2;
			}
		}

	}

	/**
	 * Completes a spin and moves the player along the board. 
	 */
	public void spinAndMove() {
		int move = spin();
		move(move);
	}

	/**
	 * Returns <code>true</code> if reward is equal or greater than 1000.
	 * 
	 * @return <code>true</code> if reward is equal or greater than 1000;
	 *         <code>false</code> otherwise
	 */
	private boolean gameOver() {
		return (reward >= 1000);
	}

	/**
	 * Prints that the game is over, the number of rounds played and final reward of the player 
	 */
	public void displayReport() {
		System.out.println("game over");
		System.out.println("rounds of play: " + roundsPlayed);
		System.out.println("final reward: " + reward);
	}

	/**
	 * Creates and begins a new game and runs the game until game is over. Prints final results.
	 */
	public void playGame() {
		while (!gameOver()) {
			drawBoard();
			spinAndMove();
			roundsPlayed++;
		}
		displayReport();
	}

	/**
	 * Prints the board according to the position of the player.
	 */
	public void drawBoard() {
		for (int i = 0; i <= boardSize - 1; i++) {
			if (i != position) {
				System.out.print("*");
			} else {
				System.out.print("o");
			}
		}
		System.out.println(" " + reward);
	}

}
