package com.openjfx.chisquare;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1280, 900);
        // Checks the Java version and run time version.
        // In case mismatch, change the version of pom.xml to match fxml runtime version.
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("javafx.runtime.version: " + System.getProperty("javafx.runtime.version"));
        stage.setScene(scene);
        stage.show();
        logo(stage);
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    private boolean logo(Stage x) {
    	return x.getIcons().add(new Image(App.class.getResourceAsStream("img/ChiSquare.png")));
    }
    public static void main(String[] args) {
        launch();
    }
}