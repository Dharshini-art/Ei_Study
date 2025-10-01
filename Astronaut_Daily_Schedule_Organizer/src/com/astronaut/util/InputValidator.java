package com.astronaut.util;

public class InputValidator {
    private static final String[] VALID_PRIORITIES = {"High", "Medium", "Low"};
    
    public static boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty() && description.trim().length() <= 200;
    }
    
    public static boolean isValidPriority(String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            return false;
        }
        
        String normalizedPriority = priority.trim();
        for (String validPriority : VALID_PRIORITIES) {
            if (validPriority.equalsIgnoreCase(normalizedPriority)) {
                return true;
            }
        }
        return false;
    }
    
    public static String normalizePriority(String priority) {
        if (!isValidPriority(priority)) {
            return "Medium";
        }
        
        String normalized = priority.trim().toLowerCase();
        if (normalized.equals("high")) return "High";
        if (normalized.equals("medium")) return "Medium";
        if (normalized.equals("low")) return "Low";
        
        return "Medium";
    }
    
    public static boolean isValidMenuOption(String input, int minOption, int maxOption) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        try {
            int option = Integer.parseInt(input.trim());
            return option >= minOption && option <= maxOption;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}