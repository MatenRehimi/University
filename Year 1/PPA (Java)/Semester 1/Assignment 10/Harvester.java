public class Harvester 
{
	private int fuelTankSize;	//declaring fields
	private int topSpeed;
/**
*Constructor initialises fields
*/ 
	public Harvester(int fuelTankSize, int topSpeed)
	{
		this.fuelTankSize = fuelTankSize;
		this.topSpeed = topSpeed;
	}
/**
*Method to find the harvesting capacity
*/
	public int harvestingCapacity()
	{
		int harvestingCapacity = fuelTankSize + topSpeed;
		return harvestingCapacity;
	}
}