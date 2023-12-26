package com.openjfx.chisquare;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import com.openjfx.animator.Animate;
import com.openjfx.business.logic.ChaiTest;
import com.openjfx.component.Selector;
import com.openjfx.flask.Fetch;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class SecondaryController2 implements Initializable{

    @FXML
    private CheckBox bothValue_box;

    @FXML
    private ImageView toHome;

    @FXML
    private TextField df_field;

    @FXML
    private TextArea observable_input;

    @FXML
    private Button generate_btn;

    @FXML
    private ImageView clear_inputs;

    @FXML
    private Label chi_label;

    @FXML
    private CheckBox criticalValue_box;
    
    @FXML
    private ImageView pValue_img;

    @FXML
    private CheckBox pValue_box;

    @FXML
    private Label page_title;

    @FXML
    private Label error_msg;
    /*============================
     * 	Labels as for final Result
     * ===========================*/
    @FXML
    private Label dof_result;
    
    @FXML
    private Label chi_result;
    
    @FXML
    private Label av_result;
    
    @FXML
    private Label hypothesis;
    
    @FXML
    private Hyperlink pdf_result;


    private Animate animator = new Animate();

    private static Selector selector = new Selector();

    private static ChaiTest chaiTest = new ChaiTest();

	// Passes varargs to Selector Class for evaluation if Check Boxes are checked.
    // Disables all other Check Boxes if found that a Check Box is already cheked.
    
    @Deprecated
    @FXML
    void toBothValues(ActionEvent event) {
    	selector.toEnableCheckbox(bothValue_box.isSelected(),pValue_box, criticalValue_box);
    	String tick_Control = bothValue_box.isSelected() ? chaiTest.bothCheckbox() : "";
    	chaiTest.setGraphType("Both");
    	System.out.println(tick_Control);
    }
    
    @Deprecated
    @FXML
    void toCriticalValue(ActionEvent event) {
    	selector.toEnableCheckbox(criticalValue_box.isSelected(),pValue_box, bothValue_box);
    	String tick_Control = criticalValue_box.isSelected() ? chaiTest.criticalValueCheckbox() : "";
    	chaiTest.setGraphType("Critical Value");
    	System.out.println(tick_Control);
    }
    
    @Deprecated
    @FXML
    void toPValue(ActionEvent event) {
    	selector.toEnableCheckbox(pValue_box.isSelected(),bothValue_box, criticalValue_box);
    	String tick_Control = pValue_box.isSelected() ? chaiTest.pvalueCheckbox() : "";
    	chaiTest.setGraphType("Pvalue");
    	System.out.println(tick_Control);
    }
    // Initializes data to be used.
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	chaiTest.setStdError(error_msg);
    	chaiTest.inputCharDisable(df_field);
    	chaiTest.inputCharDisable(observable_input);
    	disabledComponents();
    	setupFieldListeners();
	}
    // Handles button even, filters the keyboard characters, gets the user input.
    @FXML
    void generateChi(ActionEvent event) throws Exception {
        chaiTest.inputCharDisable(observable_input);
        chaiTest.setDfString(df_field.getText().toString());
        String inputText = observable_input.getText();
        float[][] test = chaiTest.parseTextAreaInput(inputText);
        
        // debugger
        for(float[] testing : test) {
            for(float disp : testing) {
                System.out.print(disp+", ");
            }
            System.out.println();
        }
        chaiTest.sendData();
        SetResult();
    }
    @FXML
    void open_browser(ActionEvent event) {
    	// Open a document to download
    }
    private void disabledComponents() {
    	pValue_box.setDisable(true);
    	criticalValue_box.setDisable(true);
    	bothValue_box.setDisable(true);
    	generate_btn.setDisable(true);
    }
    private void buttonChecker() {
        if (df_field.getText().isEmpty() || observable_input.getText().isEmpty()) {
            generate_btn.setDisable(true);
        } else {
            generate_btn.setDisable(false);
        }
    }
    private void setupFieldListeners() {
        // Add a listener for df_field
        df_field.textProperty().addListener((observable, oldValue, newValue) -> {
            buttonChecker();
        });

        // Add a listener for observable_input
        observable_input.textProperty().addListener((observable, oldValue, newValue) -> {
            buttonChecker();
        });
    }
    private void SetResult() {
    	var finalData = new Fetch();
    	try {
			finalData.initializeCall();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	chi_result.setText("Chi Square Test: "+finalData.ChiResult());
    	dof_result.setText("Degree of Freedom: "+finalData.DofResult());
    	av_result.setText("P-Value: "+finalData.PvalueResult());
    	hypothesis.setText("Hypothesis Result: "+finalData.hypothesiString());
    	pValue_img.setImage(finalData.matplotlibView());
    	pdf_result.setText("Click here to open PDF.");
    	
    	File pdf = finalData.pdfResult();
    	pdf_result.setOnAction(event -> {
            if (pdf != null) {
                downloadPdf(pdf);
            } else {
                // Handle the case when the PDF file is null (optional)
                System.out.println("PDF file is null");
            }
        });
    }

    private void downloadPdf(File pdfFile) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            savePdfToFile(pdfFile, selectedFile);
        }
    }

    private void savePdfToFile(File sourceFile, File destinationFile) {
        try (FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            Files.copy(sourceFile.toPath(), outputStream);
            System.out.println("File saved successfully: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@FXML
    void toClearInput(MouseEvent event) {
		// Clears all fields.
		animator.setImgImageView(clear_inputs);
		animator.ThreeSixtyAnimation();
		observable_input.clear();
		df_field.clear();
		error_msg.setText(null);
		// Feature is deprecated, for the meantime I comment the check boxes.
		//selector.clearCheckBox(bothValue_box, criticalValue_box, pValue_box);
		dof_result.setText(null);
		chi_result.setText(null);
		av_result.setText(null);
		hypothesis.setText(null);
		pValue_img.setImage(null);
		pdf_result.setText(null);
    }
	@FXML
    void goHome(MouseEvent e) throws IOException {
		App.setRoot("primary");
    }
}