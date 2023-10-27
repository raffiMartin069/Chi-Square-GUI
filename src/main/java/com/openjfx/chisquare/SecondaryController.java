package com.openjfx.chisquare;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController implements Initializable{

    @FXML
    private CheckBox bothValue_box;

    @FXML
    private Label chi_label;

    @FXML
    private CheckBox criticalValue_box;

    @FXML
    private TextField input_box;

    @FXML
    private CheckBox pValue_box;

    @FXML
    private Label page_title;
    
    private void toEnableCheckbox(boolean isTicked, CheckBox ... value) {
    	// Handles the checkbox logic, method recieves varargs.
    	for (CheckBox checkBox : value) {
			checkBox.setDisable(isTicked);
		}
    	
    }

    @FXML
    void toBothValues(ActionEvent event) {
    	toEnableCheckbox(bothValue_box.isSelected(),pValue_box, criticalValue_box);
    }

    @FXML
    void toCriticalValue(ActionEvent event) {
    	toEnableCheckbox(criticalValue_box.isSelected(),pValue_box, bothValue_box);
    }

    @FXML
    void toPValue(ActionEvent event) {
    	toEnableCheckbox(pValue_box.isSelected(),bothValue_box, criticalValue_box);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
