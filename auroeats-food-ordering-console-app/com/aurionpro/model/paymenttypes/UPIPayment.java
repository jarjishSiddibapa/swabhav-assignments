package com.aurionpro.model.paymenttypes;

import com.aurionpro.util.ConsoleFormatter;
import com.aurionpro.util.PaymentValidator;

public class UPIPayment implements IPayment {
	private String upiId;

	public UPIPayment(String upiId) {
		PaymentValidator.validateUpi(upiId);
		this.upiId = upiId;
	}

	@Override
	public boolean pay(double amount) {
		ConsoleFormatter.printRow("Payment", "Processing UPI payment of ₹" + amount + " via " + upiId);
		return true;
	}

	@Override
	public String getPaymentDetails() {
		return "UPI: " + upiId;
	}
}
