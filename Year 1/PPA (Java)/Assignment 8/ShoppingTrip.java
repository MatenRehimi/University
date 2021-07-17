import java.util.ArrayList;	//Importing classes so this class has access to it
import java.util.Scanner;

/**
* This is the main method
* Initially it creates three product objects with the supplied parameters
* Then it creates one shop object with the suppled parameter
* It adds the 3 products to the products arraylist in the shop1 object
* Then it adds 125 goldCoins to the coinbox arraylist in the shop1 object
* It prints out the shop1 object which prints shop then all the individual product name and prices 
* It makes an object of the scanner class that I imported at the start of this class
* It prints two Strings.
* Then the while loop keeps going until it is manually stopped by the string exit
* It prints all the products in the shop, the products in the shopping basket and the number of coins in the purse
* Then it ask the user to input a String
* This String is stored as the variable input of type String
* 	If the input = add product then it asks for another input which is stored as input1
* 		It finds the product which correlates to input1 and stores it as item1
* 		If item1 is not null then it adds it to the shopping basket and removes it from the shop
*	If the input = remove product then it asks for another input which is stored as input2
*		It finds the product which correlates to input2 and stores it as item2
*		If item2 is not null then it removes it from the shopping basket and adds it onto the shop
*	If the input = purchase then it buys all the items in the shopping basket at the current time
*/
public class ShoppingTrip
{
	public static void main (String args [])
	{
		Product product1 = new Product("Diamond",40);
		Product product2 = new Product("Crown Jewels",100);
		Product product3 = new Product("Silver Locket",60);

		Shop shop1 = new Shop("Hidden Hideaway");

		
		shop1.addProduct(product1);
		shop1.addProduct(product2);
		shop1.addProduct(product3);

		for (int i=0; i<125; i++)
		{
			shop1.addGoldCoin(new GoldCoin());
		}
		System.out.println("");
		System.out.println(shop1);
		System.out.println("Number of Coins in the coinbox = " + shop1.getCoinBoxSize());

		Customer customer1 = new Customer("BlackBeard");

		for (int j=0; j<100; j++)
		{
			customer1.addCoin(new GoldCoin());
		}
		System.out.println("Name = " + customer1.getName() + ", Coins left in purse = " + customer1.getPurse());

		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to Hidden Hideaway!");
		System.out.println("");

		while (true)
		{	
			System.out.println(shop1);
			System.out.println(customer1.getShoppingBasket());
			System.out.println(customer1.getPurse());
			System.out.println();
			System.out.println("Please add a product, remove a product or purchase your products in your basket");
			String input = in.nextLine();
			if (input.equals("exit"))
			{
				break;
			}
			else if (input.equals("add product"))
			{
				System.out.println("Which product would you like to add to your basket?");
				System.out.println(customer1.getShoppingBasket());
				String input1 = in.nextLine();
				Product item1 = shop1.searchProduct(input1);
				if (item1 != null)
				{
					customer1.addToShoppingBasket(item1);
					shop1.removeProduct(item1);
				}
				else
				{
					System.out.println("Sorry the product was not found in the shop");
				}								
			}
			else if (input.equals("remove product"))
			{
				System.out.println("Which product would you like to remove from your basket?");
				System.out.println(customer1.getShoppingBasket());
				String input2 = in.nextLine();
				Product item2 = customer1.searchShoppingBasket(input2);
				if (item2 != null)
				{
					if (customer1.removeFromShoppingBasket(item2))
					{
						shop1.addProduct(item2);
					}	
					else
					{
						System.out.println("Sorry the product was not found in your shopping basket");
					}
				}
			}
			else if (input.equals("purchase"))
			{
				customer1.purchaseProducts(shop1);
			}
			else
			{
				System.out.println("Input is not recognised");
			}
		}
	}
}