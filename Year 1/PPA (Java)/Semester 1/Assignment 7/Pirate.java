public class Pirate
{
	private String name;
	private GoldCoin[] purse;	//creating the purse array of slot GoldCoin
	private Island[] map;		//creating the map array of slot Island
	/*
	*This method is a constructor
	*assign field name to parameter name
	*assign field map to parameter map
	*Initialise the purse to produce an array of goldcoins and the number of slots is equal to (number of islands* max number of coins)
	*/

	public Pirate (String name, Island[] map)
	{
		this.name=name;
		this.map=map;
		purse = new GoldCoin[map.length*TreasureChest.MAX_NUMBER_OF_COINS];
	}
	/**
	*This method returns an Island and a string is supplied 
	*for loop is used if you don't know the number of iterations as the number of islands on the map could change
	*the index i goes through each slot in the array 
	*if the name inputted is the same as one of the names of an island slot 
	*then return the island slot 
	*Otherwise if the name inputted is the same as an island then return null.
	*/
	public Island search(String name)
	{
		for (int i = 0; i < map.length;i=i+1)
		{
			if (name.equals(map[i].getName()))
			{
				return map[i];
			}
		}
		return null;
	}
	/**
	*This methods returns a boolean value and an island is supplied
	*	Making a variable called treasureChest of type TreasureChest
	*	for loop is created, index i goes through all location slots in the island array
	*	treasurechest is assigned to a method which returns a treasurechest or null after it goes through each location slot on the island 
	*		if the treasureChest is on a location on an island then go to the for loop
	*			for loop is created, index o goes through all goldcoin slots in the treasurechest
	*				for loop is created, index j goes through each goldcoin slot in the purse array
	*					if the goldcoin slot is equal to null then put a gold coin into the next empty slot 
	*					Then stop the cycle so you don't add goldcoins in all null slots in the purse array
	*		return true if treasurechest is in the island
	*return false if treasurechest in not in the island
	*/
	public boolean getTreasure(Island island)
	{
		TreasureChest treasureChest;

		for (int i=0; i < island.getLocations(); i=i+1)
		{
			treasureChest = island.dig(i);
			if (treasureChest != null)
			{
				for(int o = 0; o < TreasureChest.MAX_NUMBER_OF_COINS; o++)
				{
					for (int j=0; j < purse.length; j=j+1)
						if (purse[j] == null)
						{
							purse[j] = treasureChest.takeOneGoldCoin();
							break;
						}
				}
				return true;
			}
		}
		return false;
	}
	/**
	*This method returns an integer
	*Declare int y to be 0
	*For loop, with index m which goes through all slots in the purse array
	*if the goldcoin slot is not equal to null then increment y
	*Then return y
	*/
	public int totalCoins()
	{
		int y = 0;
		for (int m=0; m < purse.length; m=m+1)
		{
			if (purse[m] != null)
			{
				y=y+1;
			}
		}
		return y;
	}
	/**
	*This method does not return anything
	*This creates an array of Strings in suffix
	*A random number is generated and a random suffix is used
	*This prints the phrase supplied and a random suffix
	*/
	public void speak(String phrase)
	{
		String[] suffix = {", arrr!", ", shiver me timbers!", ", avast!", ", ahoy, matey!", ", yo, ho ho!"};

		int randNum = (int) (Math.random() * suffix.length);

		System.out.println(phrase + suffix[randNum]);
	}
}