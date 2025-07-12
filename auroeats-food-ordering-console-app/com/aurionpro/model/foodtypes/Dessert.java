package com.aurionpro.model.foodtypes;

public class Dessert implements IFoodType {
	private static String foodType = "Dessert";
	@Override
	public String getFoodType() {
		return foodType;
	}
}
