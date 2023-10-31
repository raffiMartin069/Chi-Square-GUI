package com.openjfx.chisquare;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PrimaryController {
	
	@FXML
    private ImageView main_visual;

    @FXML
    private BorderPane primary_border;

    @FXML
    private Button proceed_btn;
    
    @FXML
    void toMainPage(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }
    
}
