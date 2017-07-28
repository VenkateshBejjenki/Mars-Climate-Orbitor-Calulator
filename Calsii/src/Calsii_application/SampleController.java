package Calsii_application;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * <p> Title: Sample Controller class </p>
 * 
 * <p> Description: Controller class for the calculator applciation </p>
 * 
 * <p> Copyright: Venkatesh Bejjenki  © 2017 </p>
 * 
 * @author Venkatesh Bejjenki 
 * 
 * @version 1.00	Initial baseline
 * 
 */

public class SampleController {

	// Values used to parameterize the graphical user interface.
	private char activeTextField ;										 
	private boolean rad=true;
	private int historyPointer=0;
	private String finalResult="";
	private String optionMeasurement="";
	private String ans="0";
	private String value1="",value2="";
	private String operation="+";
	private ArrayList<String> history = new ArrayList<String>(); 
	// Object creation for HelperUNumber class
	HelperUNumber help = new HelperUNumber();
	
	//Declaration of all the buttons, textfields, textareas, labels ,etc.
	@FXML
	private Button mul;
	@FXML
	private Button div;
	@FXML
	private Button mod;
	@FXML
	private Button abs;
	@FXML
	private Button nsquare;
	@FXML
	private Button root;
	@FXML
	private Button up;
	@FXML
	private Button down;
	@FXML
	private Button ansButton;
	@FXML
	private Button openbrace;
	@FXML
	private Button closebrace;

	@FXML
	private TextField operand1;
	@FXML
	private TextField operand2;

	@FXML
	private TextField answer;
	@FXML
	private ComboBox<String> measurements;

	@FXML
	private TextArea previous;
	@FXML
	private ComboBox<String> operand1measure;
	@FXML
	private ComboBox<String> operand2measure; 
	@FXML
	private ComboBox<String> resultmeasure;
	@FXML
	private Label operand1error;
	@FXML
	private Label operand2error; 
	@FXML
	private Label resultlabel;
	

	//Creating String array of all available conversions
	private String[] measure = {"Length","Mass","Time","Current"}; 
	private ObservableList<String> listMeasure = (ObservableList<String>) FXCollections.observableArrayList(measure);

	private String[] lengthMeasure={"Miles","Kilometers","Meters","Feets","Inches","Centimeters"};
	private ObservableList<String> listLengthMeasure = (ObservableList<String>) FXCollections.observableArrayList(lengthMeasure);

	private String[] massMeasure={"Tonnes","Kilograms","Pounds","Grams"};
	private ObservableList<String> listMassMeasure = (ObservableList<String>) FXCollections.observableArrayList(massMeasure);

	private String[] timeMeasure={"Years","Days","Hours","Minutes","Seconds"};
	private ObservableList<String> listTimeMeasure = (ObservableList<String>) FXCollections.observableArrayList(timeMeasure);

	private String[] currentMeasure={"Ampere","Milliamperes"};
	private ObservableList<String> listCurrentMeasure = (ObservableList<String>) FXCollections.observableArrayList(currentMeasure);

	//Initializing the calculator  
	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	@FXML
	public void initialize() {
		answer.setText("");												//initially the answer field is set to empty
		previous.setText("");											//initially the history field is set to empty
		measurements.setItems(listMeasure);								//initializing the Measurement combobox
		measurements.getSelectionModel().selectFirst();					//initializing the measurement combobox to the first option

		resultmeasure.setItems(listLengthMeasure);						//initializing the Result Measure combobox
		resultmeasure.getSelectionModel().selectFirst();				//initializing the Result Measure combobox to the first option
		operand1measure.setItems(listLengthMeasure);					//initializing the operand1measure combobox
		operand1measure.getSelectionModel().selectFirst();				//initializing the operand1measure combobox to the first option
		operand2measure.setItems(listLengthMeasure);					//initializing the operand2measure combobox
		operand2measure.getSelectionModel().selectFirst();				//initializing the operand2measure combobox to the first option

		resultlabel.setText("Result");									//initializing the result label to empty
		previous.setDisable(true);										//initially enabling the previous/ history text area
		activeTextField='1';											// Setting activeTextField to operand1 text area
		buttonDisable(true);											//initially disabling few buttons   
		
	}

	//Combobox enabling the measurements
	/**********
	 * This method enables all the comboboxes based on the measurement combobox. These assignments
	 * determine the change in the subsequent comboboxes.
	 */
	@FXML
	private void selectMeasure() {

		optionMeasurement = measurements.getValue();
		// if selected option is Length
		// Setting the associated comboboxes 
		
		if (optionMeasurement.equals("Length")) {
			resultmeasure.setItems(listLengthMeasure);
			resultmeasure.getSelectionModel().selectFirst();
			operand1measure.setItems(listLengthMeasure);
			operand1measure.getSelectionModel().selectFirst();
			operand2measure.setItems(listLengthMeasure);
			operand2measure.getSelectionModel().selectFirst();

		}
		// if selected option is Mass
		// Setting the associated comboboxes 
		else if (optionMeasurement.equals("Mass")) {
			resultmeasure.setItems(listMassMeasure);
			resultmeasure.getSelectionModel().selectFirst();
			operand1measure.setItems(listMassMeasure);
			operand1measure.getSelectionModel().selectFirst();
			operand2measure.setItems(listMassMeasure);
			operand2measure.getSelectionModel().selectFirst();
		}
		// if selected option is Time
		// Setting the associated comboboxes 
		else if (optionMeasurement.equals("Time")) {
			resultmeasure.setItems(listTimeMeasure);
			resultmeasure.getSelectionModel().selectFirst();
			operand1measure.setItems(listTimeMeasure);
			operand1measure.getSelectionModel().selectFirst();
			operand2measure.setItems(listTimeMeasure);
			operand2measure.getSelectionModel().selectFirst();
		}
		// if selected option is Current
		// Setting the associated comboboxes 
		else if (optionMeasurement.equals("Current")) {
			resultmeasure.setItems(listCurrentMeasure);
			resultmeasure.getSelectionModel().selectFirst();
			operand1measure.setItems(listCurrentMeasure);
			operand1measure.getSelectionModel().selectFirst();
			operand2measure.setItems(listCurrentMeasure);
			operand2measure.getSelectionModel().selectFirst();
		}

	}

	//Method to invoke the the getAnswer() method upon action of this method 
	@FXML
	private void selectResultMeasure() {

		getAnswer();
	}

	/**********
	 * This method is used to enable the basic mode or to disable the basic mode. These assignments
	 * determine the change in the corresponding buttons and fields.
	 */
	
	@FXML
	private void onBasic() {
		if(rad==true){
			rad=false;
			buttonDisable(false);										// Calling the buttonDisable method to enable buttons
			resultlabel.setText("Evaluater");							// Setting the label for the text field
			previous.setDisable(false);									// To Disable the previous text area 	
			clearAll();													// Calling clearAll() method 
			activeTextField='r';										// Setting activeTextField to result
		}
		else{
			rad=true;
			buttonDisable(true);										// Calling the buttonDisable method to disable buttons
			resultlabel.setText("Result");								//Setting the label for the text field					
			previous.setDisable(true);									// To Enable the previous text area
			clearAll();													// Calling clearAll() method
			activeTextField='1';										// Setting activeTextField to operand1 text area
		}

	}


	/**********
	 * This method is used to enable or disable the below listed buttons, textfields and comboboxes. These assignments
	 * determine the change in the corresponding buttons and fields.
	 */
	public void buttonDisable(boolean flag) {

		mul.setDisable(flag);
		div.setDisable(flag);
		mod.setDisable(flag);  
		abs.setDisable(flag);  
		nsquare.setDisable(flag);
		root.setDisable(flag); 
		up.setDisable(flag); 
		down.setDisable(flag);  
		openbrace.setDisable(flag);
		closebrace.setDisable(flag);
		operand1.setDisable(!flag);
		operand2.setDisable(!flag);
		operand1measure.setDisable(!flag);
		operand2measure.setDisable(!flag);
		resultmeasure.setDisable(!flag);
		measurements.setDisable(!flag);

	}

	//Active field is set to '1' when the user clicks on the operand 1 text area 
	@FXML
	void operand1Clicked() {
		activeTextField='1';
	}
	
	//Active field is set to '2' when the user clicks on the operand 2 text area 
	@FXML
	void operand2Clicked() {
		activeTextField='2';
	}

	//Active field is set to 'r' when the user clicks on the result text area 

	@FXML
	void resultClicked() {
		activeTextField='r';
	}



	//Sets the digit on to the result field when clicked

	/**********
	 * This method is used to set the clicked button source to the active text field. These assignments
	 * determine the change in the corresponding active text fields.
	 */

	@FXML
	public void setDigit(ActionEvent event) {
		//To store the clicked button source in the string 
		String digit = ((Button) event.getSource()).getText();
		//Storing in the global variable finalResult with the existing appended text
		finalResult = answer.getText()+digit;
		// To check whether the clicked button is any one of the following operators and radio button status
		if((digit.equals("+") || digit.equals("-") || digit.equals("*") || digit.equals("/")) && rad==true){
			//Storing the operator in the variable operation
			operation=digit;
			getAnswer();												// Calling the getAnswer method.
			return;
		}
		// Condition for activating the operand 1 text field
		if(activeTextField=='1'){								
			System.out.println("focus 1");
			operand1.appendText(finalResult);
			errorCheck("operand1");										// Calling the errorCheck()- method
		}
		// Condition for activating the operand 2 text field
		else if(activeTextField=='2'){
			operand2.appendText(finalResult);
			errorCheck("operand2");										// Calling the errorCheck()- method
		}
		// Condition for activating the expression evaluation text field
		else if(activeTextField=='r'){
			answer.setText(finalResult);

		}
	}
	/**********
	 * This method is used to check for error when user enters input from keyboard. These assignments
	 * determine the error message population.
	 */

	@FXML
	public void keyboardAction(){
		if(activeTextField=='1'){	
			errorCheck("operand1");										// Calling the errorCheck()- method
		}
		else if(activeTextField=='2'){
			errorCheck("operand2");										// Calling the errorCheck()- method
		}

	}

	/**********
	 * This method is used to check for error by parsing string with the Floating Point Recognizer class. These assignments
	 * determine the error message in return.
	 */

	public void errorCheck(String labelSetter){

		System.out.println("entered labelsetter "+labelSetter );
		if(labelSetter.equals("operand1")){
			if (!FPR.floatingPointRecognizer(operand1.getText()).equals("")) {						// See if the next token is a valid by fetching from FPR
				operand1error.setText(FPR.floatingPointRecognizer(operand1.getText())); 			// String value.  If not, signal there								
			}else 
				operand1error.setText("");
		}
		else{
			if(!FPR.floatingPointRecognizer(operand2.getText()).equals("")){
				operand2error.setText(FPR.floatingPointRecognizer(operand2.getText())); 			// String value.  If not, signal there
			}
			else
				operand2error.setText("");

		}

	}

	//Clears all the fields in the calculator
	/**********
	 * This method is used to clear all the text fields . These assignments
	 * determine the reset option of the calculator applciation.
	 */

	@FXML
	private void clearAll() {
		finalResult="";
		operand1.setText(finalResult);										// Setting operand 1 text field to empty
		operand2.setText(finalResult);										// Setting operand 2 text field to empty
		answer.setText(finalResult);										// Setting answer text field to empty
		operand1error.setText(finalResult);									// Setting operand1error text field to empty
		operand1error.setText(finalResult);									// Setting operand2error text field to empty
		//previous.setText(finalResult);
	}

	// Sets the mod text to the answer field
	
	/**********
	 * This method is used to perform the modulus operation, it take only one operand as input. These assignments
	 * basically determine the remainder value.
	 */
	@FXML
	private void modulus() {
		
		finalResult=answer.getText()+"mod";									// Appending 'mod' to the input expression
		answer.setText(finalResult);										// Setting it to the answer text field

	}

	// TO do backspace of the text entered

	/**********
	 * This method is used to delete the last entered character. These assignments
	 * basically perform the backspace key functionality
	 */
	@FXML
	private void backspace(ActionEvent event) {

		if(activeTextField=='1'){
			System.out.println("focus 1");
			String op1=operand1.getText();
			operand1.setText(op1.substring(0, op1.length()-1));				// Removing last character by using substring method 
			errorCheck("operand1");
		}
		else if(activeTextField=='2'){
			String op2=operand2.getText();
			operand1.setText(op2.substring(0, op2.length()-1));				// Removing last character by using substring method
			errorCheck("operand2");
		}
		else if(activeTextField=='r'){
			finalResult = finalResult.substring(0, finalResult.length()-1);	// Removing last character by using substring method
			answer.setText(finalResult);
		}

	}

	//To set the result

	/**********
	 * This method is used to determine the result based on the radio button followed by the units entered or the expression
	 * entered. These assignments basically perform the result computation functionality
	 */
	@FXML
	private void getAnswer(){
		try{
		if(rad==false){
			finalResult = answer.getText();
			String former=finalResult;
			if (!finalResult.equals("")) {
				ExpressionEvaluation exp = new ExpressionEvaluation();		// Creating an object for the Expression Evaluation class
				//previous.setText(previous.getText()+"\n"+finalResult.replaceAll("\\s",""));
				
				history.add(finalResult);									// Storing the previous operations in the history list
				if(finalResult.contains("^")){								// If it contains '^' symbol 
					
					finalResult = exp.power(finalResult);
					finalResult = help.convertE(finalResult);
				}
				else if(finalResult.contains("mod")){						// If it contains 'mod' symbol
					finalResult = exp.mod(finalResult);
					finalResult = help.convertE(finalResult);

				}
				else{
					String re= exp.evaluate(finalResult);					// Calling the evaluate method 
					System.out.println("re="+re);
					if(re.equals("Invalid input")||re.equals("+0.99999999999999999999E+999999")){
						answer.setText("Invalid input");
						return;
					}
						
					finalResult = help.convertE(re);
				}
				previous.setText(previous.getText()+"\n"+former.replaceAll("\\s",""));
				previous.appendText(" = "+finalResult);						// Storing the previous operation's result
				historyPointer=history.size();
				answer.setText(finalResult);								// Setting the result text area with 'finalResult'
				ans=finalResult;
			}
		}
		// Basically it enters into else part when the radio button is on
		else {
			
			//Storing all the values into different strings for future use
			value1=operand1.getText();
			value2=operand2.getText();
			String measure = measurements.getValue();
			String unit1 = operand1measure.getValue();
			String unit2 = operand2measure.getValue();
			String resultUnit=resultmeasure.getValue();
	
			if(!value1.equals("") && !value2.equals("")){					//Checking whether both the operands are filled or not
				
				Measurements u=new Measurements();							// Object creation for the Measurement class
				UNumber un=u.operationUnits(measure,unit1,value1,unit2,value2,resultUnit,operation);  // Calling the operationUnits() methods
				answer.setText(help.roundTo(un));							// Setting the rounded value of the output 
				finalResult = answer.getText();
				if (!finalResult.equals("")) {
					history.add(finalResult);								// Storing the previous answer values
				}
			}

		}
		}
		catch (Exception e) {
			answer.setText("Invalid input");
		}
	}
	
	/**********
	 * This method sets the input to the previously performed computation.
	 * These assignments basically fetch the previous input from the history list 
	 */
	
	@FXML
	private	void upAction(ActionEvent event) {
		if(historyPointer>=1)
			answer.setText(history.get(--historyPointer));
			//resulterror.setText(finalResult);									// Setting resulterror text field to empty
	}
	
	/**********
	 * This method sets the input to the recently performed computation.
	 * These assignments basically fetch the next immediate input from the history list 
	 */
	
	@FXML
	private void downAction(ActionEvent event) {
		if(historyPointer<history.size())
			answer.setText(history.get(historyPointer++));
			//resulterror.setText(finalResult);									// Setting resulterror text field to empty
	}
	/**********
	 * This method is used to store the final result of the previous computation. This assignments basically perform 
	 * the local storage of the previously stores 
	 */
	
	@FXML
	private void ansAction(ActionEvent event) {
		//		String s = ((Button) event.getSource()).getText();
		if(answer.getText().equals("Invalid input")){
			answer.setText(ans);
			return;
		}
		if(ans.charAt(0)=='+')
			ans=ans.substring(1);
		
		if(history.size()>1)
			finalResult=(answer.getText())+ans;
		else 
			finalResult=answer.getText()+ans;
		
		answer.setText(finalResult);
		
		
	}
	
	/**********
	 * This method is used to determine the result when the user enters clicks on '=' .
	 * These assignments basically perform the action of getting the computed answer
	 */
	@FXML
	private void enterResult() {
		getAnswer();
	}

	/**********
	 * This method is used to determine the n square operation.
	 */
	
	@FXML
	void nsquareAction() {
		finalResult=answer.getText()+"^";
		answer.setText(finalResult);
	}

	//To implement the root of a number
	/**********
	 * This method is used to determine the root of the inputed number.
	 * This method basically perform the root operation and returns the root of the expression
	 */
	
	@FXML
	void rootAction() {
		
		SquareRoot sqrt = new SquareRoot();									// Creating an object for the Square root class
		finalResult = sqrt.squareRoot(answer.getText());					// Fetching the root of the entered inputr
		
		finalResult=help.convertE(finalResult);								// To get formated answer
		answer.setText(finalResult);	
	}

	/**********
	 * This method is used to return the absolute value of the string .
	 * These assignments basically remove the negative or positive sign and returns just value 
	 */
	
	@FXML
	void absAction(){
		finalResult = answer.getText();										// Storing the text from answer field text to finalResult variable 
		history.add("|"+finalResult+"|");
		// To set the previous operations to history
		previous.setText(previous.getText()+"\n"+"|"+finalResult.replaceAll("\\s","")+"|");
		if(finalResult.charAt(0)=='+')
			answer.setText(finalResult.substring(1));						// Setting the absolute value to answer field
		else if(finalResult.charAt(0)=='-')
			answer.setText(finalResult.substring(1));						// Setting the absolute value to answer field
		else 												
			answer.setText(finalResult);									// Setting the absolute value to answer field
		previous.appendText(" = "+answer.getText());

	}


}
