package com.aurionpro.model;

import com.aurionpro.model.foodtypes.IFoodType;

public class FoodItem {
	private String foodName;
	private String description;
	private double price;
	private IFoodType foodType;

	public FoodItem(String foodName, String description, double price, IFoodType foodType) {
		this.foodName = foodName;
		this.description = description;
		this.price = price;
		this.foodType = foodType;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public IFoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(IFoodType foodType) {
		this.foodType = foodType;
	}
}
