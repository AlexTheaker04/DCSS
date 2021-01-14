package application;

/* Digital Chemical Storage System - CSVCode
 * Date: January 8, 2021
 * Author: Catherine Yu
 * Teacher: Mr. Ho - ICS3U1-02
 * Description: All code related to the CSV file that contains all the entered information on the chemicals
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
	 * 
	 * @author  Catherine Yu
	 * @return  The created CSV file that will contain all of the information on the chemicals
	 * @throws IOException
	 */
	public static File generateFile() throws IOException {
		
		// Set the name of the file
		String fileName = "DigitalChemicalStorageSystem.csv";
		// Create a file with the above file name
		File file = new File(fileName);
		
		// Create a String array that contains a header for the file
		String[] header = {"Chemical Name", "Mass (g)", "Volume (mL)", "Formula", "Entry Date", "Expiry Date"};
		
        	// Input the header into the file if one does not already exist
		// Since the header is automatically inputted into the file when it is created, 
        	// if the file exist, it is assumed that it contains a header
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
	 * 
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
		
		// Loops until there are no more lines to read in the file
		while ((line = bufferedreader.readLine()) != null) {        
			
			 // If the line contains a forward slash, which is used for the entry and expiry date, add the line to the TableView
			 // All lines will have an automatically generated entry date, since the entry date is automatically generated with a forward slash,
			 // any line with information is guaranteed to have a forward slash// If the line contains a forward slash, which is used for the date, add the line to the TableView
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
		
		// Close the BufferedReader
		bufferedreader.close();
	}
	
	/**
         * Add data to the CSV file that contains all the information on the chemicals
         * 
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
         * 
         * @author      Catherine Yu, Andrey Zinovyev
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
			// Transfer each line in the existing file to the temporary file if they do not contain information to be deleted
		        // Since there is validation to prevent duplicate chemicals, only the chemical name and the delimiter needs to be searched
		        // The delimiter after the chemical name needs to be searched due to chemical that start with the same substance such as carbon, and carbon dioxide
			if (!line.contains(info[0] + ",")) {
				printwriter.println(line);
			}
		}
		
		// Close the PrintWriter and the BufferedReader
		printwriter.close();
		bufferedreader.close();
		
		// Create a new file with the same name as the original file
		File k = new File("DigitalChemicalStorageSystem.csv");
		// Delete the original file
		file.delete();
		
		// Add a delay in between deleting and renaming to prevent any errors
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// Catch any exceptions that may occur
			e.printStackTrace();
		}
		
		// Rename the temporary file to the original file
		tempFile.renameTo(k);
		
	}
    	
}
