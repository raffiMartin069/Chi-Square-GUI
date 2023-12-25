package com.openjfx.business.logic;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * IMPORTANT COMMENT:
 * 	Some methods are marked as deprecated. They can be used but are discouraged.
 * The methods are currently present and the GUI components (i.e. Check boxes) are 
 * are still present, they are can still be used but not functional. They will be
 * removed soon for cleaning purposes and improve delivery to end users. 
 * 
 * 
 * Deprecation Date: 12/19/2023
 * Programmer: Rafael D. Martinez
 * 
 * 
 * */ 
public class ChaiTest {
	// Handles the logic behind user interaction to input fields.
	// Used to set values that will initialize in the Secondary Class.
	// Process and Limits user interaction.
	// Implements error handling.
	private String valueString;
	private String dfString;
	
	private String graphType;
	private Label std_error;
	private float result[][];

	public ChaiTest() {}
	public ChaiTest(String valueString, String dfString,
			float dfValue, float observableValue, CheckBox checkBox_Value, float degreeofFreedom,
			Label std_error, float result[][], String graphType) {
		this.valueString = valueString;
		this.dfString = dfString;
		this.std_error = std_error;
		this.result = result;
		this.graphType = graphType;
	}
	@Deprecated
	public String getGraphType() {
		return graphType;
	}
	@Deprecated
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	// Handles initialization of variables in Secondary class.
	// Allows to manipulate data and be accessible for process handling.
	public String getValueString() {
		return valueString.toUpperCase();
	}
	
	public void exceptionAlert() {
		var alert = new Alert(AlertType.WARNING, "Please enter a valid input.");
		alert.setTitle("Invalid Input.");
		alert.headerTextProperty().setValue(null);
		alert.initStyle(StageStyle.UTILITY);
		alert.show();
	}
	public void setValueString(String valueString) {
		try {
			this.valueString = valueString;	
		}catch (NullPointerException e) {
			exceptionAlert();
		}
	}

	public String getDfString() {
		return dfString.toUpperCase();
	}

	public void setDfString(String dfString) {
		try {
			this.dfString = dfString;	
		}catch (NullPointerException e) {
			exceptionAlert();
		}
		
	}

	public Label getStdError() {
		return this.std_error;
	}
	// Uses to pass a standard error for the program.
	// It initializes the Label in Secondary Class.
	public void setStdError(Label std_error) {
		this.std_error = std_error;
	}
	// A place holder to be triggered when converted to JSON.
	// Handled in the server to provide necessary graph and results.
	@Deprecated
	public String bothCheckbox() {
		return "Both";
	}
	@Deprecated
	public String pvalueCheckbox() {
		return "P-Value";
	}
	@Deprecated
	public String criticalValueCheckbox() {
		return "Critical Value";
	}
	// Disables other sorts of characters.
	// Only allows numbers from 0 --> 9, dot, and comma.
	// Lambda expression taken from a interface.
	// KeyEvent.KEY_TYPED, event two parameters passed.
	// Filter set in the body of Lambda.
	public void inputCharDisable(TextArea a) {
		a.addEventFilter(KeyEvent.KEY_TYPED, event -> {
		    var character = event.getCharacter();
		    var pattern = "[0-9., ,\n]";
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
	
	// Map different results using Map class.
	@Override
	public String toString() {
	    Gson gson = new Gson();
	    Map<String, Object> map = new HashMap<>();
	    map.put("observable_value", this.result);
	    map.put("degree_of_freedom", this.dfString != null ? this.dfString.toUpperCase() : "N/A");
	    //map.put("graph_type", this.getGraphType());
	    return gson.toJson(map);
	}
	public void sendData() {
	    OkHttpClient client = new OkHttpClient();

	    MediaType JSON = MediaType.get("application/json; charset=utf-8");
	    String jsonString = this.toString();
	    System.out.println("Sending JSON: " + jsonString); // Print the JSON string
	    RequestBody body = RequestBody.create(jsonString, JSON);

	    Request request = new Request.Builder()
	    	    .url("http://127.0.0.1:5000/receive-data/")
	    	    .post(body)
	    	    .header("Content-Type", "application/json; charset=utf-8") // Explicitly set the header
	    	    .build();

	    try (Response response = client.newCall(request).execute()) {
	        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
	        System.out.println(response.body().string());
	    } catch (ConnectException e) {
			var alert = new Alert(AlertType.ERROR, "Something went wrong. Try again later.");
			alert.setTitle("Unable to connect.");
			alert.headerTextProperty().setValue(null);
			alert.initStyle(StageStyle.UTILITY);
			alert.show();
		} catch (NullPointerException e) {
			exceptionAlert();
		}catch (IOException e) {
	        e.printStackTrace();
	    } 
	}

}
