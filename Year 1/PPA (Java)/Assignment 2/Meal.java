public class Meal						//This creates the class Meal.
{
	private Dish starterDish;				//This creates the object starterDish.
	private Dish mainDish;					//This creates the object mainDish.
	private Dish dessertDish;				//This creates the object dessertDish.
	private int calories;					//This creates the field calories.

	public Meal(Dish enterStarterDish, Dish enterMainDish, Dish enterDessertDish)           //This creates the method Meal with three constructors.
	{
		starterDish = enterStarterDish;							//We have to do this step so our other classes have access to the dishes in the meal as it is public.	
		mainDish = enterMainDish;
		dessertDish = enterDessertDish;
		calories = starterDish.giveCalories() + mainDish.giveCalories() + dessertDish.giveCalories();	//This gives you the total calories of all three dishes of the meal.
	}
	public int calculateCalories()				//This creates the method calculateCalories.
	{	
		return calories;
	}
}
