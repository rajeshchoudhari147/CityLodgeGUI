package controller;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import model.AbstractRoom;
import model.StandardRoom;
import model.Start;
import model.Suite;
import model.exceptions.CompleteMaintenanceException;
import model.exceptions.PerformMaintenanceException;
import view.AddRoom;
import view.CompleteMaintenance;
import view.ErrorDialog;
import view.RentRoom;
import view.ReturnRoom;
import view.RoomDetailWindow;
import view.SuccessDialog;

public class RoomDetailController implements EventHandler<ActionEvent>
{
	@SuppressWarnings("rawtypes")
	Dialog dialog = new Dialog();

	private AbstractRoom abstractRoom;

	public RoomDetailController(RoomDetailWindow theView)
	{
		this.abstractRoom = theView.getRoom();

		dialog.setResizable(false);
		ButtonType buttonTypeOk = new ButtonType("Close", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
	}

	@Override
	public void handle(ActionEvent event) 
	{
		Button button = (Button) event.getSource();
		String buttonName = button.getText();

		switch (buttonName) 
		{
			case "Rent Room":
				// display rent vehicle page
				RentRoom rentRoom = new RentRoom(abstractRoom);
				dialog.getDialogPane().setContent(rentRoom);
				dialog.showAndWait();
				break;
			
			case "Return Room":
				// display return vehicle page
				ReturnRoom returnRoom = new ReturnRoom(abstractRoom);
				dialog.getDialogPane().setContent(returnRoom);
				dialog.showAndWait();
				break;
			
			case "Complete Maintainance":
				// if vehicle is a van display complete maintenance page for user to input
				// maintenance completion date
				if (abstractRoom instanceof Suite)
				{
					CompleteMaintenance completeMaintainance = new CompleteMaintenance(abstractRoom);
					dialog.getDialogPane().setContent(completeMaintainance);
					dialog.showAndWait();
				}
				else 
				{
					try 
					{
						// if room is standard room call complete maintenance method of standard room, if successful
						// display success message
						((StandardRoom) abstractRoom).completeMaintenance();
						new SuccessDialog("Maintainance completed");
					} 
					catch (CompleteMaintenanceException | FileNotFoundException e) 
					{
						new ErrorDialog(new CompleteMaintenanceException().getMessage());
					}
				}
				break;
			
			case "Maintain Room":
				try
				{
					// call perform maintenance method of abstract room, if successful display success
					// message
					this.abstractRoom.performMaintenance();
					new SuccessDialog("Room is now under maintainance");
				} 
				catch (PerformMaintenanceException | FileNotFoundException e)
				{
					new ErrorDialog(new PerformMaintenanceException().getMessage());
				}
				break;
			
			case "Add Room":
				Start.getRoot().setCenter(new AddRoom());
			break;
		}
	}

}
