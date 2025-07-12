package com.aurionpro.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.aurionpro.util.ConsoleFormatter;

public class Cart {
	private Map<String, LineItem> cartItems;

	public Cart() {
		this.cartItems = new HashMap<>();
	}

	public void addToCart(FoodItem foodItem, int quantity) {
		String key = foodItem.getFoodName();
		if (cartItems.containsKey(key)) {
			LineItem existingItem = cartItems.get(key);
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
			return;
		}
		cartItems.put(key, new LineItem(quantity, foodItem));
	}

	public boolean removeFromCart(String foodName) {
		for (String key : cartItems.keySet()) {
			if (key.equalsIgnoreCase(foodName)) {
				cartItems.remove(key);
				return true;
			}
		}
		return false;
	}

	public void displayCart() {
		if (cartItems.isEmpty()) {
			ConsoleFormatter.printError("Cart is empty.");
			return;
		}

		ConsoleFormatter.printHeader("Food Item", "Price", "Qty", "Subtotal");

		double total = 0;
		for (LineItem item : cartItems.values()) {
			String name = item.getFoodItem().getFoodName();
			double price = item.getFoodItem().getPrice();
			int quantity = item.getQuantity();
			double subtotal = item.calculateLineItemCost();
			total += subtotal;

			ConsoleFormatter.printRow(
				name,
				"₹" + String.format("%.2f", price),
				quantity,
				"₹" + String.format("%.2f", subtotal)
			);
		}
		ConsoleFormatter.printSeparator();
		ConsoleFormatter.printTotalRow("Total Amount:", total);
	}

	public Collection<LineItem> getCartItems() {
		return cartItems.values();
	}

	public void clearCart() {
		cartItems.clear();
		ConsoleFormatter.printSuccess("Cart cleared.");
	}

	public boolean isEmpty() {
		return cartItems.isEmpty();
	}
}
