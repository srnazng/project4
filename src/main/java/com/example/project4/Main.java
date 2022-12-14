package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launch point of application
 * @author Serena Zeng, Jackson Lee
 */
public class Main extends Application {
    private static final int SIZE = 600;

    /**
     * Sets up stage when program starts and load main view
     * @param stage         top level container for program
     * @throws IOException  exception for fxmlLoader.load()
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SIZE, SIZE);
        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launch application
     * @param args  default arguments
     */
    public static void main(String[] args) {
        launch();
    }
}