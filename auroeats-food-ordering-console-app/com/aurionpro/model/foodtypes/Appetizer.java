package com.aurionpro.model.foodtypes;

public class Appetizer implements IFoodType {
	private static String foodType = "Appetizer";
	@Override
	public String getFoodType() {
		return foodType;
	}

}
