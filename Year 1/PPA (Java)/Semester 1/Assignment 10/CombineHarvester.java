public class CombineHarvester extends Harvester
{
	private int length;	//declaring variables

/**
*Constructor initialises the parameters for a combine harvester which includes harvester
*/
	public CombineHarvester(int length, int topSpeed, int fuelTankSize)
	{
		super(fuelTankSize, topSpeed);
		this.length = length;
	}
/**
*Method to overide the previous method and to find the newHarvestingCapacity
*/
	public int harvestingCapacity()
	{
		int newHarvestingCapacity = 0;
		newHarvestingCapacity = super.harvestingCapacity()*length;
		return newHarvestingCapacity;
	}
	
}