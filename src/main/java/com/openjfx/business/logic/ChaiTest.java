package com.openjfx.business.logic;

import java.util.ArrayList;
import java.util.List;

public class ChaiTest {
	private String valueString;
	private String dfString;
	private float dfValue;
	private float observableValue;
	private List<Float> observableList;


	public ChaiTest() {}

	public ChaiTest(String valueString, String dfString,
			float dfValue, float observableValue) {
		this.valueString = valueString;
		this.observableValue = observableValue;
		this.dfString = dfString;
		this.dfValue = dfValue;
		this.observableList = new ArrayList<>();
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	public String getDfString() {
		return dfString;
	}

	public void setDfString(String dfString) {
		this.dfString = dfString;
	}

	public List<Float> ObservableStringSplitter() {
	    String input = this.valueString;
	    String[] splitValues = input.split(",\\s*"); // Split the string by commas and optional spaces
	    observableList = new ArrayList<>();
	    for (String value : splitValues) {
	        try {
	            float floatValue = Float.parseFloat(value);
	            this.observableList.add(floatValue);
	        } catch (NumberFormatException e) {
	            // Handle parsing errors if needed
	            System.err.println("Error parsing the float: " + e.getMessage());
	        }
	    }
	    return this.observableList;
	}

	@Override
	public String toString() {

		for (Float container : observableList) {
			this.observableList.add(container);
		}
		return this.observableList.toString();
	}
}
