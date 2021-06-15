package model.exceptions;

@SuppressWarnings("serial")
public class RentException extends Exception 
{
	String message;

	public RentException()
	{
		super();
		this.message = "Room cannot be rented!!";
	}

	@Override
	public String getMessage() 
	{
		return message;
	}
}
