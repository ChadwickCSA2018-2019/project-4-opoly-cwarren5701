import java.util.Random;
import java.util.Scanner;

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
	public Opoly(int initBoardSize) {
		boardSize = initBoardSize;
		rand = new Random();
	}

	public Opoly(int initBoardSize, int initSeed) {
		boardSize = initBoardSize;
		seed = initSeed;
		rand = new Random(seed);

	}

	public int spin() {

		nextNum = rand.nextInt(5) + 1;
		moveCounter++;
		return nextNum;
	}

	public void move(int spinResult) {
		if (moveCounter % 10 == 0)
		{
			reward = reward - 50;
		}
		position = position + spinResult;
		if (position % 7 == 0) {
			if (position != boardSize - 1) {
				reward = reward * 2;
			}
		}
		if (position == boardSize - 1) {
			position = position - 3;
			if (position % 7 == 0) {
				reward = reward * 2;
			}
		}
		if (position > boardSize) {
			reward = reward + 100;
			position = position % boardSize;
		}

	}

	public void spinAndMove() {
		int move = spin();
		move(move);
	}

	private boolean gameOver() {
		return (reward >= 1000);
	}

	public void displayReport() {
		System.out.println("game over");
		System.out.println("rounds of play: " + roundsPlayed);
		System.out.println("final reward " + reward);
	}

	public void playGame() {
		while (!gameOver()) {
			drawBoard();
			spinAndMove();
			roundsPlayed++;
		}
		displayReport();
	}

	public void drawBoard() {
		for (int i = 0; i <= boardSize; i++) {
			if (i != position) {
				System.out.print("*");
			} else {
				System.out.print("o");
			}
		}
		System.out.println(" " + reward + " " + nextNum);
	}

	public static void main(String[] args) {
		//remove all this once you are done debugging 
		System.out.println("Enter an int > 3 - the size of the board");
		Scanner scanner = new Scanner(System.in);
		int boardSize = scanner.nextInt();
		System.out.println("Board Size: " + boardSize);
		int seed = 123546;
		Opoly game = new Opoly(boardSize, seed);
		game.playGame();
	}

}
