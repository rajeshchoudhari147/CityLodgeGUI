package model;

import java.io.FileNotFoundException;
import java.util.Formatter;

import model.exceptions.CompleteMaintenanceException;
import util.DateTime;
import view.RoomDetailWindow;

public class Suite extends AbstractRoom
{
	private static final float SIX_BED = 999;
	private static final float LATE_FEE = 1099;
	private DateTime lastMaintenanceDate;
	public Formatter fmt;
	
	public Suite(String roomID, int noOfBeds, String featureSummary, String roomType, String roomStatus, String imageName)
	{
		super(roomID, noOfBeds, featureSummary, "Suite", roomStatus, imageName);
	}
	
	public DateTime getLastMaintenanceDate() 
	{
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(DateTime lastMaintenanceDate) 
	{
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	
	public void completeMaintenance(DateTime date) throws CompleteMaintenanceException, FileNotFoundException
	{
		// condition to check whether suite is under maintenance
		if (this.getRoomStatus() == "Maintenance")
		{
			// setting last maintenance date for a suite
			this.setLastMaintenanceDate(date);
			this.setRoomStatus("Available");
			Start.getRoot().setCenter(new RoomDetailWindow(this));
		} 
		else
		{
			throw new CompleteMaintenanceException();
		}
	}
	
	@Override
	public float calculateRentalFee()
	{
		rentDays();
		return this.getDaysBeforeEstimate() * SIX_BED;
	}

	@Override
	public float calculateLateFee() 
	{
		rentDays();
		return (this.getDaysAfterEstimate() * LATE_FEE);
	}
	
	public String toString()
	{
		String s =  getRoomID() + ":" + 
					getNoOfBeds() + ":" +
					getRoomType() + ":" +
					getRoomStatus() + ":" +
					getLastMaintenanceDate() + ":" +
					getSummary() + ":" +
					getImageName();;
		return s;
	}
	
	public String getDetails()
	{
		StringBuilder sbuf = new StringBuilder();
		fmt = new Formatter(sbuf);
		fmt.format("%-26s %-10s\n", "Room ID:", getRoomID());
		fmt.format("%-26s %-10s\n", "Number of Beds:", getNoOfBeds());
		fmt.format("%-26s %-10s\n", "Feature Summary:", getSummary());
		fmt.format("%-26s %-10s\n", "Status:", getRoomStatus());
		fmt.format("%-26s %-10s\n", "Last Maintenance Date:", getLastMaintenanceDate());
		fmt.format("%-26s %-10s\n", "Image Name:", getImageName());
		return sbuf.toString();
	}
}
