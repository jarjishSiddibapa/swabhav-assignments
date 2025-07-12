package com.aurionpro.model.menutypes;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.FoodItem;
import com.aurionpro.model.foodtypes.Appetizer;
import com.aurionpro.model.foodtypes.Dessert;
import com.aurionpro.model.foodtypes.MainCourse;
import com.aurionpro.model.foodtypes.Starter;

public class FrenchMenu implements IMenuType {
	private static final String menuType = "French";
	private List<FoodItem> foodItems;

	public FrenchMenu() {
		foodItems = new ArrayList<>();

		// Appetizers
		foodItems.add(new FoodItem("Gougères", "Cheese puffs made from choux pastry", 140, new Appetizer()));
		foodItems.add(new FoodItem("Ratatouille Tart", "Savory tart with vegetables", 160, new Appetizer()));

		// Starters
		foodItems.add(new FoodItem("French Onion Soup", "Caramelized onions with cheese toast", 150, new Starter()));
		foodItems.add(new FoodItem("Escargots", "Snails cooked in garlic butter", 180, new Starter()));

		// Main Courses
		foodItems.add(new FoodItem("Coq au Vin", "Chicken braised in wine with mushrooms", 320, new MainCourse()));
		foodItems.add(new FoodItem("Ratatouille", "Stewed vegetables in tomato sauce", 250, new MainCourse()));
		foodItems.add(new FoodItem("Beef Bourguignon", "Beef stewed in red wine", 350, new MainCourse()));

		// Deserts
		foodItems.add(new FoodItem("Crème Brûlée", "Rich custard with caramelized sugar top", 160, new Dessert()));
		foodItems.add(new FoodItem("Macarons", "Colorful almond meringue cookies", 140, new Dessert()));
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
