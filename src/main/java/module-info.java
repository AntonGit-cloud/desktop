module com.example.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens com.example.desktop to javafx.fxml;
    opens  com.example.desktop.controllers to javafx.fxml;
    exports com.example.desktop;
}