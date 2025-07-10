package com.aurionpro.control_structures;

import java.util.Random;
import java.util.Scanner;

public class PiggyGame {
	public static void main(String[] args) {
		new PiggyGame().start();
	}
	
	private final Scanner scanner = new Scanner(System.in);
	private final Random random = new Random();
	private Integer score = 0;
	private Integer turns = 0;
	
	public void start() {
		System.out.println("Welcome to the Piggy Game: ");
		
		while (turns < 5 && score < 20) {
			playTurn();
		}
		
		endGame();
		scanner.close();
	}
	
	private void playTurn() {
		System.out.println("\nTurn Number: " + (turns + 1));
		boolean turnOver = false;
		
		while (!turnOver) {
			String choice = askPlayerChoice();
			
			if (choice.equals("hold")) {
				handleHold();
				turnOver = true;
				return;
			}
			
			if (choice.equals("roll")) {
				turnOver = handleRoll();
				return;
			}
			
			System.out.println("Invalid input, please type either roll or hold.");
		}
	}
	
	private String askPlayerChoice() {
		System.out.println("Would you like to roll or hold? ");
		return scanner.next().toLowerCase();
	}
	
	private void handleHold() {
		turns++;
		System.out.println("You chose to hold. Current score: " + score);
	}
	
	private boolean handleRoll() {
		int dice = random.nextInt(6) + 1;
		System.out.println("You rolled a " + dice);
		
		if (dice == 1) {
			score = 0;
			turns++;
			System.out.println("You rolled a 1! The score will be reset now bro, turn over.");
			return true;
		}
		
		score += dice;
		System.out.println("Your score is now: " + score);
		
		return score >= 20;
	}
	
	private void endGame() {
		System.out.println("\nGame Over Bro!");
		System.out.println("Turns Used: " + turns);
		System.out.println("Final Score: " + score);
		
		if (score >= 20) {
			System.out.println("Congrats! You reached the score goal of 20!!!");
			return;
		}
		
		System.out.println("You lost. Better luck next time! \n-Google");
	}
}