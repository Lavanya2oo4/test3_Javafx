package com.lavanya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 520);
        stage.setTitle("Pizza Ghost!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println(calcBill(5,10));
    }

    public static double calcBill(int toppings,int basePrice){
        double total=(toppings*1.5)+basePrice+((15/100f)*(toppings*1.5+basePrice));
        total=Double.parseDouble(String.format("%.2f",total));

        return total;
    }

}