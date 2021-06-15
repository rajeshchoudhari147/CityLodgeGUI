package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Suite;
import model.exceptions.InvalidInputException;
import model.exceptions.CompleteMaintenanceException;
import util.DateTime;
import view.CompleteMaintenance;
import view.ErrorDialog;
import view.SuccessDialog;

public class CompleteMaintenanceController implements EventHandler<ActionEvent>
{
	private CompleteMaintenance theView;

	public CompleteMaintenanceController(CompleteMaintenance theView) 
	{
		this.theView = theView;
	}
    
	@Override
	public void handle(ActionEvent event) 
	{
		try
		{
			//if suite maintainance is completed display success message
			((Suite) theView.getRoom()).completeMaintenance(DateTime.stringToDateTime(theView.getMaintenanceCompletionDate()));
			new SuccessDialog("Maintainance completed.");
		}
		catch (Exception e)
		{
			theView.setMaintenanceCompletionDate("");
			if (!(e instanceof CompleteMaintenanceException))
				new ErrorDialog(new InvalidInputException().getMessage());
			else
				new ErrorDialog(e.getMessage());
		}
	}
}
