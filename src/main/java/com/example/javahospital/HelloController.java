package com.example.javahospital;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController  {

    @FXML
    private Button doctorButton;
    @FXML
    private Button patientButton;
    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private RadioButton doctor;

    @FXML
    private TextField firstNameField;

    @FXML
    private ToggleGroup rolesGroup;

    @FXML
    private TextField phone;

    @FXML
    private Button register;

    @FXML
    private RadioButton patient;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ToggleGroup gendersGroup;

    @FXML
    private RadioButton female;

    @FXML
    private TextField email;

    @FXML
    private TextField age;

    @FXML
    private RadioButton male;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameLogin;

    void redirectDoctor(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("doctor-login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setTitle("Hospital!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleDoctorButton() {
        doctorButton.setOnAction(event -> {
            Stage stage = (Stage) patientButton.getScene().getWindow();
            redirectDoctor(stage);
        });
    }

    void redirectPatient(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patien-login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setTitle("Hospital!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handlePatientButton() {
        patientButton.setOnAction(event -> {
            Stage stage = (Stage) patientButton.getScene().getWindow();
            redirectPatient(stage);
        });
    }


    void redirectRegister(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
            stage.setTitle("Hospital!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleRegisterButton() {
        registerButton.setOnAction(event -> {
            Stage stage = (Stage) patientButton.getScene().getWindow();
            redirectRegister(stage);
        });
    }

    public void getGender(ActionEvent event) {
    }

    public void getRole(ActionEvent event) {
    }

    @FXML
    void handleNewRegister(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        int ageNo = Integer.parseInt(age.getText());
        String emailField = email.getText();
        String phoneNo = phone.getText();
        String gender = male.isSelected() ? "male" : "female";
        String role = doctor.isSelected() ? "doctor" : "patient";
        String url = "jdbc:mysql://localhost:3306/clinic_appointments";
        String usernameDB = "root";
        String passwordDB = "";
        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String sql = "INSERT INTO users (username, password, firstname, lastname, age, email, phone, gender, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setInt(5, ageNo);
            statement.setString(6, emailField);
            statement.setString(7, phoneNo);
            statement.setString(8, gender);
            statement.setString(9, role);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("User registration failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
