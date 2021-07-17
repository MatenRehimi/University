public class TreasureChest
{
	private GoldCoin[] chest;		//this creates the chest array which stores GoldCoin	
	private int totalCoins;
	public static final int MAX_NUMBER_OF_COINS=14;	// this sets the value and allows me to access it from other classes
	
	/**
	*This method is a constructor
	*Set maximum number of slots (14) in the chest
	*i goes through all slots in the array
	*It makes gold coins and input gold coins in each slot(i) in the chest
	*this allows me to store total coins so I can change the value later on
	*/
	public TreasureChest()
	{
		chest = new GoldCoin[MAX_NUMBER_OF_COINS];	
		for (int i=0; i < chest.length; i=i+1)	
		{
			chest [i] = new GoldCoin();			
		}
		totalCoins = MAX_NUMBER_OF_COINS;		

	}
	/**
	*remove represents the last gold coin in the chest
	*this makes the last coin in the chest to null
	*this reduces the totalCoins by one as the last coin has been removed
	*the coin removed is returned
	*/
	public  GoldCoin takeOneGoldCoin()
	{	
		GoldCoin remove = chest[totalCoins-1];	
		chest[totalCoins-1] = null;				
		totalCoins=totalCoins-1;				
		return remove;							
	}
}