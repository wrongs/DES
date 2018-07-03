package UI.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.dao.ActivityDao;
import data.dao.AnimalDao;
import data.entites.Activity;
import data.entites.Animal;
import data.entites.Product;
import data.enumeration.Status;
import data.enumeration.Unit;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import logic.PersistenceManager;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class AnimalScreenController implements Initializable {

    @FXML
    private AnchorPane animalScreenAP; 
    @FXML
    private Button addMainButton;
    @FXML
    private Button deleteMainButton;
    @FXML
    private Button addChildButton;
    @FXML
    private Button deleteChildButton;
    @FXML
    private Button filterChildButton;
    @FXML
    private Button modifyMainButton;
    @FXML
    private TabPane childPane;
    @FXML
    private TableView<Animal> animalTable;
    @FXML
    private TableColumn<Animal, String> idTagColumn;
    @FXML
    private TableColumn<Animal, String> speciesColumn;
    @FXML
    private TableColumn<Animal, Integer> ageColumn;
    @FXML
    private TableColumn<Animal, String> genderColumn;
    @FXML
    private TableColumn<Animal, String> farmColumn;
    @FXML
    private TableView<Activity> activitesTable;
    @FXML
    private TableColumn<Activity, String> nameColumn;
    @FXML
    private TableColumn<Activity, Status> statusColumn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> pNameColumn;
    @FXML
    private TableColumn<Product, Date> expDateColumn;
    @FXML
    private TableColumn<Product, Double> prizeColumn;
    @FXML
    private TableColumn<Product, Integer> qtyColumn;
    @FXML
    private TableColumn<Product, Unit> unitColumn;
    
    private ObservableList animalData;
    private ObservableList activitiesData;
    private ObservableList productsData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        idTagColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("licencePlate"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("species"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("gender"));
        //farmColumn.setCellValueFactory(new PropertyValueFactory<Animal, Farm>("farm"));
        /*
        farmColumn.setCellValueFactory(new Callback<CellDataFeatures<Animal, Farm>, ObservableValue<String>>() {
           
            @Override 
            public ObservableValue<String> call(CellDataFeatures<Animal, Farm> c) {
                return new SimpleStringProperty(c.getValue().toString()));
            }
        });
        */
        
        farmColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Animal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Animal, String> p) {
                return new SimpleStringProperty(p.getValue().getFarm().toString());
            }
   
         });
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<Activity, String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Activity, Status>("status"));
        
        // set value factory for product
        pNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        expDateColumn.setCellValueFactory(new PropertyValueFactory<Product, Date>("expirationDate"));
        prizeColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("prize"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Product, Unit>("unit"));
        
        expDateColumn.setCellFactory(column -> {
            TableCell<Product, Date> cell = new TableCell<Product, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                         setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        
        animalTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                loadActivitesForAnimal((Animal)newSelection);
                getProductsfromDB((Animal)newSelection);
            }
        });
        
        


        //tableau.setItems(getEnseignant());
        
        final ToggleGroup genderGroup = new ToggleGroup();
        //genderGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        
        getAnimalsfromDB();
        //getActivitesfromDB();
        Platform.runLater(() -> animalTable.refresh());
        
        
        /// bind to modify button
        //modifyMainButton.disableProperty().bind(Bindings.isNotNull(animalTable.getSelectionModel().getSelectedItem()));
        //(Bindings.size(list).isEqualTo(0));
       // BooleanBinding boolenBinding = animalTable.selectionModelProperty().
          BooleanBinding boolenBinding = animalTable.getSelectionModel().selectedItemProperty().isNull();
        //BooleanBinding boolenBinding = animalTable.getSelectionModel().cellSelectionEnabledProperty().;

        modifyMainButton.disableProperty().bind(boolenBinding);
    }    

    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
    }
  
    private void getAnimalsfromDB()
    {
        PersistenceManager manager = new PersistenceManager();
        List<Animal> animals = manager.getHqlResult(Animal.class, "from Animal");
        animalData = FXCollections.observableArrayList();
        
        for(Animal animal : animals)
        {
            animalData.add(animal);
        }
        
        animalTable.setItems(animalData);
    }
    
    /*
      private void getActivitesfromDB()
    {
        PersistenceManager manager = new PersistenceManager();
        List<Activity> activities = manager.getHqlResult(Activity.class, "from Activity");
        activitiesData = FXCollections.observableArrayList();
        
        for(Activity activity : activities)
        {
            activitiesData.add(activity);
        }
        
        activitesTable.setItems(activitiesData);
    }
    */
      
    @FXML
    private void addMainButtonOnAction(ActionEvent event) throws IOException {
        showEditAnimal(true);
    }
    
    @FXML
    private void modifyMainButtonOnAction(ActionEvent event) throws IOException {
        showEditAnimal(false);
    }
    
      /*
    @FXML
    private void addMainButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/AddAnimal.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        EditAnimalController addAnimalController = fxmlLoader.getController();
        addAnimalController.setAnimalsData(animalData);
        //media.setId(usrId);
       // editAnimalController.setMedia(media);
        //supplyerController.lblCaption.setText("Add Supplyer");
        //supplyerController.btnUpdate.setVisible(false);
        Stage nStage = new Stage();
        //supplyerController.addSupplyerStage(nStage);
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }
      */
    @FXML
    private void deleteMainButtonOnAction(ActionEvent event) {
        
        Animal selectedItem = (Animal) animalTable.getSelectionModel().getSelectedItem(); 
        
        AnimalDao animalDao = new AnimalDao();
        animalDao.remove(selectedItem);
        animalTable.getItems().remove(selectedItem);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        /*
        ObservableList selectedItems = animalTable.getSelectionModel().getSelectedItems();
        for(Animal animal : selectedItems)
        {
            animalDao.remove(animal)
        }
        animals.add(newAnimal);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        */
    }

    /*
    @FXML
    private void modifyMainButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/AddAnimal.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        EditAnimalController editAnimalController = fxmlLoader.getController();
        editAnimalController.setAnimalsData(animalData);
        editAnimalController.setButton("Modify");
        Animal selectedItem = (Animal) animalTable.getSelectionModel().getSelectedItem();
        editAnimalController.setEditedAnimal(selectedItem);
        //media.setId(usrId);
       // editAnimalController.setMedia(media);
        //supplyerController.lblCaption.setText("Add Supplyer");
        //supplyerController.btnUpdate.setVisible(false);
        Stage nStage = new Stage();
        //supplyerController.addSupplyerStage(nStage);
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();  
    }
    */
    
    /*
    @FXML
    private void addChildButtonOnAction(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/AddActivity.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        //EditAnimalController addAnimalController = fxmlLoader.getController();
        //addAnimalController.setAnimalsData(animalData);
        
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.showAndWait();
        
        //
        
        if(userController.getLogOut())
        {
            logout();
        }
    }
    */
    
    @FXML
    private void addChildButtonOnAction(ActionEvent event) throws IOException {
        if(isActivityTabSelected())
        {
            showEditActivity(true);
        }
         else 
        {
            showEditProduct(true);
        }
    }
    
    private void modifyChildButtonOnAction(ActionEvent event) throws IOException {
        if(isActivityTabSelected())
        {
            showEditActivity(false);    
        }
        else 
        {
            showEditProduct(false);
        }
    }
    
    @FXML
    private void deleteChildButtonOnAction(ActionEvent event) {
        Activity selectedActivty = (Activity) activitesTable.getSelectionModel().getSelectedItem(); 
        
        ActivityDao activityDao = new ActivityDao();
        activityDao.remove(selectedActivty);
        animalTable.getItems().remove(selectedActivty);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    }

    @FXML
    private void filterChildButtonOnAction(ActionEvent event) {
    }
    
    private void showEditAnimal(boolean isAdd) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/AddAnimal.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        EditAnimalController editAnimalController = fxmlLoader.getController();
        editAnimalController.setButton( isAdd ? "Add" : "Modify");
        
        if(!isAdd)
        {
            Animal selectedItem = (Animal) animalTable.getSelectionModel().getSelectedItem();
            editAnimalController.setEditedAnimal(selectedItem);
        }
        
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.showAndWait();
        
        if(isAdd && editAnimalController.getEditedAnimal() != null )
        {
            animalData.add(editAnimalController.getEditedAnimal());
        }
        //tefresh table 
    }
    
    private void showEditActivity(boolean isAdd) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/AddActivity.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        EditActivityController editActivityController = fxmlLoader.getController();
       
        Animal selectedAnimal = (Animal) animalTable.getSelectionModel().getSelectedItem(); 
        editActivityController.setLinkedAnimal(selectedAnimal);
        
        if(!isAdd)
        {
            Activity selectedActivity = (Activity) activitesTable.getSelectionModel().getSelectedItem();
            editActivityController.setEditedActivity(selectedActivity);
        }
        
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.showAndWait();
        
        if(isAdd && editActivityController.getEditedActivity() != null )
        {  
            Animal selectedItem = (Animal) animalTable.getSelectionModel().getSelectedItem();
            //Activity activity =  editActivityController.getEditedActivity();
            //activity.setAnimal(selectedItem);
            activitiesData.add(editActivityController.getEditedActivity());
        }
    }
    
    private void loadActivitesForAnimal(Animal animal)
    {
        PersistenceManager manager = new PersistenceManager();
        List<Activity> activities = manager.getHqlResult(Activity.class, "from Activity where animal.id = ?1", animal.getId());
        activitiesData = FXCollections.observableArrayList();
        
        for(Activity activity : activities)
        {
            activitiesData.add(activity);
        }
        
        activitesTable.setItems(activitiesData);
    }
    
    private boolean isActivityTabSelected()
    {
        return childPane.getSelectionModel().getSelectedIndex() == 0;
    }
    
    private boolean isAnimalTabSelected()
    {
        return childPane.getSelectionModel().getSelectedIndex() == 0;
    }
      
     private void showEditProduct(boolean isAdd) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Popup/EditProduct.fxml"));
        
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        EditProductController editProductController = fxmlLoader.getController();
       
        Animal selectedAnimal = (Animal) animalTable.getSelectionModel().getSelectedItem(); 
        editProductController.setLinkedAnimal(selectedAnimal);
        
        if(!isAdd)
        {   
            Product selectedProduct = (Product) productTable.getSelectionModel().getSelectedItem();
            editProductController.setEditedProduct(selectedProduct);
        }
        
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.showAndWait();
        
        if(isAdd && editProductController.getEditedProduct() != null )
        {  
            //Animal selectedItem = (Animal) animalTable.getSelectionModel().getSelectedItem();
            //Activity activity =  editActivityController.getEditedActivity();
            //activity.setAnimal(selectedItem);
            productsData.add(editProductController.getEditedProduct());
        }
    }
     
    private void getProductsfromDB(Animal animal)
    {
        PersistenceManager manager = new PersistenceManager();
        List<Product> products = manager.getHqlResult(Product.class, "from Product where animal.id = ?1", animal.getId());
           productsData = FXCollections.observableArrayList();
        
        for(Product product : products)
        {
            productsData.add(product);
        }
        
        productTable.setItems(productsData);
    }
}
