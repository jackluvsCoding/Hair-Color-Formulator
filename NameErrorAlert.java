/** 
* Program Name: ICS 141-02 FALL 2015: Final Project - MyColorGUI
* Author: Jack Hysell
* Date: 12/2/15
* Modified: 12/7/15
* (Added exception handling support for the tfGuestName field in the MyColorGUI.java class to limit
* user input and prevent malicious attacks. This new addition features an additional class called
* NameErrorAlert.java. An invalid entry prompts this new class to display an alert box and the input 
* is immediately cleared from the field.) 
* 
* Description:
* This program is designed to create a hair color formula for the user based on individual input. 
* Formulas include going lighter with color, high-lift tint, and "enlightener" (or bleach), as well as, 
* formulas for going darker as well. This program can account for many nuances such as the specific 
* developers needed, processing time, grams of pigment, grey coverage, fillers, and pre-lightening. 
* 
* This particular class is called and displayed by the MyColorGUI class when the tfGuestName field is 
* activated, and, it is determined by the guestNameInteractiveDisplay() method that the entry is invalid.
* The tfGuestName field will be immediately cleared and the user will be advised to re-enter their name
* upon closing the window.  
* 
**/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NameErrorAlert {

	public static void display(String title, String message) {
		// Create a stage
		Stage window = new Stage();

		// Create a label to place an error message in
		Label label = new Label();
		label.setText(message);

		// Create a button to close the window
		Button closeButton = new Button("Close and Re-enter Name");
		closeButton.setOnAction(e -> window.close());

		// Create a VBox, format it, and place label and closeButton
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		/**
		 * Create a scene and place it in a stage - require user to interact
		 * with window by clicking the button to close it before they can return
		 * to the previous window.
		 */
		Scene scene = new Scene(layout, 400, 200);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setScene(scene);
		window.showAndWait();
	}
}