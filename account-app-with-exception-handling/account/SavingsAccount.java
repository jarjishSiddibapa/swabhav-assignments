package com.aurionpro.account;

import com.aurionpro.exceptions.MinimumBalanceViolationException;
import com.aurionpro.exceptions.NegativeAmountException;

public class SavingsAccount extends Account {
	private static final double MIN_BALANCE = 500;

	public SavingsAccount(int accno, String name, double balance) {
		super(accno, name, balance);
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new NegativeAmountException();
		}

		balance += amount;
		System.out.println();
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new NegativeAmountException();
		}

		if ((balance - amount) < MIN_BALANCE) {
			throw new MinimumBalanceViolationException();
		}

		balance -= amount;
		System.out.println("Amount debited. Current balance is:" + balance);
	}
}
