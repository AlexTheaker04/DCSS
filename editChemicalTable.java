package application;

/*
 * Date: 1/7/2021
 * Title: addChemicalTable.
 * Author: Andrey Zinovyev.
 * Description: A class that will add items to the table.
 */

public class editChemicalTable {

	// Private string variables.
	private String chemicalName, chemicalFormula, dateOfEntry, dateOfExpiry;
	
	// Private double variables.
	private double chemicalMass, chemicalVolume;
	
	// Create a blank table.
	public editChemicalTable() {
		
		// Set default values for each variable.
		this.chemicalName = "";
		this.chemicalMass = 0;
		this.chemicalVolume = 0;
		this.chemicalFormula = "";
		this.dateOfEntry = "";
		this.dateOfExpiry = "";
		
	}
	
	/** @author: Andrey Zinovyev.
	 * @date: 1/7/2021.
	 * 
	 * editChemicalTable() is the method responsible for getting and setting the values in the text fields.
	 *
	 * @param String chemicalName: The chemical's entered name.
	 *        double chemicalMass: The chemical's entered mass.
	 *        double chemicalVolume: The chemical's entered volume.
	 *        String chemicalFormula: The chemical's entered formula.
	 *        String dateOfEntry: The chemical input's entry date.
	 *        String dateOfExpiry: The chemical's entered expiry date.
	 * 
	 */
	public editChemicalTable(String chemicalName, double chemicalMass, double chemicalVolume, String chemicalFormula, 
			String dateOfEntry, String dateOfExpiry) {
		
		// Pass in the values.
		this.chemicalName = chemicalName;
		this.chemicalMass = chemicalMass;
		this.chemicalVolume = chemicalVolume;
		this.chemicalFormula = chemicalFormula;
		this.dateOfEntry = dateOfEntry;
		this.dateOfExpiry = dateOfExpiry;
		
	}
	
	// Get the Chemical Name.
	public String getChemicalName() {
		return chemicalName;
	}
	
	// Set the Chemical Name.
	public void setChemicalName(String chemicalName) {	
		this.chemicalName = chemicalName;
	}
	// Get the Chemical Mass.
	public double getChemicalMass() {
		return chemicalMass;
	}
	
	// Set the Chemical Mass.
	public void setChemicalMass(double chemicalMass) {
		this.chemicalMass = chemicalMass;
	}
	
	// Get the Chemical Volume.
	public double getChemicalVolume() {
		return chemicalVolume;
	}
	
	// Set the Chemical Volume.
	public void setChemicalVolume(double chemicalVolume) {
		this.chemicalVolume = chemicalVolume;
	}
	
	// Get the Chemical Formula.
	public String getChemicalFormula() {
		return chemicalFormula;
	}
	
	// Set the Chemical Formula.
	public void setChemicalFormula(String chemicalFormula) {
		this.chemicalFormula = chemicalFormula;
	}
	
	// Get the Chemical's Date of Entry.
	public String getDateOfEntry() {
		return dateOfEntry;
	}
	
	// Set the Chemical's Date of Expiry.
	public void setDateOfEntry(String dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}
	
	// Get the Chemical's Date of Expiry.
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	
	// Set the Chemical's Date of Expiry.
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	
}
