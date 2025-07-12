package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.util.ConsoleFormatter;

public class Customer {
	private int id;
	private String name;
	private String contactInfo;
	private List<Order> orders;

	public Customer(String name, String contactInfo) {
		this.id = IDGenerator.generateCustomerId();
		this.name = name;
		this.contactInfo = contactInfo;
		this.orders = new ArrayList<>();
	}

	public void placeOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public String getName() {
		return name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public int getId() {
		return id;
	}

	public void displayCustomerOrders() {
		ConsoleFormatter.printTitle("Customer Order History");
		ConsoleFormatter.printRow("Customer:", name + " | Contact: " + contactInfo);
		ConsoleFormatter.printSeparator();

		if (orders.isEmpty()) {
			ConsoleFormatter.printError("No orders placed yet.");
			return;
		}

		for (Order order : orders) {
			order.displayOrder();
		}
	}
}
