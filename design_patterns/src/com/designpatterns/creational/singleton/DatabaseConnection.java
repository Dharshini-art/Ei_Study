package com.designpatterns.creational.singleton;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private boolean connected;
    private String connectionString;
    private static final Object lock = new Object();
    
    private DatabaseConnection() {
        this.connected = false;
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                    System.out.println("✓ DatabaseConnection instance created");
                }
            }
        }
        return instance;
    }
    
    public void connect() {
        if (!connected) {
            System.out.println("🔌 Connecting to database...");
            System.out.println("   Connection String: " + connectionString);
            connected = true;
            System.out.println("✓ Connected to database successfully!");
        } else {
            System.out.println("⚠ Already connected to database");
        }
    }
    
    public void disconnect() {
        if (connected) {
            System.out.println("🔌 Disconnecting from database...");
            connected = false;
            System.out.println("✓ Disconnected from database");
        } else {
            System.out.println("⚠ Not connected to database");
        }
    }
    
    public void executeQuery(String query) {
        if (!connected) {
            System.out.println("✗ Cannot execute query: Not connected to database");
            connect();
        }
        
        if (query == null || query.isEmpty()) {
            System.out.println("✗ Invalid query");
            return;
        }
        
        System.out.println("📊 Executing query: " + query);
        System.out.println("✓ Query executed successfully");
    }
    
    public boolean isConnected() {
        return connected;
    }
}
