package com.aurionpro.util;

import java.util.Scanner;

public class InputHelper {
	private static Scanner inputScanner = new Scanner(System.in);

	public static int readInt(String prompt) {
		ConsoleFormatter.prompt(prompt);
		while (!inputScanner.hasNextInt()) {
			inputScanner.next();
			ConsoleFormatter.printError("Enter a valid number.");
			ConsoleFormatter.prompt(prompt);
		}
		int val = inputScanner.nextInt();
		inputScanner.nextLine();
		return val;
	}

	public static double readDouble(String prompt) {
		ConsoleFormatter.prompt(prompt);
		while (!inputScanner.hasNextDouble()) {
			inputScanner.next();
			ConsoleFormatter.printError("Enter a valid price.");
			ConsoleFormatter.prompt(prompt);
		}
		double val = inputScanner.nextDouble();
		inputScanner.nextLine();
		return val;
	}

	public static String readLine(String prompt) {
		ConsoleFormatter.prompt(prompt);
		return inputScanner.nextLine();
	}
}
