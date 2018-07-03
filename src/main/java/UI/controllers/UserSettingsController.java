    package UI.controllers;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jvl.des.UIManager;
import data.dao.IUserDao;
import data.dao.UserDao;
import data.entites.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/** 
 * FXML Controller class
 *
 * @author Jakub
 */
public class UserSettingsController implements Initializable {

    @FXML
    private Label oldPassLbl;
    @FXML
    private Label newPassLbl;
    @FXML
    private TextField oldPassTField;
    @FXML
    private TextField newPassTField;
    @FXML
    private TextField newPassATField;
    @FXML
    private Label newPassALbl;
    @FXML
    private TextField cUsernameTField;
    @FXML
    private Label cUsernameLbl;
    @FXML
    private Button closeBtn;
    @FXML
    private Button cPassBtn;
    @FXML
    private Label cPassMsgLbl;
    @FXML
    private Label cUsernameMsgLbl;
    @FXML
    private Button cUsernameBtn;
    private User loggedUser;
    private Boolean logOut;

    public Boolean getLogOut() {
        return logOut;
    }
    
    private final IUserDao userDao = new UserDao();   
    @FXML
    private Button logOutBtn;

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logOut = false;
        
        BooleanBinding cUsernameBtnBinding = cUsernameTField.textProperty().isEmpty();
        BooleanBinding cPasswordBtnBinding = oldPassTField.textProperty().isEmpty().or(newPassTField.textProperty().isEmpty()).or(newPassATField.textProperty().isEmpty());
        
        cUsernameBtn.disableProperty().bind(cUsernameBtnBinding);
        cPassBtn.disableProperty().bind(cPasswordBtnBinding);
    }    

    @FXML
    private void cUsernameBtnAction(ActionEvent event) {
        if(cUsernameTField.getText().equals(loggedUser.getUsername()))
        {
            cUsernameMsgLbl.setText("Choose different username than actual");
        }
        else
        {
            if(userDao.findByName(cUsernameTField.textProperty().getValue()) != null)
            {
                System.out.println("Username already exists. Choose different username");
                cUsernameMsgLbl.setText("Username already exists");
            }
            else
            {
                loggedUser.setUsername(cUsernameTField.getText());
                userDao.update(loggedUser);
                cUsernameMsgLbl.setText("Username changed");
            }
        }
    }
    
    @FXML
    private void closeBtnAction(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cPassBtnAction(ActionEvent event) {
        if(!oldPassTField.getText().equals(loggedUser.getPassword()))
        {
            cPassMsgLbl.setText("Wrong old password");
        }
        else
        {
            if(!newPassTField.getText().equals(newPassATField.getText()))
            {
                cPassMsgLbl.setText("Passwords are not identical");
            }
            loggedUser.setPassword(newPassTField.getText());
            userDao.update(loggedUser);
            cUsernameMsgLbl.setText("Password changed");
        }
    }

    
    @FXML
    private void logOutBtnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        logOut = true;
        //MainUIManager.getInstance().logOut();
    }
}