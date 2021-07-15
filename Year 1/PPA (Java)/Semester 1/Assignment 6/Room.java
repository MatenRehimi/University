public class Room
{
	private String name;			//creating fields while specifying the type and accessibility from the other classes
	private Room blueDoorRoom;
	private Room redDoorRoom;
	private boolean containsMonster;
	private boolean isFinalRoom;

	public Room (String name, boolean containsMonster)	//creating constructor 1
	{
		this.name = name;							//giving access to the fields through the parameters
		this.containsMonster = containsMonster;
	}	
	public Room (String name, Room blueDoorRoom, Room redDoorRoom, boolean containsMonster, boolean isFinalRoom)	//creating constructor2
	{
		this.name = name;							//giving access to the fields through the parameters
		this.containsMonster = containsMonster;		 
		this.blueDoorRoom = blueDoorRoom;			 
		this.redDoorRoom = redDoorRoom;				 
		this.isFinalRoom = isFinalRoom;
	}	
	public Room (boolean isFinalRoom, String name)	//creating constructor3
	{	
		this.isFinalRoom = isFinalRoom;				//giving access to the fields through the parameters
		this.name = name;
	}
	public String getName ()			
	{
		return name;
	}
	/*The method getName retrieves the String from the name field*/
	public Room getBlueDoorRoom()
	{
		return blueDoorRoom;
	}
	/*The metho getBlueDoorRoom retrieves the room from the blueDoorRoom field*/
	 
	public Room getRedDoorRoom()
	{
		return redDoorRoom;
	}
	/*The method getRedDoorRoom retrieves the room from the redDoorRoom field*/
	public boolean getContainsMonster()
	{
		return containsMonster;
	}
	/*The method getContainsMonster retreives the boolean value from the containsMonster field*/
	public boolean getIsFinalRoom()
	{
		return isFinalRoom;
	}
	/* The method getIsFinalRoom retreives the boolean value from the isFinalRoom field*/
}