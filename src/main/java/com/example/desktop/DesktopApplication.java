package com.example.desktop;

import com.example.desktop.controllers.MainController;
import com.example.desktop.controllers.helper.ResizeHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DesktopApplication extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(MFXApplicationResourcesLoader.loadURL("views/main-view.fxml"));
        loader.setControllerFactory(c -> new MainController(stage));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.setTitle("Main platform education");
        ResizeHelper.addResizeListener(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}