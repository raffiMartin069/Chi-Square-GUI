package com.openjfx.chisquare;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1280, 720);
        String css = this.getClass().getResource("/com/openjfx/chisquare/secondary.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        // Checks the Java version and run time version.
        // In case mismatch, change the version of pom.xml to match fxml runtime version.
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("javafx.runtime.version: " + System.getProperty("javafx.runtime.version"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();   
    }
}