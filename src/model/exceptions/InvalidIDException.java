package model.exceptions;

@SuppressWarnings("serial")
public class InvalidIDException extends Exception 
{
	String message;

	public InvalidIDException(String message) 
	{
		super();
		this.message = message;
	}

	@Override
	public String getMessage() 
	{
		return message;
	}
}