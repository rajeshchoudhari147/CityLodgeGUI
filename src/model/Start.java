package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.database.SelectRoom;
import view.ErrorDialog;
import view.HomePage;
import view.MenuBar;

public class Start extends Application 
{
	private static BorderPane root = new BorderPane();
	private static Stage primaryStage;

	public static BorderPane getRoot() 
	{
		return root;
	}

	public static Stage getPrimaryStage() 
	{
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage)
	{
		Start.primaryStage = primaryStage;
	}

	@Override
	public void start(Stage primaryStage) 
	{
		try
		{
			// uncomment to restore original data in database
			//new DeleteTable();
			//new CreateTable();
			//new AddDefaultValues();
			new SelectRoom();
			MenuBar menu = new MenuBar();
			root.setTop(menu);
			root.setCenter(HomePage.getInstance());
			Scene scene = new Scene(root, 900, 900);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e) 
		{
			new ErrorDialog(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}