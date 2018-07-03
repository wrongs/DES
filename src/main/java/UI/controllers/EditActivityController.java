/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controllers;

import data.dao.AnimalActivityDao;
import data.entites.Activity;
import data.entites.Animal;
import data.enumeration.Status;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.PersistenceManager;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class EditActivityController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label nameLbl;
    @FXML
    private Label statusLbl;
    @FXML
    private ComboBox<?> statusComboBox;

    private Activity editedActivity;
    
    private Animal linkedAnimal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillComboBox();
    }    

    public Animal getLinkedAnimal() {
        return linkedAnimal;
    }

    public void setLinkedAnimal(Animal linkedAnimal) {
        this.linkedAnimal = linkedAnimal;
    }
    
    @FXML
    private void addButtonAction(ActionEvent event) {
        AnimalActivityDao activityDao = new AnimalActivityDao();
        
        Status status = (Status) statusComboBox.getSelectionModel().getSelectedItem(); 
        editedActivity = activityDao.create(nameTextField.getText(), linkedAnimal, status);
         
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
     public void setButton(String buttonText) {
        this.addButton.setText(buttonText);
    }
     
    public Activity getEditedActivity() {
        return editedActivity;
    }
     
    public void setEditedActivity(Activity activity) {
        this.editedActivity = activity;
        loadActivity();
    }
    
     //tato metoda by mela nacit zvire z databáze podle 
    private void loadActivity()
    {
        nameTextField.setText(editedActivity.getName());
        //statusComboBox.getSelectionModel().select(editedActivity.getStatus());
    }
    
    private void fillComboBox()
    {
        PersistenceManager pm = new PersistenceManager();
        //List<Status> statuses = pm.getHqlResult(Status.class, "from Farm");
        List<Status> statuses = Arrays.asList(Status.values());
        
        ObservableList data = FXCollections.observableArrayList();
        //data.
        for(Status status : statuses)
        {
            data.add(status);
        }    
        statusComboBox.setItems(data);
    }    
}
