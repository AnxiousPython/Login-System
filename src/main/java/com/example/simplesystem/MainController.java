package com.example.simplesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void cancelButtonOnClick(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnClick(ActionEvent e){
        if (!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()){
            //loginMessageLabel.setText("Account Logged in.");
            validateLogin();

        } else {
            loginMessageLabel.setText("No Account created.");
        }
    }
    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDatabase = connectNow.getConnection();

        String verifyLogin = "select count(1) from useraccounts where username = '" + usernameTextField.getText() + "' and password = '" + passwordTextField.getText() + "'";
        try {
            Statement statement = connectDatabase.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome");
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}