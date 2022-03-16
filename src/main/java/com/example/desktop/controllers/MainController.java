package com.example.desktop.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final Stage stage;
    private double xOffset;
    private double yOffset;
    private final ToggleGroup toggleGroup;

    private boolean isNavShown = false;
    private MFXButton opNavButton;
    private ParallelTransition openNav;
    private ParallelTransition closeNav;

    private double x;
    private double y;

    private boolean isMin;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private HBox windowHeader;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon alwaysOnTopIcon;

    @FXML
    private MFXFontIcon userMani;

    @FXML
    private StackPane navBar;

    @FXML
    private StackPane contentPane;


    public MainController(Stage stage) {
        this.stage = stage;
        stage.setResizable(true);
        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
        isMin = true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Window window = rootPane.getScene().getWindow();
            if (isMin) {
                ((Stage) window).setMaximized(true);
                isMin = false;
            } else {
                ((Stage) window).setMaximized(false);
                isMin = true;
            }
        });

        userMani.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> animate());

        /*alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !stage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            stage.setAlwaysOnTop(newVal);

        });*/

        windowHeader.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        initializeLoader();

        navBar.setVisible(false);
        initAnimations();

    }


    private void initializeLoader() {
        MFXLoader loader = new MFXLoader();
        loader.setOnLoadedAction(beans -> {
            List<ToggleButton> nodes = beans.stream()
                    .map(bean -> {
                        ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper().get();
                        toggle.setOnAction(event -> contentPane.getChildren().setAll(bean.getRoot()));
                        if (bean.isDefaultView()) {
                            contentPane.getChildren().setAll(bean.getRoot());
                            toggle.setSelected(true);
                        }
                        return toggle;
                    })
                    .toList();
            //navBar.getChildren().setAll(nodes);
        });
        loader.start();
    }


    private void initAnimations() {
        openNav = (ParallelTransition) AnimationUtils.ParallelBuilder.build()
                .show(100, navBar)
                .add(new KeyFrame(Duration.millis(300), new KeyValue(navBar.translateXProperty(), 2)))
                //.add(new KeyFrame(Duration.millis(200), new KeyValue(opNavButton.rotateProperty(), -180)))
                .setOnFinished(event -> isNavShown = true)
                .getAnimation();

        closeNav = (ParallelTransition) AnimationUtils.ParallelBuilder.build()
                .hide(500, navBar)
                .add(new KeyFrame(Duration.millis(300), new KeyValue(navBar.translateXProperty(), -240)))
                //.add(new KeyFrame(Duration.millis(200), new KeyValue(opNavButton.rotateProperty(), 0)))
                .setOnFinished(event -> isNavShown = false)
                .getAnimation();
    }

    private void animate() {
        if (!isNavShown) {
            navBar.setVisible(true);
            openNav.play();
        } else {
            closeNav.play();
        }
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

}
