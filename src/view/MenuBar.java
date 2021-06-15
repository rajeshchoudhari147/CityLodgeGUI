package view;

import controller.MenuBarController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox
{
	private Button back = new Button("Back");
	private Button addRoom = new Button("Add Room");
	private Button importData = new Button("Import data");
	private Button exportData = new Button("Export data");
	private Button saveData = new Button("Save To Database");
	private Button quit = new Button("Quit");

	public MenuBar() 
	{
		MenuBarController handler = new MenuBarController();
		back.setOnAction(handler);
		addRoom.setOnAction(handler);
		importData.setOnAction(handler);
		exportData.setOnAction(handler);
		saveData.setOnAction(handler);
		quit.setOnAction(handler);

		this.getChildren().addAll(back, addRoom, importData, exportData, saveData, quit);
		this.setPadding(new Insets(20));
		this.setSpacing(15);
		this.setAlignment(Pos.TOP_CENTER);
	}
}
