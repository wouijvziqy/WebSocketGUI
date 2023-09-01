module com.mechoy.websocketgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires Java.WebSocket;


    opens com.mechoy.ui to javafx.fxml;
    exports com.mechoy.ui;
}