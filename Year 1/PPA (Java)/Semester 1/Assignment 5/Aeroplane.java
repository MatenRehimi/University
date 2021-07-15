public class Aeroplane
{
	private Coordinates aeroplane;		//creating fields
	private int speed;
	private int totalDistance;
	private int repairDistance;
	private String name;

	public Aeroplane( String name, Coordinates coordinates, int speed, int totalDistance, int repairDistance)	//creating constructor and initialising objects
	{
		this.name=name;						//initialising fields
		this.aeroplane=coordinates;
		this.speed=speed;
		this.totalDistance=totalDistance;
		this.repairDistance=repairDistance;
	}
	public int getSpeed()			//getter for speed
	{
		return speed;
	}
	public String getName()			//getter for name
	{
		return name;
	}
	public int getXCoordinate()		//getter for X Coordinate
	{
		return aeroplane.getXCoordinate();
	}
	public int getYCoordinate()		//getter for Y Coordinate
	{
		return aeroplane.getYCoordinate();
	}
	public int singleFlight(Destination destination)		
	{
		int singleFlightDistance =0 ;							
		if (aeroplane.getXCoordinate() < destination.getXCoordinate())		//if aeroplane X < destination X
		{
			singleFlightDistance = singleFlightDistance + (destination.getXCoordinate() - aeroplane.getXCoordinate());	
		}
		else if (aeroplane.getXCoordinate() > destination.getXCoordinate())	//if aeroplane X > destination x
		{
			singleFlightDistance = singleFlightDistance + (aeroplane.getXCoordinate() - destination.getXCoordinate());
		}
		else
		{
			singleFlightDistance = singleFlightDistance;
		}
		if (aeroplane.getYCoordinate() < destination.getYCoordinate())   //if aeroplane Y < destination Y
		{
			singleFlightDistance = singleFlightDistance + (destination.getYCoordinate() - aeroplane.getYCoordinate());
		}
		else if (aeroplane.getYCoordinate() > destination.getYCoordinate())	//if aeroplane Y > destination Y
		{
			singleFlightDistance = singleFlightDistance + (aeroplane.getYCoordinate() - destination.getYCoordinate());
		}
		else
		{
			singleFlightDistance = singleFlightDistance;
		}
		while ((aeroplane.getXCoordinate() != destination.getXCoordinate()) || (aeroplane.getYCoordinate() != destination.getYCoordinate()))	//keep repeating until aeroplane x = destination x and aeroplane y = destination y
		{

			if ((destination.getXCoordinate() - aeroplane.getXCoordinate()) < 0 && getSpeed() > (aeroplane.getXCoordinate() - destination.getXCoordinate()))	// if aeroplane x > destination x and speed > (aeroplane x - destination x)
				{
				aeroplane.setXCoordinate(aeroplane.getXCoordinate() - getSpeed());						// aeroplane x - speed
				}
				else 																					
				{
				aeroplane.setXCoordinate(destination.getXCoordinate()) ;							
				}	
			
			if  ((destination.getXCoordinate() - aeroplane.getXCoordinate()) > 0 && getSpeed() < (destination.getXCoordinate() - aeroplane.getXCoordinate()))		// if destination x > aeroplane x and speed < (destination x - aeroplane x)																				// if speed > (destination x - aeroplane x)
				{
				aeroplane.setXCoordinate(aeroplane.getXCoordinate() + getSpeed());					// aeroplane x + speed					
				}
				else 
				{
				aeroplane.setXCoordinate(destination.getXCoordinate()) ;
				}

			if ((destination.getYCoordinate() - aeroplane.getYCoordinate()) < 0 && (getSpeed() > (aeroplane.getYCoordinate() - destination.getYCoordinate())))				// if aeroplane y > destination y and speed > (aeroplane y - destination y)		
				{
				aeroplane.setYCoordinate(aeroplane.getYCoordinate() - getSpeed());					// 	aeroplane y + speed		
				}
				else 																					
				{
				aeroplane.setYCoordinate(destination.getYCoordinate()) ;							
				}	
			
			if  ((destination.getYCoordinate() - aeroplane.getYCoordinate()) > 0 && getSpeed() < (destination.getYCoordinate() - aeroplane.getYCoordinate()))				// if destination y > aeroplane y and speed < (destination y - aeroplane y)																					// if speed > (destination x - aeroplane x)
				{
				aeroplane.setYCoordinate(aeroplane.getYCoordinate() + getSpeed());								
				}
				else 
				{
				aeroplane.setYCoordinate(destination.getYCoordinate()) ;
				}

		}		
		return singleFlightDistance;
	}
	public int totalDistance(Destination destination)
	{
		if (aeroplane.getXCoordinate() < destination.getXCoordinate())										// aeroplane X < destination X
		{
			totalDistance = totalDistance + (destination.getXCoordinate() - aeroplane.getXCoordinate());	
		}
		else if (aeroplane.getXCoordinate() > destination.getXCoordinate())									// aeroplane X > destination X
		{
			totalDistance = totalDistance + (aeroplane.getXCoordinate() - destination.getXCoordinate());
		}
		else
		{
			totalDistance = totalDistance;
		}
		if (aeroplane.getYCoordinate() < destination.getYCoordinate())										// aeroplane Y < destination Y
		{
			totalDistance = totalDistance + (destination.getYCoordinate() - aeroplane.getYCoordinate());
		}
		else if (aeroplane.getYCoordinate() > destination.getYCoordinate())									// aeroplane Y > destination Y
		{
			totalDistance = totalDistance + (aeroplane.getYCoordinate() - destination.getYCoordinate());
		}
		else
		{
			totalDistance = totalDistance;
		}
		return totalDistance;
	}
	public int getRepairDistance()			//getter for repairDistance
	{
		return repairDistance;
	}
	public void setTotalDistance(int num)	//setter for totalDistance
	{
		this.totalDistance = num;
	}
	public int getTotalDistance()			//getter for totalDistance
	{
		return totalDistance;
	}

}
































