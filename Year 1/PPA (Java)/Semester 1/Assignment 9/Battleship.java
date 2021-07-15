import java.util.ArrayList; //import classes
public class Battleship
{
	private ArrayList<Part> parts = new ArrayList<Part>();	//creating field
/**
*This method is a constructor. And it loops through all the parts arraylist and adds individual parts to the arraylist
*/
	public Battleship (int row, int column)
	{
		for (int i=0; i < column; i++)
		{
		Part part = new Part(row,i);
		parts.add(part);
		}
	}
/**
*This method makes a part of the row and column. then it goes through the arraylist and checks if the two are the same. 
*If so it makes the field destroyed in that part true and return true
*/	
	public boolean hit(int row, int column)
	{
		Part part = new Part(row,column);
		for (Part part1 : parts )
		{
			if (part.equals(part1))
			{
				part1.set(true);
				return true;
			}
		}
		return false;
	}
	/**
	*This equals method is like the toString method. Parameter must be inputBattleship and type must be boolean.
	*It first checks if the object is of type battleship
	*Next it checks if the size of each array is the same size.
	*Then it uses a tempVariable to count the number of destroyed parts
	*If all the parts are destroyed in either trip then return false
	*Otherwise return true
	*/
		public boolean equals(Object inputBattleship)
	{
		if (!(inputBattleship instanceof Battleship))
		{
			return false;
		}		
		if(!(((Battleship)inputBattleship).parts.size() == parts.size()))
		{
			return false;
		}
		int firstDestroyedParts1 = 0;
		int secondDestroyedParts2 = 0;
		for (int i=0; i < parts.size(); i++)
		{
			if(parts.get(i).is())
			{
				firstDestroyedParts1 ++;
			}
		}
		for (int j=0; j < ((Battleship)inputBattleship).parts.size(); j++)
		{
			if(((Battleship)inputBattleship).parts.get(j).is())
			{
				secondDestroyedParts2++;
			}
		}
		if (secondDestroyedParts2 == ((Battleship)inputBattleship).parts.size() || firstDestroyedParts1 == parts.size())
		{
			return false;
		}
		return true;
	}
	public String toString()
	{
		String row = "";
		for (Part element: parts)
		{
			row += element.toString();
		}
		return row;
	}
}