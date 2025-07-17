package object_oriented_tic_tac_toe.com.aurionpro.model;

import java.util.Scanner;

public class GameController {
	private final Board board = new Board();
	private final Player player1;
	private final Player player2;
	private Player current;

	private final Scanner scanner;

	public GameController(String name1, String name2, Scanner scanner) {
		this.player1 = new Player(name1, Mark.X);
		this.player2 = new Player(name2, Mark.O);
		this.current = player1;
		this.scanner = scanner;
	}

	public void start() {
		boolean playAgain = true;
		while (playAgain) {
			playOneRound();
			System.out.printf("Score: %s=%d, %s=%d%n", player1.getName(), player1.getScore(), player2.getName(),
					player2.getScore());
			System.out.print("Play again? (y/n): ");
			playAgain = scanner.next().trim().equalsIgnoreCase("y");
			board.reset();
			current = player1;
		}
		System.out.println("Thanks for playing!");
	}

	private void playOneRound() {
		while (true) {
			System.out.println("\nCurrent board:");
			System.out.println(board.display());
			System.out.printf("%s (%s), choose 1–9: ", current.getName(), current.getMark());

			int choice = readIntInRange(1, 9);
			try {
				board.markPosition(choice, current.getMark());
			} catch (IllegalStateException | IllegalArgumentException exception) {
				System.out.println("Invalid move: " + exception.getMessage());
				continue;
			}

			if (board.hasWinner()) {
				System.out.println(board.display());
				System.out.printf("🎉 %s wins this round!%n", current.getName());
				current.incrementScore();
				break;
			}

			if (board.isFull()) {
				System.out.println(board.display());
				System.out.println("It's a draw!");
				break;
			}
			
			switchCurrentPlayer();
		}
	}
	
	private int readIntInRange(int min, int max) {
		while (true) {
			if (!scanner.hasNextInt()) {
				System.out.println("Please enter a number: ");
				scanner.next();
				continue;
			}
			
			int value = scanner.nextInt();
			if (value < min || value > max) {
                System.out.printf("Choose between %d and %d: ", min, max);
			}
			
			return value;
		}
	}
	
	private void switchCurrentPlayer() {
		current = (current == player1) ? player2 : player1;
	}
}
