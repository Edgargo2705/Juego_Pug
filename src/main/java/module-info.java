module com.example.juego_pug {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juego_pug to javafx.fxml;
    exports com.example.juego_pug;
    exports com.example.juego_pug.controllers;
    opens com.example.juego_pug.controllers to javafx.fxml;
}