/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controllers;

import data.dao.IUserDao;
import data.entites.User;
import data.dao.UserDao;
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
import javafx.scene.control.Label;
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
    private Button createBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label usernameLbl;
    @FXML
    private Label passLbl;
    @FXML
    private Label cPassLbl;
    @FXML
    private TextField usernameTfield;
    @FXML
    private TextField passTfield;
    @FXML
    private TextField cPassTfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBinding = usernameTfield.textProperty().isEmpty()
                .or(passTfield.textProperty().isEmpty().or(cPassTfield.textProperty().isEmpty()));
        addTextLimiter(usernameTfield, 9);
        addTextLimiter(passTfield, 10);
        addTextLimiter(cPassTfield, 10);
        
        createBtn.disableProperty().bind(boolenBinding);
    }    

    @FXML
    private void createButtonAction(ActionEvent event) {
        if(! passTfield.getText().equals(cPassTfield.getText()))
        {
            
        }
        IUserDao userDao = new UserDao();
        User user = userDao.findByName(usernameTfield.getText());
        if (user != null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sucess");
            alert.setHeaderText("Error : user already exists.");
            alert.setContentText("Username: " + usernameTfield.getText() + " already exists .Please enter different username.");
            alert.initStyle(StageStyle.TRANSPARENT);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getScene().setFill(new Color(0, 0, 0, 0));
            dialogPane.getStylesheets().add(getClass().getResource("/styles/AlertStyle.css").toExternalForm());
            alert.showAndWait();
        }
        else
        {
            userDao.create(usernameTfield.getText(), passTfield.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : User was created.");
            alert.setContentText("User was created. Now you can log in.");
            alert.initStyle(StageStyle.TRANSPARENT);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getScene().setFill(new Color(0, 0, 0, 0));
            dialogPane.getStylesheets().add(getClass().getResource("/styles/AlertStyle.css").toExternalForm());
            alert.showAndWait();
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
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
