package com.openjfx.chisquare;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.openjfx.animator.Animate;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    private List<Image> imgArray;
    private Timeline imageTimeline;
    private Animate animate;

    @FXML
    void toMainPage(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    public void images() {
    	imgArray = new ArrayList<>();
    	animate = new Animate();

    	String[] imageFileNames = {
    	        "img/data-analysis.png",
    	        "img/data-mining.png",
    	        "img/quantum-computing.png",
    	        "img/main-image.png"
    	    };

    	try {
    		for (String string : imageFileNames) {
				imgArray.add(new Image(getClass().getResourceAsStream(string)));
			}
			if (!imgArray.isEmpty()) {
	            main_visual.setImage(imgArray.get(0));

	            KeyFrame keyFrame = animate.imageTimeLine(imgArray, main_visual);
	            imageTimeline = new Timeline();
	            imageTimeline.setCycleCount(Animation.INDEFINITE);
	            imageTimeline.getKeyFrames().add(keyFrame);
	            imageTimeline.play();
	        }
		} catch (Exception e) {
			System.out.println("Array is empty..." + e.getMessage());
		}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		images();
	}
}
