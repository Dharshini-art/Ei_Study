package com.designpatterns.behavioral.observer;

public class TVDisplay implements WeatherObserver {
    private String deviceName;
    
    public TVDisplay() {
        this.deviceName = "TV Display";
    }
    
    @Override
    public void update(float temperature) {
        System.out.println("📺 " + deviceName + ": Current temperature is " + temperature + "°C");
        if (temperature < 15) {
            System.out.println("   Alert: It's cold! Wear warm clothes.");
        }
    }
}
