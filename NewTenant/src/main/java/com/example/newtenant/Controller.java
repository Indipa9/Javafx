package com.example.newtenant;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controller {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(e -> addTenant());
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/hostel";
        String user = "root";
        String password = "";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            showAlert("Database Connection Error", "Could not connect to the database: " + e.getMessage());
            return null;
        }
    }

    private void addTenant() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        String insertSQL = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            if (conn == null) return;

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Tenant added successfully.");
            } else {
                showAlert("Failure", "Tenant could not be added.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Could not add tenant: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
