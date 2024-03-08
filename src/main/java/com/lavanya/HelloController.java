package com.lavanya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.ConnectException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

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
    double  basePrice=0;
    double tax=15;
    double eachTopping=1.5;
    double netToppingCost=0;
    double finalPrice=0;
    ObservableList<Modal> list= FXCollections.observableArrayList();

    private final  String url="jdbc:mysql://localhost:3306/test3_pizza";
    private final  String username="root";
    private final  String password="Oreo@2004";



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
        setMsg("");
        tableview.getItems().clear();
        String name_=inputName.getText();
        String toppings=inputToppings.getText();
        String contact=inputContact.getText();

        if(name_.equals("") || toppings.equals("") || contact.equals("")){
            setMsg("NAME TOPPINGS CONTACT AND SIZE ARE REQUIRED");

            return;
        }
        try{
            Integer.parseInt(toppings);
        }
        catch (Exception e){
            setMsg("TOPPINGS MUST BE A NUMBER");
            return;
        }

        try{
//            Class.forName("com.cj.mysql.jdbc.Driver");

        }
        catch(Exception e){
            System.out.println( e);
        }
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            String query="INSERT INTO orders (name,contact,size,toppings,total) VALUE (?,?,?,?,?) ";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name_);
            preparedStatement.setString(2,contact);
            preparedStatement.setString(3,selectedSize);
            preparedStatement.setInt(4,Integer.parseInt(toppings));
            preparedStatement.setDouble(5,finalPrice);

            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0){
                setMsg("ORDER PLACED SUCCESSFULLY");
                fetchAll();
            }
            else{
                setMsg("FAILED TO PLACE ORDER");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deletOrder() {
        String orderId=orderIdInput.getText();
        if(orderId.equals("")){
            setMsg("ORDER ID IS NEEDED");
            return;
        }

        try{
            Class.forName("com.cj.mysql.jdbc.Driver");

        }
        catch(Exception e){
//            System.out.println( e);
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM orders WHERE orderID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(orderId));

            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected>0){
                setMsg("ORDER DELETED SUCCESSFULLY");

            }
            else{
                setMsg("FAILED TO DELETE ORDER");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void viewOrders() {
        setMsg("");
        fetchAll();
    }
    public void fetchAll(){
        tableview.getItems().clear();
        try{
            Class.forName("com.cj.mysql.jdbc.Driver");

        }
        catch(Exception e){
//            System.out.println( e);
        }
        try{
            Connection connection=DriverManager.getConnection(url,username,password);
            String query="SELECT * FROM orders ";
            PreparedStatement preparedStatement=connection.prepareStatement(query);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int orderId=resultSet.getInt("orderId");
                String name=resultSet.getString("name");
                String contact=resultSet.getString("contact");
                String size=resultSet.getString("size");
                int toppings=resultSet.getInt("toppings");
                double total=resultSet.getDouble("total");
                tableview.getItems().add(new Modal(orderId,name,contact,size,toppings,total));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
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
        try{
            Integer.parseInt(toppings);
        }
        catch (Exception e){
            setMsg("TOPPINGS MUST BE A NUMBER");
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
            netToppingCost=(Integer.parseInt(toppings)*eachTopping);
            double netPrice=basePrice+netToppingCost;
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;

        } else if (selectedSize.equals("m")) {
            basePrice=10.00;
            netToppingCost=(Integer.parseInt(toppings)*eachTopping);
            double netPrice=basePrice+netToppingCost;
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;


        } else if (selectedSize.equals("l")) {
            basePrice=12.00;
            netToppingCost=(Integer.parseInt(toppings)*eachTopping);
            double netPrice=basePrice+netToppingCost;
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;


        }
        else {
            basePrice=15.00;
            netToppingCost=(Integer.parseInt(toppings)*eachTopping);
            double netPrice=basePrice+netToppingCost;
            double afterTax=netPrice+((tax/100)*netPrice);
            finalPrice=afterTax;


        }

        basicCost.setText(String.valueOf(basePrice));
        toppingCost.setText(String.valueOf(netToppingCost));
        total.setText(String.valueOf(finalPrice));
    }
    public void setMsg(String msgg){
        msg.setText(msgg);
    }
}