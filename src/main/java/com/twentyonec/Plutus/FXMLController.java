package com.twentyonec.Plutus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {

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

    @FXML
    private void handleRoomButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String roomName = button.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/room.fxml"));
            Parent root = loader.load();

            RoomController controller = loader.getController();
            controller.setRoomName(roomName);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
