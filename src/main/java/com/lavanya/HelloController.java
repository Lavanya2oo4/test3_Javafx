package com.lavanya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public TextField inputName;
    public TextField inputContact;
    public CheckBox sizeS;
    public CheckBox sizeM;
    public CheckBox sizeL;
    public CheckBox sizeXL;
    public TextField inputToppings;
    public Label msg;
    public TableView<Modal> tableview;
    public TableColumn<Modal,Integer> tableId;
    public TableColumn<Modal,String> tableName;
    public TableColumn<Modal,String> tableContact;
    public TableColumn<Modal,String> tableSize;
    public TableColumn<Modal,Integer> tableToppings;
    public TableColumn<Modal,Double> tableTotal;
    public Pane billPane;
    public Label basicCost;
    public Label toppingCost;
    public Label total;
    public TextField orderIdInput;

    ObservableList<Modal> list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableId.setCellValueFactory(new PropertyValueFactory<Modal,Integer>("orderId"));
        tableName.setCellValueFactory(new PropertyValueFactory<Modal,String>("name"));
        tableContact.setCellValueFactory(new PropertyValueFactory<Modal,String>("contact"));
        tableSize.setCellValueFactory(new PropertyValueFactory<Modal,String>("size"));
        tableToppings.setCellValueFactory(new PropertyValueFactory<Modal,Integer>("toppings"));
        tableTotal.setCellValueFactory(new PropertyValueFactory<Modal,Double>("total"));
         tableview.setItems(list);
    }


    public void placeOrder() {
    }

    public void deletOrder() {
    }

    public void viewOrders() {
    }

    public void updateOrder() {
    }

    public void continueBtn() {
    }
}