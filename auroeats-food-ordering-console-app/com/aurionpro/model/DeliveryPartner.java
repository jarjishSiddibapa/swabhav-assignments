package com.aurionpro.model;

import com.aurionpro.util.ConsoleFormatter;

public class DeliveryPartner {
	private final String name;

	public DeliveryPartner(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void assignOrder(Order order) {
		ConsoleFormatter.printRow("[Dispatch]", name + " assigned to deliver Order #" + order.getId());

		try {
			Thread.sleep(5000); // simulate delivery delay
			ConsoleFormatter.printRow("[Delivery]", name + " has delivered Order #" + order.getId());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			ConsoleFormatter.printError("Delivery interrupted for Order #" + order.getId());
		}
	}
}
