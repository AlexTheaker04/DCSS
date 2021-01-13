package application;

/*
 * Date: 1/7/2021
 * Title: ExitBox
 * Author: Andrey Zinovyev
 * Description: A class that will handle the pop up window which appears when the 'Exit' or 
 * the 'x' buttons are clicked in the 'DCSS' class.
 */

// Imports.
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ExitBox {
	
	// Static variable.
	static boolean answer;
	
	/* @author: Andrey Zinovyev.
	 * @date: 1/7/2021.
	 * 
	 * display() displays the main window of the pop-up window that appears when program is requested to close.
	 * 
	 * display() When the program is requested to close, either through the 'Exit' or the 'X' buttons, a pop-up
	 * window will show up for confirmation. When a user chooses their choice, the 'answer' variable will change and 
	 * different action will be performed depending on it.
 	 *
	 * @param String title: Takes in the title of the window from 'DCSS' class.
	 * 		  String message: Takes in the message that appears on the window from the 'DCSS' class.
	 * 
	 * @return boolean: Returns 'answer' as true or false depending which button is clicked.
	 */
	public static boolean display(String title, String message) {
		
		// Create a new stage.
		Stage popWindow = new Stage();
		
		// Makes it so all other inputs are blocked until this window is resolved.
		popWindow.initModality(Modality.APPLICATION_MODAL);
		
		// Setting it up.
		popWindow.setTitle(title);
		popWindow.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		// Set the text alignment to center of the label.
		label.setTextAlignment(TextAlignment.CENTER);
		
		// Create the yes and no buttons.
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		// When yes button is clicked, answer will change to yes.
		yesButton.setOnAction(e -> {
			answer = true;
			popWindow.close();
		});
		
		// When no button is clicked, answer will change to no.
		noButton.setOnAction(e -> {
			answer = false;
			popWindow.close();
		});
		
		// New HBox layout with label.
		HBox exitTopMenu = new HBox();
		exitTopMenu.getChildren().add(label);
		exitTopMenu.setAlignment(Pos.CENTER);
		
		// New HBox layout with yes and no buttons.
		HBox exitBottomMenu = new HBox();
		exitBottomMenu.getChildren().addAll(yesButton, noButton);
		exitBottomMenu.setAlignment(Pos.CENTER);
		
		// New BorderPane, has label on the top and buttons on the bottom.
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(exitTopMenu);
		borderPane.setBottom(exitBottomMenu);
		
		// Make a new scene.
		Scene mainScene = new Scene(borderPane, 400, 100);
		
		// Set the window scene to the main scene.
		popWindow.setScene(mainScene);
		
		// Show and wait for response.
		popWindow.showAndWait();
		
		// Returns the button clicked.
		return answer;
		
	}
	
}
