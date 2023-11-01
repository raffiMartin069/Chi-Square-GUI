package com.openjfx.animator;

import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animate {
	private RotateTransition rtTransition;
	private KeyFrame keyFrame;
	private ImageView imgImageView;
	private int currentImageIndex = 0;

	private Timeline imageTimeline;

	public ImageView getImgImageView() {
		return imgImageView;
	}

	public void setImgImageView(ImageView imgImageView) {
		this.imgImageView = imgImageView;
	}

	public void ThreeSixtyAnimation() {
		this.rtTransition = new RotateTransition(Duration.millis(1000), this.imgImageView);
		this.rtTransition.setByAngle(-720);
		this.rtTransition.setCycleCount(1);
		this.rtTransition.setAutoReverse(true);
		this.rtTransition.play();
	}

	public KeyFrame imageTimeLine(List<Image>img, ImageView imgView) {
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
            currentImageIndex = (currentImageIndex + 1) % img.size();
            imgView.setImage(img.get(currentImageIndex));
        });
        imageTimeline = new Timeline(keyFrame);
        imageTimeline.setCycleCount(Animation.INDEFINITE);
        return keyFrame;
	}

}
