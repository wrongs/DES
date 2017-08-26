            package com.jvl.des;

import data.IUserDao;
import data.User;
import data.UserDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.CryptoProvider;
import logic.EMFProvider;
import logic.PersistenceManager;

public class LoginController implements Initializable {
    
    @FXML
    private Button loginButton;
    @FXML
    private Label usernamelbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label loginStatusLabel;
    @FXML
    private Button createNewButton;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        BooleanBinding boolenBinding = usernameTextField.textProperty().isEmpty()
                .or(passwordTextField.textProperty().isEmpty());

        loginButton.disableProperty().bind(boolenBinding);

    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        IUserDao userDao = new UserDao();   
        /*
        String cryptedPass = null;
        try {
            cryptedPass = CryptoProvider.encrypt(passwordTextFiled.textProperty().getValue());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User userToLogIn = userDao.findByNamePassword(usernameTextFiled.textProperty().getValue(), cryptedPass);
        */
        User userToLogIn = userDao.findByNamePassword(usernameTextField.textProperty().getValue(), passwordTextField.textProperty().getValue());
        if(userToLogIn == null)
        {
            System.out.println("login failed");
            // tady bude spusteni hlavniho okna;
            loginStatusLabel.setText("wrong username or password");
        }
        else
        {
            System.out.println("login succeed");
            /*
            FXMLLoader loader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            MainController apControl = loader.getController();
            adminPanelStage.setScene(adminPanelScene);
            adminPanelStage.getIcons().add(new Image("/images/icon.png"));
            adminPanelStage.setTitle("APLIKACE");
            adminPanelStage.show();
            */
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Main.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            MainController apControl = loader.getController();
            
            adminPanelStage.setScene(adminPanelScene);
            adminPanelStage.getIcons().add(new Image("/images/icon.png"));
            adminPanelStage.setTitle("APLIKACE");
            adminPanelStage.show();
            
            adminPanelStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    EMFProvider.getInstance().closeEntityManagerFactory();
                    //Platform.exit();
                }
            });

            apControl.setLoggedUser(userToLogIn);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML   
    private void cancelButtonAction(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        // do what you have to do
        stage.close();
        EMFProvider.getInstance().closeEntityManagerFactory();
    }

    @FXML
    private void createNewButtonAction(ActionEvent event) throws IOException {
        
        /*Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Popup/NewUser.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
        */
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/NewUser.fxml"));
        
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
        nStage.show();
    }
}
