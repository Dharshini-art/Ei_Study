# Ei_Study (Coding Assessment)

Name : Dharshini T K
Roll No : 224011
College : K L N College Of Engineering

This repository contains two parts:
1. design_patterns – Exercise 1 with six design pattern demos.
2. Astronaut_Daily_schedule_Organizer – Exercise 2 for the Astronaut Daily Schedule Organizer.

# Design Patterns Demo - Exercise 1

A comprehensive Java implementation demonstrating 6 different design patterns with real-world use cases.

## 🎯 Design Patterns Implemented

### Behavioral Patterns
1. **Observer Pattern** - Weather Station system with multiple display devices
2. **Strategy Pattern** - Payment system supporting multiple payment methods

### Creational Patterns
3. **Factory Pattern** - Vehicle creation system
4. **Singleton Pattern** - Database connection and logging system

### Structural Patterns
5. **Adapter Pattern** - Media player supporting multiple formats
6. **Decorator Pattern** - Coffee shop with customizable add-ons

Class & Package Overview

  • Packages: 
    - behavioral (Observer & Strategy)
    - creational (Factory & Singleton)
    - structural (Adapter & Decorator)
    - menu (MenuSystem)
    
  • Key Classes: 
    - WeatherStation, WeatherDisplay, PaymentStrategy
    - VehicleFactory, DatabaseConnection, Logger
    - MediaPlayer, Coffee, CoffeeDecorator, Main
    
  • Key Features: 
    - Interactive menu system
    - No hard-coded while(true) loops
    - Comprehensive logging
    - Exception handling at all levels
    - Clean code organization


Compile the Project

```bash
javac -cp src src/com/designpatterns/Main.java
```

Run the Application

```bash
java -cp src com.designpatterns.Main
```
## 🎮 How to Use
When you run the application, you'll see an interactive menu:


DESIGN PATTERNS DEMO                 

  BEHAVIORAL PATTERNS:
    1. Observer Pattern (Weather Station)
    2. Strategy Pattern (Payment System)

  CREATIONAL PATTERNS:
    3. Factory Pattern (Vehicle Creation)
    4. Singleton Pattern (Database Connection)

  STRUCTURAL PATTERNS:
    5. Adapter Pattern (Media Player)
    6. Decorator Pattern (Coffee Shop)

  0. Exit Application

Choose an option: 
Simply enter a number (1-6) to see each pattern in action, or 0 to exit.

## 📊 Logging

All application events are logged to `logs/application.log` with timestamps:

[2025-10-01 10:30:45] Application started
[2025-10-01 10:30:52] User selected option: 1
[2025-10-01 10:30:52] Observer pattern demo completed
[2025-10-01 10:31:15] Application stopped
_________________________________________________________________________

# 🚀 Astronaut Daily Schedule Organizer - Exercise 2

A console-based application that helps astronauts organize their daily schedules efficiently. Uses Singleton, Factory, and Observer patterns for efficient task management. The application allows users to add, remove, view, and manage daily tasks with automatic conflict detection.

## 🎯 Design Patterns

### 1. Singleton Pattern
**Class:** `ScheduleManager`  
**Purpose:** Ensures only one instance of the schedule manager exists throughout the application  
**Implementation:** Thread-safe double-checked locking

### 2. Factory Pattern
**Class:** `TaskFactory`  
**Purpose:** Centralizes task creation with built-in validation  
**Benefits:** 
- Consistent object creation
- Validation in one place
- Easy to extend with new task types

### 3. Observer Pattern
**Classes:** `TaskObserver` (interface), `ConflictNotifier` (implementation)  
**Purpose:** Notifies users when task conflicts are detected  
**Benefits:**
- Loose coupling between schedule manager and notification system
- Easy to add multiple notification types (email, SMS, etc.)


Compile

```bash
# From project root directory
javac -cp src src/com/astronaut/*.java src/com/astronaut/*/*.java
javac -cp src src/com/astronaut/Main.java
```

Run

```bash
java -cp src com.astronaut.Main
```

## 📖 Usage Guide

### Main Menu Options

ASTRONAUT SCHEDULE MENU           

  1. Add New Task
  2. Remove Task
  3. View All Tasks (Sorted by Time)
  4. View Tasks by Priority
  5. Edit Task
  6. Mark Task as Completed
  0. Exit

### Example Usage
#### Adding a Task
Choose an option: 1
Enter task description: Morning Exercise
Enter start time (HH:MM in 24-hour format): 07:00
Enter end time (HH:MM in 24-hour format): 08:00
Enter priority (High/Medium/Low): High

✓ Task added successfully. No conflicts.
   07:00 - 08:00: Morning Exercise [High]
```

#### Viewing All Tasks
```
Choose an option: 3


ALL SCHEDULED TASKS                ║

Total Tasks: 2

1. 07:00 - 08:00: Morning Exercise [High]
2. 09:00 - 10:00: Team Meeting [Medium]

## 📝 Logging

All operations are logged to `logs/schedule.log`:

[2025-10-01 14:30:00] [INFO] Astronaut Daily Schedule Organizer started
[2025-10-01 14:30:15] [INFO] Task created successfully: Morning Exercise
[2025-10-01 14:30:15] [INFO] Task added: Morning Exercise
[2025-10-01 14:31:20] [WARN] Task conflicts with existing task
[2025-10-01 14:32:00] [INFO] Task removed: Morning Exercise
[2025-10-01 14:35:00] [INFO] Application stopped

Summary
This repository demonstrates:
• Strong OOP principles:
- Encapsulation, Inheritance, Polymorphism, Interfaces
Proper use of Behavioural, Creational, and Structural design patterns
• Real-time interactive applications with proper design patterns
• User input-driven programs with comprehensive error handling
________________________________________________________________________________
