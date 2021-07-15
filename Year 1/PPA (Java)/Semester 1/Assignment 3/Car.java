public class Car
{	
	private int id;						//creating fields
	private int fuel;
	private int lowFuelBoost;
	private int highFuelSlowdown;
	private int fuelConsumptionPerLap;
	private int pitStopTime;
	private int rainSlowDown;
	private int totalTime;

	public Car(int enterId,int enterFuel,int enterLowFuelBoost,int enterHighFuelSlowdown,int enterFuelConsumptionPerLap,int enterPitStopTime,int enterRainSlowDown,int enterTotalTime)
								//creating constructors
	{
	id = enterId;							//initialising fields-setting values to the fields
	fuel= enterFuel;
	lowFuelBoost = enterLowFuelBoost;
	highFuelSlowdown = enterHighFuelSlowdown;
	fuelConsumptionPerLap =  enterFuelConsumptionPerLap;		
	pitStopTime = enterPitStopTime;
	rainSlowDown =  enterRainSlowDown;
	totalTime = enterTotalTime;
	}
	public int getId()
	{
		return id;
	}
	public int getTotalTime()
	{
		return totalTime;
	}
	public int completeLap(int averageLapTime, boolean raining)		//returns the totalTime of each car
	{
	totalTime =totalTime + averageLapTime;					//acculumates time over each lap
	if (fuel<fuelConsumptionPerLap)
	{
		totalTime=totalTime+pitStopTime;				//adds pitStopTime to the totaltime when there isn't enough fuel to complete a lap
		this.fuel=100;
	}
	if (fuel>50)
	{
		totalTime=totalTime+highFuelSlowdown;				//car slows down if the fuel is above 50		
	}
	else 
	{
		totalTime=totalTime-lowFuelBoost;				//car speeds up if the fuel is 50 or below
	}
	if (raining == true)
	{
		totalTime=totalTime+rainSlowDown;				// car slows down if it is raining
	}
	else
	{
		totalTime=totalTime;						//if it isn't raining car doesn't slow down
	}
	fuel=fuel-fuelConsumptionPerLap;					//car loses fuel after completing a lap
	return totalTime;							//returns totalTime
	}


}	


