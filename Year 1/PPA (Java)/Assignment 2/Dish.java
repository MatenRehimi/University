public class Dish				//This creates the class Dish.
{
	private int calories;			//This creates a field called calories which can only be an integer.
	public Dish (int enterCalories)		//This creates a method called Dish with a constructor called int enterCalories.
	{
		this.calories=enterCalories;	//This assigns the field calories to entercalories.
	}
	public int giveCalories()		//This creates a method called giveCalories which returns the calories.
	{
	return calories;			
	}
}
