package com.designpatterns.behavioral.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Invalid amount!");
            return;
        }
        System.out.println("💳 Processing Credit Card payment...");
        System.out.println("   Card: **** **** **** " + cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
        System.out.println("   Amount: ₹" + amount);
        System.out.println("✓ Payment successful!");
    }
    
    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}

