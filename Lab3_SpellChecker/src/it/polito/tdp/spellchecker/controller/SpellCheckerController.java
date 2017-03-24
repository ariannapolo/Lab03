/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;

public class SpellCheckerController {
	Dictionary d;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="lblResult"
    private Label lblResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="lblTime"
    private Label lblTime; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtResult.clear();
    	lblResult.setText("");
        lblTime.setText("");
         

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	Long t1 = System.nanoTime();
    	d.loadDictionary(cmbLanguage.getValue());
    	int num = 0;
    	String[] inputArray =txtInput.getText().toLowerCase().replaceAll("[\\p{Punct}]","").split(" ");
    	List<String> input = new ArrayList<String>(Arrays.asList(inputArray));
    	//List<RichWord> l = d.spellCheckText(input);
    	List<RichWord> l = d.spellCheckTextDicotomy(input);
    	for(RichWord r : l){
    		if(!r.isCorretta()){
    			txtResult.appendText(r.getParola()+"\n");
    			num++;
    		}
    		}
    	lblResult.setText("The text contains "+num+" errors");
    	Long t2 = System.nanoTime();
    	lblTime.setText("Spell Check completed in "+(t2-t1)/1e9+" seconds");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblResult != null : "fx:id=\"lblResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
        //combo box
        cmbLanguage.getItems().addAll("English","Italian");//metto <String> all'inizio
        if(cmbLanguage.getItems().size()>0)
        	cmbLanguage.setValue(cmbLanguage.getItems().get(0));
        lblResult.setText("");
        lblTime.setText("");
        

    }
    
    public void setModel(Dictionary model) {
		this.d = model;
	}
}
