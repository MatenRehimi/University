public class Destination
{
	private String name;									//creating fields
	private Coordinates coordinates;

	public Destination(String name, Coordinates coordinates)	// creating constructor and initialising objects
	{
		this.name=name;											//initialising fields
		this.coordinates=coordinates;
	}		
	public int getXCoordinate()							//getter for Destination X Coordinate
	{
		return coordinates.getXCoordinate();
	}
	public int getYCoordinate()							//getter for Destination Y Coordinate
	{
		return coordinates.getYCoordinate();
	}
	public String getName()								//getter for destination name 
	{
		return name;
	}
}