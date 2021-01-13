package application;

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


public class Backup extends Application {
	
	// Create a Stage and Scenes.
	Stage mainWindow;
	Scene mainScene, viewScene, editScene, addScene;
	
	// Create TextFields.
	TextField chemicalNameInput, chemicalMassInput, chemicalVolumeInput, chemicalFormulaInput, chemicalEntryInput, chemicalExpiryInput;
	
	// Create two TableViews.
	TableView<editChemicalTable> chemicalTable;
	TableView<editChemicalTable> viewCSVTable;
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	/** @author: Andrey Zinovyev.
	 * @date: 1/7/2021.
	 * 
	 * start() Displays the starting screen as well as contains all the other scenes in the program.
	 * 
	 * start() Contains the scenes which are used to create the program, it contains all the necessary
	 * methods and functions used to make the program run. This method is also responsible for all the
	 * 
	 * @param Stage mainWindow: Takes in mainWindow.
	 * 
	 * @throws Exception: If something goes wrong during run, program will not crash.
	 * 
	 * @return void: Does not return anything.
	 */
	
	@Override
	public void start(Stage mainWindow) throws Exception{
		
		// Create backgrounds.
		BackgroundFill lightGreenBG = new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill lightBlueBG = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill lightYellowBG = new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
		
		Background mainBG = new Background(lightGreenBG);
		Background viewBG = new Background(lightBlueBG);
		Background addBG = new Background(lightYellowBG);
		
		// Custom fonts.
		Font hugeFont = Font.font("Arial", FontWeight.BOLD, 80);
		Font bigFont = Font.font("Courier New", FontWeight.BOLD, 36);
		Font mediumFont = Font.font("Courier New", FontWeight.BOLD, 20);
		
		// Create Title label.
		Label dcssLabel = new Label("Digital Chemical\nStorage System");
		dcssLabel.setFont(hugeFont);
		dcssLabel.setTextAlignment(TextAlignment.CENTER);
		dcssLabel.setTranslateY(-350);
		dcssLabel.setTextFill(Color.color(0, 0, 1));
		
		// Generate a CSV file to contain the information if one does not already exist
		File file = CSVCode.generateFile();
		
		// Obtain the current date and set the entry date as the current date
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime currentDate = LocalDateTime.now();  
		String date = format.format(currentDate);
		
		// VIEW FILE BUTTON AND SCENE
		
		// Create the 'View File' button.
		Button csvButton = new Button("View/Edit File");
		csvButton.setOnAction(e -> {
			// Set the scene as the viewScene
			mainWindow.setScene(viewScene);
		});
		
		csvButton.setFont(bigFont);
		csvButton.setTranslateY(-100);
		
		// Create a column for the chemical name
		TableColumn<editChemicalTable, String> chemicalName = new TableColumn<>("Chemical Name");
		chemicalName.setMinWidth(200);
		chemicalName.setCellValueFactory(new PropertyValueFactory<>("chemicalName"));
		
		// Create a column for the chemical mass
		TableColumn<editChemicalTable, String> chemicalMass = new TableColumn<>("Mass");
		chemicalMass.setMinWidth(200);
		chemicalMass.setCellValueFactory(new PropertyValueFactory<>("chemicalMass"));
		
		// Create a column for the chemical volume
		TableColumn<editChemicalTable, String> chemicalVolume = new TableColumn<>("Volume");
		chemicalVolume.setMinWidth(200);
		chemicalVolume.setCellValueFactory(new PropertyValueFactory<>("chemicalVolume"));
		
		// Create a column for the chemical formula
		TableColumn<editChemicalTable, String> chemicalFormula = new TableColumn<>("Formula");
		chemicalFormula.setMinWidth(200);
		chemicalFormula.setCellValueFactory(new PropertyValueFactory<>("chemicalFormula"));
		
		// Create a column for the entry date
		TableColumn<editChemicalTable, String> chemicalEntry = new TableColumn<>("Date of Entry");
		chemicalEntry.setMinWidth(200);
		chemicalEntry.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
		
		// Create a column for the expiry date
		TableColumn<editChemicalTable, String> chemicalExpiry = new TableColumn<>("Date of Expiry");
		chemicalExpiry.setMinWidth(200);
		chemicalExpiry.setCellValueFactory(new PropertyValueFactory<>("dateOfExpiry"));
		
		// Format the TableView with the created columns
		viewCSVTable = new TableView<>();
		viewCSVTable.setItems(addChemicals());
		viewCSVTable.getColumns().addAll(chemicalName, chemicalMass, chemicalVolume, chemicalFormula, 
				chemicalEntry, chemicalExpiry);
		
		// Read the CSV file and display its contents.
		CSVCode.viewFile(file, viewCSVTable);
		
		// Create 'Back' button for view file scene.
		Button viewBackButton = new Button("Back");
		viewBackButton.setFont(mediumFont);
		viewBackButton.setOnAction(e -> {
			// Set the scene as the main scene
			mainWindow.setScene(mainScene);
		});
		
		// Create the "view file" scene.
		HBox viewMiddleMenu = new HBox();
		viewMiddleMenu.getChildren().add(viewCSVTable);
		
		HBox viewBottomMenu = new HBox();
		viewBottomMenu.getChildren().addAll(viewBackButton);
		
		BorderPane viewBorderPane = new BorderPane();
		viewBorderPane.setCenter(viewMiddleMenu);
		viewBorderPane.setBottom(viewBottomMenu);
		
		viewBorderPane.setBackground(viewBG);
		viewScene = new Scene(viewBorderPane, 1200, 1000);
		
		// ADD/DELETE BUTTON AND SCENE.
		
		// Create the 'Add/Delete' button.
		Button addButton = new Button("Add/Delete");
		addButton.setOnAction(e -> {
			
			// Code Here.
			
			mainWindow.setScene(addScene);
		});
		
		addButton.setFont(bigFont);
		
		// Name column.
		TableColumn <editChemicalTable, String> chemicalNameColumn = new TableColumn<>("Chemical Name");
		chemicalNameColumn.setMinWidth(200);
		chemicalNameColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalName"));
		
		// Mass column.
		TableColumn <editChemicalTable, Double> chemicalMassColumn = new TableColumn<>("Mass");
		chemicalMassColumn.setMinWidth(200);
		chemicalMassColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalMass"));
		
		// Volume column.
		TableColumn <editChemicalTable, Double> chemicalVolumeColumn = new TableColumn<>("Volume");
		chemicalVolumeColumn.setMinWidth(200);
		chemicalVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalVolume"));
		
		// Formula column.
		TableColumn <editChemicalTable, String> chemicalFormulaColumn = new TableColumn<>("Formula");
		chemicalFormulaColumn.setMinWidth(200);
		chemicalFormulaColumn.setCellValueFactory(new PropertyValueFactory<>("chemicalFormula"));
		
		// Date of entry column.
		TableColumn <editChemicalTable, String> chemicalEntryColumn = new TableColumn<>("Date of Entry");
		chemicalEntryColumn.setMinWidth(200);
		chemicalEntryColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfEntry"));
		
		// Date of expiry column.
		TableColumn <editChemicalTable, String> chemicalExpiryColumn = new TableColumn<>("Date of Expiry");
		chemicalExpiryColumn.setMinWidth(200);
		chemicalExpiryColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfExpiry"));
		
		// Chemical Name.
		chemicalNameInput = new TextField();
		chemicalNameInput.setPromptText("Chemical Name");
		chemicalNameInput.setMinWidth(100);
		
		// Chemical Mass.
		chemicalMassInput = new TextField();
		chemicalMassInput.setPromptText("Mass");
		chemicalMassInput.setMinWidth(100);
		
		// Chemical Volume.
		chemicalVolumeInput = new TextField();
		chemicalVolumeInput.setPromptText("Volume");
		chemicalVolumeInput.setMinWidth(100);
		
		// Chemical Formula.
		chemicalFormulaInput = new TextField();
		chemicalFormulaInput.setPromptText("Formula");
		chemicalFormulaInput.setMinWidth(100);
		
		// Chemical Entry Date.
		chemicalEntryInput = new TextField();
		chemicalEntryInput.setPromptText("Entry Date");
		chemicalEntryInput.setMinWidth(100);
		
		// Chemical Expiry Date.
		chemicalExpiryInput = new TextField();
		chemicalExpiryInput.setPromptText("Expiry Date");
		chemicalExpiryInput.setMinWidth(100);
		
		// Buttons (Add/Delete).
		Button addChemicalButton = new Button("Add");
		addChemicalButton.setFont(mediumFont);
		addChemicalButton.setOnAction(e -> addButtonClicked(file, date));
		
		Button deleteChemicalButton = new Button("Delete");
		deleteChemicalButton.setFont(mediumFont);
		deleteChemicalButton.setOnAction(e -> deleteButtonClicked(file));
		
		Button updateChemicalButton = new Button("Update");
		updateChemicalButton.setFont(mediumFont);
		//updateChemicalButton.setOnAction(e -> updateChemicalButton(file));
		
		// Create 'Back' button for view chemicals scene.
		Button addBackButton = new Button("Back");
		addBackButton.setFont(mediumFont);
		addBackButton.setOnAction(e -> {
			
			// Code Here.
			
			mainWindow.setScene(mainScene);
		});
		
		HBox textField = new HBox();
		textField.setPadding(new Insets(10, 10, 10, 10));
		textField.setSpacing(10);
		textField.getChildren().addAll(addBackButton, chemicalNameInput, chemicalMassInput, chemicalVolumeInput, chemicalFormulaInput,
				chemicalExpiryInput, addChemicalButton, deleteChemicalButton, updateChemicalButton);
		
		chemicalTable = new TableView<>();
		chemicalTable.setItems(addChemicals());
		chemicalTable.getColumns().addAll(chemicalNameColumn, chemicalMassColumn, chemicalVolumeColumn, chemicalFormulaColumn, 
				chemicalEntryColumn, chemicalExpiryColumn);
		
		// Read the CSV file and display its contents.
		CSVCode.viewFile(file, chemicalTable);
		
		// Create add chemical scene.
		HBox addMiddleMenu = new HBox();
		addMiddleMenu.getChildren().addAll(chemicalTable);
		
		HBox addBottomMenu = new HBox();
		addBottomMenu.getChildren().addAll(textField);
		
		BorderPane addBorderPane = new BorderPane();
		addBorderPane.setCenter(addMiddleMenu);
		addBorderPane.setBottom(addBottomMenu);
		
		addBorderPane.setBackground(addBG);
		addScene = new Scene(addBorderPane, 1200, 1000);
		
		// MAIN BUTTONS AND SCENE.
		
		Button closeButton = new Button("Exit");
		closeButton.setOnAction(e -> {
			boolean result = ExitBox.display("Exit Dialogue", "Are you sure you want to exit? This action will close the "
					+ "program.\nAll work will be automatically saved to the CSV file.");
			if (result == true) {
				
				// ANY SAVES HERE.
				
				mainWindow.close();
			}
			
		});
		
		closeButton.setFont(bigFont);
		closeButton.setTranslateY(100);
		
		// Create main scene.
		StackPane mainLayout = new StackPane();
		mainLayout.setBackground(mainBG);
		mainLayout.getChildren().addAll(dcssLabel, csvButton, addButton, closeButton);
		mainScene = new Scene(mainLayout, 1200, 1000);
		
		// Show the mainWindow
		mainWindow.setScene(mainScene);
		mainWindow.setTitle("Digital Chemical Storage System");
		mainWindow.setResizable(true); // Makes it not resizable.
		mainWindow.setOnCloseRequest(e -> {
			boolean result = ExitBox.display("Exit Dialogue", "Are you sure you want to exit? This action will close the "
					+ "program.\nAll work will be automatically saved to the CSV file.");
			e.consume();
			if (result == true) {
				
				// ANY SAVES HERE.
				
				mainWindow.close();
			}
			
		});
		
		mainWindow.show();
		
	}
	
	/** @author: Andrey Zinovyev.
	 * @date: 1/7/2021.
	 * 
	 * start() Displays the starting screen as well as contains all the other scenes in the program.
	 * 
	 * start() Contains the scenes which are used to create the program, it contains all the necessary
	 * methods and functions used to make the program run. This method is also responsible for all the
	 * 
	 * @param Stage mainWindow: Takes in mainWindow.
	 * 
	 * @throws Exception: If something goes wrong during run, program will not crash.
	 * 
	 * @return void: Does not return anything.
	 */
	
	public void addButtonClicked(File file, String date) {
		
		editChemicalTable item = new editChemicalTable();
		
		// Get the text entered into the text fields.
		item.setChemicalName(chemicalNameInput.getText());
		item.setChemicalMass(Double.parseDouble(chemicalMassInput.getText()));
		item.setChemicalVolume(Double.parseDouble(chemicalVolumeInput.getText()));
		item.setChemicalFormula(chemicalFormulaInput.getText());
		// item.setDateOfEntry(chemicalEntryInput.getText());
		item.setDateOfEntry(date);
		item.setDateOfExpiry(chemicalExpiryInput.getText());
		
		// Get the items in the text field and add them to the table.
		chemicalTable.getItems().add(item);
		
		// Create an array to store all the information on the current chemical
		String[] chemicalInformation = new String[6];
		// Populate the array based on user inputs in the text fields
		chemicalInformation[0] = chemicalNameInput.getText();
		chemicalInformation[1] = chemicalMassInput.getText();
		chemicalInformation[2] = chemicalVolumeInput.getText();
		chemicalInformation[3] = chemicalFormulaInput.getText();
		// Set the entry date as the current date
		chemicalInformation[4] = date;
		chemicalInformation[5] = chemicalExpiryInput.getText();
		
		try {
			// Add the information in the array into the CSV file
			CSVCode.addFileData(file, chemicalInformation);
		} catch (IOException e) {
			// Catch any exceptions
			e.printStackTrace();
		}
		
		// Clear the text fields.
		chemicalNameInput.clear();
		chemicalMassInput.clear();
		chemicalVolumeInput.clear();
		chemicalFormulaInput.clear();
		chemicalEntryInput.clear();
		chemicalExpiryInput.clear();
		
	}
	
	// Delete button Clicked.
	public void deleteButtonClicked(File file) {
		
		// Create an Observable list.
		ObservableList<editChemicalTable> itemSelected, allItems;
		
		// Get all the items in the table.
		allItems = chemicalTable.getItems();
		
		// Get the selected row.
		itemSelected = chemicalTable.getSelectionModel().getSelectedItems();
		System.out.println(itemSelected);
		
		// Put the information in the selected row into the text fields
		
		
		// Create an array to store the information in the text fields
		String[] chemicalInformation = new String[6];
		// Populate the array based on the information in the text fields
		chemicalInformation[0] = chemicalNameInput.getText();
		chemicalInformation[1] = chemicalMassInput.getText();
		chemicalInformation[2] = chemicalVolumeInput.getText();
		chemicalInformation[3] = chemicalFormulaInput.getText();
		chemicalInformation[4] = chemicalEntryInput.getText();
		chemicalInformation[5] = chemicalExpiryInput.getText();
		
		try {
			// Delete the selected row from the CSV file
			CSVCode.deleteFileData(file, chemicalInformation);
		} catch (IOException e) {
			// Catch any exceptions
			e.printStackTrace();
		}
		
		// Remove the selected row's items.
		itemSelected.forEach(allItems::remove);
		
	}
	
	// Get all of the products.
	public ObservableList<editChemicalTable> addChemicals() {
		
		// Create a new Observable list with an array to add the chemicals.
		ObservableList<editChemicalTable> chemicals = FXCollections.observableArrayList();
		
		// Add one placeholder element to the table.
		//chemicals.add(new editChemicalTable("----", 0000, 0000, "----", "----", "----"));
		
		// Return the chemicals.
		return chemicals;
		
	}

}