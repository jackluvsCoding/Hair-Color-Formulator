/**
 * Program Name: ICS 141-02 FALL 2015: Final Project - MyDarkerFormula Author:
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
 * This particular file, "MyDarkerFormula", is responsible for formulas that are
 * created to go darker. It inherits two abstract methods from its parent class
 * which allow this particular class to create a specific target formula as well
 * as obtain the required developer which is typically going to be a lower
 * volume than what is needed for going lighter. Additionally, should a users
 * desired level be more than four levels darker than their starting level, this
 * program will formulate a filler formula which is performed prior to the final
 * desired formula. This ensures adequate coverage and depth of tone and is
 * required for optimal results.
 * 
 **/

public class MyDarkerFormula extends MyColorFormula {
	// Data Fields
	private Boolean greyCoverNeeded;
	private String desiredTone;

	// Constructors
	/** Default Constructor */
	public MyDarkerFormula() {
	}

	/** Constructor with parameters required for creating a darker formula. */
	public MyDarkerFormula(int naturalLevel, int desiredLevel, String desiredTone, Boolean greyCoverage) {
		this.setNaturalLevel(naturalLevel);
		this.setDesiredLevel(desiredLevel);
		this.desiredTone = desiredTone;
		this.greyCoverNeeded = greyCoverage;
	}

	// Getters & Setters
	/** Return desiredTone */
	public String getDesiredTone() {
		return desiredTone;
	}

	/** Return if greyCoverage is desired */
	public Boolean isGreyCoverageNeeded() {
		return greyCoverNeeded;
	}

	/** Set new desiredTone */
	public void setDesiredTone(String desiredTone) {
		this.desiredTone = desiredTone;
	}

	/** Set greyCoverage */
	public void setGreyCoverage(Boolean greyCoverage) {
		this.greyCoverNeeded = greyCoverage;
	}

	// Methods
	/**
	 * This method overrides the getTargetFormula() method from its parent class
	 * and creates a formula for going darker based on specific input by the
	 * user. A formula going darker is much simpler as the desiredLevel is the
	 * level we would use. The only complexity here is what the final desired
	 * pigment is and if we are trying to cover grey hair or not. Additionally,
	 * if we are trying to go more than 4 levels darker than the starting or
	 * naturalLevel, a filler service will be required for optimal results.
	 */
	public String getTargetFormula() {

		int targetFormula = getDesiredLevel();

		if (getDesiredLevel() > getNaturalLevel()) { // Ensures that the user
														// selects the
														// appropriate level for
														// going darker
			return "ERROR: Desired level must be less than the natural level when going "
					+ "\ndarker. Please adjust your selection."
					+ "\n\nExample: If the \"Natural Level\" is 6, then the \"Desired Level\" \nmust be 5, 4, 3, 2, or 1."
					+ "\n\nPlease \"RESET\" and try again.";
		}

		return getFillFormula() + "\nTo achieve this result, mix: \n\u2022 40g " + targetFormula + "N + 40g "
				+ getDeveloper() + "Vol. " + "+ " + getGramsOfPigment(targetFormula) + "g " + getTone()
				+ "\n\u2022 Process for " + super.getProcessingTime() + " minutes." + "\n\nGuest Profile Created on: "
				+ super.getDateCreated();
	}

	/**
	 * This method overrides the "getDeveloper" method from its parent class and
	 * determines the level of developer needed (determined by whether we are
	 * covering gray or not) in order to produce the desired results.
	 */
	public int getDeveloper() {

		int developer = Math.abs(getNaturalLevel() - getDesiredLevel()) * 10;
		/** For grey coverage, 20vol is required */
		if (greyCoverNeeded == true) {
			return 20;
		}
		/**
		 * It is recommended to use 10vol when going darker for maximum color
		 * deposit and to protect hair integrity.
		 */
		else if (developer >= 10) {
			return 10;
		}
		/**
		 * A 5vol. developer is recommended when refreshing color at the same
		 * level as the natural level.
		 */
		else {
			return 5;
		}
	}

	/**
	 * Using a 2D array we can compare user input for the desired tone to that
	 * of the strings in the toneFinder array and then return its corresponding
	 * 2 character formula representation in row 2.
	 */
	public String getTone() {

		String[][] toneFinder = { { "Natural", "Gold", "Copper", "Red", "Red Violet", "Ash", "Violet Ash" },
				{ "NN", "YO", "RO", "RR", "VR", "BG", "BV" } };

		for (int row = 0; row < toneFinder.length; row++) {
			for (int column = 0; column < toneFinder[row].length; column++) {
				if (desiredTone.equalsIgnoreCase(toneFinder[0][column])) {
					desiredTone = toneFinder[1][column];
				}
			}
		}

		return desiredTone;
	}

	/**
	 * Should there be more than 4 levels between the natural level and desired
	 * level, it is necessary to fill the hair first, before applying the final
	 * color. This method provides a formula to accomplish that.
	 */
	public String getFillFormula() {

		if (Math.abs(getDesiredLevel() - getNaturalLevel()) > 4) {
			int fillFormula = (int) Math.floor((getDesiredLevel() + getNaturalLevel()) / 2);
			return "For optimal results, it is recommended that you first fill "
					+ "\nthe hair with the following formula. \nMix: " + "\n\u2022 40g " + fillFormula
					+ "N + 40g 5vol. " // determineDeveloper() is not needed
										// here because 5vol. is standard when
										// filling
					+ "+ " + getGramsOfPigment(fillFormula) + "g " + getEnhancingPigment(fillFormula)
					+ "\n\u2022 Process 15 to 20 minutes and continue with the following formula.\n";
		}
		return "Filling is not required.";
	}
}