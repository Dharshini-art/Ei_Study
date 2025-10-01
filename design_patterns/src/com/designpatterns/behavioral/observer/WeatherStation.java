package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    private List<WeatherObserver> observers;
    private float temperature;
    
    public WeatherStation() {
        this.observers = new ArrayList<>();
    }
    
    public void addObserver(WeatherObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✓ Observer added successfully");
        }
    }
    
    public void removeObserver(WeatherObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("✓ Observer removed successfully");
        }
    }
    
    public void setTemperature(float temperature) {
        if (temperature < -50 || temperature > 60) {
            System.out.println("⚠ Warning: Unusual temperature value!");
        }
        this.temperature = temperature;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            try {
                observer.update(temperature);
            } catch (Exception e) {
                System.err.println("Error notifying observer: " + e.getMessage());
            }
        }
    }
    
    public float getTemperature() {
        return temperature;
    }
}