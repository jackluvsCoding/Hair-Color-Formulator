
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
* This particular file, "MyColorGUI", creates the user interface aspect of this program. Twelve 
* additional methods at the end of this GUI were created to add an interactive element. Each method
* contains specific responses to various input to guide the user through the program. 
* 
**/

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyColorGUI extends Application {

	Stage window;
	Button btnFormulate, btnReset;
	ComboBox<Integer> cbNaturalLevel, cbDesiredLevelLighter, cbDesiredLevelDarker;
	ComboBox<String> cbDesiredTone;
	TextField tfGuestName = new TextField();
	TextArea taDisplayFormula = new TextArea();
	RadioButton rbGreyCoverYes = new RadioButton("Yes");
	RadioButton rbGreyCoverNo = new RadioButton("No");
	RadioButton rbVirginHairYes = new RadioButton("Yes");
	RadioButton rbVirginHairNo = new RadioButton("No");
	RadioButton rbLighter = new RadioButton("Lighter");
	RadioButton rbDarker = new RadioButton("Darker");
	ToggleGroup greyCoverYN, virginHairYN, lighterDarker;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// Main Header Row 0
		Label mainTitle = new Label("Create a New Color Formula!");
		mainTitle.setMinWidth(48);
		mainTitle.setMaxWidth(2000);
		mainTitle.setPadding(new Insets(8));
		mainTitle.setAlignment(Pos.CENTER);
		mainTitle.setStyle(
				"-fx-border-color: black; -fx-font-size: 32px; -fx-background-color: DarkGray; -fx-text-fill: NAVY;");

		// Create Row 1 with input for guestName and naturalLevel
		Label nameLabel = new Label("Guest Name:");
		nameLabel.setStyle("-fx-Font-Weight: BOLD; -fx-font-size: 14px");
		tfGuestName = new TextField();
		tfGuestName.setPromptText("Please enter your guest's name: ");
		tfGuestName.setOnAction(e -> guestNameInteractiveDisplay());

		cbNaturalLevel = new ComboBox<>();
		cbNaturalLevel.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbNaturalLevel.setPromptText("What is your natural level? ");
		cbNaturalLevel.setOnAction(e -> naturalLevelInteractiveDisplay());

		// Place guestName and naturalLevel fields in Row 1 vBox
		VBox vbGeneralInfo = new VBox(20, nameLabel, tfGuestName, cbNaturalLevel);
		vbGeneralInfo.setPadding(new Insets(10));
		vbGeneralInfo.setStyle("-fx-border-color: black; -fx-background-color: lightblue; -fx-text-fill: white;");

		// Create Row 2, Column 0 Going Lighter with desired level and virgin
		// hair input
		rbLighter = new RadioButton("Select this button to go lighter!");
		rbLighter.setOnAction(e -> rbLighterInteractiveDisplay());

		cbDesiredLevelLighter = new ComboBox<>();
		cbDesiredLevelLighter.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbDesiredLevelLighter.setPromptText("What is your desired level? ");
		cbDesiredLevelLighter.setOnAction(e -> cbDesiredLighterInteractiveDisplay());

		rbVirginHairYes = new RadioButton("Virgin hair");
		rbVirginHairYes.setOnAction(e -> rbVirginYesInteractiveDisplay());
		rbVirginHairNo = new RadioButton("Previously Colored");
		rbVirginHairNo.setOnAction(e -> rbVirginNoInteractiveDisplay());

		// Set Radio Button toggle group for virgin hair yes/no
		virginHairYN = new ToggleGroup();
		rbVirginHairYes.setToggleGroup(virginHairYN);
		rbVirginHairNo.setToggleGroup(virginHairYN);

		// Place fields for going lighter in the Row 2, Column 0 vBox
		VBox lighterBox = new VBox(20, rbLighter, cbDesiredLevelLighter, rbVirginHairYes, rbVirginHairNo);
		lighterBox.setPadding(new Insets(10));
		lighterBox.setMaxWidth(300);
		lighterBox.setStyle("-fx-border-color: black; -fx-background-color: lightblue");

		// Create Row 2, Column 1 Going Darker with desired level, desired tone,
		// and grey cover yes/no
		rbDarker = new RadioButton("Select this button to go darker!");
		rbDarker.setOnAction(e -> rbDarkerInteractiveDisaply());

		cbDesiredLevelDarker = new ComboBox<>();
		cbDesiredLevelDarker.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbDesiredLevelDarker.setPromptText("What is your desired level? ");
		cbDesiredLevelDarker.setOnAction(e -> cbDesiredDarkerInteractiveDisplay());

		cbDesiredTone = new ComboBox<>();
		cbDesiredTone.getItems().addAll("Natural", "Gold", "Copper", "Red", "Red Violet", "Ash", "Violet Ash");
		cbDesiredTone.setPromptText("What is your desired tone? ");
		cbDesiredTone.setOnAction(e -> cbDesiredToneInteractiveDisplay());

		rbGreyCoverYes = new RadioButton("Grey coverage needed");
		rbGreyCoverYes.setOnAction(e -> rbGreyYesInteractiveDisplay());
		rbGreyCoverNo = new RadioButton("No grey coverage needed");
		rbGreyCoverNo.setOnAction(e -> rbGreyNoInteractiveDisplay());

		// Set Radio Button toggle group
		greyCoverYN = new ToggleGroup();
		rbGreyCoverYes.setToggleGroup(greyCoverYN);
		rbGreyCoverNo.setToggleGroup(greyCoverYN);
		greyCoverYN.getSelectedToggle();

		// Place fields for going darker in the Row 2 Column 1 vBox
		VBox darkerBox = new VBox(20, rbDarker, cbDesiredLevelDarker, cbDesiredTone, rbGreyCoverYes, rbGreyCoverNo);
		darkerBox.setPadding(new Insets(10));
		darkerBox.setMaxWidth(300);
		darkerBox.setStyle("-fx-border-color: black; -fx-background-color: lightblue");

		// Set toggle for lighterDarker
		lighterDarker = new ToggleGroup();
		rbLighter.setToggleGroup(lighterDarker);
		rbDarker.setToggleGroup(lighterDarker);

		// Create the Formulate button and action handler
		btnFormulate = new Button("Formulate My Color!");
		btnFormulate.setStyle("-fx-base: green;");
		btnFormulate.setOnAction(e -> {

			// Check tfGuestName
			isValidName();

			// Clear text area to make way for the incoming formula
			taDisplayFormula.clear();

			// Actions when a lighter result is desired
			if (rbLighter.isSelected()) {

				// Grab values to be passed into the constructor for lighter
				Boolean virginHair = rbVirginHairYes.isSelected();
				int i = cbNaturalLevel.getValue();
				int naturalLevel = i;
				int j = cbDesiredLevelLighter.getValue();
				int desiredLevel = j;

				// Create a goingLighter object
				MyLighterFormula goingLighter = new MyLighterFormula(naturalLevel, desiredLevel, virginHair);

				// Display formula
				taDisplayFormula.appendText("Alright, " + tfGuestName.getText() + " here you go - now get busy!\n"
						+ "...and don't forget to wear gloves this time!\n");
				taDisplayFormula.appendText("\n" + goingLighter.getTargetFormula());
			}

			// Actions when a darker result is desired
			else {

				// Grab values to be passed into the constructor for darker
				int i = cbNaturalLevel.getValue();
				int naturalLevel = i;
				int k = cbDesiredLevelDarker.getValue();
				int desiredLevel = k;
				String tone = cbDesiredTone.getValue();
				String desiredTone = tone;
				Boolean greyCoverage = rbGreyCoverYes.isSelected();

				// Create a goingDarker object
				MyDarkerFormula goingDarker = new MyDarkerFormula(naturalLevel, desiredLevel, desiredTone,
						greyCoverage);

				// Display formula
				taDisplayFormula.appendText("Alright, " + tfGuestName.getText() + " here you go - now get busy!\n"
						+ "...and don't forget to wear gloves this time!\n");
				taDisplayFormula.appendText("\n" + goingDarker.getTargetFormula());
			}

		});
		// Create RESET button to clear fields and create a new formula
		btnReset = new Button("RESET: Clear Fields");
		btnReset.setStyle("-fx-base: RED;");
		btnReset.setOnAction(e -> {
			cbNaturalLevel.setValue(null);
			cbDesiredLevelLighter.setValue(null);
			cbDesiredLevelDarker.setValue(null);
			cbDesiredTone.setValue(null);
			rbLighter.setSelected(false);
			rbDarker.setSelected(false);
			rbGreyCoverYes.setSelected(false);
			rbGreyCoverNo.setSelected(false);
			rbVirginHairYes.setSelected(false);
			rbVirginHairNo.setSelected(false);
			tfGuestName.clear();
			taDisplayFormula.clear();
		});

		// Create grid pane and format it
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(8);
		pane.setHgap(8);

		// Add title to top row centered over 2 columns
		pane.addRow(0, mainTitle);
		GridPane.setColumnSpan(mainTitle, 2);
		GridPane.setHalignment(mainTitle, HPos.CENTER);

		// Place Row to get name and natural level
		pane.addRow(1, vbGeneralInfo);
		GridPane.setColumnSpan(vbGeneralInfo, 2);
		GridPane.setHalignment(vbGeneralInfo, HPos.LEFT);

		// Place row with 2 columns. Left column is going lighter, right column
		// is going darker
		pane.addRow(2, lighterBox, darkerBox);

		// Place the formulate button & reset button in the 3rd row
		pane.addRow(3, btnFormulate, btnReset);
		GridPane.setColumnSpan(btnFormulate, 1);
		GridPane.setColumnSpan(btnReset, 1);
		GridPane.setHalignment(btnFormulate, HPos.CENTER);
		GridPane.setHalignment(btnReset, HPos.CENTER);

		// Text box to display formula
		pane.addRow(4, taDisplayFormula);
		taDisplayFormula.setPrefHeight(300);
		GridPane.setColumnSpan(taDisplayFormula, 2);
		GridPane.setHalignment(taDisplayFormula, HPos.CENTER);
		taDisplayFormula.setEditable(false);

		// Create a scene and place it in a stage
		Scene scene = new Scene(pane, 520, 700);
		primaryStage.setTitle("My Color Formula");
		primaryStage.setResizable(false);
		primaryStage.show();

		window = primaryStage;
		window.setTitle("My Color Formula");
		window.setScene(scene);
		window.show();
	}

	/**
	 * This method checks any occurrence where the tfGuestName is used and
	 * ensures the input is valid. Should the input be invalid, an error message
	 * is displayed and the text field is cleared.
	 */
	public boolean isValidName() {

		String name = tfGuestName.getText();
		boolean isString = name.matches("[a-zA-Z]{1,20}+");
		if (isString == true) {
			return true;
		}

		else {
			tfGuestName.clear();
			NameErrorAlert.display("Name Error",
					"ERROR: THE NAME ENTERED IS INVALID\n\n\u2022Be sure it is less than 20 characters\n\u2022Use only letters"
							+ "\n\u2022Numbers and symbols are not accepted");
		}

		return null != null;
	}

	/**
	 * If the user hits enter after entering their name into the tfGuestName
	 * field, it will display a welcome text with some basic information on
	 * selecting your natural level providing that they have entered a valid
	 * name that is no greater than 20 characters in length and contains no
	 * numerical or symbolic values.
	 */
	public void guestNameInteractiveDisplay() {

		// Convert name from the text field and check if the entry is valid.
		String name = tfGuestName.getText();
		boolean isString = name.matches("[a-zA-Z]{1,20}+");
		if (isString == true) {
			// Display this if valid
			taDisplayFormula.appendText("Hello, " + tfGuestName.getText() + "! Please select your Natural Level...\n"
					+ "\nKeep in mind that a level 1 is virtually black, and a level 10 is light blonde.\n");
		}

		else {
			// Prompt Error Alert to display and clear text field
			tfGuestName.clear();
			NameErrorAlert.display("Name Error",
					"ERROR: THE NAME ENTERED IS INVALID\n\n\u2022Be sure it is less than 20 characters\n\u2022Use only letters"
							+ "\n\u2022Numbers and symbols are not accepted");
		}
	}

	/**
	 * As users choose a natural level, the program will respond with
	 * descriptive text about their selection to help guide the user experience.
	 * Levels 1 & 10 are the most important as you cannot go darker than a 1 or
	 * lighter than a 10 - this will inform the user of that and instruct them
	 * on what to choose next.
	 */
	public void naturalLevelInteractiveDisplay() {
		taDisplayFormula.appendText(
				"\n\u2022Your natural level is " + cbNaturalLevel.getValue() + ", " + levelDescription() + ".\n");

		if (cbNaturalLevel.getValue() == 1) {
			taDisplayFormula.appendText("\nYou can't get any darker than a natural level of 1. "
					+ "\n\u2022  Please select the button to go lighter!");
		}

		else if (cbNaturalLevel.getValue() == 2) {
			taDisplayFormula.appendText(
					"\nA natural level of 2 is about as dark as it gets, I'm guessing" + "\nyou want to go lighter?");
		}

		else if (cbNaturalLevel.getValue() == 3) {
			taDisplayFormula.appendText("\nWith a natural level of 3 you could easily darken it up, but"
					+ "\nsomething a litte lighter wouldn't look bad either! So which is it?");
		}

		else if (cbNaturalLevel.getValue() == 9) {
			taDisplayFormula.appendText("\nIt is said that blondes have all the fun, and with a starting level"
					+ "\nof 9, you're not far from platinum! Should we go a bit lighter, or "
					+ "\ndarken things up a bit?");
		}

		else if (cbNaturalLevel.getValue() == 10) {
			taDisplayFormula.appendText("\nA natural level of 10 is the lightest possible starting point. "
					+ "\n\u2022Please select the button to go darker!");
		}

		else {
			taDisplayFormula.appendText("\nLighter or darker, the choice is yours! Please select either: "
					+ "\n\u2022 The button to go lighter..." + "\n\u2022 The button to go darker...");
		}

	}

	/**
	 * This method informs the user which values in the desiredLevel combo box
	 * are appropriate for going lighter.
	 */
	public void rbLighterInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText("So you want to go lighter?  Awesome!"
				+ "\n\u2022 Be sure when chosing a desired level that it's larger than " + cbNaturalLevel.getValue()
				+ "\n");
	}

	/**
	 * This method informs the user which values in the desiredLevel combo box
	 * are appropriate for going darker
	 */
	public void rbDarkerInteractiveDisaply() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText("Darker it is! Lets get started... "
				+ "\n\u2022 Be sure when chosing a desired level that it's smaller than " + cbNaturalLevel.getValue()
				+ "\n");
	}

	/**
	 * Most importantly, this method will warn a user if they have chose a
	 * desired level which is not compatible with going darker. Additionally, it
	 * will also inform the user when they have selected a desired level which
	 * will likely require an additional filler service if they are going 4 or
	 * more levels darker. The rest of the outputs are meant to add an
	 * interactive personality to the interface
	 */
	public void cbDesiredDarkerInteractiveDisplay() {
		taDisplayFormula.clear();
		if (cbDesiredLevelDarker.getValue() >= cbNaturalLevel.getValue()) {
			taDisplayFormula.appendText("I think you said you wanted to go darker... Make sure you"
					+ "\nselect a number SMALLER than your natural level!");
		}

		else if ((cbNaturalLevel.getValue() - cbDesiredLevelDarker.getValue()) >= 4) {
			taDisplayFormula.appendText(
					"Because you are starting at a level " + cbNaturalLevel.getValue() + " and desire a" + "\nlevel "
							+ cbDesiredLevelDarker.getValue() + ", it's worth keeping in mind that a filler"
							+ "\nmay be required in order to achieve a desireable end result. "
							+ "\nIf thats ok, then lets pick out a tone next!");
		}

		else if (cbDesiredLevelDarker.getValue() < 3) {
			taDisplayFormula.appendText("WOW thats dark - a brave sould indeed! Lets do this!"
					+ "\n\u2022 Now, what is the desired undertone for this color? ");
		}

		else if (cbDesiredLevelDarker.getValue() == 3) {
			taDisplayFormula.appendText("Excellent! This is a beautiful, and rich darker level! "
					+ "\nNow, lets select a tone to bring this color to life...");
		}

		else {
			taDisplayFormula.appendText("Great choice! Now, lets select a tone to enhance your color...");
		}
	}

	/**
	 * Once a tone has been selected, this method will (in a comical way)
	 * instruct the user to select whether or not grey coverage is desired. This
	 * is usually a sore subject for people, the comical tone is meant to
	 * lighten that mood a bit and ensure honesty for optimal results.
	 */
	public void cbDesiredToneInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText(cbDesiredTone.getValue() + " is going to look great on you! "
				+ "\nNow, theres one piece of unfinished business before I can get your  "
				+ "\nformula... Don't freak out, but I have to ask, are we covering grey hair? \n"
				+ "\nPlease select either option: " + "\n\u2022 \"Grey coverage needed\""
				+ "\n\u2022 \"No grey coverage needed\"");
	}

	/** Additional comical banter is added if a guest chooses grey coverage */
	public void rbGreyYesInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText("...from your lips to my ears, I swear, it's our secret!"
				+ "\nNOW, click \"Formulate My Color\" and lets get mixing! ");
	}

	/**
	 * Additional comical banter is added if a guest does not need grey coverage
	 */
	public void rbGreyNoInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula
				.appendText("This should be a breeze... NOW, click \"Formulate My Color\" and " + "\nlets get mixing!");
	}

	/**
	 * Most importantly, this method will warn a user if they have chose a
	 * desired level which is not compatible with going lighter. Additionally,
	 * it will also instruct the user to be honest about whether their hair has
	 * color in it or not. Some methods of lightening WILL NOT WORK if the hair
	 * has color, this is an added precaution to coax the user into providing
	 * that information to prevent a disaster scenario.
	 */
	public void cbDesiredLighterInteractiveDisplay() {
		taDisplayFormula.clear();
		if (cbDesiredLevelLighter.getValue() <= cbNaturalLevel.getValue()) {
			taDisplayFormula.appendText("I think you said you wanted to go lighter... Make sure you"
					+ "\nselect a number LARGER than your natural level!");
		}

		else {
			taDisplayFormula.appendText("A good choice indeed! Now, one last and very important deatil..."
					+ "\nI need to know if this is your natural, virgin color or if you have previous"
					+ "\ncolor in your hair. Be honest, it will make a HUGE difference!\n"
					+ "\nSelect: \n\u2022 \"Virgin Hair\" if you have NO color in your hair now"
					+ "\n\u2022 \"Previously Colored\" if you DO have color in your hair now");
		}
	}

	/** Additional comical banter is added if the user selects virgin hair. */
	public void rbVirginYesInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText("You have selected \"Virgin hair\". Phew... that makes this A LOT easier!"
				+ "\n\u2022 Just click \"Formulate My Color!\" and lets get mixing!");
	}

	/**
	 * Additional comical banter is added if the user selects previously colored
	 * hair.
	 */
	public void rbVirginNoInteractiveDisplay() {
		taDisplayFormula.clear();
		taDisplayFormula.appendText("You have selected \"Previously Colored\" - We've got our work cut out for us!\n "
				+ "\nPlease note that previously colored hair will take considerably longer and "
				+ "\nmay require more than one pre-lightening step. Brace yourself, it's about " + "\nto get real!\n"
				+ "\n\u2022 Click \"Formulate my Color!\" and lets get mixing!");
	}

	/**
	 * This method contains a switch statement that will return a string
	 * representation of the corresponding natural level for the user to prevent
	 * confusion when selecting a natural level.
	 */
	public String levelDescription() {

		switch (cbNaturalLevel.getValue()) {
		case 1:
			return "black";
		case 2:
			return "dark black-brown";
		case 3:
			return "black-brown";
		case 4:
			return "dark brown";
		case 5:
			return "medium brown";
		case 6:
			return "light brown";
		case 7:
			return "dark blonde";
		case 8:
			return "medium blonde";
		case 9:
			return "light blonde";
		case 10:
			return "lightest blonde";
		}

		return null;
	}
}