package com.designpatterns.creational.factory;

public class Bike implements Vehicle {
    
    @Override
    public void drive() {
        System.out.println("🏍️ Riding a bike through city traffic at 60 km/h!");
    }
    
    @Override
    public String getType() {
        return "Bike";
    }
}
