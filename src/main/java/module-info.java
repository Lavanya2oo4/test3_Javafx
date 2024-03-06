module com.lavanya {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.lavanya to javafx.fxml;
    exports com.lavanya;
}