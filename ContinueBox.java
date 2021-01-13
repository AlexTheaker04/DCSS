package application;

/*
 * Date: 1/11/2021
 * Title: ContinueBox
 * Author: Catherine Yu, Andrey Zinovyev
 * Description: A class that will create a pop up window with a "continue" button 
 * for the user to click once they have updated the required information
 */

// Imports
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ContinueBox {
	
	// Static variable.
	static boolean answer;
	
    /**
     * 
     * @author  Andrey Zinovyev, Catherine Yu
     * @param title     The title of the window
     * @param message   A message that appears in the window
     * @return          Returns 'answer' as true when the continue button is clicked
     */
	public static boolean display(String title, String message) {
		
		// Create a new stage.
		Stage popWindow = new Stage();
		
		// Setting it up.
		popWindow.setTitle(title);
		popWindow.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		// Set the text alignment to center of the label.
		label.setTextAlignment(TextAlignment.CENTER);
		
		// Create the continue button
		Button continueButton = new Button("Continue.");
		
		// When the "continue" button is clicked, answer will change to true.
		continueButton.setOnAction(e -> {
			answer = true;
			popWindow.close();
		});
		
		// New HBox layout with label.
		HBox exitTopMenu = new HBox();
		exitTopMenu.getChildren().add(label);
		exitTopMenu.setAlignment(Pos.CENTER);
		
		// New HBox layout with yes and no buttons.
		HBox exitBottomMenu = new HBox();
		exitBottomMenu.getChildren().addAll(continueButton);
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
		
		// Returns the value of answer.
		return answer;
		
	}
	
}
