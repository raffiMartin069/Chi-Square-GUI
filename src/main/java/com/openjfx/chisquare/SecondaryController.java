package com.openjfx.chisquare;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import com.openjfx.animator.Animate;
import com.openjfx.business.logic.ChaiTest;
import com.openjfx.component.Selector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SecondaryController implements Initializable{

    @FXML
    private CheckBox bothValue_box;

    @FXML
    private ImageView toHome;

    @FXML
    private TextField df_field;

    @FXML
    private Button generate_btn;

    @FXML
    private ImageView clear_inputs;

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
    
    @FXML
    private Label error_msg;

    private Animate animator = new Animate();

    private static Selector selector = new Selector();

    private static ChaiTest chaiTest = new ChaiTest();
    
    @FXML
    void toBothValues(ActionEvent event) {
    	selector.toEnableCheckbox(bothValue_box.isSelected(),pValue_box, criticalValue_box);
    	System.out.println(chaiTest.bothCheckbox());
    }

    @FXML
    void toCriticalValue(ActionEvent event) {
    	selector.toEnableCheckbox(criticalValue_box.isSelected(),pValue_box, bothValue_box);
    	System.out.println(chaiTest.criticalValueCheckbox());
    }

    @FXML
    void toPValue(ActionEvent event) {
    	selector.toEnableCheckbox(pValue_box.isSelected(),bothValue_box, criticalValue_box);
    	System.out.println(chaiTest.pvalueCheckbox());
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	@FXML
    void generateChi(ActionEvent event) {
//		chaiTest = new ChaiTest();
		chaiTest.setValueString(input_box.getText().toString());
		chaiTest.setDfString(df_field.getText().toString());
		try {
			if (chaiTest.dfToFloat() == chaiTest.dfToFloat() || 
				chaiTest.valueToFloat() == chaiTest.valueToFloat()) {
				error_msg.setText("");
				System.out.println(chaiTest.dfToFloat());
				System.out.println(chaiTest.valueToFloat());
			}
		} catch (Exception e) {
			error_msg.setText("Only decimal and non-decimal numbers are allowed.");
		}
	}
	@FXML
    void toClearInput(MouseEvent event) {
		animator.setImgImageView(clear_inputs);
		animator.ThreeSixtyAnimation();
		input_box.clear();
		df_field.clear();
		error_msg.setText("");
		selector.clearCheckBox(bothValue_box, criticalValue_box, pValue_box);
    }

	@FXML
    void goHome(MouseEvent e) throws IOException {
		App.setRoot("primary");
    }
}
