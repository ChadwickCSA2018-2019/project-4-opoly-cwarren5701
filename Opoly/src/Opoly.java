import java.util.Random;
import java.util.Scanner;

/**
 * @author CarterWarren
 *
 */
public class Opoly {

	private int boardSize;
	private int seed;
	private Random rand;
	private int roundsTotal; // number of rounds to play
	private int roundsPlayed; // number of rounds that have completed
	private int position = 0;
	private int reward = 100;
	private int moveCounter = 0;
	private int nextNum;

	/**
	 * @param initBoardSize
	 */
	public Opoly(int initBoardSize) {
		boardSize = initBoardSize;
		rand = new Random();
	}

	/**
	 * @param initBoardSize
	 * @param initSeed
	 */
	public Opoly(int initBoardSize, int initSeed) {
		boardSize = initBoardSize;
		seed = initSeed;
		rand = new Random(seed);

	}

	/**
	 * @return
	 */
	public int spin() {

		nextNum = rand.nextInt(5) + 1;
		moveCounter++;
		return nextNum;
	}

	/**
	 * @param spinResult
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
	 * 
	 */
	public void spinAndMove() {
		int move = spin();
		move(move);
	}

	/**
	 * @return
	 */
	private boolean gameOver() {
		return (reward >= 1000);
	}

	/**
	 * 
	 */
	public void displayReport() {
		System.out.println("game over");
		System.out.println("rounds of play: " + roundsPlayed);
		System.out.println("final reward " + reward);
	}

	/**
	 * 
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
	 * 
	 */
	public void drawBoard() {
		for (int i = 0; i <= boardSize - 1; i++) {
			if (i != position) {
				System.out.print("*");
			} else {
				System.out.print("o");
			}
		}
		System.out.println(" " + reward + " " + nextNum);
	}

}
