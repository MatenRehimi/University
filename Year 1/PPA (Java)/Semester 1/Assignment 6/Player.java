public class Player
{
	private String name;		//creating fields while specifying the type and accessibility from the other classes
	private int lives;			
	private Room currentRoom;

	public Player (String name, int lives, Room currentRoom)	//creating constructor
	{
		this.name = name;				//giving access to the fields through the parameters
		this.lives = lives;				
		this.currentRoom = currentRoom;
	}
	public int getLives()	
	{
		return lives;
	}
	/*The method getLives retrieves the number of lives from the field*/
	public String getName()		
	{
		return name;
	}
	/*The method getName retrieves the name from the field*/
	public void setLives(int lives)		
	{
		this.lives = lives;
	}
	/*The method setLives assigns values to the lives field*/
	public void setName(String name)	
	{
		this.name = name;
	}
	/*The method setName assigns a String to the name field*/
	public void stats ()	//prints the stats of the player
	{
		System.out.println("");
		System.out.println("Name: " + getName());
		System.out.println("Lives: " + getLives());
		System.out.println("Current Room: " + currentRoom.getName());
		System.out.println("Pick red or blue to go through one of the doors!");
		System.out.println("");
	}	
	/*The method stats prints nothing,the name and calls the getName method, the lives and calls the getLives method, the current room and calls
	 *the method getName, instructions and then nothing again*/
	public void move(Room room)	
	{
			if (room.getContainsMonster() == true)
			{
				System.out.println("You have picked the wrong room!");
				System.out.println("You have been eaten by the monster!");
				lives=lives-1;
			}
			else
			{
				currentRoom = room;
				System.out.println("You have picked the correct room!");
				System.out.println("You have proceeded into the next room!");
			}
			if (room.getIsFinalRoom() == true)
			{
				System.out.println("You have reached the final room!");
				System.out.println("You have found the key!");
				System.out.println("You win!");
				lives = 0;
			}
			else if (room.getIsFinalRoom() == false && lives == 0)
			{
				System.out.println("You have lost all your lives!");
				System.out.println("You lose!");
			}
	}
	/* The method move first checks if the room inputted contains a monster, if true it prints two statements and subtracts 1 from the lives.
	 * Otherwise, it assigns the players current room to the room inputted and prints two statements.
	 * The method then checks if the room inputted is the final room, if true it prints three statements and assigns 0 to lives so the while loop
	 * will stop once it reaches the final room.
	 * Finally, it checks if the room inputted is the final room and it compares if the lives is the same to 0, if false and lives=0 then it prints two 
	 * statements out.
	*/
	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	/*The method getRoom retrieves the currentRoom string from the field
	*/
}













