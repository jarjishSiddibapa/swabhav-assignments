package com.aurionpro.model.menutypes;

import java.util.List;

import com.aurionpro.model.FoodItem;

public interface IMenuType {
	String getMenuType();
	List<FoodItem> getFoodItems();
}
