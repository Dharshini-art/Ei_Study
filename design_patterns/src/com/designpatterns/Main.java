package com.designpatterns;

import com.designpatterns.menu.MenuManager;
import com.designpatterns.creational.singleton.Logger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Application started");
        
        Scanner scanner = new Scanner(System.in);
        MenuManager menuManager = new MenuManager(scanner);
        
        try {
            menuManager.run();
        } catch (Exception e) {
            logger.log("Critical error: " + e.getMessage());
            System.err.println("Application encountered an error. Please check logs.");
        } finally {
            scanner.close();
            logger.log("Application stopped");
        }
    }
}