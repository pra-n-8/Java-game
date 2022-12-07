module ucd.javafx.game_fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens ucd.javafx.game_fx to javafx.fxml;
    exports ucd.javafx.game_fx;

}