public class Harvest 
{
/**
*Creates a farm
*Adds a new harvester in the harvesters arraylist
*Adds a new CombineHarvester in the harvesters arraylist
*Creates 5 of each type of field
*Harvests the farm
*Prints the profit of the harvest
*/
	public static void main (String args [])
	{
		Farm farm = new Farm();
		farm.addHarvester(new Harvester(1,1));
		farm.addHarvester(new CombineHarvester(3,2,2));

		for (int i=0; i<5; i++)
		{
			farm.addField(new Field("corn", 20));
			farm.addField(new Field("wheat", 20));
			farm.addField(new Field("oats", 20));
			farm.addField(new Field("barley", 20));
		}
		
		farm.harvest(farm);
		System.out.println(farm.getProfit());
	}
}