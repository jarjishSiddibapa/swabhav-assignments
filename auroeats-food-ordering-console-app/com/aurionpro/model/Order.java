package com.aurionpro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private LocalDateTime date;
	private List<LineItem> items;
	private double totalPrice;

	public Order(List<LineItem> items) {
		this.id = IDGenerator.generateOrderId();
		this.date = LocalDateTime.now();
		this.items = new ArrayList<>(items); // defensive copy (something new I learned!)
		this.totalPrice = calculateOrderPrice(); // automatically calculated when order is created

	}

	public int getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	private double calculateOrderPrice() {
		double total = 0;
		for (LineItem item : items) {
			total += item.calculateLineItemCost();
		}
		return total;
	}

	public void displayOrder() {
		System.out.println("\nOrder ID: " + id + " | Date: " + date);
		System.out.printf("%-25s %-10s %-10s %-10s\n", "Food Item", "Qty", "Price", "Total");
		for (LineItem item : items) {
			FoodItem food = item.getFoodItem();
			System.out.printf("%-25s %-10d %-10.2f %-10.2f\n", food.getFoodName(), item.getQuantity(), food.getPrice(),
					item.calculateLineItemCost());
		}
		System.out.println("Order Total: ₹" + String.format("%.2f", calculateOrderPrice()));
		System.out.println("--------------------------------------------------");

	}
}
