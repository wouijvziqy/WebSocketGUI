package com.mechoy.ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.skin.TabPaneSkin;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (MainController.wsService != null) {
                    MainController.wsService.closeConnect();
                }
            }
        });

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        VBox load = fxmlLoader.load();
        Scene scene = new Scene(load);
        stage.setTitle("WebSocketGUI");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                MainController controller = fxmlLoader.getController();
                controller.init();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}