package com.aurionpro.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aurionpro.model.paymenttypes.IPayment;
import com.aurionpro.util.ConsoleFormatter;

public class Invoice {
	private int invoiceNo;
	private Order order;
	private Customer customer;
	private IPayment payment;
	private LocalDateTime timestamp;

	public Invoice(Order order, Customer customer, IPayment payment) {
		this.invoiceNo = IDGenerator.generateInvoiceId();
		this.order = order;
		this.customer = customer;
		this.payment = payment;
		this.timestamp = LocalDateTime.now();
	}

	public void print() {
		ConsoleFormatter.printTitle("Invoice #" + invoiceNo);
		ConsoleFormatter.printRow("Date", timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		ConsoleFormatter.printRow("Customer", customer.getName() + " (" + customer.getContactInfo() + ")");
		ConsoleFormatter.printSeparator();

		order.displayOrder(); // prints item details

		ConsoleFormatter.printRow("Payment Method", payment.getPaymentDetails());
		ConsoleFormatter.printSuccess("Thank you for your order!");
	}
}
