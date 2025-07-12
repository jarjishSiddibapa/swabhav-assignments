package com.aurionpro.model.paymenttypes;

import com.aurionpro.util.ConsoleFormatter;
import com.aurionpro.util.PaymentValidator;

public class CreditCardPayment implements IPayment {
	private String cardNumber;

	public CreditCardPayment(String cardNumber) {
		PaymentValidator.validateCard(cardNumber);
		this.cardNumber = cardNumber;
	}

	@Override
	public boolean pay(double amount) {
		String masked = "****" + cardNumber.substring(cardNumber.length() - 4);
		ConsoleFormatter.printRow("Payment", "Processing Credit Card payment of ₹" + amount + " (Card " + masked + ")");
		// Simulate success
		return true;
	}

	@Override
	public String getPaymentDetails() {
		return "Credit Card ****" + cardNumber.substring(cardNumber.length() - 4);
	}
}
