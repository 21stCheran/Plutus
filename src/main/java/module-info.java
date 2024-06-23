module com.twentyonec {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires javafx.graphics;
    opens com.twentyonec.Plutus to javafx.fxml;
    exports com.twentyonec.Plutus;
}