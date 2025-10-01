package com.astronaut.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TimeValidator {
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):([0-5][0-9])$");
    
    public static boolean isValidTimeFormat(String time) {
        if (time == null || time.trim().isEmpty()) {
            return false;
        }
        
        Matcher matcher = TIME_PATTERN.matcher(time.trim());
        if (!matcher.matches()) {
            return false;
        }
        
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            
            return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isEndTimeAfterStartTime(String startTime, String endTime) {
        if (!isValidTimeFormat(startTime) || !isValidTimeFormat(endTime)) {
            return false;
        }
        
        try {
            int startMinutes = convertToMinutes(startTime);
            int endMinutes = convertToMinutes(endTime);
            
            return endMinutes > startMinutes;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static int convertToMinutes(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be null or empty");
        }
        
        String[] parts = time.trim().split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        
        return hours * 60 + minutes;
    }
    
    public static String convertMinutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
}