package com.openjfx.business.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ChaiTest {
	// Handles the logic behind user interaction to input fields.
	// Used to set values that will initialize in the Secondary Class.
	// Process and Limits user interaction.
	// Implements error handling.
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
	// Handles initialization of variables in Secondary class.
	// Allows to manipulate data and be accessible for process handling.
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
	// Uses to pass a standard error for the program.
	// It initializes the Label in Secondary Class.
	public void setStdError(Label std_error) {
		this.std_error = std_error;
	}
	// Converts String to Float from input field.
	// Makes Degree of Freedom become a Float instead of String.
	public float dfToFloat() {
		return Float.parseFloat(this.dfString);	
	}
	// A place holder to be triggered when converted to JSON.
	// Handled in the server to provide necessary graph and results.
	public String bothCheckbox() {
		return "Both";
	}
	public String pvalueCheckbox() {
		return "P-value";
	}
	public String criticalValueCheckbox() {
		return "Critical Value";
	}
	// Converts String to Float for Observable Value field.
	// Splits values into comma separated values (CSV).
	// Stores converted values into a List.
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
	// Highly unlikely to be used. Implemented to mitigate possible errors.
	// In case of unwanted errors an custom error will be thrown.
	// Checks if user inputs legal characters i.e numbers, decimals and commas.
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
	// Disables other sorts of characters.
	// Only allows numbers from 0 --> 9, dot, and comma.
	// Lambda expression taken from a interface.
	// KeyEvent.KEY_TYPED, event two params passed.
	// Filter set in the body of Lamda.
	public void inputCharDisable(TextField a) {
		a.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String character = event.getCharacter();
		    String pattern = "[0-9., ]";
		    if (!character.matches(pattern)) {
		        event.consume();
		    }
		});
	}
	// Will be used to send a JSON value to local server.
	@Override
	public String toString() {

		for (Float container : observableList) {
			this.observableList.add(container);
		}
		return this.observableList.toString();
	}
}
