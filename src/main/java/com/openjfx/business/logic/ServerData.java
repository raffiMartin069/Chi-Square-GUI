package com.openjfx.business.logic;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerData {
	private double chiSquare;
	private double dof;
	private List<Double> expectedValue;
	private String image64bitString;
	private double pValue;
	private String hypothesisResult;
	
	
	public ServerData() {}
	public ServerData(double chiSquare, double dof, List<Double> expectedVaList, double pValue, String image64bitString, String hypothesisResult) {
		this.image64bitString = image64bitString;
		this.chiSquare = chiSquare;
		this.dof = dof;
		this.expectedValue = expectedVaList;
		this.pValue = pValue;
	}
	
	
	public String getHypothesisResult() {
		return hypothesisResult;
	}
	public void setHypothesisResult(String hypothesisResult) {
		this.hypothesisResult = hypothesisResult;
	}
	public String getImage64bitString() {
		return image64bitString;
	}
	public void setImage64bitString(String image64bitString) {		
		this.image64bitString = image64bitString;
	}
	public double getChiSquare() {
		return chiSquare;
	}
	public void setChiSquare(double chiSquare) {
		this.chiSquare = chiSquare;
	}
	public double getDof() {
		return dof;
	}
	public void setDof(double dof) {
		this.dof = dof;
	}
	public List<Double> getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(List<Double> expectedValue) {
		this.expectedValue = expectedValue;
	}
	public double getpValue() {
		return pValue;
	}
	public void setpValue(double pValue) {
		this.pValue = pValue;
	}
	public Image imgDecoder() {
		try {
			byte[] decodeImg = Base64.getDecoder().decode(this.image64bitString);
			var img = new Image(new ByteArrayInputStream(decodeImg));
			return img;			
		} catch (IllegalArgumentException e) {
			System.out.println("Error occured at Server Data Class, please check imgDecoder Method. Invalid character input found.");
		} catch (NullPointerException e) {
			System.out.println("Error occured at Server Data Class, please check imgDecoder Method");
		}
		return null;
	}
	@Override
	public String toString() {
		return "ServerData [chiSquare=" + chiSquare + ", dof=" + dof + ", expectedValue=" + expectedValue + ", pValue="
				+ pValue + "]";
	}
	
}
