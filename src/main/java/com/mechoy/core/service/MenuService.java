package com.mechoy.core.service;

import com.mechoy.ui.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Author  Mechoy
 * Version 1.0
 * Date    2023/8/31
 * Description
 */
public class MenuService {

    public TextArea logTextArea;

    public MenuService() {
    }

    public MenuService(TextArea logTextArea) {
        this.logTextArea = logTextArea;
    }

    public void menuHelpAbout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("about.fxml"));
        VBox load = fxmlLoader.load();
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
}
