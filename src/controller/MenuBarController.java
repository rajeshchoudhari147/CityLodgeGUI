package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.SaveToText;
import model.Start;
import model.database.AddRoomData;
import view.AddRoom;
import view.ErrorDialog;
import view.HomePage;

public class MenuBarController implements EventHandler<ActionEvent>
{
	@Override
	public void handle(ActionEvent event)
	{
		Button button = (Button) event.getSource();
		String buttonName = button.getText();

		switch (buttonName)
		{
			case "Back":
			try 
			{
				// display home page on press of back button
				Start.getRoot().setCenter(HomePage.getInstance());
			}
			catch (FileNotFoundException e) 
			{
				new ErrorDialog(e.getMessage());
			}
			break;
			
			case "Add Room":
				Start.getRoot().setCenter(new AddRoom());
				break;
		
			case "Quit":
				Platform.exit();
				break;
		
			case "Save To Database":
				new AddRoomData();
				break;
			
			case "Import data":
			try 
			{
				// open a file chooser, if file is selected read it
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(Start.getPrimaryStage());
				if (fileChooser != null)
					SaveToText.readFromFile(file.getAbsolutePath());
			}
			catch (Exception e)
			{
			}
			break;
		
			case "Export data":
			try 
			{
				// select a directory to save textfile with exported data
				DirectoryChooser fileChooser = new DirectoryChooser();
				File file = fileChooser.showDialog(Start.getPrimaryStage());
				if (fileChooser != null)
					SaveToText.writeToFile(file.getAbsolutePath());
			} 
			catch (Exception e)
			{
			}
			break;
		}
	}
}
