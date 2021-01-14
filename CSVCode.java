package application;

/*
 * Date: January 8, 2021
 * Title: CSVCode
 * Author: Catherine Yu
 * Description: All code involving the CSV file that contains all of the information on the chemicals.
 * These codes include generating the file, adding information to the file, and deleting existing information in the file.
 */

// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.TableView;
import java.util.concurrent.TimeUnit;
	
public class CSVCode {
	
	/**
	 * Create a file, with a header, that will store all the information on the chemicals
	 * @author  Catherine Yu
	 * @return  The created CSV file that will contain all of the information on the chemicals
	 * @throws IOException
	 */
	public static File generateFile() throws IOException {
		// Set the name of the file
		String fileName = "DigitalChemicalStorageSystem.csv";
		// Create a file with the above file name
		File file = new File(fileName);
		
		// Create a String array to input a header
		String[] header = {"Chemical Name", "Mass (g)", "Volume (mL)", "Formula", "Entry Date", "Expiry Date"};
		
		// Input the header into the file if one does not already exist
		if (!file.exists()) {
			// Create a FileWriter and PrintWriter
			FileWriter filewriter = new FileWriter(file, true);
			PrintWriter printwriter = new PrintWriter(filewriter);        
			
			// Input the header, separated by commas as the delimiter
			for (int i = 0; i < header.length; i++) {
				printwriter.print(header[i] + ",");
			}
			
			// Close the PrintWriter
			printwriter.close();
		}
		
		// Return the created file
		return file;
	}
	
	/**
	 * Display the contents of the CSV file into the TableView
	 * @author      Catherine Yu
	 * @param file  The CSV file that contains all of the information on the chemicals
	 * @param table The TableView that will display the contents of the CSV file
	 * @throws IOException
	 */
	public static void viewFile(File file, TableView table) throws IOException {
		// Create a BufferedReader that uses a FileReader to read the CSV file
		BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
		
		// Declare line
		String line;
		
		// Loops until there are no more lines to read
		while ((line = bufferedreader.readLine()) != null) {        
			
			// If the line contains a forward slash, which is used for the date, add the line to the TableView
			if (line.contains("/")) {
				// Initialize a string array by splitting the current line by the delimiter ","
				String[] info = line.split(",");
				
				// Create an item
				editChemicalTable item = new editChemicalTable();
				
				// Initialize the item with the information in the current line
				item.setChemicalName(info[0]);
				item.setChemicalMass(Double.parseDouble(info[1]));
				item.setChemicalVolume(Double.parseDouble(info[2]));
				item.setChemicalFormula(info[3]);
				item.setDateOfEntry(info[4]);
				item.setDateOfExpiry(info[5]);
				
				// Add the item with the information into the TableView
				table.getItems().add(item);
				
			}
			
		}
		
		bufferedreader.close();
	}
	
	/**
	 * Add data to the CSV file that contains all the information on the chemicals
	 * @author      Catherine Yu
	 * @param file  The CSV file that contains all of the information on the chemicals
	 * @param info  The populated array that contains the information to be added
	 * @throws IOException
	 */
	public static void addFileData(File file, String[] info) throws IOException {
		// Create a FileWriter and PrintWriter
		FileWriter filewriter = new FileWriter(file, true);
		PrintWriter printwriter = new PrintWriter(filewriter);
		
		// Print an empty line to distinguish the information from the previous line
		printwriter.println();
		
		// Input all of the chemical's information
		for (int i = 0; i < info.length; i++) {
			// Separate the information with commas, as the delimiter, due to the style of CSV files
			printwriter.print(info[i] + ",");
		}
		
		// Close the PrintWriter
		printwriter.close();
		
	}
	
	/**
	 * Delete existing information in the CSV file that contains all the information on the chemicals
	 * @author      Catherine Yu
	 * @param file  The CSV file that contains all of the information on the chemicals
	 * @param info  The populated array that contains the information to be deleted
	 * @throws IOException
	 */
	public static void deleteFileData(File file, String[] info) throws IOException {
		// Create a BufferedReader that uses a FileReader to read the CSV file
		BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
		
		// Create a temporary file to hold the updated information
		File tempFile = new File("TemporaryDigitalChemicalStorageSystem.csv");
		// Create a FileWriter and PrintWriter for the temporary file
		FileWriter filewriter = new FileWriter(tempFile, true);
		PrintWriter printwriter = new PrintWriter(filewriter);
		
		// Declare line
		String line;
		
		// Loops until there are no more lines to read
		while ((line = bufferedreader.readLine()) != null) {        
			// Transfer each line in the existing file to the temporary file if they do not contain the selected row of information
			if (!line.contains(info[0] + ",")) {
				printwriter.println(line);
			}
		}
		
		// Close the PrintWriter and the BufferedReader
		printwriter.close();
		bufferedreader.close();
		
		File k = new File("DigitalChemicalStorageSystem.csv");
		file.delete();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Rename the temporary file to the original file to overwrite the original contents
		tempFile.renameTo(k);
		
	}
    	
}
