package com.example.desktop.controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    @FXML
    private TextField loginField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private HBox windowsBar;

    @FXML
    private AnchorPane ap;

    private static double xOffset = 0;
    private static double yOffset = 0;


    @FXML
    public void onHelloButtonClick() throws IOException {
        // welcomeText.setText("Welcome to JavaFX Application!");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/auth/login");

        String json = "{\n" +
                "  \"login\": \"Anton@Afanasiev.com\",\n" +
                "  \"password\": \"DG7Uj6xp26FjFVyW\",\n" +
                "  \"rememberMe\": true\n" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.toString());

        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
        client.close();
        //test.setText(responseString);
    }


    @FXML
    protected void movingWindow() {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("MyGui.fxml"));
        Parent root = (Parent)loader.load();
        AuthController controller = (AuthController)loader.getController();
        controller.setStageAndSetupListeners(stage);*/

        Stage stage = (Stage) ap.getScene().getWindow();

        windowsBar.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });


        windowsBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}