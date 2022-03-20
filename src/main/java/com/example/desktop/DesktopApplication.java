package com.example.desktop;

import com.example.desktop.controllers.MainController;
import com.example.desktop.controllers.helper.ResizeHelper;
import com.example.desktop.i18n.I18N;
import com.example.desktop.i18n.Language;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ResourceBundle;

import static com.example.desktop.MFXApplicationResourcesLoader.loadURL;

public class DesktopApplication extends Application {

    private Scene scene;

    public static Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle1 = I18N.getBundle(Language.defaultLocale());
        FXMLLoader loader = new FXMLLoader(loadURL("views/main-view.fxml"), bundle1);
        loader.setControllerFactory(c -> new MainController(stage));
        root = loader.load();
        //root.getStylesheets().add(loadURL("css/light/main-light.css").toExternalForm());
        //root.getStylesheets().add(loadURL("css/dark/main-dark.css").toExternalForm());
        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        // scene.getStylesheets().add(loadURL("css/light/main-light.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        ResizeHelper.addResizeListener(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}