package com.designpatterns.behavioral.strategy;

public class CashPayment implements PaymentStrategy {
    
    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Invalid amount!");
            return;
        }
        System.out.println("💵 Processing Cash payment...");
        System.out.println("   Amount: ₹" + amount);
        System.out.println("✓ Cash payment received!");
    }
    
    @Override
    public String getPaymentType() {
        return "Cash";
    }
}
