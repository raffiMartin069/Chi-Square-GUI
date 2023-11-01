package com.openjfx.component;

import javafx.scene.control.CheckBox;

public class Selector {

	public void toEnableCheckbox(boolean isTicked, CheckBox ... value) {
    	// Handles the checkbox logic, method recieves varargs.
    	for (CheckBox checkBox : value) {
			checkBox.setDisable(isTicked);
		}
    }
	public void clearCheckBox(CheckBox ... value) {
		for (CheckBox checkBox : value) {
			checkBox.setSelected(false);
			checkBox.setDisable(false);
		}
	}
}
