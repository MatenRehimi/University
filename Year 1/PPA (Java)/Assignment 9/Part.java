public class Part
{
	private int row;		//creating the fields while specifying the type and accessibility from other classes
	private int column;
	private boolean destroyed;

/**
*This method is a constructor
*This assigns the parameters to the fields
*/
	public Part(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
/**
*A setter method to store a boolean value into the field
*/
	public void set(boolean value)
	{
		destroyed = value;
	}
/**
*A getter method which returns the boolean value in the field destroyed
*/
	public boolean is()
	{
		return destroyed;
	}
/**
*A method which compares the inputted object to all other part objects
*If the object inputted is not a part then it returns false.
*If the object inputted (type Part) has the same row and column as another part then it returns true
*Otherwise it returns false
*/
	public boolean equals(Object part)
	{
		if (!(part instanceof Part))
		{
			return false;
		}
			if (((Part)part).row == row && ((Part)part).column == column)
			{
				return true;
			}
			else
			{
			return false;
			}
	}
/**
*A method that prints a string
*This changes depending on whether or not the destroyed field is true or false
*
*/
	public String toString()
	{
		if (destroyed == true)
		{
			return "[X]";
		}
		else
		{
			return "[ ]";
		}
	}
}