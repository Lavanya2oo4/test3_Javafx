module com.lavanya {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.lavanya to javafx.fxml;
    exports com.lavanya;
}