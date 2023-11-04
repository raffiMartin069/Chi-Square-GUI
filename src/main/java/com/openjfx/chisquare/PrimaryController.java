package com.openjfx.chisquare;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.openjfx.animator.Animate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PrimaryController implements Initializable{

	@FXML
    private ImageView main_visual;

    @FXML
    private BorderPane primary_border;

    @FXML
    private Button proceed_btn;

    private Animate animate = new Animate();

    @FXML
    void toMainPage(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    public void images() {
    List<Image> imgList = new ArrayList<>();
    // Stores .png directory path.
    String[] imageFileNames = {
        "img/data-analysis.png",
        "img/data-mining.png",
        "img/quantum-computing.png",
        "img/main-image.png"
    };
    // Loop through the directory path and stores to a List.
    // Takes the resources from the Resource folder.
    for (String fileName : imageFileNames) {
        imgList.add(new Image(getClass().getResourceAsStream(fileName)));
    }
    // Calls a method from Animate Class which handles Time Line.
    animate.imageAnimation(main_visual, imgList);
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Initialize image to the UI.
		images();
	}
}
