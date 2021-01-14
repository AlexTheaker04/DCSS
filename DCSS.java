package application;

/* Educational Productivity Software - Digital Chemical Storage System.
 * Date: January 7, 2021.
 * Authors: Ian Ludanik, Alex Theaker, Catherine Yu, and Andrey Zinovyev.
 * Teacher: Mr. Ho - ICS3U1-02.
 * Description: A digital chemical substance storage system that allows the user to input chemical substances, 
 * its mass in grams, its amount in milliliters, its formula, and its expiry date.
 * This program will add the data into a JavaFX TableView with an automatically generated entry date, 
 * the entered information will also be added to a CSV file. 
 * This program allows the user to select a row of data in the TableView and either delete the row from the TableView and the CSV file or
 * update the data by transferring the information into the TextFields and allowing edits to be made.
 * Finally, this program allows the user to display the contents of the CSV file in TableView.
 */

// Imports
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
	
public class DCSS extends Application {
	
	// Create a main Stage, a main Scene, a Scene to view the CSV file, and a Scene to make edits.
	Stage mainWindow;
	Scene mainScene, viewScene, editScene, addScene;
	
	// Create TextFields to contain the information that will be added and/or updated.
	TextField chemicalNameInput, chemicalMassInput, chemicalVolumeInput, chemicalFormulaInput, chemicalExpiryInput;
	
	// Create two TableViews, one for editing and one for viewing.
	TableView<editChemicalTable> chemicalTable;
	TableView<editChemicalTable> viewCSVTable;
	
	// Main method only used for launching the GUI.
	public static void main(String[] args) {
		
		// Launch the program.
		launch(args);
	}
	
	/** 
	 * @author	Andrey Zinovyev, Catherine Yu.
	 * @date	1/7/2021.
	 * 
	 * start() Displays the starting screen as well as contains all the other scenes in the program.
	 * 
	 * start() Contains the scenes which are used to create the program, it contains all the necessary
	 * methods and functions used to make the program run. This method is also responsible for all the
	 * 
	 * @param Stage mainWindow: Takes in mainWindow.
	 * 
	 * @throws Exception: If something goes wrong during the run, the program will not crash.
	 * 
	 * @return void: Does not return anything.
	 */
	
	@Override
	public void start(Stage mainWindow) throws Exception{
		
		// Set the colour of three backgrounds, one for each Scene.
		BackgroundFill lightGreenBG = new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill lightBlueBG = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill lightYellowBG = new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
		
		// Set the backgrounds.
		Background mainBG = new Background(lightGreenBG);
		Background viewBG = new Background(lightBlueBG);
		Background addBG = new Background(lightYellowBG);
		
		// Create custom fonts.
		Font hugeFont = Font.font("Arial", FontWeight.BOLD, 80);
		Font bigFont = Font.font("Courier New", FontWeight.BOLD, 36);
		Font mediumFont = Font.font("Courier New", FontWeight.BOLD, 20);
		
		// Create the Title label.
		Label dcssLabel = new Label("Digital Chemical\nStorage System");
		dcssLabel.setFont(hugeFont);
		dcssLabel.setTextAlignment(TextAlignment.CENTER);
		dcssLabel.setTranslateY(-350);
		dcssLabel.setTextFill(Color.color(0, 0, 1));
		
		// Generate a CSV file to contain the information if one does not already exist
		File file = CSVCode.generateFile();
		
		// Automatically generate the current date.
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime currentDate = LocalDateTime.now();  
		String date = format.format(currentDate);
		
		// VIEW FILE BUTTON AND SCENE
		
		// Create the 'View File' button.
		Button csvButton = new Button("View File");
		csvButton.setOnAction(e -> {
			// Set the scene as the viewScene once the button is clicked.
			mainWindow.setScene(viewScene);
			// Display the contents of the CSV file.
			viewButtonClicked(file);
			
		});
		
		// Set the font size and location of the button.
		csvButton.setFont(bigFont);
		csvButton.setTranslateY(-100);
		
		// Create a column in the TableView for the chemical name.
		TableColumn<editChemicalTable, String> chemicalName = new TableColumn<>("Chemical Name");
		chemicalName.setMinWidth(200);
		chemicalName.setCellValueFactory(new PropertyValueFactory<>("chemicalName"));
		
		// Create a column in the TableView for the chemical mass.
		TableColumn<editChemicalTable, String> chemicalMass = new TableColumn<>("Mass (g)");
		chemicalMass.setMinWidth(200);
		chemicalMass.setCellValueFactory(new PropertyValueFactory<>("chemicalMass"));
		
		// Create a column in the TableView for the chemical volume.
		TableColumn<editChemicalTable, String> chemicalVolume = new TableColumn<>("Volume (mL)");
		chemicalVolume.setMinWidth(200);
		chemicalVolume.setCellValueFactory(new PropertyValueFactory<>("chemicalVolume"));
		
		// Create a column in the TableView for the chemical formula.
		TableColumn<editChemicalTable, String> chemicalFormula = new TableColumn<>("Formula");
		chemicalFormula.setMinWidth(200);
		chemicalFormula.setCellValueFactory(new PropertyValueFactory<>("chemicalFormula"));
		
		// Create a column in the TableView for the entry date.
		TableColumn<editChemicalTable, String> chemicalEntry = new TableColumn<>("Date of Entry");
		chemicalEntry.setMinWidth(200);
		chemicalEntry.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
		
		// Create a column in the TableView for the expiry date.
		TableColumn<editChemicalTable, String> chemicalExpiry = new TableColumn<>("Date of Expiry");
		chemicalExpiry.setMinWidth(200);
		chemicalExpiry.setCellValueFactory(new PropertyValueFactory<>("dateOfExpiry"));
		
		// Format the TableView with the created columns.
		viewCSVTable = new TableView<>();
		viewCSVTable.setItems(addChemicals());
		viewCSVTable.getColumns().addAll(chemicalName, chemicalMass, chemicalVolume, chemicalFormula, 
				chemicalEntry, chemicalExpiry);
		
		// Create a 'Back' button for the "View File" scene.
		Button viewBackButton = new Button("Back");
		// Set the font of the button.
		viewBackButton.setFont(mediumFont);
		viewBackButton.setOnAction(e -> {
			// Set the Scene as the main scene when the button is clicked.
			mainWindow.setScene(mainScene);
		});
		
		// Create an HBox with the TableView that displays the contents of the CSV file
		HBox viewMiddleMenu = new HBox();
		viewMiddleMenu.getChildren().add(viewCSVTable);
		
		// Create an HBox with the "Back" button
		HBox viewBottomMenu = new HBox();
		viewBottomMenu.getChildren().addAll(viewBackButton);
		
		// Create a BorderPane with the two HBoxes above
		BorderPane viewBorderPane = new BorderPane();
		viewBorderPane.setCenter(viewMiddleMenu);
		viewBorderPane.setBottom(viewBottomMenu);
		
		// Create the "View File" scene.
		viewBorderPane.setBackground(viewBG);
		viewScene = new Scene(viewBorderPane, 1200, 1000);
		
		// ADD/DELETE BUTTON AND SCENE.
		
		// Create the 'Add/Delete/Update' button.
		Button addButton = new Button("Add/Delete/Update");
		addButton.setOnAction(e -> {
			
			// Set the Scene as the addScene.
			mainWindow.setScene(addScene);
		});
		
		// Set the font size of the button.
		addButton.setFont(bigFont);
		
		// Create a column in the TableView for the chemical name.
		TableColumn <editChemicalTable, String> chemicalNameColumn = new TableColumn<>("Chemical Name");
		chemicalNameColumn.setMinWidth(200);
		chemicalNameColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalName"));
		
		// Create a column in the TableView for the chemical mass.
		TableColumn <editChemicalTable, Double> chemicalMassColumn = new TableColumn<>("Mass (g)");
		chemicalMassColumn.setMinWidth(200);
		chemicalMassColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalMass"));
		
		// Create a column in the TableView for the chemical volume.
		TableColumn <editChemicalTable, Double> chemicalVolumeColumn = new TableColumn<>("Volume (mL)");
		chemicalVolumeColumn.setMinWidth(200);
		chemicalVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalVolume"));
		
		// Create a column in the TableView for the chemical formula.
		TableColumn <editChemicalTable, String> chemicalFormulaColumn = new TableColumn<>("Formula");
		chemicalFormulaColumn.setMinWidth(200);
		chemicalFormulaColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalFormula"));
		
		// Create a column in the TableView for the entry date.
		TableColumn <editChemicalTable, String> chemicalEntryColumn = new TableColumn<>("Date of Entry");
		chemicalEntryColumn.setMinWidth(200);
		chemicalEntryColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
		
		// Create a column in the TableView for the expiry date.
		TableColumn <editChemicalTable, String> chemicalExpiryColumn = new TableColumn<>("Date of Expiry");
		chemicalExpiryColumn.setMinWidth(200);
		chemicalExpiryColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfExpiry"));
		
		// Create a TextField for the chemical name.
		chemicalNameInput = new TextField();
		chemicalNameInput.setPromptText("Chemical Name");
		chemicalNameInput.setMinWidth(100);
		
		// Create a TextField for the chemical mass.
		chemicalMassInput = new TextField();
		chemicalMassInput.setPromptText("Mass (g)");
		chemicalMassInput.setMinWidth(100);
		
		// Create a TextField for the chemical volume.
		chemicalVolumeInput = new TextField();
		chemicalVolumeInput.setPromptText("Volume (mL)");
		chemicalVolumeInput.setMinWidth(100);
		
		// Create a TextField for the chemical formula.
		chemicalFormulaInput = new TextField();
		chemicalFormulaInput.setPromptText("Formula");
		chemicalFormulaInput.setMinWidth(100);
		
		// Create a TextField for the expiry date.
		chemicalExpiryInput = new TextField();
		chemicalExpiryInput.setPromptText("Expiry Date (YY/MM/DD)");
		chemicalExpiryInput.setMinWidth(100);
		
		// Create and format the "Add" button.
		Button addChemicalButton = new Button("Add");
		addChemicalButton.setFont(mediumFont);
		addChemicalButton.setOnAction(e -> addButtonClicked(file, date));
		
		// Create and format the "Delete" button.
		Button deleteChemicalButton = new Button("Delete");
		deleteChemicalButton.setFont(mediumFont);
		deleteChemicalButton.setOnAction(e -> deleteButtonClicked(file, date));
		
		// Create and format the "Update" button.
		Button updateChemicalButton = new Button("Update");
		updateChemicalButton.setFont(mediumFont);
		updateChemicalButton.setOnAction(e -> updateButtonClicked(file, date));

		// Create a 'Back' button for the 'Add/Delete/Update" Scene.
		Button addBackButton = new Button("Back");
		// Set the font size of the button.
		addBackButton.setFont(mediumFont);
		addBackButton.setOnAction(e -> {
			
			// Set the Scene as the main Scene.
			mainWindow.setScene(mainScene);
		});
		
		// Create an HBox that contain the TextFields, "Back", "Add", "Delete", and "Update" buttons.
		HBox textField = new HBox();
		textField.setPadding(new Insets(10, 10, 10, 10));
		textField.setSpacing(10);
		textField.getChildren().addAll(addBackButton, chemicalNameInput, chemicalMassInput, chemicalVolumeInput, chemicalFormulaInput,
				chemicalExpiryInput, addChemicalButton, deleteChemicalButton, updateChemicalButton);
		
		// Create a TableView to display the created columns and the information.
		chemicalTable = new TableView<>();
		chemicalTable.setItems(addChemicals());
		chemicalTable.getColumns().addAll(chemicalNameColumn, chemicalMassColumn, chemicalVolumeColumn, chemicalFormulaColumn, 
				chemicalEntryColumn, chemicalExpiryColumn);
		
		// Read the CSV file and display its contents into the TableView.
		CSVCode.viewFile(file, chemicalTable);
		
		// Create an HBox for the TableView to contain the TableView.
		HBox addMiddleMenu = new HBox();
		addMiddleMenu.getChildren().addAll(chemicalTable);
		
		// Create an HBox for the TextFields.
		HBox addBottomMenu = new HBox();
		addBottomMenu.getChildren().addAll(textField);
		
		// Create a BorderPane with the two HBoxes above.
		BorderPane addBorderPane = new BorderPane();
		addBorderPane.setCenter(addMiddleMenu);
		addBorderPane.setBottom(addBottomMenu);
		
		// Create the "Add/Delete/Update" Scene.
		addBorderPane.setBackground(addBG);
		addScene = new Scene(addBorderPane, 1200, 1000);
		
		// MAIN BUTTONS AND SCENE.
		
		// Create the "Exit" Button.
		Button closeButton = new Button("Exit");
		closeButton.setOnAction(e -> {
			// Display the ExitBox Scene with a message and initialize the value of result.
			boolean result = ExitBox.display("Exit Dialogue", "Are you sure you want to exit? This action will close the "
					+ "program.\nAll work will be automatically saved to the CSV file.");
			// If the value of result is true, close the main Scene.
			if (result == true) {

				mainWindow.close();
			}
			
		});
		
		// Set the font size and location of the "Exit" button.
		closeButton.setFont(bigFont);
		closeButton.setTranslateY(100);
		
		// Create and format the main Scene.
		StackPane mainLayout = new StackPane();
		mainLayout.setBackground(mainBG);
		mainLayout.getChildren().addAll(dcssLabel, csvButton, addButton, closeButton);
		mainScene = new Scene(mainLayout, 1200, 1000);
		
		// Show and format the mainWindow.
		mainWindow.setScene(mainScene);
		mainWindow.setTitle("Digital Chemical Storage System");
		// Make the main Scene resizable.
		mainWindow.setResizable(true);
		
		// When the user clicks the "x" button to close the program, the ExitBox will display.
		mainWindow.setOnCloseRequest(e -> {
			// Display the ExitBox Scene with a message and initialize the value of result.
			boolean result = ExitBox.display("Exit Dialogue", "Are you sure you want to exit? This action will close the "
					+ "program.\nAll work will be automatically saved to the CSV file.");
			e.consume();
			
			// If the value of result is true, close the program.
			if (result == true) {
				
				mainWindow.close();
			}
			
		});
		
		// Display the main Scene, ie. the program.
		mainWindow.show();
		
	}
	
	/**
	 * The "View File" button is clicked.
	 * 
	 * @author		Catherine Yu
	 * @param file	The CSV file that contains all the information on the chemicals.
	 */
	public void viewButtonClicked(File file) {
		
		// Clear the original contents in the TableView to prevent duplication.
		viewCSVTable.getItems().clear();
		
		// Read the CSV file and display its contents in the TableView.
		try {
			CSVCode.viewFile(file, viewCSVTable);
		} catch (IOException e) {
			// Catch any exceptions that may occur.
			e.printStackTrace();
		}		
	}
	
	/**
	 * The "Add" button is clicked
	 * 
	 * @author Andrey Zinovyev, Catherine Yu, Alex Theaker
	 * @date:  1/7/2021
	 * @param file	The CSV file that contains all the information on the chemicals.
	 * @param date	The automatically generated date.
	 * @return void: Does not return anything.
	 */
	public void addButtonClicked(File file, String date) {
		
		// Create an item to store and add information to the TableView.
		editChemicalTable item = new editChemicalTable();
		
		// Validate the inputs and get the text entered into the text fields.
		if(!chemicalNameInput.getText().equals("")) {
			item.setChemicalName(chemicalNameInput.getText());
		} else {
			//chemicalNameColumn.setStyle("-fx-text-fill: red;");
			item.setChemicalName("BLANK!");
		}
		
		if(Double.parseDouble(chemicalMassInput.getText()) <0) {
			item.setChemicalName("Error with mass!");
		} else {
			item.setChemicalMass(Double.parseDouble(chemicalMassInput.getText()));
		}
		
		if(Double.parseDouble(chemicalVolumeInput.getText()) <0) {
			item.setChemicalName("Error with volume!");
		} else {
			item.setChemicalVolume(Double.parseDouble(chemicalVolumeInput.getText()));
		}
		
		if(!chemicalFormulaInput.getText().equals("")) {
			item.setChemicalFormula(chemicalFormulaInput.getText());
		} else {
			item.setChemicalName("Error with formula!");
		}
		
		if ((chemicalExpiryInput.getText().equals("")) || (chemicalExpiryInput.getText()).length()<8) {	
			item.setChemicalName("Error with expiry date!");
		} else {
			item.setDateOfExpiry(chemicalExpiryInput.getText());
		}
		
		if(Double.parseDouble(chemicalMassInput.getText()) == 0 && Double.parseDouble(chemicalVolumeInput.getText()) == 0) {
			item.setChemicalName(chemicalNameInput.getText() + " (GONE!)");
		}
		// The entry date is also added, and is not validated as it is not a user input.
		item.setDateOfEntry(date);
		
		
		// Get the items in the text field and add them to the table.
		chemicalTable.getItems().add(item);
		
		// Create an array to store all the information on the current chemical.
		String[] chemicalInformation = new String[6];
		
		// Populate the array based on user inputs in the text fields.
		chemicalInformation[0] = chemicalNameInput.getText();
		chemicalInformation[1] = chemicalMassInput.getText();
		chemicalInformation[2] = chemicalVolumeInput.getText();
		chemicalInformation[3] = chemicalFormulaInput.getText();
		// Set the entry date as the current date
		chemicalInformation[4] = date;
		chemicalInformation[5] = chemicalExpiryInput.getText();
		
		try {
			// Add the information in the array into the CSV file.
			CSVCode.addFileData(file, chemicalInformation);
		} catch (IOException e) {
			// Catch any exceptions that may occur.
			e.printStackTrace();
		}
		
		// Clear the text fields.
		chemicalNameInput.clear();
		chemicalMassInput.clear();
		chemicalVolumeInput.clear();
		chemicalFormulaInput.clear();
		chemicalExpiryInput.clear();
		
	}
	
	/**
	 * The "Delete" button is clicked
	 * 
	 * @author	Andrey Zinovyev, Catherine Yu,
	 * @date: 1/7/2021
	 * @param file	The CSV file that contains all the information on the chemicals
	 */
	public void deleteButtonClicked(File file, String date) {
		
		// Create an Observable list.
		ObservableList<editChemicalTable> itemSelected, allItems;
		
		// Get all the items in the table.
		allItems = chemicalTable.getItems();
		
		// Get the selected row.
		itemSelected = chemicalTable.getSelectionModel().getSelectedItems();
		
		// Create an array to store the selected row of information.
		String[] chemicalInformation = new String[6];
		
		// Populate the array based on the information in the selected row.
		chemicalInformation[0] = itemSelected.get(0).getChemicalName();
		chemicalInformation[1] = itemSelected.get(0).getChemicalMass() + "";
		chemicalInformation[2] = itemSelected.get(0).getChemicalVolume() + "";
		chemicalInformation[3] = itemSelected.get(0).getChemicalFormula();
		chemicalInformation[4] = itemSelected.get(0).getDateOfEntry();
		chemicalInformation[5] = itemSelected.get(0).getDateOfExpiry();
		
		try {
			// Delete the selected row from the CSV file.
			CSVCode.deleteFileData(file, chemicalInformation);
		} catch (IOException e) {
			// Catch any exceptions that may occur.
			e.printStackTrace();
		}
		
		// Remove the selected row's items from the TableView.
		itemSelected.forEach(allItems::remove);
		
	}
	
	/**
	 * The "Update" button is clicked
	 * 
	 * @author	Andrey Zinovyev, Catherine Yu, Alex Theaker
	 * @date	1/7/2021
	 * @param file	 The CSV file that contains all the information on the chemicals
	 * @param date	 The current date
	 * @return void: Does not return anything
	 */
	public void updateButtonClicked(File file, String date) {
		
		// Create two Observable lists.
		ObservableList<editChemicalTable> itemSelected, allItems;
				
		// Get all the items in the table.
		allItems = chemicalTable.getItems();
		
		// Get the selected row.
		itemSelected = chemicalTable.getSelectionModel().getSelectedItems();
		
		// Create an array to store the information in the selected row
		String[] originalChemicalInformation = new String[6];
		
		// Populate the array based on the selected row of information in the TableView
		originalChemicalInformation[0] = itemSelected.get(0).getChemicalName();
		originalChemicalInformation[1] = itemSelected.get(0).getChemicalMass() + "";
		originalChemicalInformation[2] = itemSelected.get(0).getChemicalVolume() + "";
		originalChemicalInformation[3] = itemSelected.get(0).getChemicalFormula();
		originalChemicalInformation[4] = itemSelected.get(0).getDateOfEntry();
		originalChemicalInformation[5] = itemSelected.get(0).getDateOfExpiry();
		
		// If the text fields are empty, the information in the selected row will be placed into the text fields.
		if (!chemicalExpiryInput.getText().contains("/")) {
		
			// Store the information in the selected row into the text fields
			chemicalNameInput.setText(originalChemicalInformation[0]);
			chemicalMassInput.setText(originalChemicalInformation[1]);
			chemicalVolumeInput.setText(originalChemicalInformation[2]);
			chemicalFormulaInput.setText(originalChemicalInformation[3]);
			chemicalExpiryInput.setText(originalChemicalInformation[5]);
		
		} else {
			// Otherwise, the chemical's information is updated.
			
			// Create an array to store all the updated information on the current chemical
			String[] updatedChemicalInformation = new String[6];
			
			// Populate the array based on updated information in the text fields
			updatedChemicalInformation[0] = chemicalNameInput.getText();
			updatedChemicalInformation[1] = chemicalMassInput.getText();
			updatedChemicalInformation[2] = chemicalVolumeInput.getText();
			updatedChemicalInformation[3] = chemicalFormulaInput.getText();
			// Set the entry date as the current date
			updatedChemicalInformation[4] = date;
			updatedChemicalInformation[5] = chemicalExpiryInput.getText();
			
			// Create an item to store and add information to the TableView.
			editChemicalTable item = new editChemicalTable();
			
			// Validate the inputs and get the updated information entered into the text fields.
			if (!chemicalNameInput.getText().equals("")) {
				item.setChemicalName(chemicalNameInput.getText());
			} else {
				//chemicalNameColumn.setStyle("-fx-text-fill: red;");
				item.setChemicalName("BLANK!");
			}
			
			if (Double.parseDouble(chemicalMassInput.getText()) <0) {
				item.setChemicalName("Error with mass!");
			} else {
				item.setChemicalMass(Double.parseDouble(chemicalMassInput.getText()));
			}
			
			if(Double.parseDouble(chemicalVolumeInput.getText()) <0) {
				item.setChemicalName("Error with volume!");
			}
			else {
				item.setChemicalVolume(Double.parseDouble(chemicalVolumeInput.getText()));
			}
			
			if (!chemicalFormulaInput.getText().equals("")) {
				item.setChemicalFormula(chemicalFormulaInput.getText());
			} else {
				item.setChemicalName("Error with formula!");
			}
			
			if ((chemicalExpiryInput.getText().equals("")) || (chemicalExpiryInput.getText()).length()<8) {	
				item.setChemicalName("Error with expiry date!");
			} else {
				item.setDateOfExpiry(chemicalExpiryInput.getText());
			}
		
			if(Double.parseDouble(chemicalMassInput.getText()) == 0 && Double.parseDouble(chemicalVolumeInput.getText()) == 0) {
				item.setChemicalName(chemicalNameInput.getText() + " (GONE!)");
			}
			item.setDateOfEntry(date);
			
			// Get the updated items in the text fields and add them to the table.
			chemicalTable.getItems().add(item);
			
			try {
				// Delete the original row of information from the CSV file.
				CSVCode.deleteFileData(file, originalChemicalInformation);
				
				// Add a delay in between deleting and adding to prevent any errors.
				 try {
					TimeUnit.SECONDS.sleep(2);	
				} catch (InterruptedException e) {
					// Catch any exceptions that may occur.
					e.printStackTrace();
				}
				
				// Add the updated information in the array into the CSV file.
				CSVCode.addFileData(file, updatedChemicalInformation);
				
			} catch (IOException e) {
				// Catch any exceptions that may occur.
				e.printStackTrace();
			}
			
			// Clear the text fields.
			chemicalNameInput.clear();
			chemicalMassInput.clear();
			chemicalVolumeInput.clear();
			chemicalFormulaInput.clear();
			chemicalExpiryInput.clear();
			
			// Remove the selected row's items.
			itemSelected.forEach(allItems::remove);
		}
		
	}
	
	/**
	 * Create an ObservableList with an array to add the chemicals into the TableView.
	 * @author	Andrey Zinovyev.
	 * @date 	1/7/2021
	 * @return	An ObservableList with an array to add the chemicals to the TableView
	 */
	public ObservableList<editChemicalTable> addChemicals() {
		
		// Create a new ObservableList with an array to add the chemicals.
		ObservableList<editChemicalTable> chemicals = FXCollections.observableArrayList();
		
		// Return the ObservableList.
		return chemicals;
		
	}

}
