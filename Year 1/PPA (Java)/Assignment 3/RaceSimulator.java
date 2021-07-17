public class RaceSimulator
{
	public static void main (String [] args)
	{
	RaceTrack silverstone= new RaceTrack(90,false);				//creates 4 objects
	Car car1=new Car(1,55,6,5,25,12,15,0);					
	Car car2=new Car(2,60,8,7,28,14,10,0);					
	Car car3=new Car(3,90,10,6,30,16,9,0);					
	System.out.println(silverstone.determineRaceLeader(car1,car2,car3));	//prints the lap1 leader's id
	System.out.println(silverstone.determineRaceLeader(car1,car2,car3));	//prints the lap2 leader's id
	silverstone.setRain(true);						//Assigns the boolean isRaining value to true
	System.out.println(silverstone.determineRaceLeader(car1,car2,car3));	//prints the lap3 leader's id
	}
}
		
