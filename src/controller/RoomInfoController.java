package controller;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.AbstractRoom;
import model.Start;
import view.ErrorDialog;
import view.RoomDetailWindow;

public class RoomInfoController implements EventHandler<ActionEvent>
{
	private AbstractRoom abstractRoom;

	public RoomInfoController(AbstractRoom abstractRoom) 
	{
		this.abstractRoom = abstractRoom;
	}

	@Override
	public void handle(ActionEvent event) 
	{
		RoomDetailWindow roomDetail = null;
		try 
		{
			roomDetail = new RoomDetailWindow(abstractRoom);
		}
		catch (FileNotFoundException e) 
		{
			new ErrorDialog(e.getMessage());
		}
		Start.getRoot().setCenter(roomDetail);
	}
}
