package com.aurionpro.exceptions;

public class NegativeAmountException extends RuntimeException {
	public String getMessage() {
		return "Please do not enter a negative value.";
	}
}
