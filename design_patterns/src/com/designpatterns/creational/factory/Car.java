package com.designpatterns.creational.factory;

public class Car implements Vehicle {
    
    @Override
    public void drive() {
        System.out.println("🚗 Driving a car on the highway at 100 km/h!");
    }
    
    @Override
    public String getType() {
        return "Car";
    }
}
