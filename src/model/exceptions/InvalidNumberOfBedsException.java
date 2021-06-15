package model.exceptions;

@SuppressWarnings("serial")
public class InvalidNumberOfBedsException extends Exception 
{
	String message;

	public InvalidNumberOfBedsException(String message)
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