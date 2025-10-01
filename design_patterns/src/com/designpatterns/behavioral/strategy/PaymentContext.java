package com.designpatterns.behavioral.strategy;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = paymentStrategy;
        System.out.println("Payment method set to: " + paymentStrategy.getPaymentType());
    }
    
    public void processPayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("✗ No payment method selected!");
            return;
        }
        
        try {
            paymentStrategy.pay(amount);
        } catch (Exception e) {
            System.out.println("✗ Payment failed: " + e.getMessage());
        }
    }
}