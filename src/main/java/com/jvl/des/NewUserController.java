/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvl.des;

import data.IUserDao;
import data.User;
import data.UserDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class NewUserController implements Initializable {

    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBinding = usernameTextField.textProperty().isEmpty()
                .or(passwordTextField.textProperty().isEmpty().or(confirmPasswordTextField.textProperty().isEmpty()));
        addTextLimiter(usernameTextField, 9);
        addTextLimiter(passwordTextField, 10);
        addTextLimiter(confirmPasswordTextField, 10);
        
        createButton.disableProperty().bind(boolenBinding);
    }    

    @FXML
    private void createButtonAction(ActionEvent event) {
        IUserDao userDao = new UserDao();
        User user = userDao.findByName(usernameTextField.getText());
        if (user != null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sucess");
            alert.setHeaderText("Error : user already exists.");
            alert.setContentText("Username: " + usernameTextField.getText() + " already exists .Please enter different username.");
            alert.initStyle(StageStyle.TRANSPARENT);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getScene().setFill(new Color(0, 0, 0, 0));
            dialogPane.getStylesheets().add(getClass().getResource("/styles/AlertStyle.css").toExternalForm());
            alert.showAndWait();
        }
        else
        {
            userDao.create(usernameTextField.getText(), passwordTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : User was created.");
            alert.setContentText("User was created. Now you can log in.");
            alert.initStyle(StageStyle.TRANSPARENT);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getScene().setFill(new Color(0, 0, 0, 0));
            dialogPane.getStylesheets().add(getClass().getResource("/styles/AlertStyle.css").toExternalForm());
            alert.showAndWait();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
   
   private void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
