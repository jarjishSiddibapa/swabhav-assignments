package com.aurionpro.model;

public class IDGenerator {
	private static int customerId = 1000;
	private static int orderId = 2000;
	private static int invoiceId = 1;
	
	public static int generateCustomerId() {
		return customerId++;
	}
	
	public static int generateOrderId() {
		return orderId++;
	}
	
	public static int generateInvoiceId() {
		return invoiceId++;
	}
}
