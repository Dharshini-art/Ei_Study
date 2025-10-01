package com.astronaut.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private static final String LOG_FILE = "logs/schedule.log";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Object lock = new Object();
    
    private Logger() {
        initializeLogFile();
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    private void initializeLogFile() {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            File logFile = new File(LOG_FILE);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }
    
    public void log(String level, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);
        
        writeToFile(logMessage);
    }
    
    private void writeToFile(String message) {
        synchronized (lock) {
            try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
                writer.write(message + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Failed to write to log file: " + e.getMessage());
            }
        }
    }
    
    public void info(String message) {
        log("INFO", message);
    }
    
    public void warn(String message) {
        log("WARN", message);
    }
    
    public void error(String message) {
        log("ERROR", message);
    }
    
    public void debug(String message) {
        log("DEBUG", message);
    }
}