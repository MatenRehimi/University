public class Person						//This creates the class Person.
{
	private int calories;					//This creates the field calories.
	public Person()						//This creates the class Person.
	{
		calories=0;
	}
	public void eat(Meal consumed)				//This creates the method eat with a constructor.
	{
		int caloriesConsumed = consumed.calculateCalories();
		calories=calories+caloriesConsumed;
	}
	public void walk(int minute)				//This creates the method walk with a constructor.
	{
		calories=calories-minute;
	}
	public int returnCalories()				//This creates the method returnCalories.
	{
	return calories;
	}
}


		
