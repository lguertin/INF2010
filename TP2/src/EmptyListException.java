

public class EmptyListException extends Exception
{
	public EmptyListException()
	{
	    super("Error: the list is empty");
	}
	
	public EmptyListException(String s)
	{
		super(s);
	}
};
