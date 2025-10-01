# 🚀 Astronaut Daily Schedule Organizer

A console-based application that helps astronauts organize their daily schedules efficiently. The application allows users to add, remove, view, and manage daily tasks with automatic conflict detection.

---

## 📋 Table of Contents

- [Features](#features)
- [Design Patterns](#design-patterns)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Usage Guide](#usage-guide)
- [Test Cases](#test-cases)
- [Design Decisions](#design-decisions)

---

## ✨ Features

### Mandatory Features
✅ Add new tasks with description, start time, end time, and priority level  
✅ Remove existing tasks  
✅ View all tasks sorted by start time  
✅ Automatic validation to prevent task overlaps  
✅ Comprehensive error messages for invalid operations  

### Optional Features (Implemented)
✅ Edit existing tasks  
✅ Mark tasks as completed  
✅ View tasks filtered by priority level (High/Medium/Low)  

### Non-Functional Features
✅ Graceful exception handling at all levels  
✅ Performance-optimized data structures  
✅ Comprehensive logging mechanism  
✅ Defensive programming with input validation  
✅ No hard-coded boolean flags (uses `shouldContinue()` method)  

---

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

---

## 📁 Project Structure

```
astronaut-schedule-organizer/
│
├── src/
│   └── com/
│       └── astronaut/
│           ├── Main.java                      # Entry point
│           ├── manager/
│           │   └── ScheduleManager.java       # Singleton - manages all tasks
│           ├── model/
│           │   └── Task.java                  # Task entity
│           ├── factory/
│           │   └── TaskFactory.java           # Factory - creates tasks
│           ├── observer/
│           │   ├── TaskObserver.java          # Observer interface
│           │   └── ConflictNotifier.java      # Concrete observer
│           ├── util/
│           │   ├── Logger.java                # Logging utility (Singleton)
│           │   ├── TimeValidator.java         # Time validation
│           │   └── InputValidator.java        # Input validation
│           └── menu/
│               └── MenuManager.java           # Menu system
│
├── logs/
│   └── schedule.log                           # Application logs
│
├── .gitignore
└── README.md
```

---

## 🚀 Setup Instructions

### Prerequisites
- Java JDK 8 or higher
- Command line / Terminal
- Git (for version control)


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

---

## 📖 Usage Guide

### Main Menu Options

```
╔══════════════════════════════════════════════╗
║           ASTRONAUT SCHEDULE MENU            ║
╚══════════════════════════════════════════════╝
  1. Add New Task
  2. Remove Task
  3. View All Tasks (Sorted by Time)
  4. View Tasks by Priority
  5. Edit Task
  6. Mark Task as Completed
  0. Exit
══════════════════════════════════════════════
```

### Example Usage

#### Adding a Task
```
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

╔══════════════════════════════════════════════╗
║           ALL SCHEDULED TASKS                ║
╚══════════════════════════════════════════════╝
Total Tasks: 2

1. 07:00 - 08:00: Morning Exercise [High]
2. 09:00 - 10:00: Team Meeting [Medium]
══════════════════════════════════════════════
```

---

## 🧪 Test Cases

### Positive Test Cases

| Input | Expected Output | Status |
|-------|----------------|--------|
| Add Task("Morning Exercise", "07:00", "08:00", "High") | Task added successfully. No conflicts. | ✅ |
| Add Task("Team Meeting", "09:00", "10:00", "Medium") | Task added successfully. No conflicts. | ✅ |
| View Tasks | Displays all tasks sorted by start time | ✅ |
| Remove Task("Morning Exercise") | Task removed successfully. | ✅ |
| Add Task("Lunch Break", "12:00", "13:00", "Low") | Task added successfully. No conflicts. | ✅ |

### Negative Test Cases

| Input | Expected Output | Status |
|-------|----------------|--------|
| Add Task("Training", "09:30", "10:30", "High") | Error: Task conflicts with existing task "Team Meeting" | ✅ |
| Remove Task("Non-existent Task") | Error: Task not found. | ✅ |
| Add Task("Invalid", "25:00", "26:00", "Low") | Error: Invalid time format | ✅ |
| Add Task("Overlap", "08:30", "09:30", "Medium") | Error: Task conflicts with existing task | ✅ |
| View Tasks (empty schedule) | No tasks scheduled for the day. | ✅ |

---

## 🏗️ Design Decisions

### 1. **Why Singleton for ScheduleManager?**
- Only one schedule should exist per application instance
- Centralized task management prevents data inconsistency
- Thread-safe implementation for future scalability

### 2. **Why Factory Pattern for Task Creation?**
- Centralizes all validation logic in one place
- Makes task creation consistent across the application
- Easy to extend with new task types or validation rules

### 3. **Why Observer Pattern for Conflicts?**
- Loose coupling between task management and notifications
- Easy to add multiple notification channels (console, email, SMS)
- Follows Single Responsibility Principle

### 4. **Time Representation**
- Used String format (HH:MM) for user-friendly input
- Converted to minutes internally for efficient comparison
- Regex validation ensures proper format

### 5. **No while(true) Implementation**
- Uses `shouldContinue()` method instead of hard-coded `while(true)`
- Provides clean exit mechanism
- Better testability and maintainability

### 6. **Validation Strategy**
- **Defensive Programming:** Validate at multiple levels
  - User input validation (InputValidator)
  - Time format validation (TimeValidator)
  - Business logic validation (ScheduleManager)
- Fail-fast approach with clear error messages

### 7. **Exception Handling**
- Try-catch blocks at all critical points
- Graceful error recovery
- User-friendly error messages
- Comprehensive logging for debugging

### 8. **Performance Optimization**
- ArrayList for tasks (O(n) for most operations, suitable for small datasets)
- Stream API for filtering and sorting
- Single instance of managers (Singleton pattern)

---

## 📊 SOLID Principles Applied

### Single Responsibility Principle (SRP)
- `Task` - Only handles task data
- `ScheduleManager` - Only manages tasks
- `TimeValidator` - Only validates time
- `Logger` - Only handles logging

### Open/Closed Principle (OCP)
- Can add new observers without modifying ScheduleManager
- Can extend TaskFactory for new task types

### Liskov Substitution Principle (LSP)
- Any TaskObserver implementation can replace another

### Interface Segregation Principle (ISP)
- TaskObserver has only one method - minimal interface

### Dependency Inversion Principle (DIP)
- ScheduleManager depends on TaskObserver interface, not concrete implementation

---

## 📝 Logging

All operations are logged to `logs/schedule.log`:

```
[2025-10-01 14:30:00] [INFO] Astronaut Daily Schedule Organizer started
[2025-10-01 14:30:15] [INFO] Task created successfully: Morning Exercise
[2025-10-01 14:30:15] [INFO] Task added: Morning Exercise
[2025-10-01 14:31:20] [WARN] Task conflicts with existing task
[2025-10-01 14:32:00] [INFO] Task removed: Morning Exercise
[2025-10-01 14:35:00] [INFO] Application stopped
```

---

## 🎓 Key Learnings

1. **Design Patterns in Practice:** Real-world application of Singleton, Factory, and Observer patterns
2. **Defensive Programming:** Multiple layers of validation
3. **Clean Code:** Separation of concerns, meaningful names
4. **Error Handling:** Comprehensive exception handling
5. **User Experience:** Clear messages and intuitive interface

---

## 🔧 Future Enhancements

- [ ] Persistent storage (save tasks to file/database)
- [ ] Task recurrence (daily, weekly tasks)
- [ ] Task reminders before start time
- [ ] Export schedule to PDF/CSV
- [ ] Multiple astronaut profiles
- [ ] Task categories/tags
- [ ] Calendar view

---

