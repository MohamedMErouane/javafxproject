// TodoListController.java
package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoListController {

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextField taskTextField;

    @FXML
    private Label todoMessage;

    private ObservableList<TodoItem> todoList;

    @FXML
    public void initialize() {
        todoList = FXCollections.observableArrayList();
        todoListView.setItems(todoList);
        DbHelper dbHelper = new DbHelper();
        dbHelper.createTasksTable();

        todoListView.setCellFactory(param -> new ListCell<TodoItem>() {
            @Override
            protected void updateItem(TodoItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    @FXML
    private void handleDeleteTask() {
        TodoItem selectedTask = todoListView.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            // Remove the task from the UI
            todoList.remove(selectedTask);

            // Delete the task from the database using the task name
            DbHelper dbHelper = new DbHelper();
            dbHelper.deleteTask(selectedTask.getName());

            todoMessage.setText("Task deleted successfully.");
        } else {
            todoMessage.setText("Please select a task to delete.");
        }
    }


    @FXML
    private void handleAddTask() {
        String task = taskTextField.getText().trim();

        if (!task.isEmpty()) {
            TodoItem todoItem = new TodoItem(task);
            todoList.add(todoItem);

            // Clear the taskTextField
            taskTextField.clear();

            todoMessage.setText("Task added successfully.");

            // Save the task to the database
            DbHelper dbHelper = new DbHelper();
            dbHelper.insertTask(task, false); // Initially set as not completed
        } else {
            todoMessage.setText("Please enter a task.");
        }
    }


    public void handleToggleCompleted(ActionEvent actionEvent) {
        TodoItem selectedTask = todoListView.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            try {
                // Toggle the completion status in the UI
                selectedTask.setCompleted(!selectedTask.isCompleted());

                // Update the completion status in the database
                DbHelper dbHelper = new DbHelper();
                dbHelper.updateTaskCompleted(selectedTask.getName(), selectedTask.isCompleted());

                todoMessage.setText("Task completion status updated successfully.");
            } catch (Exception e) {
                // Log the exception for debugging purposes
                e.printStackTrace();

                // Display an error message to the user
                todoMessage.setText("Error updating task completion status.");
            }
        } else {
            todoMessage.setText("Please select a task to update.");
        }
    }

}

