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


    String selectedSize;
    double  basePrice;
    double tax=15;
    double eachTopping=1.5;
    double finalPrice=0;
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
        setMsg("");
        String name_=inputName.getText();
        String toppings=inputToppings.getText();
        String contact=inputContact.getText();

        if(name_.equals("") || toppings.equals("") || contact.equals("")){
            setMsg("NAME TOPPINGS CONTACT AND SIZE ARE REQUIRED");
            return;
        }

        if(sizeS.isSelected()){
            sizeL.setSelected(false);
            sizeM.setSelected(false);
            sizeXL.setSelected(false);
            selectedSize=sizeS.getText();
        }
        else if (sizeL.isSelected()) {
            sizeS.setSelected(false);
            sizeM.setSelected(false);
            sizeXL.setSelected(false);
            selectedSize=sizeL.getText();

        }else if (sizeM.isSelected()) {
            sizeS.setSelected(false);
            sizeL.setSelected(false);
            sizeXL.setSelected(false);
            selectedSize=sizeM.getText();

        }else if (sizeXL.isSelected()) {
            sizeL.setSelected(false);
            sizeM.setSelected(false);
            sizeXL.setSelected(false);
            selectedSize=sizeXL.getText();


        }
        else{
            setMsg("SIZE IS REQUIRED");
            return;
        }

        billPane.setOpacity(1);

        if(selectedSize.equals("s")){
             basePrice=8.00;
             double netPrice=basePrice+(Integer.parseInt(toppings)*eachTopping);
             double afterTax=netPrice+((tax/100)*netPrice);
             finalPrice=afterTax;

            System.out.println(afterTax);

        } else if (selectedSize.equals("m")) {
            basePrice=10.00;
            double netPrice=basePrice+(Integer.parseInt(toppings)*eachTopping);
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;


        } else if (selectedSize.equals("l")) {
            basePrice=12.00;
            double netPrice=basePrice+(Integer.parseInt(toppings)*eachTopping);
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;


        }
        else {
            basePrice=15.00;
            double netPrice=basePrice+(Integer.parseInt(toppings)*eachTopping);
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;

        }

        basicCost.setText(String.valueOf(basePrice));
        toppingCost.setText(String.valueOf(Integer.parseInt(toppings)*eachTopping));
        total.setText(String.valueOf(finalPrice));
    }
    public void setMsg(String msgg){
        msg.setText(msgg);
    }
}