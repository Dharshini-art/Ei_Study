package com.designpatterns.creational.factory;

public class Truck implements Vehicle {
    
    @Override
    public void drive() {
        System.out.println("🚚 Driving a truck carrying heavy goods at 80 km/h!");
    }
    
    @Override
    public String getType() {
        return "Truck";
    }
}