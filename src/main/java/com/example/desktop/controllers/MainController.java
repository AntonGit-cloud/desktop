package com.example.desktop.controllers;

import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.AnimationUtils;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.desktop.DesktopApplication.root;
import static com.example.desktop.MFXApplicationResourcesLoader.loadFxml;
import static com.example.desktop.MFXApplicationResourcesLoader.loadURL;

public class MainController implements Initializable {

    private static Stage stage;
    private double xOffset;
    private double yOffset;

    private boolean isNavShown = false;
    private int menu = 0;
    private int subjectCount = 0;
    private ParallelTransition openNav;
    private ParallelTransition closeNav;

    public static boolean isLightMode = true;

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
    private MFXFontIcon expandIcon;

    @FXML
    private MFXFontIcon userMani;

    @FXML
    private MFXFontIcon home;

    @FXML
    private MFXFontIcon subject;

    @FXML
    private MFXFontIcon video;

    @FXML
    private StackPane navBar;

    @FXML
    private StackPane contentPane;

    public MainController(Stage stage) {
        this.stage = stage;
        isMin = true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        expandIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Window window = rootPane.getScene().getWindow();
            if (isMin) {
                ((Stage) window).setMaximized(true);
                isMin = false;
            } else {
                ((Stage) window).setMaximized(false);
                isMin = true;
            }
        });

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

        initAnimations(navBar);
    }


    private void initializeLoader() {
        MFXLoader loader = new MFXLoader();
        //loader.addView(MFXLoaderBean.of("BUTTONS", loadURL("views/menu-view.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-circle-dot", "Buttons")).setDefaultRoot(true).get());
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
            navBar.getChildren().setAll(nodes);
        });
        loader.start();
    }


    private void initAnimations(StackPane stackPane) {
        openNav = (ParallelTransition) AnimationUtils.ParallelBuilder.build()
                .show(100, stackPane)
                .add(new KeyFrame(Duration.millis(300), new KeyValue(navBar.translateXProperty(), 5)))
                .setOnFinished(event -> {
                    isNavShown = true;
                }).getAnimation();

        closeNav = (ParallelTransition) AnimationUtils.ParallelBuilder.build()
                .hide(500, stackPane)
                .add(new KeyFrame(Duration.millis(300), new KeyValue(navBar.translateXProperty(), -240)))
                .setOnFinished(event -> {
                    isNavShown = false;
                    menu = 0;
                    subjectCount = 0;
                })
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

    public void manu(MouseEvent actionEvent) throws IOException {
        if (!isNavShown) {
            navBar.setVisible(true);
            openNav.play();
            loadFxml("views/menu-view.fxml", navBar);
            menu += 2;
        } else {
            if (menu >= 1) {
                menu = 0;
                closeNav.play();
            }
            menu++;
            subjectCount = 0;
            loadFxml("views/menu-view.fxml", navBar);
        }
    }

    public void subject(MouseEvent actionEvent) throws IOException {

        if (!isNavShown) {
            navBar.setVisible(true);
            openNav.play();
            loadFxml("views/subject-menu-view.fxml", navBar);
            subjectCount += 2;
        } else {
            if (subjectCount == 1) {
                subjectCount = 0;
                closeNav.play();
            }
            subjectCount++;
            menu = 0;
            loadFxml("views/subject-menu-view.fxml", navBar);
        }
    }

    public void home(MouseEvent actionEvent) throws IOException {
        if (isNavShown) {
            closeNav.play();
        }
        loadFxml("views/home-view.fxml", contentPane);
    }

    public static void setLightMode() {
        isLightMode = true;

        root.getStylesheets().remove(loadURL("css/dark/main-dark.css").toExternalForm());
        root.getStylesheets().add(loadURL("css/light/main-light.css").toExternalForm());

    }

    public static void setDarkMode() {
        isLightMode = false;
        root.getStylesheets().remove(loadURL("css/light/main-light.css").toExternalForm());
        root.getStylesheets().add(loadURL("css/dark/main-dark.css").toExternalForm());
    }
}
