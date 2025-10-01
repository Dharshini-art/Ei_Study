package com.designpatterns.structural.decorator;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        if (coffee == null) {
            throw new IllegalArgumentException("Coffee cannot be null");
        }
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

