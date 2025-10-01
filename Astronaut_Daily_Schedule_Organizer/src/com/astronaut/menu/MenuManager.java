package com.astronaut.menu;

import com.astronaut.manager.ScheduleManager;
import com.astronaut.model.Task;
import com.astronaut.factory.TaskFactory;
import com.astronaut.observer.ConflictNotifier;
import com.astronaut.util.Logger;
import com.astronaut.util.InputValidator;
import java.util.Scanner;
import java.util.List;

public class MenuManager {
    private final Scanner scanner;
    private final ScheduleManager scheduleManager;
    private final Logger logger;
    private boolean isRunning;
    
    public MenuManager(Scanner scanner) {
        this.scanner = scanner;
        this.scheduleManager = ScheduleManager.getInstance();
        this.logger = Logger.getInstance();
        this.isRunning = true;
        
        // Register observer for conflict notifications
        ConflictNotifier notifier = new ConflictNotifier();
        scheduleManager.addObserver(notifier);
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
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           ASTRONAUT SCHEDULE MENU            ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("  1. Add New Task");
        System.out.println("  2. Remove Task");
        System.out.println("  3. View All Tasks (Sorted by Time)");
        System.out.println("  4. View Tasks by Priority");
        System.out.println("  5. Edit Task");
        System.out.println("  6. Mark Task as Completed");
        System.out.println("  0. Exit");
        System.out.println("══════════════════════════════════════════════");
        System.out.print("Choose an option: ");
    }
    
    private void handleUserInput() {
        try {
            String input = scanner.nextLine();
            if (!InputValidator.isValidMenuOption(input, 0, 6)) {
                System.out.println("⚠ Invalid option. Please enter a number between 0-6.");
                return;
            }
            
            int choice = Integer.parseInt(input.trim());
            processChoice(choice);
        } catch (Exception e) {
            logger.error("Error processing input: " + e.getMessage());
            System.out.println("⚠ An error occurred. Please try again.");
        }
    }
    
    private void processChoice(int choice) {
        logger.info("User selected option: " + choice);
        
        try {
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    viewAllTasks();
                    break;
                case 4:
                    viewTasksByPriority();
                    break;
                case 5:
                    editTask();
                    break;
                case 6:
                    markTaskCompleted();
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("\n✓ Exiting Astronaut Schedule Organizer...");
                    break;
                default:
                    System.out.println("⚠ Invalid option.");
            }
        } catch (Exception e) {
            logger.error("Error executing option " + choice + ": " + e.getMessage());
            System.out.println("⚠ Error: " + e.getMessage());
        }
    }
    
    private void addTask() {
        System.out.println("\n--- ADD NEW TASK ---");
        
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter start time (HH:MM in 24-hour format): ");
        String startTime = scanner.nextLine();
        
        System.out.print("Enter end time (HH:MM in 24-hour format): ");
        String endTime = scanner.nextLine();
        
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();
        
        try {
            Task task = TaskFactory.createTask(description, startTime, endTime, 
                                              InputValidator.normalizePriority(priority));
            
            if (scheduleManager.addTask(task)) {
                System.out.println("✓ Task added successfully. No conflicts.");
                System.out.println("   " + task.toString());
            } else {
                System.out.println("✗ Task could not be added due to conflicts.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private void removeTask() {
        System.out.println("\n--- REMOVE TASK ---");
        
        if (scheduleManager.getTaskCount() == 0) {
            System.out.println("⚠ No tasks available to remove.");
            return;
        }
        
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();
        
        if (scheduleManager.removeTask(description)) {
            System.out.println("✓ Task removed successfully.");
        } else {
            System.out.println("✗ Error: Task not found.");
        }
    }
    
    private void viewAllTasks() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           ALL SCHEDULED TASKS                ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        
        List<Task> tasks = scheduleManager.getTasksSortedByStartTime();
        
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks scheduled for the day.");
        } else {
            System.out.println("Total Tasks: " + tasks.size() + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println("══════════════════════════════════════════════");
    }
    
    private void viewTasksByPriority() {
        System.out.println("\n--- VIEW TASKS BY PRIORITY ---");
        System.out.print("Enter priority level (High/Medium/Low): ");
        String priority = scanner.nextLine();
        
        if (!InputValidator.isValidPriority(priority)) {
            System.out.println("✗ Invalid priority level. Please use High, Medium, or Low.");
            return;
        }
        
        List<Task> tasks = scheduleManager.getTasksByPriority(priority);
        
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║     TASKS WITH PRIORITY: " + InputValidator.normalizePriority(priority).toUpperCase());
        System.out.println("╚══════════════════════════════════════════════╝");
        
        if (tasks.isEmpty()) {
            System.out.println("📭 No tasks found with priority: " + priority);
        } else {
            System.out.println("Total Tasks: " + tasks.size() + "\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println("══════════════════════════════════════════════");
    }
    
    private void editTask() {
        System.out.println("\n--- EDIT TASK ---");
        
        if (scheduleManager.getTaskCount() == 0) {
            System.out.println("⚠ No tasks available to edit.");
            return;
        }
        
        System.out.print("Enter task description to edit: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter new start time (HH:MM): ");
        String newStartTime = scanner.nextLine();
        
        System.out.print("Enter new end time (HH:MM): ");
        String newEndTime = scanner.nextLine();
        
        System.out.print("Enter new priority (High/Medium/Low): ");
        String newPriority = scanner.nextLine();
        
        try {
            // Validate new inputs using TaskFactory validation logic
            TaskFactory.createTask("temp", newStartTime, newEndTime, 
                                  InputValidator.normalizePriority(newPriority));
            
            if (scheduleManager.editTask(description, newStartTime, newEndTime, 
                                        InputValidator.normalizePriority(newPriority))) {
                System.out.println("✓ Task edited successfully.");
            } else {
                System.out.println("✗ Error: Task not found or conflicts with existing task.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private void markTaskCompleted() {
        System.out.println("\n--- MARK TASK AS COMPLETED ---");
        
        if (scheduleManager.getTaskCount() == 0) {
            System.out.println("⚠ No tasks available to mark as completed.");
            return;
        }
        
        System.out.print("Enter task description to mark as completed: ");
        String description = scanner.nextLine();
        
        if (scheduleManager.markTaskAsCompleted(description)) {
            System.out.println("✓ Task marked as completed successfully.");
        } else {
            System.out.println("✗ Error: Task not found.");
        }
    }
}