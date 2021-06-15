package model.exceptions;

@SuppressWarnings("serial")
public class CompleteMaintenanceException extends Exception 
{
	String message;

	public CompleteMaintenanceException()
	{
		super();
		this.message = "Room is not under maintainance!!";
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
