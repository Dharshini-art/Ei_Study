package com.astronaut.manager;

import com.astronaut.model.Task;
import com.astronaut.observer.TaskObserver;
import com.astronaut.util.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<TaskObserver> observers;
    private final Logger logger;
    
    private ScheduleManager() {
        this.tasks = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.logger = Logger.getInstance();
        logger.info("ScheduleManager initialized");
    }
    
    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }
    
    public void addObserver(TaskObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            logger.info("Observer added to ScheduleManager");
        }
    }
    
    public void removeObserver(TaskObserver observer) {
        if (observers.remove(observer)) {
            logger.info("Observer removed from ScheduleManager");
        }
    }
    
    private void notifyObservers(String message, Task conflictingTask) {
        for (TaskObserver observer : observers) {
            try {
                observer.onTaskConflict(message, conflictingTask);
            } catch (Exception e) {
                logger.error("Error notifying observer: " + e.getMessage());
            }
        }
    }
    
    public boolean addTask(Task task) {
        if (task == null) {
            logger.error("Attempted to add null task");
            return false;
        }
        
        // Check for conflicts
        Task conflictingTask = findConflictingTask(task);
        if (conflictingTask != null) {
            String message = "Task conflicts with existing task: " + conflictingTask.getDescription();
            logger.warn(message);
            notifyObservers(message, conflictingTask);
            return false;
        }
        
        tasks.add(task);
        logger.info("Task added: " + task.getDescription());
        return true;
    }
    
    private Task findConflictingTask(Task newTask) {
        for (Task existingTask : tasks) {
            if (newTask.overlapsWith(existingTask)) {
                return existingTask;
            }
        }
        return null;
    }
    
    public boolean removeTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            logger.error("Invalid task description for removal");
            return false;
        }
        
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description.trim())) {
                taskToRemove = task;
                break;
            }
        }
        
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            logger.info("Task removed: " + description);
            return true;
        } else {
            logger.warn("Task not found for removal: " + description);
            return false;
        }
    }
    
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    public List<Task> getTasksSortedByStartTime() {
        return tasks.stream()
                .sorted((t1, t2) -> Integer.compare(t1.getStartMinutes(), t2.getStartMinutes()))
                .collect(Collectors.toList());
    }
    
    public List<Task> getTasksByPriority(String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return tasks.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority.trim()))
                .sorted((t1, t2) -> Integer.compare(t1.getStartMinutes(), t2.getStartMinutes()))
                .collect(Collectors.toList());
    }
    
    public boolean editTask(String description, String newStartTime, String newEndTime, String newPriority) {
        Task taskToEdit = null;
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description.trim())) {
                taskToEdit = task;
                break;
            }
        }
        
        if (taskToEdit == null) {
            logger.warn("Task not found for editing: " + description);
            return false;
        }
        
        // Temporarily remove the task to check conflicts
        tasks.remove(taskToEdit);
        
        // Update task details
        taskToEdit.setStartTime(newStartTime);
        taskToEdit.setEndTime(newEndTime);
        taskToEdit.setPriority(newPriority);
        
        // Check for conflicts with the updated task
        Task conflictingTask = findConflictingTask(taskToEdit);
        if (conflictingTask != null) {
            // Revert and add back the original task
            tasks.add(taskToEdit);
            String message = "Updated task conflicts with: " + conflictingTask.getDescription();
            logger.warn(message);
            notifyObservers(message, conflictingTask);
            return false;
        }
        
        tasks.add(taskToEdit);
        logger.info("Task edited: " + description);
        return true;
    }
    
    public boolean markTaskAsCompleted(String description) {
        if (description == null || description.trim().isEmpty()) {
            return false;
        }
        
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description.trim())) {
                task.setCompleted(true);
                logger.info("Task marked as completed: " + description);
                return true;
            }
        }
        
        logger.warn("Task not found to mark as completed: " + description);
        return false;
    }
    
    public int getTaskCount() {
        return tasks.size();
    }
    
    public void clearAllTasks() {
        tasks.clear();
        logger.info("All tasks cleared");
    }
}