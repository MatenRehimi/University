import java.util.ArrayList;	//import Arraylist class
public class Farm
{
	private ArrayList<Field> fields;		//declaring variables
	private ArrayList<Harvester> harvesters;
	private int profit;
/**
*Constructor initialises fields and harvesters
*/
	public Farm()
	{
		fields = new ArrayList<Field>();
		harvesters = new ArrayList<Harvester>();
	}
/**
*Method to add a harvester to the arraylist harvesters
*/
	public void addHarvester(Harvester harvester)
	{
		harvesters.add(harvester);
	}
/**
*Method to add a field to the arraylist fields
*/
	public void addField(Field field)
	{
		fields.add(field);
	}
/**
*Method to return profit field
*/
	public int getProfit()
	{
		return profit;
	}
/**
*Method to harvest the fields depending on totalHarvestingCapacity
*Overall it finds the profit of the crops that it has harvested in all the fields
*/
	public void harvest(Farm farm)
	{
		int totalHarvestingCapacity = 0;
		for (Harvester element : harvesters)
		{
			totalHarvestingCapacity += element.harvestingCapacity();
		}
		if (totalHarvestingCapacity < fields.size())
		{
			for (int i=0  ; i < totalHarvestingCapacity ; i++ )
			{
				profit += fields.get(i).harvest();
			}
		}
		else 
		{
			for (Field element : fields)
			{
			profit += element.harvest();
			}
		}
	}
}