package com.aurionpro.model.paymenttypes;

public interface IPayment {
	boolean pay(double amount);
	String getPaymentDetails();
}
