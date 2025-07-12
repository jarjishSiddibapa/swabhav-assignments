package com.aurionpro.util;

import com.aurionpro.exception.InvalidCardException;
import com.aurionpro.exception.InvalidUPIException;

public class PaymentValidator {
//	verifying upi using simple regex for the upi id
	public static void validateUpi(String upiId) {
		if (upiId == null || !upiId.matches("[\\w.\\-]+@[\\w]+")) {
			throw new InvalidUPIException("Invalid UPI ID format.");
		}
	}
	
//	verifying credit card number using Luhn's Algorithm!
	public static void validateCard(String cardNumber) {
        if (cardNumber == null || !cardNumber.matches("\\d{12,19}")) {
            throw new InvalidCardException("Card number must be 12–19 digits");
        }
        int sum = 0;
        boolean alt = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = cardNumber.charAt(i) - '0';
            if (alt) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alt = !alt;
        }
        if (sum % 10 != 0) {
            throw new InvalidCardException("Credit card number failed Luhn check");
        }
    }
}