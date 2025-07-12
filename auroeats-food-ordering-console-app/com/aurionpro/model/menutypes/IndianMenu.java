package com.aurionpro.model.menutypes;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.FoodItem;
import com.aurionpro.model.foodtypes.Appetizer;
import com.aurionpro.model.foodtypes.Dessert;
import com.aurionpro.model.foodtypes.MainCourse;
import com.aurionpro.model.foodtypes.Starter;

public class IndianMenu implements IMenuType {
	private static final String menuType = "Indian";
	private List<FoodItem> foodItems;

	public IndianMenu() {
		foodItems = new ArrayList<>();
		// Appetizers
		foodItems.add(new FoodItem("Papdi Chaat", "Crispy wafers topped with spicy chutneys and yogurt", 90,
				new Appetizer()));
		foodItems.add(new FoodItem("Dhokla", "Soft and spongy steamed savory cake", 70, new Appetizer()));

		// Starters
		foodItems.add(new FoodItem("Paneer Tikka", "Grilled cottage cheese cubes with spices", 180, new Starter()));
		foodItems.add(new FoodItem("Chicken Pakora", "Spicy chicken fritters", 160, new Starter()));

		// Main Courses
		foodItems.add(new FoodItem("Paneer Butter Masala", "Spicy cottage cheese curry", 220, new MainCourse()));
		foodItems.add(new FoodItem("Chicken Biryani", "Fragrant rice with chicken", 250, new MainCourse()));
		foodItems.add(new FoodItem("Dal Makhani", "Creamy black lentils with butter", 190, new MainCourse()));
		foodItems.add(new FoodItem("Butter Naan", "Soft Indian bread with butter", 50, new MainCourse()));

		// Deserts
		foodItems.add(new FoodItem("Gulab Jamun", "Soft milk balls soaked in syrup", 60, new Dessert()));
		foodItems.add(new FoodItem("Rasmalai", "Cottage cheese balls soaked in sweet milk", 80, new Dessert()));
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
