package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.CityLodge;
import model.Start;
import model.exceptions.InvalidNumberOfBedsException;
import model.exceptions.InvalidIDException;
import model.exceptions.InvalidInputException;
import view.AddRoom;
import view.ErrorDialog;
import view.HomePage;
import view.SuccessDialog;

public class AddRoomController implements EventHandler<ActionEvent> 
{
	private CityLodge cityLodge = CityLodge.getInstance();
	private AddRoom addRoomView;
    
	public AddRoomController(AddRoom addRoomView)
	{
		this.addRoomView = addRoomView;
	}
	
    @Override
	public void handle(ActionEvent event) 
	{
		try 
		{
			cityLodge.addRoom(this.addRoomView.getRoomID(),	Integer.parseInt(this.addRoomView.getNoOfBeds()),this.addRoomView.getFeatureSummary());
			new SuccessDialog("Room added.");
			Start.getRoot().setCenter(HomePage.getInstance());
		}
		catch (Exception e) 
		{
			if (!(e instanceof InvalidIDException || e instanceof InvalidNumberOfBedsException))
			{
				new ErrorDialog(new InvalidInputException().getMessage());
			}
			else
			{
				new ErrorDialog(e.getMessage());
			}
		}
	}
}
