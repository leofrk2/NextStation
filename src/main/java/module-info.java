module com.game.nextstation.nextstation {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.game.nextstation.nextstation to javafx.fxml;
    exports com.game.nextstation.nextstation;
}
