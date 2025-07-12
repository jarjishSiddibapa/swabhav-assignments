package com.aurionpro.model.menutypes;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.FoodItem;
import com.aurionpro.model.foodtypes.Appetizer;
import com.aurionpro.model.foodtypes.Dessert;
import com.aurionpro.model.foodtypes.MainCourse;
import com.aurionpro.model.foodtypes.Starter;

public class ChineseMenu implements IMenuType {
	private static final String menuType = "Chinese";
	private List<FoodItem> foodItems;

	public ChineseMenu() {
		foodItems = new ArrayList<>();

		// Appetizers
		foodItems.add(new FoodItem("Spring Rolls", "Crispy rolls stuffed with veggies", 110, new Appetizer()));
		foodItems.add(new FoodItem("Chinese Bhel", "Crispy noodles with tangy sauce", 90, new Appetizer()));

		// Starters
		foodItems.add(new FoodItem("Hot & Sour Soup", "Spicy and tangy soup", 130, new Starter()));
		foodItems.add(new FoodItem("Chicken Lollipop", "Fried spicy chicken wings", 160, new Starter()));

		// Main Courses
		foodItems.add(new FoodItem("Hakka Noodles", "Stir-fried noodles with vegetables", 200, new MainCourse()));
		foodItems.add(new FoodItem("Schezwan Fried Rice", "Spicy rice with Chinese sauces", 210, new MainCourse()));
		foodItems.add(new FoodItem("Manchurian Gravy", "Veg dumplings in spicy sauce", 180, new MainCourse()));

		// Deserts
		foodItems.add(new FoodItem("Darsaan", "Honey glazed noodles with ice cream", 130, new Dessert()));
		foodItems.add(new FoodItem("Fortune Cookie", "Crispy cookie with message", 50, new Dessert()));
	}

	@Override
	public String getMenuType() {
		return menuType;
	}

	@Override
	public List<FoodItem> getFoodItems() {
		return foodItems;
	}
}
