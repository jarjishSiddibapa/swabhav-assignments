package com.aurionpro.account;

public abstract class Account {
	protected int accno;
	protected String name;
	protected double balance;

	public Account(int accountNumber, String name, double balance) {
		this.accno = accountNumber;
		this.name = name;
		this.balance = balance;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public abstract void deposit(double amount);
	
	public abstract void withdraw(double amount);

	
	public void displayDetails() {
        System.out.println("Account No: " + accno);
        System.out.println("Name: " + name);
        System.out.println("Balance: ₹" + balance);
	}
}
