public class Coordinates
{
	private int x;						//creating fields
	private int y;
	
	public Coordinates(int x,int y)		//creating constructor and initialising objects
	{
		this.x=x;						//initialising fields
		this.y=y;
	}
	public int getXCoordinate()			//getter for x Coordinate
	{
		return this.x;
	}
	public void setXCoordinate(int x)	//setter for x Coordinate
	{
		this.x=x;
	}
	public int getYCoordinate()			//getter for y Coordinate
	{
		return this.y;
	}
	public void setYCoordinate(int y)	//setter for x Coordinate
	{
		this.y=y;
	}

}