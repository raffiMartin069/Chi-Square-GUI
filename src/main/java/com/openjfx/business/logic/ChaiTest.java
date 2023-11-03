package com.openjfx.business.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;

public class ChaiTest {
	private String valueString;
	private String dfString;
	private CheckBox checkBox_Value;
	private float dfValue;
	private float observableValue;
	private List<Float> observableList;


	public ChaiTest() {}

	public ChaiTest(String valueString, String dfString,
			float dfValue, float observableValue, CheckBox checkBox_Value) {
		this.valueString = valueString;
		this.observableValue = observableValue;
		this.dfString = dfString;
		this.dfValue = dfValue;
		this.checkBox_Value = checkBox_Value;
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
	
	@Override
	public String toString() {

		for (Float container : observableList) {
			this.observableList.add(container);
		}
		return this.observableList.toString();
	}
}
