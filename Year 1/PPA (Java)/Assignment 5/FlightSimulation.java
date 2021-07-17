public class FlightSimulation
{
	public static void main(String [] args)							//main method
	{																					//initialising objects
		Coordinates destination1Coordinates=new Coordinates(10,35);						//stores coordinates
		Destination destination1=new Destination("Zurich",destination1Coordinates); 	//stores name and coordinates
		Coordinates destination2Coordinates=new Coordinates(15,45);						//stores coordinates
		Destination destination2=new Destination("Doha",destination2Coordinates);		//stores name and coordinates
		Coordinates destination3Coordinates=new Coordinates(150,125);					//stores coordinates
		Destination destination3=new Destination("Tokyo", destination3Coordinates);		//stores name and coordinates

		Coordinates aeroplaneCoordinates=new Coordinates(10,35);							//stores coordinates 
		Aeroplane aeroplane=new Aeroplane("Wright Flyer",aeroplaneCoordinates,17,0,2000);	//stores values and name for aeroplane

		for (int day = 1; day <= 120; day=day+1)							//loop until day=120 and the while loop represents 1 day
		{
			System.out.println("day:"+day);									//represents one flight
			System.out.println("");
			System.out.println(aeroplane.getName());
			System.out.println("Start Destination: " + destination1.getName());
			System.out.println("End Destination: " + destination2.getName());
			System.out.println("Total distance travelled:" + aeroplane.totalDistance(destination2));
			System.out.println("Single journey distance:" + aeroplane.singleFlight(destination2));
			System.out.println("Aeroplane's Coordinates(" + aeroplane.getXCoordinate() + "," + aeroplane.getYCoordinate() + ")") ;
			System.out.println("");

			System.out.println(aeroplane.getName());							//represents one flight
			System.out.println("Start Destination: " + destination2.getName());
			System.out.println("End Destination: " + destination3.getName());
			System.out.println("Total distance travelled:" + aeroplane.totalDistance(destination3));
			System.out.println("Singe journey distance:" + aeroplane.singleFlight(destination3));
			System.out.println("Aeroplane's Coordinates(" + aeroplane.getXCoordinate() + "," + aeroplane.getYCoordinate() + ")") ;
			System.out.println("");

			System.out.println(aeroplane.getName());								//represents one flight
			System.out.println("Start Destination: " + destination3.getName());
			System.out.println("End Destination: " + destination2.getName());
			System.out.println("Total distance travelled:" + aeroplane.totalDistance(destination2));
			System.out.println("Singe journey distance:" + aeroplane.singleFlight(destination2));
			System.out.println("Aeroplane's Coordinates(" + aeroplane.getXCoordinate() + "," + aeroplane.getYCoordinate() + ")") ;
			System.out.println("");

			System.out.println(aeroplane.getName());							//represents one flight
			System.out.println("Start Destination: " + destination2.getName());
			System.out.println("End Destination: " + destination1.getName());
			System.out.println("Total distance travelled:" + aeroplane.totalDistance(destination1));
			System.out.println("Singe journey distance:" + aeroplane.singleFlight(destination1));
			System.out.println("Aeroplane's Coordinates(" + aeroplane.getXCoordinate() + "," + aeroplane.getYCoordinate() + ")") ;
			System.out.println("");

			if (aeroplane.getTotalDistance() >= aeroplane.getRepairDistance())		//if totalDistance >= repairDistance
			{
				day=day+6;								
				aeroplane.setTotalDistance(0);		
			}
		}
	}
}