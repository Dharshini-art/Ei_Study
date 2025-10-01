package com.astronaut.model;

public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private boolean completed;
    
    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public int getStartMinutes() {
        String[] parts = startTime.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    
    public int getEndMinutes() {
        String[] parts = endTime.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    
    public boolean overlapsWith(Task other) {
        if (other == null) {
            return false;
        }
        
        int thisStart = this.getStartMinutes();
        int thisEnd = this.getEndMinutes();
        int otherStart = other.getStartMinutes();
        int otherEnd = other.getEndMinutes();
        
        return (thisStart < otherEnd && thisEnd > otherStart);
    }
    
    @Override
    public String toString() {
        String status = completed ? "[COMPLETED]" : "";
        return String.format("%s - %s: %s [%s] %s", 
            startTime, endTime, description, priority, status);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return description.equals(task.description) && 
               startTime.equals(task.startTime);
    }
    
    @Override
    public int hashCode() {
        return description.hashCode() + startTime.hashCode();
    }
}