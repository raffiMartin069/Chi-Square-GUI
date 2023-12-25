package com.openjfx.flask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.openjfx.business.logic.ChaiTest;
import com.openjfx.business.logic.ServerData;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class Fetch {
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Gson GSON = new Gson();
    private static final ServerData SERVERDATA = new ServerData();
    
    public String fetchData(String url) throws IOException {
        var request = new Request.Builder().url(url).build();

        try {
        	var response = CLIENT.newCall(request).execute();
        	return response.body().string();
        }catch (ConnectException e) {
        	System.out.println("Error Information: "+e);
		}catch (NullPointerException e) {
			return "Input invalid";
		}
		return "Error at Fetch Class, check fetchData Method.";
    }

    public void initializeCall() throws IOException {
    	
            var urlString = "http://127.0.0.1:5000/get-data/";
            Fetch fetch = new Fetch();
            String jsonResponse = fetch.fetchData(urlString);
            try {
            // Parse JSON response using Gson
            JsonObject jsonObject = GSON.fromJson(jsonResponse, JsonObject.class);

            // Extract the "expected_value" array using Gson
            double[][] expectedValue = GSON.fromJson(jsonObject.get("expected_value"), double[][].class);

            List<Double> eVList = new ArrayList<Double>();
            
            // Example usage of the "expectedValue" array
            for (double[] row : expectedValue) {
                for (double value : row) {
                    //System.out.print(value + " ");
                	eVList.add(value);
                }
                System.out.println();
            }
         // Extract values from JSON and assign them to variables
            SERVERDATA.setImage64bitString(jsonObject.get("graph").getAsString());
            SERVERDATA.setChiSquare(jsonObject.get("chi_value").getAsDouble());
            SERVERDATA.setDof(jsonObject.get("degree_of_freedom").getAsDouble());
            SERVERDATA.setpValue(jsonObject.get("p_value").getAsDouble());
            SERVERDATA.setExpectedValue(eVList);
            SERVERDATA.setHypothesisResult(jsonObject.get("hypothesis").getAsString());
    	}catch (NullPointerException e) {
			var error_Handler = new ChaiTest();
			error_Handler.exceptionAlert();
		}catch (JsonParseException e) {
			System.out.println("Error Parsing Json, check Fetch Data class -> initializeCall method. ");
		}
    }
    // Retrieve each data using this methods.
    public Double ChiResult() {
    	return SERVERDATA.getChiSquare();
    }
    public Double DofResult() {
    	return SERVERDATA.getDof();
    }
    public Double PvalueResult() {
    	return SERVERDATA.getpValue();
    }
    public List<Double> exprectedValue() {
    	return SERVERDATA.getExpectedValue();
    }
    public Image matplotlibView() {
    	return SERVERDATA.imgDecoder();
    }
    public String hypothesiString() {
    	return SERVERDATA.getHypothesisResult();
    }
}
