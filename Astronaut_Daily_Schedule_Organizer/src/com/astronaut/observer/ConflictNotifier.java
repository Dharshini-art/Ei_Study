package com.astronaut.observer;

import com.astronaut.model.Task;
import com.astronaut.util.Logger;

public class ConflictNotifier implements TaskObserver {
    private final Logger logger;
    
    public ConflictNotifier() {
        this.logger = Logger.getInstance();
    }
    
    @Override
    public void onTaskConflict(String message, Task conflictingTask) {
        if (message == null || conflictingTask == null) {
            return;
        }
        
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║           ⚠ CONFLICT DETECTED ⚠              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("❌ " + message);
        System.out.println("📋 Conflicting Task Details:");
        System.out.println("   " + conflictingTask.toString());
        System.out.println("══════════════════════════════════════════════\n");
        
        logger.warn("Conflict detected: " + message);
    }
}