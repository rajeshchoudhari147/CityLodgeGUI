package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.exceptions.InvalidInputException;
import model.exceptions.RentException;
import util.DateTime;
import view.ErrorDialog;
import view.RentRoom;
import view.SuccessDialog;

public class RentRoomController implements EventHandler<ActionEvent> 
{
    private RentRoom theView;

	public RentRoomController(RentRoom theView) 
	{
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) 
	{
		try 
		{
			// call rent method of abstractRoom, if rented successfully show success
			theView.getRoom().rent(
					theView.getCustomerID(), 
					DateTime.stringToDateTime(theView.getRentDate()),
					Integer.parseInt(theView.getNumberOfDays()));
			new SuccessDialog("Room was rented.");
		}
		catch (Exception e) 
		{
			// clear the form,if data entered was in valid format and room could not be
			// rented show rent exception else display invalid data entered message
			theView.setCustomerID("");
			theView.setRentDate("");
			theView.setNumberOfDays("");
			if (!(e instanceof RentException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());
		}
	}
}
