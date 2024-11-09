module com.example.newtenant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;  // For using JavaFX's graphics


    requires org.kordamp.bootstrapfx.core;

    opens com.example.newtenant to javafx.fxml;
    exports com.example.newtenant;
}