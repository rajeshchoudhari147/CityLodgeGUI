package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import model.exceptions.InvalidInputException;
import model.exceptions.PerformMaintenanceException;
import model.exceptions.RentException;
import model.exceptions.ReturnException;
import util.DateTime;
import view.RoomDetailWindow;

public abstract class AbstractRoom implements Room
{
	private String roomID;
	private int noOfBeds;
	private String featureSummary;
	private String roomType;
	private String roomStatus;
	private int daysBeforeEstimate;
	private int daysAfterEstimate;
	private String imageName;
	private ArrayList<HiringRecord> hiringRecord;
	private static final int MAINTENANCE_INTERVAL = 10;
	
	public AbstractRoom(String roomID, int noOfBeds, String featureSummary, String roomType, String roomStatus,  String imageName)
	{
		this.roomID = roomID;
		this.noOfBeds = noOfBeds;
		this.featureSummary = featureSummary;
		this.roomType = roomType;
		this.roomStatus = roomStatus;
		this.imageName = imageName;
	}
	
	@Override
	public String getRoomID()
	{
		return roomID;
	}
	
	public void setRoomID(String roomID)
	{
		this.roomID = roomID;
	}
	
	@Override
	public int getNoOfBeds()
	{
		return noOfBeds;
	}
	
	public void setNoOfBeds(int noOfBeds)
	{
		this.noOfBeds = noOfBeds;
	}
	
	@Override
	public String getSummary()
	{
		return featureSummary;
	}
	
	public void setSummary(String featureSummary)
	{
		this.featureSummary = featureSummary;
	}
	
	@Override
	public String getRoomType()
	{
		return roomType;
	}
	
	public void setRoomType(String roomType)
	{
		this.roomType = roomType;
	}
	
	@Override
	public String getRoomStatus()
	{
		return roomStatus;
	}
	
	public void setRoomStatus(String rs)
	{
		roomStatus = rs;
	}
	
	public int getDaysBeforeEstimate() 
	{
		return daysBeforeEstimate;
	}

	public void setDaysBeforeEstimate(int daysBeforeEstimate) 
	{
		this.daysBeforeEstimate = daysBeforeEstimate;
	}

	public int getDaysAfterEstimate() 
	{
		return daysAfterEstimate;
	}

	public void setDaysAfterEstimate(int daysAfterEstimate) 
	{
		this.daysAfterEstimate = daysAfterEstimate;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}
	
	public ArrayList<HiringRecord> getHiringRecordList()
	{
		return hiringRecord;
	}
	
	public void setHiringRecordList(ArrayList<HiringRecord> hiringRecord) 
	{
		this.hiringRecord = hiringRecord;
	}
	
	public void rent(String customerID, DateTime rentDate, int NumberOfRentDay) throws RentException, InvalidInputException, FileNotFoundException
	{
		boolean rent;
		DateTime estimatedReturnDate = DateTime.getEstimatedReturnDate(rentDate.toString(), NumberOfRentDay);
		if (this.roomStatus.equals("Available")) 
		{
			if (this instanceof Suite) 
			{
				int check = 0;
				if (((Suite) this).getLastMaintenanceDate() != null)
				{
					DateTime nextMaintainanceDate = DateTime.getEstimatedReturnDate(((Suite) this).getLastMaintenanceDate().toString(), MAINTENANCE_INTERVAL);
					// checking if rental period exceeds date on which suite needs to go under maintenance
					check = DateTime.diffDays(estimatedReturnDate, nextMaintainanceDate);
				}
				// checking if number of days to be rented is greater than one
				if (NumberOfRentDay >= 1 && check <= 0)
					rent = true;
				else
					rent = false;
			} 
			else
			{
				// condition checks for a standard room
				String Day = rentDate.getNameOfDay();
				if (((Day.equals("Saturday") || Day.equals("Sunday")) && NumberOfRentDay >= 3 && NumberOfRentDay <= 10)
						|| ((Day.equals("Monday") || Day.equals("Tuesday")
								|| Day.equals("Wednesday") || Day.equals("Thursday")
								|| Day.equals("Friday")) && NumberOfRentDay >= 2 && NumberOfRentDay <= 10))
					rent = true;
				else
					rent = false;
			}
		}
		else 
		{
			rent = false;
		}
		// creating a new hiring record, updating status after condition check
		if (rent) 
		{
			String recordID = this.roomID + "_" + customerID + "_" + rentDate.getEightDigitDate();
			this.setRoomStatus("Rented");
			this.getHiringRecordList().add(new HiringRecord(recordID, rentDate, estimatedReturnDate));// addded
			Start.getRoot().setCenter(new RoomDetailWindow(this));

		} 
		else
		{
			throw new RentException();
		}
	}

	public void returnRoom(DateTime returnDate) throws ReturnException, FileNotFoundException
	{
		float fees = 0;
		float lateFees = 0;
		// condition to check if vehicle is rented
		if (this.getRoomStatus()=="Rented" &&
		// check to verify return date entered by the user is greater than the rent date
				(DateTime.diffDays(returnDate,this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getRentDate()) > 0)) 
		{
			// updating the latest hiring record with actual return date and fees, updating
			// status after condition check
			this.setRoomStatus("Available");
			this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).setActualReturnDate(returnDate);
			if (this instanceof StandardRoom) 
			{
				fees = ((StandardRoom) this).calculateRentalFee();
				lateFees = ((StandardRoom) this).calculateLateFee();
			} 
			else
			{
				fees = ((Suite) this).calculateRentalFee();
				lateFees = ((Suite) this).calculateLateFee();
			}
			this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).setRentalFee(fees);
			this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).setLateFee(lateFees);
			Start.getRoot().setCenter(new RoomDetailWindow(this));
		} 
		else
		{
			throw new ReturnException();
		}

	}
	
	public void performMaintenance() throws PerformMaintenanceException, FileNotFoundException
	{
		if(this.getRoomStatus() == "Available")
		{
			this.setRoomStatus("Maintenance");
			Start.getRoot().setCenter(new RoomDetailWindow(this));
		}
		else
		{
			throw new PerformMaintenanceException();
		}
	}

	public void rentDays() 
	{
		// checking if room was returned late
		if (DateTime.diffDays(
				this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getActualReturnDate(),
				this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getEstimatedReturnDate()) > 0) 
		{
			daysBeforeEstimate = DateTime.diffDays(
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getEstimatedReturnDate(),
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getRentDate());
			daysAfterEstimate = DateTime.diffDays(
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getActualReturnDate(),
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getEstimatedReturnDate());
		} 
		else 
		{
			// loop is entered if room was returned before estimated date
			daysBeforeEstimate = DateTime.diffDays(
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getActualReturnDate(),
					this.getHiringRecordList().get(this.getHiringRecordList().size() - 1).getRentDate());
			daysAfterEstimate = 0;
		}
	}
	
	public String getRecentRecord()
	{
		String s = "";
		int i = hiringRecord.size() - 1;
		int j = 0;
		while(i >= 0 && j <= 10)
		{
			s = s + hiringRecord.get(i).getDetails() + "---------------------------------------------\n";
			j++;
			i--;
		}
		return s;
	}
	
	public abstract float calculateRentalFee();
	public abstract float calculateLateFee();
	public abstract String toString();
	public abstract String getDetails();

	
}
