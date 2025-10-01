package com.designpatterns.menu;

import com.designpatterns.behavioral.observer.*;
import com.designpatterns.behavioral.strategy.*;
import com.designpatterns.creational.factory.*;
import com.designpatterns.creational.singleton.*;
import com.designpatterns.structural.adapter.*;
import com.designpatterns.structural.decorator.*;
import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;
    private boolean isRunning;
    private Logger logger;
    
    public MenuManager(Scanner scanner) {
        this.scanner = scanner;
        this.isRunning = true;
        this.logger = Logger.getInstance();
    }
    
    public void run() {
        while (shouldContinue()) {
            displayMenu();
            handleUserInput();
        }
    }
    
    private boolean shouldContinue() {
        return isRunning;
    }
    
    private void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║      DESIGN PATTERNS                  ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("  BEHAVIORAL PATTERNS:");
        System.out.println("    1. Observer Pattern (Weather Station)");
        System.out.println("    2. Strategy Pattern (Payment System)");
        System.out.println("\n  CREATIONAL PATTERNS:");
        System.out.println("    3. Factory Pattern (Vehicle Creation)");
        System.out.println("    4. Singleton Pattern (Database Connection)");
        System.out.println("\n  STRUCTURAL PATTERNS:");
        System.out.println("    5. Adapter Pattern (Media Player)");
        System.out.println("    6. Decorator Pattern (Coffee Shop)");
        System.out.println("\n  0. Exit Application");
        System.out.println("════════════════════════════════════════════");
        System.out.print("Choose an option: ");
    }
    
    private void handleUserInput() {
        try {
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("⚠ Invalid input. Please enter a number.");
                return;
            }
            int choice = Integer.parseInt(input.trim());
            processChoice(choice);
        } catch (NumberFormatException e) {
            logger.log("Invalid input format: " + e.getMessage());
            System.out.println("⚠ Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            logger.log("Error processing input: " + e.getMessage());
            System.out.println("⚠ An error occurred. Please try again.");
        }
    }
    
    private void processChoice(int choice) {
        logger.log("User selected option: " + choice);
        
        try {
            switch (choice) {
                case 1:
                    runObserverDemo();
                    break;
                case 2:
                    runStrategyDemo();
                    break;
                case 3:
                    runFactoryDemo();
                    break;
                case 4:
                    runSingletonDemo();
                    break;
                case 5:
                    runAdapterDemo();
                    break;
                case 6:
                    runDecoratorDemo();
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("\n✓ Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("⚠ Invalid option. Please choose between 0-6.");
            }
        } catch (Exception e) {
            logger.log("Error in demo execution: " + e.getMessage());
            System.out.println("⚠ Error running demo: " + e.getMessage());
        }
    }
    
    private void runObserverDemo() {
        System.out.println("\n--- OBSERVER PATTERN ---");
        WeatherStation station = new WeatherStation();
        
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        TVDisplay tvDisplay = new TVDisplay();
        
        station.addObserver(phoneDisplay);
        station.addObserver(tvDisplay);
        
        System.out.println("Setting temperature to 25°C:");
        station.setTemperature(25.0f);
        
        System.out.println("\nSetting temperature to 30°C:");
        station.setTemperature(30.0f);
        
        System.out.println("\nRemoving phone display...");
        station.removeObserver(phoneDisplay);
        
        System.out.println("Setting temperature to 28°C:");
        station.setTemperature(28.0f);
        
        logger.log("Observer pattern completed");
    }
    
    private void runStrategyDemo() {
        System.out.println("\n--- STRATEGY PATTERN ---");
        PaymentContext paymentContext = new PaymentContext();
        
        System.out.println("Paying 500 rupees via Credit Card:");
        paymentContext.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        paymentContext.processPayment(500.0);
        
        System.out.println("\nPaying 300 rupees via UPI:");
        paymentContext.setPaymentStrategy(new UPIPayment("user@upi"));
        paymentContext.processPayment(300.0);
        
        System.out.println("\nPaying 150 rupees via Cash:");
        paymentContext.setPaymentStrategy(new CashPayment());
        paymentContext.processPayment(150.0);
        
        logger.log("Strategy pattern completed");
    }
    
    private void runFactoryDemo() {
        System.out.println("\n--- FACTORY PATTERN ---");
        
        try {
            Vehicle car = VehicleFactory.createVehicle("car");
            System.out.print("Created Car: ");
            car.drive();
            
            Vehicle bike = VehicleFactory.createVehicle("bike");
            System.out.print("Created Bike: ");
            bike.drive();
            
            Vehicle truck = VehicleFactory.createVehicle("truck");
            System.out.print("Created Truck: ");
            truck.drive();
            
            System.out.println("\nTrying invalid vehicle type:");
            Vehicle invalid = VehicleFactory.createVehicle("plane");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        logger.log("Factory pattern completed");
    }
    
    private void runSingletonDemo() {
        System.out.println("\n--- SINGLETON PATTERN ---");
        
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();
        db1.executeQuery("SELECT * FROM users");
        
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.executeQuery("SELECT * FROM products");
        
        System.out.println("\nChecking if both instances are same:");
        if (db1 == db2) {
            System.out.println("✓ Both references point to the SAME instance (Singleton working!)");
        } else {
            System.out.println("✗ Different instances (Singleton failed!)");
        }
        
        db1.disconnect();
        
        logger.log("Singleton pattern completed");
    }
    
    private void runAdapterDemo() {
        System.out.println("\n--- ADAPTER PATTERN ---");
        
        MediaPlayer audioPlayer = new MediaPlayer();
        
        System.out.println("Playing MP3 file:");
        audioPlayer.play("song.mp3");
        
        System.out.println("\nPlaying VLC file:");
        audioPlayer.play("movie.vlc");
        
        System.out.println("\nPlaying MP4 file:");
        audioPlayer.play("video.mp4");
        
        System.out.println("\nTrying unsupported format:");
        audioPlayer.play("document.avi");
        
        logger.log("Adapter pattern completed");
    }
    
    private void runDecoratorDemo() {
        System.out.println("\n--- DECORATOR PATTERN ---");
        
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " - ₹" + simpleCoffee.getCost());
        
        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(milkCoffee.getDescription() + " - ₹" + milkCoffee.getCost());
        
        Coffee sugarMilkCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(sugarMilkCoffee.getDescription() + " - ₹" + sugarMilkCoffee.getCost());
        
        Coffee premiumCoffee = new WhippedCreamDecorator(
            new SugarDecorator(
                new MilkDecorator(new SimpleCoffee())
            )
        );
        System.out.println(premiumCoffee.getDescription() + " - ₹" + premiumCoffee.getCost());
        
        logger.log("Decorator pattern completed");
    }
}
