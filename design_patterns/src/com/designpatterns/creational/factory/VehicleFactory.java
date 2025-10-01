package com.designpatterns.creational.factory;

public class VehicleFactory {
    
    public static Vehicle createVehicle(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        
        String normalizedType = type.trim().toLowerCase();
        
        switch (normalizedType) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            case "truck":
                return new Truck();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type + 
                    ". Available types: car, bike, truck");
        }
    }
    
    public static boolean isValidVehicleType(String type) {
        if (type == null || type.isEmpty()) {
            return false;
        }
        String normalizedType = type.trim().toLowerCase();
        return normalizedType.equals("car") || 
               normalizedType.equals("bike") || 
               normalizedType.equals("truck");
    }
}
