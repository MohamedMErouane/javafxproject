package com.example.demo;

import java.sql.*;
public class DbHelper {
    private Connection databaseLink;
    public Connection getConnection() {
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost:3306/fx";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database: " + e.getMessage());
        }

        return databaseLink;
    }


    public void closeConnection() {
        try {
            if (databaseLink != null && !databaseLink.isClosed()) {
                databaseLink.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework instead
        }
    }
    public void createTasksTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS Tasks (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "task VARCHAR(255) NOT NULL," +
                    "completed BOOLEAN NOT NULL)";
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTask(String taskName) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM tasks WHERE task = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, taskName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error, show a message to the user)
        }
    }


    public void insertTask(String task, boolean completed) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO tasks (task, completed) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, task);
                statement.setBoolean(2, completed);
                statement.executeUpdate();

                // Retrieve the auto-generated task ID
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int taskId = resultSet.getInt(1);
                        // You can store or use the taskId as needed
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void updateTaskCompleted(String taskName, boolean currentCompletedStatus) {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE tasks SET completed = ? WHERE task = ?";

            // Toggle the completed status
            boolean newCompletedStatus = !currentCompletedStatus;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, newCompletedStatus);
                statement.setString(2, taskName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error, show a message to the user)
        }
    }

}
