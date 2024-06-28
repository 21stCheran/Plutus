package com.twentyonec.Plutus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import com.twentyonec.Plutus.config.*;
import com.twentyonec.Plutus.storage.Storage;


public class Plutus extends Application {
    private static Stage stage;

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage = s;
        setRoot("primary", "Plutus");
        stage.show();
        
        Storage storage = Storage.getStorage();
        storage.setUpTable();
    }

    static void setRoot(String fxml) throws IOException {
    	stage.setMaximized(true); // Ensure the window is maximized
        setRoot(fxml, stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
    	stage.setMaximized(true); // Ensure the window is maximized
    	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(loadFXML(fxml), screenSize.getWidth(), screenSize.getHeight());
        stage.setTitle(title);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Plutus.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @SuppressWarnings("exports")
	public static Stage getStage() {
    	return stage;
    }
}
