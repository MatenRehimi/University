public class GoldCoin
{
	private int coinNumber;				//creating fields while specifying the type and accessibility from other classes
	private static int coinStamped;	
	/**
	*This method is a constructor
	*This stamps the coin with the coin number
	*Then the coinStamped is incrememnted every time a gold coin is created so it is treated like a counter
	*/ 
	public GoldCoin()		
	{
		coinStamped = coinNumber;		
		coinStamped = coinStamped + 1;
	}
	/**
	* This method returns the field coinNumber
	*/
	public int getCoinNumber()
	{
		return coinNumber;
	}
}