package com.aurionpro.model.menutypes;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.FoodItem;
import com.aurionpro.model.foodtypes.Appetizer;
import com.aurionpro.model.foodtypes.Dessert;
import com.aurionpro.model.foodtypes.MainCourse;
import com.aurionpro.model.foodtypes.Starter;

public class ItalianMenu implements IMenuType {
	private static final String menuType = "Italian";
	private List<FoodItem> foodItems;

	public ItalianMenu() {
		foodItems = new ArrayList<>();

		// Appetizers
		foodItems.add(new FoodItem("Bruschetta", "Grilled bread with tomato and basil", 120, new Appetizer()));
		foodItems.add(new FoodItem("Caprese Salad", "Fresh mozzarella, tomatoes, and basil", 150, new Appetizer()));

		// Starters
		foodItems.add(new FoodItem("Garlic Bread", "Toasted bread with garlic butter", 100, new Starter()));
		foodItems.add(new FoodItem("Minestrone Soup", "Hearty vegetable soup", 130, new Starter()));

		// Main Courses
		foodItems.add(new FoodItem("Spaghetti Carbonara", "Pasta with bacon, cheese, and egg", 280, new MainCourse()));
		foodItems.add(new FoodItem("Margherita Pizza", "Classic tomato and cheese pizza", 300, new MainCourse()));
		foodItems.add(new FoodItem("Lasagna", "Layered pasta with meat and cheese", 320, new MainCourse()));

		// Deserts
		foodItems.add(new FoodItem("Tiramisu", "Coffee-flavored Italian dessert", 150, new Dessert()));
		foodItems.add(new FoodItem("Panna Cotta", "Creamy dessert with berry sauce", 140, new Dessert()));
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
