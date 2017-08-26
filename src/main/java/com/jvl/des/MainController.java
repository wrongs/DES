/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvl.des;

import data.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane mainMenu;
    @FXML
    private VBox userBox;
    @FXML
    private Button buttonUser;
    @FXML
    private Label usernameLabel;
    @FXML
    private StackPane screenPane;
    
    private User loggedUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            animalButtonActive();
        } catch (Exception e) {   
            System.out.println("Napicu");
        }   
    }    

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
        usernameLabel.setText(loggedUser.getUsername());
    }
    
    @FXML
    private void buttonUserOnAction(ActionEvent event) {
    }
    
    private void animalButtonActive() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Screens/AnimalScreen.fxml"));
        /*
        try {
            fxmlLoader.load(getClass().getResource("/fxml/Screens/AnimalScreen.fxml"));
            // Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        } catch (Exception e) {     
            
        }*/
       // AnchorPane root = fxmlLoader.getRoot();
        screenPane.getChildren().clear();
        screenPane.getChildren().add(root);
    }
}