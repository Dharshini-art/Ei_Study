package com.designpatterns.behavioral.observer;

public class PhoneDisplay implements WeatherObserver {
    private String deviceName;
    
    public PhoneDisplay() {
        this.deviceName = "Phone Display";
    }
    
    @Override
    public void update(float temperature) {
        System.out.println("📱 " + deviceName + ": Temperature updated to " + temperature + "°C");
        if (temperature > 35) {
            System.out.println("   Alert: It's very hot outside!");
        }
    }
}
