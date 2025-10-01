package com.astronaut.factory;

import com.astronaut.model.Task;
import com.astronaut.util.Logger;
import com.astronaut.util.TimeValidator;
import com.astronaut.util.InputValidator;

public class TaskFactory {
    private static final Logger logger = Logger.getInstance();
    
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        // Validate inputs
        if (!InputValidator.isValidDescription(description)) {
            logger.error("Invalid task description: " + description);
            throw new IllegalArgumentException("Task description cannot be null or empty");
        }
        
        if (!TimeValidator.isValidTimeFormat(startTime)) {
            logger.error("Invalid start time format: " + startTime);
            throw new IllegalArgumentException("Invalid start time format. Use HH:MM (24-hour format)");
        }
        
        if (!TimeValidator.isValidTimeFormat(endTime)) {
            logger.error("Invalid end time format: " + endTime);
            throw new IllegalArgumentException("Invalid end time format. Use HH:MM (24-hour format)");
        }
        
        if (!TimeValidator.isEndTimeAfterStartTime(startTime, endTime)) {
            logger.error("End time must be after start time: " + startTime + " - " + endTime);
            throw new IllegalArgumentException("End time must be after start time");
        }
        
        if (!InputValidator.isValidPriority(priority)) {
            logger.error("Invalid priority level: " + priority);
            throw new IllegalArgumentException("Priority must be High, Medium, or Low");
        }
        
        Task task = new Task(description, startTime, endTime, priority);
        logger.info("Task created successfully: " + description);
        return task;
    }
    
    public static Task createTaskWithDefaults(String description, String startTime, String endTime) {
        return createTask(description, startTime, endTime, "Medium");
    }
}