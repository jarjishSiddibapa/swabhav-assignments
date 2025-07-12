package com.aurionpro.model;

public class LineItem {
	private int quantity;
	private FoodItem foodItem;

	public LineItem(int quantity, FoodItem foodItem) {
		this.quantity = quantity;
		this.foodItem = foodItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public double calculateLineItemCost() {
		return foodItem.getPrice() * quantity;
	}
	
	@Override
	public String toString() {
		return quantity + " x " + foodItem.getFoodName() +
		       " @ ₹" + foodItem.getPrice() +
		       " = ₹" + String.format("%.2f", calculateLineItemCost());
	}

}
