package com.twentyonec.Plutus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

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

    @FXML
    private void handleReturnAction(ActionEvent event) {
        try {
            MainApp.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
