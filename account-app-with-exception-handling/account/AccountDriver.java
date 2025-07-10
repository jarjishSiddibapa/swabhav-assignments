package com.aurionpro.account;

import java.util.Scanner;

import com.aurionpro.exceptions.MinimumBalanceViolationException;
import com.aurionpro.exceptions.NegativeAmountException;
import com.aurionpro.exceptions.OverdraftLimitReachedException;

public class AccountDriver {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SavingsAccount savingsAccount = null;
        CurrentAccount currentAccount = null;

        boolean isProgramRunning = true;

        while (isProgramRunning) {
            System.out.println("\nWelcome to AurionPro Bank!");
            System.out.println("1. Create a Savings Account");
            System.out.println("2. Create a Current Account");
            System.out.println("3. Credit Amount");
            System.out.println("4. Debit Amount");
            System.out.println("5. Display Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	try {
                		
                		System.out.print("Enter Account Number: ");
                		int sAccNo = scanner.nextInt();
                		scanner.nextLine(); // clear newline
                		
                		System.out.print("Enter Name: ");
                		String sName = scanner.nextLine();
                		
                		System.out.print("Enter Initial Balance: ");
                		double sBalance = scanner.nextDouble();
                		
                		if (sBalance < 500) {
                			throw new MinimumBalanceViolationException();
                		}
                		
                		savingsAccount = new SavingsAccount(sAccNo, sName, sBalance);
                		System.out.println("Savings Account created successfully.");
                		break;
                	} catch (MinimumBalanceViolationException exception) {
                		System.out.println(exception.getMessage());
                	}
                	break;

                case 2:
                	try {
      
                		System.out.print("Enter Account Number: ");
                		int cAccNo = scanner.nextInt();
                		scanner.nextLine(); // clear newline
                		
                		System.out.print("Enter Name: ");
                		String cName = scanner.nextLine();
                		
                		System.out.print("Enter Initial Balance: ");
                		double cBalance = scanner.nextDouble();
                		
                		if (cBalance < -50000) {
                			throw new OverdraftLimitReachedException();
                		}
                		
                		currentAccount = new CurrentAccount(cAccNo, cName, cBalance);
                		System.out.println("Current Account created successfully.");
                		break;
                	} catch (OverdraftLimitReachedException exception) {
                		System.out.println(exception);
                	}
                	break;

                case 3:
                	try {
                		
                		System.out.println("Credit to:\n1. Savings Account\n2. Current Account");
                		int creditChoice = scanner.nextInt();
                		
                		System.out.print("Enter amount to credit: ");
                		double creditAmount = scanner.nextDouble();
                		
                		if (creditChoice == 1 && savingsAccount == null) {
                			System.out.println("Savings Account not found.");
                			break;
                		}
                		
                		if (creditChoice == 2 && currentAccount == null) {
                			System.out.println("Current Account not found.");
                			break;
                		}
                		
                		if (creditChoice == 1) {
                			savingsAccount.deposit(creditAmount);
                			break;
                		}
                		
                		if (creditChoice == 2) {
                			currentAccount.deposit(creditAmount);
                			break;
                		}
                		
                		System.out.println("Invalid choice.");
                		break;
                	} catch (NegativeArraySizeException exception) {
                		System.out.println(exception);
                	}
                	break;

                case 4:
                	try {
                		
                		System.out.println("Debit from:\n1. Savings Account\n2. Current Account");
                		int debitChoice = scanner.nextInt();
                		
                		System.out.print("Enter amount to debit: ");
                		double debitAmount = scanner.nextDouble();
                		
                		if (debitChoice == 1 && savingsAccount == null) {
                			System.out.println("Savings Account not found.");
                			break;
                		}
                		
                		if (debitChoice == 2 && currentAccount == null) {
                			System.out.println("Current Account not found.");
                			break;
                		}
                		
                		if (debitChoice == 1) {
                			savingsAccount.withdraw(debitAmount);
                			break;
                		}
                		
                		if (debitChoice == 2) {
                			currentAccount.withdraw(debitAmount);
                			break;
                		}
                		
                		System.out.println("Invalid choice.");
                		break;
                	} catch (MinimumBalanceViolationException exception) {
                		System.out.println(exception);
                	} catch (NegativeAmountException exception) {
                		System.out.println(exception);
                	}
                	break;

                case 5:
                    System.out.println("Display which account?\n1. Savings Account\n2. Current Account");
                    int displayChoice = scanner.nextInt();

                    if (displayChoice == 1 && savingsAccount == null) {
                        System.out.println("Savings Account not found.");
                        break;
                    }

                    if (displayChoice == 2 && currentAccount == null) {
                        System.out.println("Current Account not found.");
                        break;
                    }

                    if (displayChoice == 1) {
                        savingsAccount.displayDetails();
                        break;
                    }

                    if (displayChoice == 2) {
                        currentAccount.displayDetails();
                        break;
                    }

                    System.out.println("Invalid choice.");
                    break;

                case 6:
                    System.out.println("Thank you for using AurionPro Banking Application.");
                    isProgramRunning = false;
                    break;

                default:
                    System.out.println("Invalid menu choice. Please try again.");
            }
        }

        scanner.close();
    }

}
