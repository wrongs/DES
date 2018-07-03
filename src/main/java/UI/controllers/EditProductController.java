/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.controllers;

import data.dao.AnimalActivityDao;
import data.dao.AnimalProductDao;
import data.dao.ProductDao;
import data.entites.Animal;
import data.entites.Product;
import data.enumeration.Status;
import data.enumeration.Unit;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class EditProductController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<?> unitComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private Spinner<Double> prizeSpinner;
    @FXML
    private Spinner<Integer> qtySpinner;

    private Product editedProduct;
    
    private Animal linkedAnimal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        expDatePicker.showWeekNumbersProperty().set(false);
        //prizeSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, 0.00));
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100000, 1, 0.5);
        prizeSpinner.setEditable(true); 
        prizeSpinner.setValueFactory(valueFactory);
        
        TextFormatter formatter = new TextFormatter(valueFactory.getConverter(), valueFactory.getValue());
        prizeSpinner.getEditor().setTextFormatter(formatter);
        valueFactory.valueProperty().bindBidirectional(formatter.valueProperty());
        
        SpinnerValueFactory<Integer> intValueFactory = 
               new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1, 1);
        qtySpinner.setEditable(true);
        qtySpinner.setValueFactory(intValueFactory);
        
        formatter = new TextFormatter(intValueFactory.getConverter(), intValueFactory.getValue());
        qtySpinner.getEditor().setTextFormatter(formatter);
             
        intValueFactory.valueProperty().bindBidirectional(formatter.valueProperty());
        //expDatePicker.setValue();
        /*
        expDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }

        });
        */
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //expDatePicker.setValue(LocalDate.parse(formatter.format(LocalDate.now())));
        expDatePicker.setValue(LocalDate.now());
        fillComboBox();
    }    
    
    public Product getEditedProduct() {
        return editedProduct;
    }

    public void setEditedProduct(Product editedProduct) {
        this.editedProduct = editedProduct;
    }
    
    public Animal getLinkedAnimal() {
        return linkedAnimal;
    }

    public void setLinkedAnimal(Animal linkedAnimal) {
        this.linkedAnimal = linkedAnimal;
    }
    
    @FXML
    private void addButtonAction(ActionEvent event) {
        
        AnimalProductDao productDao = new AnimalProductDao();
        Unit unit = (Unit) unitComboBox.getSelectionModel().getSelectedItem(); 
        Date selectedDate = Date.from(expDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        editedProduct = productDao.create(nameTextField.getText(), selectedDate, 
                prizeSpinner.getValue(), qtySpinner.getValue(), unit, linkedAnimal);
         
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
     
       //tato metoda by mela nacit zvire z databáze podle 
    private void loadActivity()
    {
        nameTextField.setText(editedProduct.getName());
        //statusComboBox.getSelectionModel().select(editedActivity.getStatus());
    }
    
    private void fillComboBox()
    {
        // PersistenceManager pm = new PersistenceManager();
        //List<Status> statuses = pm.getHqlResult(Status.class, "from Farm");
        
        List<Unit> units = Arrays.asList(Unit.values());
        ObservableList data = FXCollections.observableArrayList();
        //data.
        for(Unit unit : units)
        {
            data.add(unit);
        }    
        unitComboBox.setItems(data);
    }    
}
