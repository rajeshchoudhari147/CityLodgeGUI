package model.exceptions;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception 
{
	String message;

	public InvalidInputException() 
	{
		super();
		this.message = "Invalid input!";
	}

	@Override
	public String getMessage() 
	{
		return message;
	}
}