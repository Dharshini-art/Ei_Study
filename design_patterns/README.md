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

---

## 📁 Project Structure

```
design-patterns-demo/
│
├── src/
│   └── com/
│       └── designpatterns/
│           ├── Main.java
│           ├── menu/
│           │   └── MenuManager.java
│           ├── behavioral/
│           │   ├── observer/
│           │   │   ├── WeatherStation.java
│           │   │   ├── WeatherObserver.java
│           │   │   ├── PhoneDisplay.java
│           │   │   └── TVDisplay.java
│           │   └── strategy/
│           │       ├── PaymentStrategy.java
│           │       ├── CreditCardPayment.java
│           │       ├── UPIPayment.java
│           │       ├── CashPayment.java
│           │       └── PaymentContext.java
│           ├── creational/
│           │   ├── factory/
│           │   │   ├── Vehicle.java
│           │   │   ├── Car.java
│           │   │   ├── Bike.java
│           │   │   ├── Truck.java
│           │   │   └── VehicleFactory.java
│           │   └── singleton/
│           │       ├── DatabaseConnection.java
│           │       └── Logger.java
│           └── structural/
│               ├── adapter/
│               │   ├── MediaPlayer.java
│               │   ├── AdvancedMediaPlayer.java
│               │   ├── VLCPlayer.java
│               │   ├── MP4Player.java
│               │   └── MediaAdapter.java
│               └── decorator/
│                   ├── Coffee.java
│                   ├── SimpleCoffee.java
│                   ├── CoffeeDecorator.java
│                   ├── MilkDecorator.java
│                   ├── SugarDecorator.java
│                   └── WhippedCreamDecorator.java
│
├── logs/
│   └── application.log
│
├── .gitignore
└── README.md
```

Compile the Project

```bash
javac -cp src src/com/designpatterns/Main.java
```

Run the Application

```bash
java -cp src com.designpatterns.Main
```

---

## 🎮 How to Use

When you run the application, you'll see an interactive menu:

```
╔════════════════════════════════════════════╗
║      DESIGN PATTERNS DEMO                  ║
╚════════════════════════════════════════════╝
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
════════════════════════════════════════════
Choose an option:
```

Simply enter a number (1-6) to see each pattern in action, or 0 to exit.

---

## 📝 Key Features

### ✅ Compliance with Requirements

- **Language**: Pure Java (no external dependencies)
- **No Hard-coded Loops**: Uses `shouldContinue()` method instead of `while(true)`
- **Logging Mechanism**: Singleton Logger writing to `logs/application.log`
- **Exception Handling**: Try-catch blocks at all levels
- **Defensive Programming**: Null checks, validation on all inputs
- **Best Practices**: 
  - Each class in separate file
  - Consistent naming convention (PascalCase for classes, camelCase for methods)
  - Clean package structure
  - Interface-based design

### 🛡️ Error Handling

- Input validation at all entry points
- Graceful error messages
- Logging of all errors
- Safe resource cleanup

---

## 🔍 Pattern Details

### 1. Observer Pattern (Weather Station)
**Use Case**: Weather station broadcasts temperature updates to multiple display devices.

**Key Classes**:
- `WeatherStation` - Subject that holds state
- `WeatherObserver` - Interface for observers
- `PhoneDisplay`, `TVDisplay` - Concrete observers

**Demo**: Shows how adding/removing observers affects notifications.

---

### 2. Strategy Pattern (Payment System)
**Use Case**: E-commerce payment processing with multiple payment methods.

**Key Classes**:
- `PaymentStrategy` - Strategy interface
- `CreditCardPayment`, `UPIPayment`, `CashPayment` - Concrete strategies
- `PaymentContext` - Context that uses strategies

**Demo**: Processes payments using different methods dynamically.

---

### 3. Factory Pattern (Vehicle Creation)
**Use Case**: Vehicle rental system creating different vehicle types.

**Key Classes**:
- `Vehicle` - Product interface
- `Car`, `Bike`, `Truck` - Concrete products
- `VehicleFactory` - Factory for creating vehicles

**Demo**: Creates vehicles based on string input, handles invalid types.

---

### 4. Singleton Pattern (Database Connection)
**Use Case**: Database connection pool and application-wide logger.

**Key Classes**:
- `DatabaseConnection` - Singleton database connection
- `Logger` - Singleton logger (thread-safe)

**Demo**: Shows that multiple references point to same instance.

---

### 5. Adapter Pattern (Media Player)
**Use Case**: Media player supporting multiple audio/video formats.

**Key Classes**:
- `MediaPlayer` - Client interface
- `AdvancedMediaPlayer` - Adaptee interface
- `VLCPlayer`, `MP4Player` - Adaptees
- `MediaAdapter` - Adapter

**Demo**: Plays different formats (MP3, VLC, MP4) through unified interface.

---

### 6. Decorator Pattern (Coffee Shop)
**Use Case**: Coffee shop where customers add toppings to base coffee.

**Key Classes**:
- `Coffee` - Component interface
- `SimpleCoffee` - Concrete component
- `CoffeeDecorator` - Decorator base class
- `MilkDecorator`, `SugarDecorator`, `WhippedCreamDecorator` - Concrete decorators

**Demo**: Shows dynamic addition of features and cost calculation.

---

## 📊 Logging

All application events are logged to `logs/application.log` with timestamps:

```
[2025-10-01 10:30:45] Application started
[2025-10-01 10:30:52] User selected option: 1
[2025-10-01 10:30:52] Observer pattern demo completed
[2025-10-01 10:31:15] Application stopped
```

---

## 🧪 Testing Approach

Each pattern demo includes:
- Normal operation scenarios
- Edge cases (null inputs, invalid data)
- Error handling demonstrations
- Console output showing pattern behavior

---

## 🔧 Extensibility

The code is designed for easy extension:

1. **Add new patterns**: Create new package and follow existing structure
2. **Add new menu options**: Update `MenuManager.processChoice()`
3. **Add new strategies/products**: Implement respective interfaces
4. **Add new decorators**: Extend `CoffeeDecorator`

---

## 📚 Design Decisions

1. **No External Dependencies**: Pure Java for simplicity and portability
2. **Menu System**: Custom implementation avoiding hard-coded loops
3. **Logging**: File-based logging for persistence
4. **Error Handling**: Multiple layers of validation and try-catch
5. **Package Structure**: Organized by pattern type for clarity

---

## 🎓 Learning Outcomes

This project demonstrates:
- Understanding of 6 core design patterns
- Real-world application scenarios
- Clean code principles
- Exception handling best practices
- Defensive programming techniques
- Performance optimization (Singleton for shared resources)

---

