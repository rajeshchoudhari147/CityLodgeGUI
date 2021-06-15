package model.exceptions;

@SuppressWarnings("serial")
public class PerformMaintenanceException extends Exception 
{
	String message;

	public PerformMaintenanceException() 
	{
		super();
		this.message = "Room cannot go under maintainance!!";
	}

	@Override
	public String getMessage() 
	{
		return message;
	}
}