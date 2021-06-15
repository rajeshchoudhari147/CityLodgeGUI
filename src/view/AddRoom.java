package view;

import controller.AddRoomController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddRoom extends GridPane 
{
	TextField roomID = new TextField();
	TextField noOfBeds = new TextField();
	TextField featureSummary = new TextField();

	public AddRoom() 
	{
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);
		this.setMaxSize(900, 900);

		Button add = new Button("Add Room");

		Label label = new Label();
		this.add(label, 11, 1);

		AddRoomController addRoomController = new AddRoomController(this);
		add.setOnAction(addRoomController);

		// Place nodes in the pane
		this.add(new Label("Enter the Room ID:"), 7, 0);
		this.add(roomID, 11, 0);

		this.add(new Label("Enter Number of Beds"), 7, 1);
		this.add(noOfBeds, 11, 1);

		this.add(new Label("Enter Features:"), 7, 2);
		this.add(featureSummary, 11, 2);

		this.add(add, 9, 5);
	}

	public String getRoomID() 
	{
		return this.roomID.getText();
	}

	public String getNoOfBeds() 
	{
		return this.noOfBeds.getText();
	}

	public String getFeatureSummary() 
	{
		return this.featureSummary.getText();
	}
}