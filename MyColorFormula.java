/**
 * Program Name: ICS 141-02 FALL 2015: Final Project - MyColorFormula Author:
 * Jack Hysell Date: 12/2/15 Modified: 12/7/15 (Added exception handling support
 * for the tfGuestName field in the MyColorGUI.java class to limit user input
 * and prevent malicious attacks. This new addition features an additional class
 * called NameErrorAlert.java. An invalid entry prompts this new class to
 * display an alert box and the input is immediately cleared from the field.)
 * 
 * Description: This program is designed to create a hair color formula for the
 * user based on individual input. Formulas include going lighter with color,
 * high-lift tint, and "enlightener" (or bleach), as well as, formulas for going
 * darker as well. This program can account for many nuances such as the
 * specific developers needed, processing time, grams of pigment, grey coverage,
 * fillers, and pre-lightening.
 * 
 * This particular file, "MyColorFormula", is the abstract parent class for this
 * program. Its major responsibility is to provide the basic components which
 * are needed by its two child/sub-classes. Both sub-classes will have access to
 * getters and setters from this class, as well as to its abstract methods which
 * are both utilized in different ways by its children.
 * 
 **/

public abstract class MyColorFormula {
	// Data Fields
	private int naturalLevel;
	private int desiredLevel;
	private java.util.Date dateCreated;

	// Constructors
	/** Construct a default MyColorFormula */
	protected MyColorFormula() {
		dateCreated = new java.util.Date();
	}

	/**
	 * Construct MyColorFormula with naturalLevel, desiredLevel, and developer
	 */
	protected MyColorFormula(int naturalLevel, int desiredLevel) {
		dateCreated = new java.util.Date();
		this.naturalLevel = naturalLevel;
		this.desiredLevel = desiredLevel;
	}

	/** Return naturalLevel */
	public int getNaturalLevel() {
		return naturalLevel;
	}

	/** Return desiredLevel */
	public int getDesiredLevel() {
		return desiredLevel;
	}

	/** Set new naturalLevel */
	public void setNaturalLevel(int naturalLevel) {
		this.naturalLevel = naturalLevel;
	}

	/** Set new desiredLevel */
	public void setDesiredLevel(int desiredLevel) {
		this.desiredLevel = desiredLevel;
	}

	/** Get dateCreate */
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	// Abstract Methods
	public abstract String getTargetFormula();

	public abstract int getDeveloper();

	// Concrete Methods
	/**
	 * The natural level system goes from 1-10. Each level has a corresponding
	 * neutralizing pigment for the natural remaining pigment (NRP) at each
	 * level. This array points to that neutralizing pigment by taking the
	 * desiredLevel - 1 and retrieving the string from the corresponding index.
	 */
	public String getNeutralPigment(int desiredLevel) {

		String[] neutralPigment = { "dark BG", "dark BG", "dark BG", "dark BG", "dark BV", "dark BV", "light BB",
				"light BB", "light VB", "light VB" };

		return neutralPigment[desiredLevel - 1];
	}

	/**
	 * Similar to the "getNeutralPigment" method just above, this array now
	 * points to a levels corresponding enhancing pigment, or "fill" pigment. T
	 */
	public String getEnhancingPigment(int desiredLevel) {

		String[] enhancingPigment = { "dark RR", "dark RR", "dark RR", "dark RR", "dark RO", "dark RO", "light OR",
				"light OR", "light YO", "light YO" };

		return enhancingPigment[desiredLevel - 1];
	}

	/**
	 * The amount of pigment needed varies based on the desiredLevel. For
	 * Example: Levels: 10, 9, 8, 7 require 2grams, 4grams, 6grams, and 8grams
	 * respectively. This pattern resets at level 6 and therefore: Levels: 6, 5,
	 * 4, 3 require 2grams, 4grams, 6grams, and 8grams respectively. Levels 2
	 * and 1 are too dark for pigments to be seen and therefore the max is
	 * 8grams.
	 */
	public int getGramsOfPigment(int desiredLevel) {

		int[] gramsOfPigmentNeeded = { 8, 8, 8, 6, 4, 2, 8, 6, 4, 2 };

		return gramsOfPigmentNeeded[desiredLevel - 1];
	}

	/**
	 * Different developers have different processing times. 10vol = 20mins.,
	 * 20vol = 30mins., 30vol. = 40mins., and 40vol. = 45mins. This method will
	 * return the appropriate processing time based on the developer.
	 */
	public int getProcessingTime() {

		int processingTime = 0;

		switch (getDeveloper()) {

		case 5:
			processingTime = 15;
			break;
		case 10:
			processingTime = 20;
			break;
		case 20:
			processingTime = 30;
			break;
		case 30:
			processingTime = 40;
			break;
		case 40:
			processingTime = 45;
			break;
		}

		return processingTime;
	}
}