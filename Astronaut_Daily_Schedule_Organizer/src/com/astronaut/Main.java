package com.astronaut;

import com.astronaut.menu.MenuManager;
import com.astronaut.util.Logger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.info("Astronaut Daily Schedule Organizer started");
        
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   ASTRONAUT DAILY SCHEDULE ORGANIZER              ║");
        System.out.println("║   Manage your daily tasks efficiently             ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager(scanner);
        
        try {
            menuManager.run();
        } catch (Exception e) {
            logger.error("Critical error: " + e.getMessage());
            System.err.println("⚠ Application encountered a critical error. Please check logs.");
            e.printStackTrace();
        } finally {
            scanner.close();
            logger.info("Application stopped");
            System.out.println("\n✓ Thank you for using Astronaut Schedule Organizer!");
        }
    }
}