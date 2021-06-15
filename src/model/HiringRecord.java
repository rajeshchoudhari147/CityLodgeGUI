package model;

import java.util.Formatter;

import util.DateTime;

public class HiringRecord 
{
	private String recordID;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private float rentalFee;
	private float lateFee;
	public Formatter fmt;
	
	public HiringRecord(String recordID, DateTime rentDate, DateTime estimatedReturnDate) 
	{
		this.recordID = recordID;
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
	}

	public HiringRecord(String recordID, DateTime rentDate, DateTime estimatedReturnDate, DateTime actualReturnDate, float rentalFee, float lateFee)
	{
		this.recordID = recordID;
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
	}
	
	public String getRecordID()
	{
		return recordID;
	}
	
	public DateTime getActualReturnDate()
	{
		return actualReturnDate;
	}
	
	public DateTime getEstimatedReturnDate()
	{
		return estimatedReturnDate;
	}
	
	public DateTime getRentDate()
	{
		return rentDate;
	}
	
	public double getRentalFee()
	{
		return rentalFee;
	}
	
	public double getLateFee()
	{
		return lateFee;
	}
	
	public void setActualReturnDate(DateTime actualReturnDate) 
	{
		this.actualReturnDate = actualReturnDate;
	}

	public void setRentalFee(float rentalFee) 
	{
		this.rentalFee = rentalFee;
	}

	public void setLateFee(float lateFee) 
	{
		this.lateFee = lateFee;
	}
	
	@Override
	public String toString()
	{
		String none = "none";
		String s = recordID + ":" + 
				   rentDate + ":" +
				   estimatedReturnDate + ":" +
				   ((actualReturnDate !=null)? actualReturnDate : none) + ":" +
				   ((rentalFee == 0.0)? none : rentalFee) + ":" +
				   ((lateFee == 0.0)? none : lateFee);
		return s;		
	}
	
	public String getDetails()
	{
		StringBuilder sb = new StringBuilder();
		fmt = new Formatter(sb);
		fmt.format("%-26s %-10s\n", "Record ID:", recordID);
		fmt.format("%-26s %-10s\n", "Rent Date:", rentDate);
		fmt.format("%-26s %-10s\n", "Estimated Return Date:", estimatedReturnDate);
		if (actualReturnDate != null) 
		{
			fmt.format("%-26s %-10s\n", "Actual Return Date:", actualReturnDate);
			fmt.format("%-26s %-10s\n", "Rental Fee:", rentalFee);
			fmt.format("%-26s %-10s\n", "Late Fee:", lateFee);
		}
		return sb.toString();
	}
}
