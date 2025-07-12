package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.exception.InvalidCardException;
import com.aurionpro.exception.InvalidInputException;
import com.aurionpro.exception.InvalidMenuSelectionException;
import com.aurionpro.exception.InvalidPriceException;
import com.aurionpro.exception.InvalidQuantityException;
import com.aurionpro.exception.InvalidUPIException;
import com.aurionpro.model.foodtypes.Appetizer;
import com.aurionpro.model.foodtypes.Dessert;
import com.aurionpro.model.foodtypes.IFoodType;
import com.aurionpro.model.foodtypes.MainCourse;
import com.aurionpro.model.foodtypes.Starter;
import com.aurionpro.model.menutypes.ChineseMenu;
import com.aurionpro.model.menutypes.FrenchMenu;
import com.aurionpro.model.menutypes.IMenuType;
import com.aurionpro.model.menutypes.IndianMenu;
import com.aurionpro.model.menutypes.ItalianMenu;
import com.aurionpro.model.paymenttypes.CreditCardPayment;
import com.aurionpro.model.paymenttypes.IPayment;
import com.aurionpro.model.paymenttypes.UPIPayment;
import com.aurionpro.util.ConsoleFormatter;
import com.aurionpro.util.InputHelper;

public class ApplicationFacade {
	private final Scanner inputScanner = new Scanner(System.in);
	private final List<IMenuType> menus;
	private final AdminUser admin;
	private final List<DeliveryPartner> deliveryPartners = new ArrayList<>();
	private final Customer customer;

	public ApplicationFacade() {
		this.menus = List.of(new IndianMenu(), new ItalianMenu(), new ChineseMenu(), new FrenchMenu());
		this.admin = new AdminUser("admin", "password");
		deliveryPartners.add(new DeliveryPartner("Swiggy"));
		deliveryPartners.add(new DeliveryPartner("Zomato"));
		this.customer = new Customer("Jarjish", "jarjish.siddibapa@aurionpro.com");
	}

	public void start() {
		while (true) {
			showMainMenu();
			int choice = InputHelper.readInt("Choice");

			switch (choice) {
			case 1 -> handleAdminLogin();
			case 2 -> handleCustomerFlow();
			case 3 -> {
				exitApp();
				return; // jarjish here-> I'm using return for cleaner code, could've used break as
						// well!
			}
			default -> ConsoleFormatter.printError("Invalid choice");
			}
		}
	}

	private void showMainMenu() {
		ConsoleFormatter.printTitle("AuroEats!");
		ConsoleFormatter.printHeader("No.", "Action");
		ConsoleFormatter.printRow(1, "Admin Login");
		ConsoleFormatter.printRow(2, "Customer Menu");
		ConsoleFormatter.printRow(3, "Exit");
	}

	private void exitApp() {
		ConsoleFormatter.printSuccess("Thank you for using AuroEats!");
		inputScanner.close();
	}

	private void handleAdminLogin() {
		String username = InputHelper.readLine("Enter admin username");
		String password = InputHelper.readLine("Enter admin password");

		if (!admin.login(username, password)) {
			ConsoleFormatter.printError("Invalid admin credentials");
			return;
		}

		ConsoleFormatter.printSuccess("Admin login successful");

//		try {
//			IMenuType selectedMenu = selectMenu();
//			handleMenuModification(selectedMenu);
//		} catch (InvalidMenuSelectionException e) {
//			ConsoleFormatter.printError(e.getMessage());
//		}
		while (true) {
			ConsoleFormatter.printHeader("No.", "Action");
			ConsoleFormatter.printRow(1, "Modify Menus");
			ConsoleFormatter.printRow(2, "Manage Delivery Partners");
			ConsoleFormatter.printRow(3, "Logout");
			int choice = InputHelper.readInt("Choice");
			switch (choice) {
			case 1 -> {
				try {
					IMenuType menu = selectMenu();
					handleMenuModification(menu);
				} catch (InvalidMenuSelectionException e) {
					ConsoleFormatter.printError(e.getMessage());
				}
			}
			case 2 -> manageDeliveryPartners();
			case 3 -> {
				ConsoleFormatter.printSuccess("Admin logged out");
				return;
			}
			default -> ConsoleFormatter.printError("Invalid choice");
			}
		}
	}

	private void handleMenuModification(IMenuType menu) {
		while (true) {
			ConsoleFormatter.printHeader("No.", "Action");
			ConsoleFormatter.printRow(1, "Add Food");
			ConsoleFormatter.printRow(2, "Remove Food");
			ConsoleFormatter.printRow(3, "View Menu");
			ConsoleFormatter.printRow(4, "Back to Admin Panel");

			int choice = InputHelper.readInt("Choice");

			switch (choice) {
			case 1 -> {
				try {
					addFoodToMenu(menu);
				} catch (InvalidPriceException | InvalidInputException e) {
					ConsoleFormatter.printError(e.getMessage());
				}
			}
			case 2 -> removeFoodFromMenu(menu);
			case 3 -> viewMenuItems(menu);
			case 4 -> {
				ConsoleFormatter.printSuccess("Returning to admin panel");
				return;
			}
			default -> ConsoleFormatter.printError("Invalid choice");
			}
		}
	}

	private void addFoodToMenu(IMenuType menu) throws InvalidPriceException, InvalidInputException {
		ConsoleFormatter.printTitle("Add New Food Item");

		String name = InputHelper.readLine("Name");
		
		// new modification, now the system also checks whether the food already exists.
		for (FoodItem existing : menu.getFoodItems()) {
	        if (existing.getFoodName().equalsIgnoreCase(name)) {
	            ConsoleFormatter.printError("Food item \"" + name + "\" already exists in the menu.");
	            return;  // back to the admin modification loop
	        }
	    }
		
		String desc = InputHelper.readLine("Description");
		double price = InputHelper.readDouble("Price");
		if (price <= 0)
			throw new InvalidPriceException("Price must be greater than 0");

		String typeStr = InputHelper.readLine("FoodType (Appetizer/Starter/MainCourse/Dessert)");
		IFoodType type = mapFoodTypeFromString(typeStr);
		if (type == null)
			throw new InvalidInputException("Invalid food type entered");

		admin.addFood(menu, new FoodItem(name, desc, price, type));
		ConsoleFormatter.printSuccess("Food added successfully");
	}

	private void removeFoodFromMenu(IMenuType menu) {
		List<FoodItem> items = menu.getFoodItems();

		// 1. If menu is empty, bail out
		if (items.isEmpty()) {
			ConsoleFormatter.printError("No food items in this menu to remove.");
			return;
		}

		// 2. Show the menu with indices
		ConsoleFormatter.printTitle(menu.getMenuType() + " Menu");
		ConsoleFormatter.printHeader("No.", "Name", "Price", "Type");
		for (int i = 0; i < items.size(); i++) {
			FoodItem item = items.get(i);
			ConsoleFormatter.printRow(i + 1, item.getFoodName(), "₹" + String.format("%.2f", item.getPrice()),
					item.getFoodType().getFoodType());
		}

		// 3. Prompt for the item number
		int choice = InputHelper.readInt("Enter the item number to remove");

		// 4. Validate and remove
		if (choice < 1 || choice > items.size()) {
			ConsoleFormatter.printError("Invalid selection. Please enter a number between 1 and " + items.size());
			return;
		}
		FoodItem toRemove = items.get(choice - 1);
		boolean removed = admin.removeFood(menu, toRemove.getFoodName());

		// 5. Feedback
		if (removed) {
			ConsoleFormatter.printSuccess("Removed \"" + toRemove.getFoodName() + "\" successfully.");
		} else {
			// (Shouldn't happen if admin.removeFood works off that exact name)
			ConsoleFormatter.printError("Failed to remove item. Please try again.");
		}
	}

	private void viewMenuItems(IMenuType menu) {
		List<FoodItem> items = menu.getFoodItems();
		if (items.isEmpty()) {
			ConsoleFormatter.printError("No food items in this menu");
			return;
		}

		ConsoleFormatter.printTitle(menu.getMenuType() + " Menu");
		ConsoleFormatter.printHeader("No.", "Name", "Price", "Type");
		for (int i = 0; i < items.size(); i++) {
			FoodItem item = items.get(i);
			ConsoleFormatter.printRow(i + 1, item.getFoodName(), "₹" + String.format("%.2f", item.getPrice()),
					item.getFoodType().getFoodType());
			System.out.println("      ↳ " + item.getDescription());
			System.out.println();
		}
	}

	private void manageDeliveryPartners() {
		while (true) {
			ConsoleFormatter.printTitle("Delivery Partner Management");
			ConsoleFormatter.printHeader("No.", "Action");
			ConsoleFormatter.printRow(1, "List Partners");
			ConsoleFormatter.printRow(2, "Add Partner");
			ConsoleFormatter.printRow(3, "Remove Partner");
			ConsoleFormatter.printRow(4, "Back to Admin Panel");
			int choice = InputHelper.readInt("Choice");
			switch (choice) {
			case 1 -> listDeliveryPartners();
			case 2 -> addDeliveryPartner();
			case 3 -> removeDeliveryPartner();
			case 4 -> {
				ConsoleFormatter.printSuccess("Returning to admin panel");
				return;
			}
			default -> ConsoleFormatter.printError("Invalid choice");
			}
		}
	}

	private void listDeliveryPartners() {
		if (deliveryPartners.isEmpty()) {
			ConsoleFormatter.printError("No delivery partners available.");
			return;
		}
		ConsoleFormatter.printTitle("Current Delivery Partners");
		ConsoleFormatter.printHeader("No.", "Name");
		for (int i = 0; i < deliveryPartners.size(); i++) {
			ConsoleFormatter.printRow(i + 1, deliveryPartners.get(i).getName());
		}
	}

	private void addDeliveryPartner() {
		String name = InputHelper.readLine("Enter new partner name");
		deliveryPartners.add(new DeliveryPartner(name));
		ConsoleFormatter.printSuccess("Added partner: " + name);
	}

	private void removeDeliveryPartner() {
		if (deliveryPartners.isEmpty()) {
			ConsoleFormatter.printError("No partners to remove.");
			return;
		}
		listDeliveryPartners();
		int index = InputHelper.readInt("Enter number of partner to remove");
		if (index < 1 || index > deliveryPartners.size()) {
			ConsoleFormatter.printError("Invalid selection.");
			return;
		}
		String removed = deliveryPartners.remove(index - 1).getName();
		ConsoleFormatter.printSuccess("Removed partner: " + removed);
	}

	private void handleCustomerFlow() {
		Cart cart = new Cart();
		while (true) {
			ConsoleFormatter.printTitle("Customer Menu");
			ConsoleFormatter.printHeader("No.", "Action");
			ConsoleFormatter.printRow(1, "Browse & Add to Cart");
			ConsoleFormatter.printRow(2, "View Cart");
			ConsoleFormatter.printRow(3, "Remove Item from Cart");
			ConsoleFormatter.printRow(4, "Checkout");
			ConsoleFormatter.printRow(5, "Back to Main Menu");

			int choice = InputHelper.readInt("Choice");

			switch (choice) {
			case 1 -> {
				try {
					browseAndAdd(cart);
				} catch (InvalidMenuSelectionException | InvalidQuantityException e) {
					ConsoleFormatter.printError(e.getMessage());
				}
			}
			case 2 -> cart.displayCart();
			case 3 -> removeFromCart(cart);
			case 4 -> checkout(cart);
			case 5 -> {
				ConsoleFormatter.printSuccess("Returning to main menu");
				return;
			}
			default -> ConsoleFormatter.printError("Invalid choice");
			}
		}
	}

	private void browseAndAdd(Cart cart) throws InvalidMenuSelectionException, InvalidQuantityException {
		IMenuType selectedMenu = selectMenu();
		List<FoodItem> items = selectedMenu.getFoodItems();

		if (items.isEmpty()) {
			ConsoleFormatter.printError("No food items in this menu");
			return;
		}

		ConsoleFormatter.printTitle("Available Food Items");
		ConsoleFormatter.printHeader("No.", "Name", "Price", "Type");
		for (int i = 0; i < items.size(); i++) {
			FoodItem item = items.get(i);
			ConsoleFormatter.printRow(i + 1, item.getFoodName(), "₹" + String.format("%.2f", item.getPrice()),
					item.getFoodType().getFoodType());
			System.out.println("      ↳ " + item.getDescription());
			System.out.println();
		}

		int itemChoice = InputHelper.readInt("Select item to add to cart");
		if (itemChoice < 1 || itemChoice > items.size())
			throw new InvalidMenuSelectionException("Invalid item selection");

		int quantity = InputHelper.readInt("Enter quantity");
		if (quantity <= 0)
			throw new InvalidQuantityException("Quantity must be greater than 0");

		cart.addToCart(items.get(itemChoice - 1), quantity);
		ConsoleFormatter.printSuccess("Added to cart: " + items.get(itemChoice - 1).getFoodName() + " x" + quantity);
	}

	private void removeFromCart(Cart cart) {
		if (cart.isEmpty()) {
			ConsoleFormatter.printError("Cart is empty.");
			return;
		}

		cart.displayCart();
		String foodName = InputHelper.readLine("Enter the food name to remove");

		if (cart.removeFromCart(foodName)) {
			ConsoleFormatter.printSuccess("Removed: " + foodName);
		} else {
			ConsoleFormatter.printError("Item not found in cart: " + foodName);
		}
	}

	private void checkout(Cart cart) {
		if (cart.isEmpty()) {
			ConsoleFormatter.printError("Cart is empty. Add items first");
			return;
		}

		cart.displayCart();

		// Step 1: Calculate total (without streams)
		double total = 0;
		for (LineItem item : cart.getCartItems()) {
			total += item.calculateLineItemCost();
		}

		// Step 2: Apply discount if total > 500 (added new feature of discount, the amount could be managed in the code itself)
		double discount = 0;
		if (total > 500) {
			discount = 100;
			ConsoleFormatter.printSuccess("Flat ₹100 discount applied on orders above ₹500!");
		}

		double finalAmount = total - discount;

		// Step 3: Ask user to confirm checkout
		String confirm = InputHelper.readLine("Total amount ₹" + String.format("%.2f", finalAmount) + " — proceed? (yes/no)");
		if (!confirm.equalsIgnoreCase("yes")) {
			ConsoleFormatter.printError("Checkout cancelled");
			return;
		}

		// Step 4: Choose payment method
		ConsoleFormatter.printHeader("No.", "Method");
		ConsoleFormatter.printRow(1, "UPI");
		ConsoleFormatter.printRow(2, "Credit Card");

		IPayment payment = null;
		try {
			int method = InputHelper.readInt("Select payment method");
			switch (method) {
			case 1 -> payment = new UPIPayment(InputHelper.readLine("Enter your UPI ID"));
			case 2 -> payment = new CreditCardPayment(InputHelper.readLine("Enter your Credit Card Number"));
			default -> {
				ConsoleFormatter.printError("Invalid payment choice");
				return;
			}
			}
		} catch (InvalidUPIException | InvalidCardException | InvalidInputException e) {
			ConsoleFormatter.printError(e.getMessage());
			return; // back to customer menu
		}

		// Step 5: Make the payment
		if (!payment.pay(finalAmount)) {
			ConsoleFormatter.printError("Payment failed. Try again.");
			return;
		}

		// Step 6: Place order
		Order order = new Order(new ArrayList<>(cart.getCartItems()));
		customer.placeOrder(order);
		new Invoice(order, customer, payment).print();
		cart.clearCart();

		// Step 7: Assign delivery partner
		DeliveryPartner partner = deliveryPartners.get((int) (Math.random() * deliveryPartners.size()));
		partner.assignOrder(order);

		ConsoleFormatter.printSuccess("Order placed successfully!");
	}


	private IMenuType selectMenu() throws InvalidMenuSelectionException {
		ConsoleFormatter.printHeader("No.", "Menu Type");
		for (int i = 0; i < menus.size(); i++) {
			ConsoleFormatter.printRow(i + 1, menus.get(i).getMenuType());
		}
		int choice = InputHelper.readInt("Select a menu");
		if (choice < 1 || choice > menus.size())
			throw new InvalidMenuSelectionException("Invalid menu selection");
		return menus.get(choice - 1);
	}

	private IFoodType mapFoodTypeFromString(String input) {
		return switch (input.toLowerCase()) {
		case "appetizer" -> new Appetizer();
		case "starter" -> new Starter();
		case "maincourse" -> new MainCourse();
		case "dessert" -> new Dessert();
		default -> null;
		};
	}
}
