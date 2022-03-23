module com.example.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires MaterialFX;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires java.prefs;


    opens com.example.desktop to javafx.fxml;
    opens com.example.desktop.controllers to javafx.fxml;
    exports com.example.desktop;
    exports com.example.desktop.config;
    opens com.example.desktop.config to javafx.fxml;
}