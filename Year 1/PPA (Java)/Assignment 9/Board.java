import java.util.ArrayList;	//import arraylist
public class Board
{
	private ArrayList<Battleship> ships = new ArrayList<Battleship>();	// make a battleship arraylist 
/**
*Adds the ships in the specific rows
*/
	public Board()
	{
		ships.add(new Battleship(0,5));
		ships.add(new Cruiser(1));
		ships.add(new Cruiser(2));
		ships.add(new Frigate(3));
		ships.add(new Minesweeper(4));
	}
/**
*Getter for ships arraylist
*/
	public ArrayList<Battleship> get()
	{
		return ships;
	}
/**
*This checks if the Part in that row and column has a destroyed field true.
*/
	public boolean hit(int row, int column)
	{
		for (Battleship element : ships)
		{
			if (element.hit(row,column))
			{
				return true;
			}
		}
		return false;
	}
/**
*This creates n number of boxes and stores it in a string
*/
	public String empty(int n)
	{
		String tempString = "";
		for (int i=0; i < n; i++)
		{
			tempString += "[ ]";
		}
		return tempString;
	}
/**
*This uses the empty method to make sure it is a 5 by 5 grid.
*/
	public String toString()
	{
		String row = "";
		int n = 0;
		for (Battleship element: ships)
		{
			if (element instanceof Cruiser)
			{
				n=1;
			}
			else if (element instanceof Frigate)
			{
				n=2;
			}
			else if (element instanceof Minesweeper)
			{
				n=3;
			}

			row += element.toString() + empty(n)+"\n";
		}
		return row;
	}
}
