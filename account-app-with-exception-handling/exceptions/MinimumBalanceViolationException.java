package com.aurionpro.exceptions;

public class MinimumBalanceViolationException extends RuntimeException {
	private final double MINIMUM_BALANCE = 500;

	public String getMessage() {
		return "You need to maintain a minimum balance of " + MINIMUM_BALANCE;
	}
}
