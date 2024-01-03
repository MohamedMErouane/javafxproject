package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., show an error message).
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
