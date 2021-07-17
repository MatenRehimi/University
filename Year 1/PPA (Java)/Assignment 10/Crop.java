public class Crop
{
	private String type;	//declaring fields
	private int value;
/**
*Constructor to initialise fields
*/
	public Crop(String type, int value)	
	{
		this.type = type;
		this.value = value;
	}
/**
*Method to return field value
*/
	public int getValue()
	{
		return value;
	}
}