package com.jvl.des;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class AnimalScreenController implements Initializable {

    @FXML
    private AnchorPane animalScreenAP;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<?> tblCustomer;
    @FXML
    private TableColumn<?, ?> tblClmName;
    @FXML
    private TableColumn<?, ?> tblClmContNo;
    @FXML
    private TableColumn<?, ?> tblClmAddres;
    @FXML
    private TableColumn<?, ?> tblClmDate;
    @FXML
    private TableColumn<?, ?> tblClmAddBy;
    @FXML
    private TableColumn<?, ?> tblClmTotalBuy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
    }
    
}
