public class Minesweeper extends Battleship	
{	
/**
*This is basically using the constructor in battleship
*/
	public Minesweeper(int row)
	{
	super (row,2);
	}	
/**
*The hit method generates a random number then it calls the hit method in Battleship
*/
	public boolean hit(int row, int column)
	{
		int randNum = ((int) (Math.random()*2));
		if (randNum == 1)
		{
			return super.hit(row,column);
		}
		else
		{
			return false;
		}
	}

}