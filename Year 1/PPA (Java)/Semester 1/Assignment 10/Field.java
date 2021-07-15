import java.util.ArrayList;	//import Arraylist class
public class Field
{
	private ArrayList<Crop> crops;			//declare crops
	private final int MAXIMUM_CROPS = 10;	//initialise and declare Maximum crops
/**
*Constructor to initialise crops and plant crops
*/
	public Field(String type, int value)
	{
		crops = new ArrayList<Crop>();
		plant(type, value);
	}
/**
*Method to make the maximum number of crops and to add them to the arraylist crops
*/
	public void plant(String type, int value)
	{
		for (int i=0; i < MAXIMUM_CROPS; i++)
		{
			Crop crop = new Crop(type, value);
			crops.add(crop);
		}
	}
/**
*Method to find the total value of all the crops in the field
*/
	public int harvest()
	{
		int total = 0;
		for (Crop element : crops)
		{
			total += element.getValue();
		}
		return total;
	}
}