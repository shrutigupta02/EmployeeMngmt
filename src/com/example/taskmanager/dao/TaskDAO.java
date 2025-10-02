package com.example.taskmanager.dao;

import com.example.taskmanager.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/task_manager_db?useSSL=false";
    private String jdbcUsername = "root"; // Your MySQL username
    private String jdbcPassword = "password"; // Your MySQL password

    private static final String INSERT_TASK_SQL = "INSERT INTO tasks (title, status, description) VALUES (?, ?, ?);";
    private static final String SELECT_TASK_BY_ID = "SELECT id, title, status, description FROM tasks WHERE id = ?;";
    private static final String SELECT_ALL_TASKS = "SELECT * FROM tasks;";
    private static final String DELETE_TASK_SQL = "DELETE FROM tasks WHERE id = ?;";
    private static final String UPDATE_TASK_SQL = "UPDATE tasks SET title = ?, status = ?, description = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertTask(Task task) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASK_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getStatus());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public Task selectTask(int id) {
        Task task = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String status = rs.getString("status");
                String description = rs.getString("description");
                task = new Task(title, status, description);
                task.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public List<Task> selectAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String status = rs.getString("status");
                String description = rs.getString("description");
                Task task = new Task(title, status, description);
                task.setId(id);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public boolean deleteTask(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASK_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTask(Task task) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_SQL)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getStatus());
            statement.setString(3, task.getDescription());
            statement.setInt(4, task.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}