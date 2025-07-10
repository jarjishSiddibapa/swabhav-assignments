package com.aurionpro.exceptions;

public class OverdraftLimitReachedException extends RuntimeException {
	public String getMessage() {
		return "You are crossing your overdraft limits, please reconsider your financial decisions.";
	}
}
