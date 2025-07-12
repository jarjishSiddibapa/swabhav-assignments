package com.aurionpro.model;

import java.util.Iterator;
import java.util.List;

import com.aurionpro.model.menutypes.IMenuType;
import com.aurionpro.util.ConsoleFormatter;

public class AdminUser {
	private String username;
	private String password;

	public AdminUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean login(String inputUsername, String inputPassword) {
		return username.equals(inputUsername) && password.equals(inputPassword);
	}

	public void addFood(IMenuType menu, FoodItem item) {
		menu.getFoodItems().add(item);
		ConsoleFormatter.printSuccess("Added '" + item.getFoodName() + "' to " + menu.getMenuType() + " menu.");
	}

	public boolean removeFood(IMenuType menu, String foodName) {
		List<FoodItem> foodItems = menu.getFoodItems();
		Iterator<FoodItem> iterator = foodItems.iterator();
		boolean removed = false;

		while (iterator.hasNext()) {
			FoodItem item = iterator.next();
			if (item.getFoodName().equalsIgnoreCase(foodName)) {
				iterator.remove();
				removed = true;
				break;
			}
		}

		if (removed) {
			ConsoleFormatter.printSuccess("'" + foodName + "' was removed from " + menu.getMenuType() + " menu.");
			return true;
		}

		ConsoleFormatter.printError("'" + foodName + "' not found in " + menu.getMenuType() + " menu.");
		return false;
	}
}
