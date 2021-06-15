package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.exceptions.InvalidInputException;
import model.exceptions.ReturnException;
import util.DateTime;
import view.ErrorDialog;
import view.ReturnRoom;
import view.SuccessDialog;

public class ReturnRoomController implements EventHandler<ActionEvent> 
{
	private ReturnRoom theView;

	public ReturnRoomController(ReturnRoom theView) 
	{
		this.theView = theView;
	}

	@Override
	public void handle(ActionEvent event) 
	{
		try 
		{
			// call return method of abstractRoom, if returned successfully show success
			theView.getRoom().returnRoom(DateTime.stringToDateTime(theView.getReturnDate()));
			new SuccessDialog("Room was returned successfully");
		} 
		catch (Exception e) 
		{
			// clear the form,if data entered was in valid format and room could not be
			// returned show return exception else display invalid data entered message
			theView.setReturnDate("");

			if (!(e instanceof ReturnException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());
		}
	}
}
