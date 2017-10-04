/**
 * Program Name: ICS 141-02 FALL 2015: Final Project - MyLighterFormula Author:
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
 * This particular file, "MyLighterFormula", is responsible for formulas that
 * are created to go lighter. It inherits two abstract methods from its parent
 * class which allow this particular class to create a specific target formula
 * as well as obtain the required developer which is typically going to be a
 * higher volume than what is needed for going darker. Additionally, should a
 * user have previously colored hair, this class will determine a color-cleanse
 * service which is designed to remove the old color in preparation for the new,
 * lighter color.
 * 
 **/

public class MyLighterFormula extends MyColorFormula {
	// Data Fields
	private Boolean virginHair;
	private String nrp;

	// Constructors
	/** Default no-arg Constructor */
	public MyLighterFormula() {
	}

	/** Constructor with parameters required for creating a lighter formula. */
	public MyLighterFormula(int naturalLevel, int desiredLevel, Boolean virginHair) {
		this.setNaturalLevel(naturalLevel);
		this.setDesiredLevel(desiredLevel);
		this.setVirginHair(virginHair);
	}

	// Getters & Setters
	/** Return Natural Remaining Pigment (nrp) */
	public String getNRP() {
		return nrp;
	}

	/** Return whether hair is pre-colored or not */
	public Boolean isVirginHair() {
		return virginHair;
	}

	/** Set nrp */
	public void setNRP(String nrp) {
		this.nrp = nrp;
	}

	/** Set virginHair */
	public void setVirginHair(Boolean virginHair) {
		this.virginHair = virginHair;
	}

	// Methods
	/**
	 * This method overrides the getTargetFormula() method in the parent class
	 * and creates a formula for going lighter based on the users input. The
	 * if/else decision structure takes into account the varied possible
	 * outcomes. First and fore most, we call the method "getVirginFormulation"
	 * to see whether pre-lightening service is required. Next, we enter the
	 * if/else block and determine the proper formula based on the inputs
	 * provided.
	 */
	public String getTargetFormula() {

		getVirginFormulation(); // Calls method first to check if any
								// pre-lightening will be required first.

		int targetFormula = ((getDesiredLevel() * 2) - getNaturalLevel());

		if (getDesiredLevel() < getNaturalLevel()) { // This makes sure that the
														// user selected the
														// appropriate level for
														// going lighter
			return "ERROR: Desired level must be larger than the natural level when going "
					+ "\nlighter. Please adjust your selection."
					+ "\n\nExample: If the \"Natural Level\" is 6, then the \"Desired Level\" \nmust be 7, 8, 9, or 10."
					+ "\n\nPlease \"RESET\" and try again.";
		}

		else if (targetFormula >= 1 && targetFormula <= 10) { // Formulates a
																// lighter color
																// using color
																// (least
																// aggressive
																// procedure)
			return getVirginFormulation() + "\nTo achieve this result, mix: \n\u2022 40g " + targetFormula + "N "
					+ "with 40g " + getDeveloper() + "Vol. " + "+ " + getGramsOfPigment(getDesiredLevel()) + "g "
					+ getNeutralPigment(getDesiredLevel()) + "\n\u2022 Process for " + super.getProcessingTime()
					+ " minutes" + "\nThe Natural Remaining Pigment (NRP) at this level is: " + returnNRP()
					+ "\n\nGuest Profile Created on: " + getDateCreated();
		}

		else if (targetFormula > 10 && targetFormula <= 12) { // Formulate a
																// lighter color
																// using
																// high-lift
																// color
																// (moderately
																// aggressive
																// procedure)
			return getVirginFormulation() + "\nTo achieve this result, mix: \n\u2022 20g Extra Lifting Creme "
					+ "\n\u2022 40g " + getDeveloper() + "vol. developer." + "\n\u2022 4g Pastel Violet"
					+ "\n\u2022 Process for " + super.getProcessingTime() + " minutes."
					+ "\n\nGuest Profile Created on: " + super.getDateCreated();
		}

		else if ((Math.abs(getNaturalLevel() - getDesiredLevel()) > 4) || targetFormula > 12) { // Formulates
																								// a
																								// lighter
																								// result
																								// with
																								// bleach
																								// (most
																								// aggressive
																								// procedure)
			return getVirginFormulation() + "\nTo achieve this result, mix: \n\u2022 30g Enlightener Powder " + "60g "
					+ getDeveloper() + "vol. developer." + "\n\u2022 Process for " + super.getProcessingTime()
					+ " minutes." + "\n\u2022 Rinse and tone with 40g " + super.getDesiredLevel() + "N + "
					+ super.getGramsOfPigment(super.getDesiredLevel()) + "g "
					+ super.getNeutralPigment(super.getDesiredLevel()) + "\n\nGuest Profile Created on: "
					+ super.getDateCreated();
		}
		return "A formula could not be determined. Please check your parameters."; // returns
																					// a
																					// message
																					// if
																					// a
																					// formula
																					// could
																					// not
																					// be
																					// determined
																					// (rare!)
	}

	/**
	 * This method overrides the "getDeveloper" method from its parent class and
	 * determines the level of developer needed (determined by levels of lift
	 * needed) in order to produce the desired results.
	 */
	public int getDeveloper() {

		int developer = Math.abs(getNaturalLevel() - getDesiredLevel()) * 10;
		// No Developer higher than 40vol. exists.
		if (developer > 40) {
			return 40;
		}
		// Developer recommended when 10vol. is not needed, typically deposit
		// only.
		else if (developer < 10) {
			return 5;
		} else
			return developer;
	}

	/**
	 * This method will determine the Natural Remaining Pigment (NRP) at the
	 * desired level and its corresponding counteracting pigments
	 */
	public String returnNRP() {

		String nrp;
		switch (getDesiredLevel()) {
		case 1:
			nrp = "Dark Red";
			break;
		case 2:
			nrp = "Dark Red";
			break;
		case 3:
			nrp = "Medium Red";
			break;
		case 4:
			nrp = "Red";
			break;
		case 5:
			nrp = "Red-Orange";
			break;
		case 6:
			nrp = "Orange-Red";
			break;
		case 7:
			nrp = "Orange";
			break;
		case 8:
			nrp = "Yellow-Orange";
			break;
		case 9:
			nrp = "Yellow";
			break;
		case 10:
			nrp = "Pale-Yellow";
			break;
		default:
			nrp = "Invalid desired level. NRP Not Available!";
			break;
		}
		return nrp;
	}

	/**
	 * This method will determine if additional work is needed based on the
	 * boolean virginHair field. Virgin hair does not require pre-lightening,
	 * but non-virgin/pre-colored hair does as you cannot lift color with color.
	 * This method is called by the "getTargetFormula()" method above.
	 */
	public String getVirginFormulation() {

		if (isVirginHair() == false) {
			return "A Pre-lightening Color Cleanse service is required "
					+ "\nbefore the desired result can be achieved."
					+ "\n\nMix: \n\u2022 30g Enlightener Powder \n\u2022 30g Detoxifying Shampoo \n\u2022 30g 20vol Color Catalyst"
					+ "\n\nBe sure to apply only to the pre-colored hair avoiding any exposed"
					+ "\nnatural color. Process 20 to 30 mins and continue with the following "
					+ "\nrecommended formula.\n";
		}
		return "Pre-ligthening is not required. ";
	}
}