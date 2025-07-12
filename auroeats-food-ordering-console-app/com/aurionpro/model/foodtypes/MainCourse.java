package com.aurionpro.model.foodtypes;

public class MainCourse implements IFoodType {
	private static String foodType = "Main Course";

	@Override
	public String getFoodType() {
		return foodType;
	}
}
