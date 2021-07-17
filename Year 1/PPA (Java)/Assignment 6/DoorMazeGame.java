import java.util.Scanner;	//gives java access the scanner class
public class DoorMazeGame
{
	public static void main (String [] args)		
	{																
		/*monsterRoom is initialised as an object from the Room class. It contains a string which is stored in the name field and a
		 *boolean value which is stored in the containsmonster field
		*/
		Room monsterRoom = new Room("The Monster Room",true);	
		/*room6 is initialised as an object from the Room class. It contains a boolean value which is stroed in the containsmonster field and a 
		 *string which is stored in the name field
		*/	
		Room room6 = new Room(true,"Chamber Six");	
		/*room5 is initialised as an object from the Room class. It contains a String which is stored in the name field, a Room object that is stored 
		 *in the blueDoorRoom field, a Room object stored in the redDoorRoom field, a boolean value which is stored in the contains monster field
		 *and another boolean value which is stored in the isFinalRoom field.
		*/			
		Room room5 = new Room("Chamber Five", monsterRoom, room6, false, false);		
		/*room4 is initialised as an object from the Room class. It contains a String which is stored in the name field, a Room object that is stored 
		 *in the blueDoorRoom field, a Room object stored in the redDoorRoom field, a boolean value which is stored in the contains monster field
		 *and another boolean value which is stored in the isFinalRoom field.
		*/	
		Room room4 = new Room("Chamber Four", monsterRoom, room5, false, false);
		/*room3 is initialised as an object from the Room class. It contains a String which is stored in the name field, a Room object that is stored 
		 *in the blueDoorRoom field, a Room object stored in the redDoorRoom field, a boolean value which is stored in the contains monster field
		 *and another boolean value which is stored in the isFinalRoom field.
		*/			
		Room room3 = new Room("Chamber Three", room4, monsterRoom, false, false);	
		/*room2 is initialised as an object from the Room class. It contains a String which is stored in the name field, a Room object that is stored 
		 *in the blueDoorRoom field, a Room object stored in the redDoorRoom field, a boolean value which is stored in the contains monster field
		 *and another boolean value which is stored in the isFinalRoom field.
		*/		
		Room room2 = new Room("Chamber Two", monsterRoom, room3, false, false);	
		/*room1 is initialised as an object from the Room class. It contains a String which is stored in the name field, a Room object that is stored 
		 *in the blueDoorRoom field, a Room object stored in the redDoorRoom field, a boolean value which is stored in the contains monster field
		 *and another boolean value which is stored in the isFinalRoom field.
		*/			
		Room room1 = new Room("Chamber One", room2, monsterRoom,false, false);			

		Player player1 = new Player("",2, room1);		//Initialises player1 as an object of class player 													
		Scanner in = new Scanner(System.in);			//Initialises in as an object of the scanner class

		System.out.println("You are trapped in a haunted house!");
		System.out.println("The door is locked so the only way through is to find the key in the highest chamber!");
		System.out.println("You win the game when you get the key!");
		System.out.println("Navigate your way through the haunted house by picking one of two doors in each chamber!");
		System.out.println("type red to go through the red door");
		System.out.println("type blue to go through the blue door");
		System.out.println("And beware of the monster that lurks behind one of the two doors in each chamber!");
		System.out.println("");
		System.out.println("Enter Name");	

		player1.setName(in.nextLine());					//This assigns the next line to be the Name of the player

		while (player1.getLives()>0)					//continues to loop as long as the player has lives
		{
			player1.stats();							//calls the stats method
			String answer=in.nextLine();				//assigns the String answer to be the next line
			if (answer.equals("blue"))
			{
				player1.move(player1.getCurrentRoom().getBlueDoorRoom());	//calls the move method, then sets the inputted Room as the Room which
			}																//corresponds with the one room they have picked
			else if (answer.equals("red"))
			{
				player1.move(player1.getCurrentRoom().getRedDoorRoom());	//calls the move method, then  sets the inputted Room as the Room which 
			}																//correspons with the one room they have picked
			else
			{
				System.out.println("Error:Pick red or blue");				
			}
		}
	}
}