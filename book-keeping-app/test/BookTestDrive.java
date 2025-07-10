package com.aurionpro.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.aurionpro.model.Book;
import com.aurionpro.model.BookDescendingTitleComparator;

public class BookTestDrive {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<Book> availableBooks = new ArrayList<Book>();
		ArrayList<Book> issuedBooks = new ArrayList<Book>();

		int choice;
		int sortBy;
		String authorName;
		String title;
		double price;
		int idOfBookToBeIssued;
		int idOfBookToBeReturned;
		boolean bookFound;

		boolean programIsRunning = true;
		while (programIsRunning) {
			System.out.println("Welcome to AurionPro's Book Management System!\nHow may we serve you?");
			System.out.println("1. Add a new book.");
			System.out.println("2. Issue a book by id.");
			System.out.println("3. Display all available books.");
			System.out.println("4. Display all issued books.");
			System.out.println("5. Return a book.");
			System.out.println("6. Sort the books?");
			System.out.println("7. Exit.");

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1:

				System.out.println("Enter the new book's details: ");
				System.out.println("Enter the book's author name: ");
				authorName = scanner.nextLine();
				System.out.println("Enter the book's title: ");
				title = scanner.nextLine();
				System.out.println("Enter the book's price: ");
				price = scanner.nextDouble();
				
				while (price < 0) {
					System.out.println("You entered a negative price value, please enter again: ");
					price = scanner.nextDouble();
				}

				availableBooks.add(new Book(authorName, title, price));
				System.out.println("Book added successfully!");
				break;

			case 2:
				System.out.println("Enter the id of the book to be issued: ");
				idOfBookToBeIssued = scanner.nextInt();
				bookFound = false;

				for (int i = 0; i < availableBooks.size(); i++) {
					Book book = availableBooks.get(i);

					if (book.getBookId() == idOfBookToBeIssued) {
						System.out.println("Found your book, issuing it asap...");
						issuedBooks.add(book);
						book.setIssued(true);

						availableBooks.remove(i);
						bookFound = true;

						break;
					}
				}
				if (!bookFound) {
					System.out.println("Sorry, couldn't find your book.");
				}
				break;

			case 3:
				System.out.println("The information about all available books are as follows: ");
				for (Book book : availableBooks) {
					book.displayDetails();
				}
				break;

			case 4:
				System.out.println("The information about all issued books are as follows: ");
				for (Book book : issuedBooks) {
					book.displayDetails();
				}
				break;

			case 5:
				System.out.println("Enter the id of the book to be returned: ");
				idOfBookToBeReturned = scanner.nextInt();
				bookFound = false;

				for (int i = 0; i < issuedBooks.size(); i++) {
					Book book = issuedBooks.get(i);

					if (book.getBookId() == idOfBookToBeReturned) {
						System.out.println("Found your book, returning it asap...");
						availableBooks.add(book);
						book.setIssued(false);

						issuedBooks.remove(i);
						bookFound = true;

						break;
					}
				}
				if (!bookFound) {
					System.out.println("Sorry, couldn't find your book.");
				}
				break;

			case 6:
				System.out.println("In which way would you like to sort the books? ");
				System.out.println("1. Ascending order of Author's names'.");
				System.out.println("2. Descending order of book's Title.");
				sortBy = scanner.nextInt();

				if (sortBy == 1) {
					Collections.sort(availableBooks);
					Collections.sort(issuedBooks);
					System.out.println("Sorting by ascending ordre of author names performed successfully!");
					break;
				}

				if (sortBy == 2) {
					Comparator bookDescendingTitleComparator = new BookDescendingTitleComparator();
					Collections.sort(availableBooks, bookDescendingTitleComparator);

					Collections.sort(availableBooks, bookDescendingTitleComparator);
					Collections.sort(issuedBooks, bookDescendingTitleComparator);
					System.out.println("Sorting by descending ordre of book titles performed successfully!");
					break;
				}

				System.out.println("Sorry, invalid choice.");
				break;

			case 7:
				System.out.println("Thank you for using AurionPro Book Management Services!");
				programIsRunning = false;
				break;

			default:
				System.out.println("Invalid choice");
			}
		}
	}

}
