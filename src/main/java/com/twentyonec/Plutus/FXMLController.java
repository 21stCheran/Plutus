package com.twentyonec.Plutus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {

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
