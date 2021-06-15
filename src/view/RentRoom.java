package view;

import controller.RentRoomController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.AbstractRoom;

public class RentRoom extends GridPane 
{
	private AbstractRoom abstractRoom;
	private TextField customerID = new TextField();
	private TextField rentDate = new TextField();
	private TextField numberOfDays = new TextField();

	public RentRoom(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		this.setHgap(5.5);
		this.setVgap(35.5);

		// Place nodes in the pane
		this.add(new Label("Enter Customer id:"), 7, 0);
		this.add(customerID, 10, 0);

		this.add(new Label("Enter Rent Date:"), 7, 1);
		this.add(rentDate, 10, 1);

		this.add(new Label("Enter the number of days:"), 7, 2);
		this.add(numberOfDays, 10, 2);

		Button rent = new Button("Rent Room");
		this.add(rent, 8, 3);

		RentRoomController r = new RentRoomController(this);
		rent.setOnAction(r);

	}

	public AbstractRoom getRoom() 
	{
		return abstractRoom;
	}

	public void setRoom(AbstractRoom abstractRoom)
	{
		this.abstractRoom = abstractRoom;
	}

	public String getCustomerID() 
	{
		return this.customerID.getText();
	}

	public String getRentDate()
	{
		return this.rentDate.getText();
	}

	public String getNumberOfDays()
	{
		return this.numberOfDays.getText();
	}

	public void setCustomerID(String customerID)
	{
		this.customerID.setText(customerID);
	}

	public void setRentDate(String rentDate)
	{
		this.rentDate.setText(rentDate);
	}

	public void setNumberOfDays(String numberOfDays)
	{
		this.numberOfDays.setText(numberOfDays);
	}
}