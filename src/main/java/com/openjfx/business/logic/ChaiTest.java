package com.openjfx.business.logic;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	private String graphType;
	private float dfValue;
	private float observableValue;
	private Label std_error;
	private List<Float> observableList;
	private float result[][];

	public ChaiTest() {}
	public ChaiTest(String valueString, String dfString,
			float dfValue, float observableValue, CheckBox checkBox_Value, float degreeofFreedom,
			Label std_error, float result[][], String graphType) {
		this.valueString = valueString;
		this.observableValue = observableValue;
		this.dfString = dfString;
		this.dfValue = dfValue;
		this.std_error = std_error;
		this.checkBox_Value = checkBox_Value;
		this.observableList = new ArrayList<>();
		this.result = result;
		this.graphType = graphType;
	}
	public String getGraphType() {
		return graphType;
	}
	public void setGraphType(String graphType) {
		this.graphType = graphType;
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
	// KeyEvent.KEY_TYPED, event two parameters passed.
	// Filter set in the body of Lambda.
	public void inputCharDisable(TextArea a) {
		a.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    String character = event.getCharacter();
		    String pattern = "[0-9., ,\n]";
		    if (!character.matches(pattern)) {
		        event.consume();
		    }
		});
	}
	// Splits the string according to the new line entered.
	// Converts string to double when stored in an 2D array.
	public float[][] parseTextAreaInput(String value) {
        String[] rows = value.split("\n");
        this.result = new float[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            this.result[i] = new float[values.length];

            for (int j = 0; j < values.length; j++) {
                try {
                	this.result[i][j] = Float.parseFloat(values[j].trim());
                } catch (NumberFormatException e) {
                    // Handle invalid input if necessary.
                }
            }
        }
        return this.result;
    }
	// This disables characters across all keyboard.
	// Eliminates the problem of F or f and D or d input.
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
	// Gson dependency used to convert 2D array to Json 2D array.
	@Override
	public String toString() {
		var gson = new Gson();
		String jsonArray = gson.toJson(this.result);
		return "Chai-Test [observable_value "+jsonArray+", degree_of_freedom = "+this.dfToFloat()+", value = "+this.getGraphType()+"]";
	}
}
