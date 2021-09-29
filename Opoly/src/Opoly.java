import java.util.Random;

public class Opoly {

	private int boardSize;
	private int seed;
	private Random rand;
	private int roundsTotal; // number of rounds to play
	private int roundsPlayed; // number of rounds that have completed
	int position = 0;
	int reward = 100;

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

		int nextNum = rand.nextInt(5) + 1;
		return nextNum;
	}

	public void move(int spinResult) {
		position = position + spinResult;

		if (position > boardSize) {
			reward = reward + 100;
		}

		position = position % boardSize;
	}

	private boolean gameOver() {
		return (roundsPlayed == roundsTotal);
	}

	public void drawBoard() {
		for (int i = 0; i <= boardSize; i++) {
			if (i != position) {
				System.out.print("*");
			} else {
				System.out.print("o");
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Opoly o1 = new Opoly(20);
		for (int i = 0; i < 10; i++) {

			System.out.println(o1.spin());
		}
	}

}
