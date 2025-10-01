package com.designpatterns.behavioral.strategy;

public class UPIPayment implements PaymentStrategy {
    private String upiId;
    
    public UPIPayment(String upiId) {
        if (upiId == null || upiId.isEmpty()) {
            throw new IllegalArgumentException("UPI ID cannot be null or empty");
        }
        this.upiId = upiId;
    }
    
    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("✗ Invalid amount!");
            return;
        }
        System.out.println("📱 Processing UPI payment...");
        System.out.println("   UPI ID: " + upiId);
        System.out.println("   Amount: ₹" + amount);
        System.out.println("✓ Payment successful via UPI!");
    }
    
    @Override
    public String getPaymentType() {
        return "UPI";
    }
}
