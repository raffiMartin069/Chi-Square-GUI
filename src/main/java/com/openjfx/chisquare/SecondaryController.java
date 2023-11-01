package com.openjfx.chisquare;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private Animate animator = new Animate();

    private static Selector selector = new Selector();
    @FXML
    void toBothValues(ActionEvent event) {
    	selector.toEnableCheckbox(bothValue_box.isSelected(),pValue_box, criticalValue_box);
    }

    @FXML
    void toCriticalValue(ActionEvent event) {
    	selector.toEnableCheckbox(criticalValue_box.isSelected(),pValue_box, bothValue_box);
    }

    @FXML
    void toPValue(ActionEvent event) {
    	selector.toEnableCheckbox(pValue_box.isSelected(),bothValue_box, criticalValue_box);
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
    void generateChi(ActionEvent event) {
		var chaiTest = new ChaiTest();
		chaiTest.setValueString(input_box.getText().toString());
		chaiTest.setDfString(df_field.getText().toString());

		if(chaiTest.ObservableStringSplitter() instanceof List<?>) {
			System.out.println(chaiTest.ObservableStringSplitter());
		}
//		System.out.println(chaiTest.getDfString());
		System.out.println(chaiTest.getValueString());
    }

	@FXML
    void toClearInput(MouseEvent event) {
		animator.setImgImageView(clear_inputs);
		animator.ThreeSixtyAnimation();
		input_box.clear();
		df_field.clear();
		selector.clearCheckBox(bothValue_box, criticalValue_box, pValue_box);
    }

	@FXML
    void goHome(MouseEvent e) throws IOException {
		App.setRoot("primary");
    }
}
