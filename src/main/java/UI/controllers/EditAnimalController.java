/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controllers;

import data.dao.AnimalDao;
import data.entites.Animal;
import data.entites.Farm;
import data.enumeration.Gender;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.PersistenceManager;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class EditAnimalController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private TextField idTagTextField;
    @FXML
    private TextField speciesTextField;
    @FXML
    private ComboBox<Farm> farmComboBox;
    @FXML
    private Spinner<Integer> ageSpinner;
    
    private ObservableList<Animal> animals ;
    
    private Animal editedAnimal;
    
    public void setAnimalsData(ObservableList<Animal> data) {
        this.animals = data ;
    }
    
    public void setButton(String buttonText) {
        this.addButton.setText(buttonText);
    }
    
    public Animal getEditedAnimal() {
        return editedAnimal;
    }

    public void setEditedAnimal(Animal animal) {
        this.editedAnimal = animal;
        loadAnimal();
    }
    
    protected static final String INITAL_VALUE = "0";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // gender group setup 
        final ToggleGroup genderGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);
           
        // age spinner setup
        SpinnerValueFactory<Integer> valueFactory = //
               new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        ageSpinner.setEditable(true);
        ageSpinner.setValueFactory(valueFactory);
        
        TextFormatter formatter = new TextFormatter(valueFactory.getConverter(), valueFactory.getValue());
        ageSpinner.getEditor().setTextFormatter(formatter);
        // bidi-bind the values
        valueFactory.valueProperty().bindBidirectional(formatter.valueProperty());
        
        // key event setup - not working yet
        EventHandler<KeyEvent> enterKeyEventHandler;
        enterKeyEventHandler = new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                // handle users "enter key event"
                if (event.getCode() == KeyCode.ENTER) {

                    try {
                // yes, using exception for control is a bad solution ;-)
                Integer.parseInt(ageSpinner.getEditor().textProperty().get());
                    }
                    catch (NumberFormatException e) {

                        // show message to user: "only numbers allowed"

                        // reset editor to INITAL_VALUE
                        ageSpinner.getEditor().textProperty().set(INITAL_VALUE);
                    }
                }
            }
        };
        
        ageSpinner.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        
        // finding for add button
        BooleanBinding boolenBinding = idTagTextField.textProperty().isEmpty()
                .or(speciesTextField.textProperty().isEmpty().or(ageSpinner.valueProperty().isNull()
                .or(farmComboBox.valueProperty().isNull().or(genderGroup.selectedToggleProperty().isNull()))));  
        addButton.disableProperty().bind(boolenBinding);
        
        // farm combo box data setup 
        Callback factory = new Callback<ListView<Farm>, ListCell<Farm>>(){
            public ListCell<Farm> call(ListView<Farm> f){
                return new ListCell<Farm>(){ 
                    @Override
                    protected void updateItem(Farm farm, boolean empty) {
                        super.updateItem(farm, empty);
                        setText(empty ? "" : farm.getLicencePlate());
                    }
                };
            }
        };

        farmComboBox.setCellFactory(factory);
        farmComboBox.setButtonCell((ListCell) factory.call(null));
        
        fillComboBox();
    }    
    
    @FXML
    private void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addButtonAction(ActionEvent event) {
        AnimalDao animalDao = new AnimalDao();
        
        Farm farm = (Farm) farmComboBox.getSelectionModel().getSelectedItem(); 
        editedAnimal = animalDao.create(idTagTextField.getText(), ageSpinner.getValue(), speciesTextField.getText(), 
                maleRadioButton.isSelected()? Gender.MALE : Gender.FEMALE, farm);
        
        //animals.add(newAnimal);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        /*
        alert.setTitle("Sucess");
        alert.setHeaderText("Sucess : User was created.");
        alert.setContentText("User was created. Now you can log in.");
        alert.initStyle(StageStyle.TRANSPARENT);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getScene().setFill(new Color(0, 0, 0, 0));
        dialogPane.getStylesheets().add(getClass().getResource("/styles/AlertStyle.css").toExternalForm());
        alert.showAndWait();
        */
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    private void fillComboBox()
    {
        PersistenceManager pm = new PersistenceManager();
        List<Farm> farms = pm.getHqlResult(Farm.class, "from Farm");
        
        ObservableList data = FXCollections.observableArrayList();
        
        for(Farm farm : farms)
        {
            data.add(farm);
        }    
        farmComboBox.setItems(data);
    }    
    
    
    //tato metoda by mela nacit zvire z databáze podle 
    private void loadAnimal()
    {
        idTagTextField.setText(editedAnimal.getLicencePlate());
        //ageSpinner.setValueFactory(editedAnimal.getAge());
        ageSpinner.getEditor().textProperty().set(Integer.toString(editedAnimal.getAge()));
        speciesTextField.setText(editedAnimal.getSpecies());
        if(editedAnimal.getGender() ==  Gender.MALE)
        {
            maleRadioButton.setSelected(true);  
        }
        else
        {
            femaleRadioButton.setSelected(true);
        }
        
        //farmComboBox.getSelectionModel().s
        farmComboBox.getSelectionModel().select(editedAnimal.getFarm());
    }
}
