package com.example.desktop.controllers;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.desktop.controllers.MainController.isLightMode;
import static com.example.desktop.controllers.MainController.setDarkMode;
import static com.example.desktop.controllers.MainController.setLightMode;

public class MenuController implements Initializable {

    @FXML
    private MFXToggleButton themeMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeMode(javafx.scene.input.MouseEvent mouseEvent) {
        if (!isLightMode) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }
}
