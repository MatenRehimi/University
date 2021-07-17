public class CalorieTracker							//This creates the class CalorieTracker.
{
	public static void main (String [] args)
	{
		Person sam=new Person();					//This creates an object called sam.
		System.out.println(sam.returnCalories());			//This prints out the object which is calling a method.
		
		Dish tomatoSoup=new Dish(40);					//This creates an object called tomatoSoup and inputs 40 which is the calories of the dish.
		Dish halloumiWrap=new Dish(250);				//This creates an object called halloumiWrap and inputs 250 which is the calories of the dish.
		Dish baklava=new Dish(150);					//This creates an object called baklava and inputs 150 which is the calories of the dish.
		Meal halloumiLunch=new Meal(tomatoSoup,halloumiWrap,baklava);	//This creates an object called halloumiLunch and inputs all the dishes for the meal.
		System.out.println(halloumiLunch.calculateCalories());		//This prints out an object calling a method.	
		sam.eat(halloumiLunch);						//This calls an object which calls a method which has an object as input.
		System.out.println(sam.returnCalories());			//This prints an object calling a method.

		Dish potatoWedges=new Dish(240);				//This creates an object called potatowedges and inptus 240 which is the calories of the dish.
		Dish pizza=new Dish(500);					//This creates an object called pizza and inputs 500 which is the calories of the dish.
		Dish cheesecake=new Dish(260);					//This creates an object called cheesecake and inputs 260 which is the calories of the dish.
		Meal pizzaDinner=new Meal(potatoWedges,pizza,cheesecake);	//This creates an object called pizzaDinner and inputs all the dishes for the meal.
		System.out.println(pizzaDinner.calculateCalories());		//This prints out an object calling a method.
		sam.eat(pizzaDinner);						//This calls an objecet which calls a method which has an object as input.
		System.out.println(sam.returnCalories());			//This prints an object calling a method.
		
		Dish samosa=new Dish(450);					//This creates an object called samosa and inputs 450 which is the calories of the dish.
		Dish chowMein=new Dish(680);					//This creates an object called chowMein and inputs 680 which is the calories of the dish.
		Dish doriyaki=new Dish(220);					//This creates an object called doriyaki and inputs 220 which is the calories of the dish.
		Meal chowMeinDinner=new Meal(samosa,chowMein,doriyaki);		//This creates an object called chowMeinDinner and inputs all the dishes for the meal.
		System.out.println(chowMeinDinner.calculateCalories());		//This prints out an object calling a method.
		sam.eat(chowMeinDinner);					//This calls an object which calls a method which has an object as input.
		System.out.println(sam.returnCalories());			//This prints out an object calling a method.

		sam.walk(790);							//This calls an object which calls a method which has an input of 790.
		System.out.println(790);					//This prints out the integer 790.
	}
}
		
		
		
