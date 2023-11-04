package com.openjfx.component;

import javafx.scene.control.CheckBox;

public class Selector {
	// Method receives varargs from SecondaryController Class and checks if Check Boxes -
	// are being checked alreayd, if so, the other check boxes wil be disabled.
	public void toEnableCheckbox(boolean isTicked, CheckBox ... value) {
    	// Handles the check box logic, method receives varargs.
    	for (CheckBox checkBox : value) {
			checkBox.setDisable(isTicked);
		}
    }
	// Clear the checks on the check boxes if called.
	public void clearCheckBox(CheckBox ... value) {
		for (CheckBox checkBox : value) {
			checkBox.setSelected(false);
			checkBox.setDisable(false);
		}
	}
}
