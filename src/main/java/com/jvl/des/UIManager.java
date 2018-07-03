/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jvl.des;

import UI.controllers.LoginController;
import UI.controllers.MainController;
import data.entites.User;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.EMFProvider;

/**
 *
 * @author Jakub
 */
public class UIManager {
    
    private static Stage mainStage;

    public UIManager (Stage stage){
        mainStage = stage;
    }
    
    public static Stage getCurrentMainStage() {
        return mainStage;
    }

    public static void setCurrentMainStage(Stage currentMainStage) {
        UIManager.mainStage = currentMainStage;
    }
    
    public void createLogin() throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Login.fxml"));
        
        Parent root = fxmlLoader.load(); 
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.getIcons().add(new Image("/images/iconColor.png"));
        mainStage.setScene(scene);
        mainStage.show();
        
        LoginController loginController = fxmlLoader.getController();
        loginController.setUiManager(this);
    }   
    
    public void showMainWindow (User loggedUser) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);
        MainController apControl = loader.getController();

        adminPanelStage.setScene(adminPanelScene);
        adminPanelStage.getIcons().add(new Image("/images/iconColor64.png"));
        adminPanelStage.setTitle("APLIKACE");
        adminPanelStage.show();

        adminPanelStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                EMFProvider.getInstance().closeEntityManagerFactory();
                //Platform.exit();
            }
        });

        apControl.setLoggedUser(loggedUser);
        apControl.setUiManager(this);
    }   
    
    public void initialize()
    {
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
    public void showLogin()
    {
         mainStage.show();
    }
}
