public class Island
{
	private String name;
	private TreasureChest[] locations; 	//this creates the locations array which stores Treasurechests
	/**
	*This method is a constructor
	*assign field to parameter
	*set maximum number of slots(bury) in locations
	*treasurechest is stored in a random location
	*/
	public Island (String name, int bury)	
	{
		this.name = name;																
		locations = new TreasureChest[bury];												
		locations [(int) Math.random()*(bury)] = new TreasureChest();						
	}
	/**
	*This method returns a TreasureChest object when an integer is supplied
	*A chest is initialised of type Treasurechest to allow us to clear the slot later on and still keep the chest
	*if the number supplied is less than the length of the array and greater than -1 then  proceed to the next line
	*	if the supplied integer slot in locations is null then proceed to the next line
	*		then the chest is in the supplied integer slot in locations
	*		the supplied integer slot in location is then null to remove the chest from that slot in locations
	*		and the chest is returned 
	*	Otherwise return null if there is no treasure chest in the supplied integer slot in locations
	*/
	public TreasureChest dig (int num)
	{
		TreasureChest chest;
		if (num < locations.length && num > -1)	
		{
			if(locations[num] != null)
			{
				chest = locations[num];
				locations[num] = null;
				return chest;
			} 
			else
			{
				return null;
			}
		}
		 else 
		{
			return null;
		}
	/**
	*The method returns the String name
	*/
	}
	public String getName()
	{
		return name;
	}
	/**
	*The method returns the number of slots in the locations array.
	*/
	public int getLocations()
	{
		return (locations.length);
	}
}