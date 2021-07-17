public class RaceTrack
{
	private int averageLapTime;		//creating fields
	private boolean isRaining;


	public RaceTrack(int overallTime, boolean raining)
	{
	averageLapTime=overallTime;					//It is assigning the field averageLapTime to the parameter overallTime
	isRaining=raining;
	}
	public int determineRaceLeader(Car car1,Car car2,Car car3)		//Creates a method with a car parameters that allow access to the class
	{
		int car1Time = car1.completeLap(averageLapTime, isRaining);	
		int car2Time = car2.completeLap(averageLapTime, isRaining);
		int car3Time = car3.completeLap(averageLapTime, isRaining);		
		if (car1Time<=car2Time && car1Time<=car3Time)			
		{
		return car1.getId();
		}
		else if (car2Time<=car3Time && car2Time<=car1Time)
		{
		return car2.getId();
		}
		else
		{
		return car3.getId();						// returns the car's id with the lowest totaltime
		}
	}
	public void setRain(boolean isRaining)				//Gives access to change the boolean	
	{
		this.isRaining=isRaining;	
	}
}

