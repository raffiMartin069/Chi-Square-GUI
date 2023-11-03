package com.openjfx.business.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ChaiTest {
	private String valueString;
	private String dfString;
	private CheckBox checkBox_Value;
	private float dfValue;
	private float observableValue;
	private Label std_error;
	private List<Float> observableList;


	public ChaiTest() {}

	public ChaiTest(String valueString, String dfString,
			float dfValue, float observableValue, CheckBox checkBox_Value, float degreeofFreedom, 
			Label std_error) {
		this.valueString = valueString;
		this.observableValue = observableValue;
		this.dfString = dfString;
		this.dfValue = dfValue;
		this.std_error = std_error;
		this.checkBox_Value = checkBox_Value;
		this.observableList = new ArrayList<>();
	}
	
	public String getValueString() {
		return valueString.toUpperCase();
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public String getDfString() {
		return dfString.toUpperCase();
	}

	public void setDfString(String dfString) {
		this.dfString = dfString;
	}
	
	public Label getStdError() {
		return this.std_error;
	}
	
	public void setStdError(Label std_error) {
		this.std_error = std_error;
	}
	
	public float dfToFloat() {
		return Float.parseFloat(this.dfString);	
	}
	
	public String bothCheckbox() {
		return "Both";
	}
	public String pvalueCheckbox() {
		return "P-value";
	}
	public String criticalValueCheckbox() {
		return "Critical Value";
	}
	public List<Float> valueToFloat() {
	    String input = this.valueString;
	    String[] splitValues = input.split(",\\s*"); // Split the string by commas and optional spaces
	    observableList = new ArrayList<>();
	    for (String value : splitValues) {
	    	float floatValue = Float.parseFloat(value);
            this.observableList.add(floatValue);
	    }
	    return this.observableList;
	}
	public void inputFilter() {
		try {
			if (this.dfToFloat() == this.dfToFloat() || 
					this.valueToFloat() == this.valueToFloat()) {
				this.std_error.setText("");
				System.out.println(this.dfToFloat());
				System.out.println(this.valueToFloat());
			}
		} catch (Exception e) {
			this.std_error.setText("Only decimal and non-decimal numbers are allowed or field can not be empty.");
		}
	}
	public void inputCharDisable(TextField a) {
		a.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String character = event.getCharacter();
		    String pattern = "[0-9., ]";
		    if (!character.matches(pattern)) {
		        event.consume();
		    }
		});
	}
	@Override
	public String toString() {

		for (Float container : observableList) {
			this.observableList.add(container);
		}
		return this.observableList.toString();
	}
}
