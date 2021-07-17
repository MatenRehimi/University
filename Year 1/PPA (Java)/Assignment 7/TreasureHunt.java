//importing the scanner class so I can allow the user to input something
import java.util.Scanner;
/**
*Three islands are produced 
*The threes islands are put into a map (array)
*A pirate is created and a map and a name is supplied
*A in class is copied from scanner to allow me to have access to methods in the scanner class
*A temporary island of type island is produced
*while loop is produced which always loops until it is manually stopped by inputting stop in the terminal
*The input of the user is stored in the string input
*the temporary island is assigned to the island slot on the map which the user has inputted
*If the island inputted has a slot then go to the next if statement
*If the island inputted contains treasure then show to the user the name of the island that contained the treasure and let them know how many coins they have got
*Otherwise if there is no treasure on the island then let the use know
*Also if the user doesn't input an island then let them know
*close the scanner
*/

public class TreasureHunt
{
	public static void main (String args[])
	{
		Island havana = new Island("Havana",16);
		Island st_Kitts = new Island("St Kitts",16);
		Island guadeloupe = new Island("Guadeloupe",16);

		Island[] map = {havana, st_Kitts, guadeloupe};
		Pirate chapbeard = new Pirate("ChapBeard", map);

		Scanner in = new Scanner(System.in);

		Island tempIsland;

		while (1==1)
		{
			String input = in.nextLine();
			if(input.equals("stop"))
				{ 
					break;
			 	}

			tempIsland = chapbeard.search(input);
			if (tempIsland != null)
			{
				if(chapbeard.getTreasure(tempIsland) == true)
				{
					chapbeard.speak("There be treasure on " + input);
					chapbeard.speak("You have now have " + chapbeard.totalCoins() + " of my hidden treasure in your purse");
				} 
				else
				{
					chapbeard.speak("arrg There be no treasure on thee Island");
				}
			}
			else
			{
				chapbeard.speak("arrg There be no treasure on thee Island");
			}
			
		}
		in.close();	
	}
}
