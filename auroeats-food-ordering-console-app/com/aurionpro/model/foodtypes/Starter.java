package com.aurionpro.model.foodtypes;

public class Starter implements IFoodType {
	private static String foodType = "Starter";

	@Override
	public String getFoodType() {
		return foodType;
	}
}
