package com.openjfx.animator;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
// See https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Animation.html for Animation documentation in Javafx.
public class Animate {
	// Handles basic animations for UI.
	private RotateTransition rtTransition;
	private KeyFrame keyFrame;	// Key Frame measures position in time. Responsible on where the object will run or animate.
	private ImageView imgImageView;
	private int currentImageIndex = 0;
	private List<Image> images;
	private Timeline imageTimeline;
	public ImageView getImgImageView() {
		return imgImageView;
	}
	public void setImgImageView(ImageView imgImageView) {
		this.imgImageView = imgImageView;
	}
	// Handles rotation animation.
	public void ThreeSixtyAnimation() {
		// Duration is measured in milliseconds and points to --> the Image.
		this.rtTransition = new RotateTransition(Duration.millis(1000), this.imgImageView);
		// Negative rotation matches the arrow position of image.
		this.rtTransition.setByAngle(-720);
		// Cycle count determines the number of rotation the object will do.
		this.rtTransition.setCycleCount(1);
		// If cycle of negative reversal is complete, it will rotate back to its original position.
		this.rtTransition.setAutoReverse(true);
		// Play the animation, will be handled in Secondary class.
		this.rtTransition.play();
	}
	public void imageAnimation(ImageView imgView, List<Image> imgList) {
	    // Check if image list is not empty.
		if (!imgList.isEmpty()) {
			//Sets the image starting index 0.
	        imgView.setImage(imgList.get(0));
	        // Loop effect by the use of key frame which increments index every 3 seconds.
	        KeyFrame frame = new KeyFrame(Duration.seconds(3), event -> {
	            currentImageIndex = (currentImageIndex + 1) % imgList.size();
	            imgView.setImage(imgList.get(currentImageIndex));
	        });
	        // INDEFENITE continuous the cycle, unless, imageTimeLine.stop() is called.
	        imageTimeline = new Timeline(frame);
	        imageTimeline.setCycleCount(Animation.INDEFINITE);
	        imageTimeline.play();
	    }
	}
}