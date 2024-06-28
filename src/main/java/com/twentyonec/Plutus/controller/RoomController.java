package com.twentyonec.Plutus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import com.twentyonec.Plutus.Plutus;

public class RoomController {

    @FXML
    private Label roomLabel;

    private String roomName;

    public void setRoomName(String roomName) {
        this.roomName = roomName;
        roomLabel.setText(roomName);
    }

    @FXML
    private void handleBookAction(ActionEvent event) {
        // Implement booking logic here
        System.out.println(roomName + " booked.");
    }

    @FXML
    private void handleVacateAction(ActionEvent event) {
        // Implement vacate logic here
        System.out.println(roomName + " vacated.");
    }
    
    //TODO -> Implement extend button

    @FXML
    private void handleReturnAction(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(Plutus.class.getResource("/fxml/" + "primary" + ".fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = Plutus.getStage();
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());

            // Set the new scene
            stage.setScene(scene);
            stage.show();

            // Ensure the window is maximized after the scene is set
            stage.setMaximized(true);
            stage.setMaximized(false);
            stage.setMaximized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox mainVBox;

    @FXML
    public void initialize() {
        // Add listeners to dynamically update the layout positions
        anchorPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainVBox.setLayoutX((newValue.doubleValue() - mainVBox.getWidth()) / 2);
        });

        anchorPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainVBox.setLayoutY((newValue.doubleValue() - mainVBox.getHeight()) / 2);
        });

        mainVBox.widthProperty().addListener((observable, oldValue, newValue) -> {
            mainVBox.setLayoutX((anchorPane.getWidth() - newValue.doubleValue()) / 2);
        });

        mainVBox.heightProperty().addListener((observable, oldValue, newValue) -> {
            mainVBox.setLayoutY((anchorPane.getHeight() - newValue.doubleValue()) / 2);
        });
    }
}
