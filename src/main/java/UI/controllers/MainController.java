/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controllers;

import com.jvl.des.UIManager;
import data.entites.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
    private ToggleButton userButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private StackPane screenPane;
    
    private User loggedUser;
    @FXML
    private ToggleButton animalsButton;
    @FXML
    private ToggleButton fieldsButton;
    @FXML
    private ToggleButton toolsButton;
    @FXML
    private ToggleButton farmsButton;
    @FXML
    private ToggleButton financeButton;
    @FXML
    private ToggleButton actionsButton;
    
    private UIManager uiManager;

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        final ToggleGroup mainToggleGroup = new ToggleGroup();
        animalsButton.setToggleGroup(mainToggleGroup);
        fieldsButton.setToggleGroup(mainToggleGroup);
        toolsButton.setToggleGroup(mainToggleGroup);
        financeButton.setToggleGroup(mainToggleGroup);
        actionsButton.setToggleGroup(mainToggleGroup);
        farmsButton.setToggleGroup(mainToggleGroup);
        // TODO
        try {
            animalButtonActive();
        } catch (Exception e) {   
            e.printStackTrace();
            //System.out.println("Napicu");
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
    private void userButtonOnAction(ActionEvent event) throws IOException {
         
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/UserSettings.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
       // AddSupplyerController supplyerController = fxmlLoader.getController();
        //media.setId(usrId);
       // supplyerController.setMedia(media);
        //supplyerController.lblCaption.setText("Add Supplyer");
        //supplyerController.btnUpdate.setVisible(false);
        Stage nStage = new Stage();
        //supplyerController.addSupplyerStage(nStage);
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        
        nStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                 userButton.setSelected(false);
                 usernameLabel.setText(loggedUser.getUsername());
                //userButton.setVisible(false);
                //usernameLabel.setText("aaaaaaa");
            }
        });
        
        UserSettingsController userController = fxmlLoader.getController();
        userController.setLoggedUser(loggedUser);
                
        nStage.showAndWait();
        if(userController.getLogOut())
        {
            logout();
        }
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
    
    private void logout() throws IOException   
    {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
        uiManager.showLogin();
    }
}