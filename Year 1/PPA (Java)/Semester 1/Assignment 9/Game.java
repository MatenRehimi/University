import java.util.Collections; //importing classes
import java.util.Scanner;
public class Game
{
	public static void main(String args[])
	{
		Board board = new Board();			//Initialise board
		Scanner in = new Scanner(System.in);		// Initialise scanner

		while(true)
		{
			System.out.println("");
			int numOfBattleship = Collections.frequency(board.get(), new Battleship(0,5));	//gives you number of Battleship in arraylist ships
			System.out.println("Number of Battleship left: " + numOfBattleship);

			int numOfCruiser = Collections.frequency(board.get(), new Cruiser(1));		//gives you number of cruiser in arraylist ships
			System.out.println("Number of Cruiser left: " + numOfCruiser);

			int numOfFrigate = Collections.frequency(board.get(), new Frigate(3));			//gives you number of frigate in arraylist ships
			System.out.println("Number of Frigate left: " + numOfFrigate);

			int numOfMinesweeper = Collections.frequency(board.get(), new Minesweeper(4));	//gives you number of Minesweeper in arraylist ships
			System.out.println("Number of Minesweeper left: " + numOfMinesweeper);

			System.out.println(board);
			System.out.println("Please enter two numbers with a space between them");
			System.out.println("Please enter numbers in the range 0-4");
			String input = in.nextLine();

			if (input.equals("quit"))
			{
				break;
			}
			String[] inputs = input.split(" ");		//Creates arraylist of type String and there is always a space between each slot
			int row = Integer.parseInt(inputs[0]);	//row is initialised and is stored in slot 0
			int column = Integer.parseInt(inputs[1]);	//column is initialised and is stored in slot 1
			if (row < 5 && column < 5 && row > -1 && column > -1)
			{
				if (board.hit(row,column))
				{
					System.out.println("Hit!");
				}	
				else
				{
					System.out.println("No hit!");
				}
			}
			else
			{
				System.out.println("Pick numbers in the range 0-4");
			}
		}
		in.close();	//close scanner
	}
}