package com.aurionpro.account;

import com.aurionpro.exceptions.NegativeAmountException;
import com.aurionpro.exceptions.OverdraftLimitReachedException;

public class CurrentAccount extends Account {
	private static final double OVER_DRAFT_LIMIT = 50000;

	public CurrentAccount(int accno, String name, double balance) {
		super(accno, name, balance);
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new NegativeAmountException();
		}

		balance += amount;
		System.out.println("Amount credited. Now the new balance is: " + balance);
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new NegativeAmountException();
		}

		if ((balance - amount) > -OVER_DRAFT_LIMIT) {
			balance -= amount;
			System.out.println("Amount debited. Current balance is: " + balance);
			return;
		}
		throw new OverdraftLimitReachedException();
	}
}
