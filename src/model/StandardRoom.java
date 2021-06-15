package model;

import java.io.FileNotFoundException;
import java.util.Formatter;

import model.exceptions.CompleteMaintenanceException;
import view.RoomDetailWindow;

public class StandardRoom extends AbstractRoom
{
	public Formatter fmt;
	private static final float ONE_BED = 59;
	private static final float TWO_BED = 99;
	private static final float FOUR_BED = 199;
	private static final float LATE_FEE = 1.35f;
	
	public StandardRoom(String roomID, int noOfBeds, String featureSummary, String roomType, String roomStatus, String imageName)
	{
		super(roomID, noOfBeds, featureSummary, "Room", roomStatus, imageName);
	}
	
	public void completeMaintenance() throws CompleteMaintenanceException, FileNotFoundException  
	{
		if (this.getRoomStatus() == "Maintenance") 
		{
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
		this.rentDays();
		if (this.getNoOfBeds() == 1)
		{
			return this.getDaysBeforeEstimate() * ONE_BED;
		}
		else if(this.getNoOfBeds() == 2)
		{
			return this.getDaysBeforeEstimate() * TWO_BED;
		}
		else
			return this.getDaysBeforeEstimate() * FOUR_BED;
	}

	@Override
	public float calculateLateFee() 
	{
		this.rentDays();
		if (this.getNoOfBeds() == 1)
		{
			return (this.getDaysAfterEstimate() * ONE_BED * LATE_FEE);
		}
		else if(this.getNoOfBeds() == 2)
		{
			return (this.getDaysAfterEstimate() * TWO_BED * LATE_FEE);
		}
		else
			return (this.getDaysAfterEstimate() * FOUR_BED * LATE_FEE);
	}
	
	public String toString()
	{
		String s =  getRoomID() + ":" + 
					getNoOfBeds() + ":" +
					getRoomType() + ":" +
					getRoomStatus() + ":" +
					getSummary() + ":" +
					getImageName();
		return s;
	}
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		fmt = new Formatter(sb);
		fmt.format("%-26s %-10s\n", "Room ID:", getRoomID());
		fmt.format("%-26s %-10s\n", "Number of Beds:", getNoOfBeds());
		fmt.format("%-26s %-10s\n", "Feature Summary:", getSummary());
		fmt.format("%-26s %-10s\n", "Status:", getRoomStatus());
		fmt.format("%-26s %-10s\n", "Image Name:", getImageName());
		return sb.toString();
	}
}
