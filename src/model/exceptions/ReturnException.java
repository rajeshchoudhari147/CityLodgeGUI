package model.exceptions;

@SuppressWarnings("serial")
public class ReturnException extends Exception 
{
	String message;

	public ReturnException() 
	{
		super();
		this.message = "Room cannot be returned!!";
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}