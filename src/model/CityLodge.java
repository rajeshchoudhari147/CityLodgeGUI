package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.exceptions.InvalidNumberOfBedsException;
import model.exceptions.InvalidIDException;

public class CityLodge 
{
	private static CityLodge single_instance = null;
	private ObservableList<AbstractRoom> roomList = FXCollections.observableArrayList();

	// logic for making the class a singleton
	public static CityLodge getInstance() 
	{
		if (single_instance == null)
		{
			single_instance = new CityLodge();
		}
		return single_instance;
	}
	
	private CityLodge()
	{

	}
	
	public ObservableList<AbstractRoom> getRoomList() 
	{
		return roomList;
	}

	public void setRoomList(ObservableList<AbstractRoom> roomList) 
	{
		this.roomList = roomList;
	}

	public void addRoom(String roomID, int noOfBeds, String featureSummary) throws InvalidNumberOfBedsException, InvalidIDException
	{
		// not allowing vehicle id as just R_ or S_ Should be more than 2 characters
		// but should contain either R_ or S_
		if (!((roomID.contains("S")) || (roomID.contains("R"))) || (roomID.length() < 1) || (roomID.length() == 1)) 
		{
			throw new InvalidIDException("Please enter ROOM ID in the format: R_000 for Room & S_000 for Suite.");
		}

		for (int i = 0; i <= roomList.size() - 1; i++) 
		{
			if (roomList.get(i) != null)
			{
				if (roomList.get(i).getRoomID().equals(roomID))
				{
					throw new InvalidIDException("Room ID already exists in the Database.");
				}
			}
		}

		// checking number of beds condition for a standard room
		if (!(noOfBeds == 1 || noOfBeds == 2 || noOfBeds == 4) && roomID.contains("R"))
		{
			throw new InvalidNumberOfBedsException("A Standard Room can only have 1, 2 or 4 beds.");
		}

		if (!(noOfBeds == 6) && roomID.contains("S")) 
		{
			throw new InvalidNumberOfBedsException("A Suite can only have 6 beds.");

		}
		if (roomID.contains("S")) 
		{
			roomList.add(new Suite(roomID, noOfBeds, featureSummary, "Suite" , "Available", "no image"));
		}
		else
			roomList.add(new StandardRoom(roomID, noOfBeds, featureSummary, "Room" , "Available", "no image"));

	}
}
