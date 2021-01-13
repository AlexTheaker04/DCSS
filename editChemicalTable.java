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
		
		// Default Values.
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
	 * @param String chemicalName: Can be retrieved from other classes.
	 *        double chemicalMass: Can be retrieved from other classes.
	 *        double chemicalVolume: Can be retrieved from other classes.
	 *        String chemicalFormula: Can be retrieved from other classes.
	 *        String dateOfEntry: Can be retrieved from other classes.
	 *        String dateOfExpiry: Can be retrieved from other classes.
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
	
	// Chemical Name getter.
	public String getChemicalName() {
		return chemicalName;
	}
	
	// Chemical Name setter.
	public void setChemicalName(String chemicalName) {	
		this.chemicalName = chemicalName;
	}
	// Chemical Mass getter.
	public double getChemicalMass() {
		return chemicalMass;
	}
	
	// Chemical Mass setter.
	public void setChemicalMass(double chemicalMass) {
		this.chemicalMass = chemicalMass;
	}
	
	// Chemical Volume getter.
	public double getChemicalVolume() {
		return chemicalVolume;
	}
	
	// Chemical Volume setter.
	public void setChemicalVolume(double chemicalVolume) {
		this.chemicalVolume = chemicalVolume;
	}
	
	// Chemical Formula getter.
	public String getChemicalFormula() {
		return chemicalFormula;
	}
	
	// Chemical Formula setter.
	public void setChemicalFormula(String chemicalFormula) {
		this.chemicalFormula = chemicalFormula;
	}
	
	// Date of Entry getter.
	public String getDateOfEntry() {
		return dateOfEntry;
	}
	
	// Date of Entry setter.
	public void setDateOfEntry(String dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}
	
	// Date of Expiry getter.
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	
	// Date of Expiry setter.
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	
}
