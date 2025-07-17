package object_oriented_tic_tac_toe.com.aurionpro.test;

import java.util.Scanner;

import object_oriented_tic_tac_toe.com.aurionpro.model.GameController;

public class ObjectOrientedTicTacToeTestDriver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter player one's name (X): ");
		String playerOneName = scanner.nextLine().trim();
		
		System.out.println("Enter player two's name (O): ");
		String playerTwoName = scanner.nextLine().trim();
		
		GameController game = new GameController(playerOneName, playerTwoName, scanner);
		game.start();
	}
}
