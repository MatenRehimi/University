import java.util.ArrayList;	//importing classes so I can access them in this class.
import java.util.TreeMap;
public class Shop
{
	private String name;															//creating field of type String
	private ArrayList<Product> products = new ArrayList<Product>();					//creating field which is an array list of type Product
	private ArrayList<GoldCoin> coinBox = new ArrayList<GoldCoin>();				//creating field which is an array list of type GoldCoin
	private TreeMap<String, Integer> customerTotalSpend = new TreeMap<String, Integer>();	//creating field which is a treemap of type String and Integer(these types must be objects so a capital must be used)
	/**
	*This method is a constructor which has a name as a parameter
	*This assigns the field to the name parameter
	*/
	public Shop (String name)
	{
		this.name = name;
	}
	/**
	*This method adds a supplied product to the arraylist products
	*/
	public void addProduct(Product product)
	{
		products.add(product);
	}
	/**
	*This method removes a supplied product from the arraylist
	*And if a product is removed it returns true otherwise it returns false
	*/
	public boolean removeProduct(Product product)
	{
		if (products.remove(product))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	*This method tries to see if the string supplied is equal to the name of any of the products.
	*If so it returns the product however if not it returns null
	*/
	public Product searchProduct(String productName)
	{
		for (Product element : products)
		{
			if (productName.equals(element.getName()))
			{
				return element;
			}
		}	
		return null;
	}
	/**
	*This method adds a supplied GoldCoin to the array list coinBox
	*/
	public void addGoldCoin(GoldCoin goldCoin)
	{
		coinBox.add(goldCoin);
	}
	/**
	*This method takes a supplied customer and int then it finds the total of the coins. 
	*Then it puts the customers name and total in a slot in the customerTotalSpend treemap.
	*/
	public void updateTotalSpend(Customer customer, int coins)
	{
		int total=0;
		total = total + coins;
		customerTotalSpend.put(customer.getName(), total);
	}
	/**
	*Creating a toString method which returns a String and a arraylist
	*/
	public String toString()
	{
		return "shop"  + products ;
	}
	/**
	*This method returns the number of slots in the coinBox arraylist
	*/
	public int getCoinBoxSize()
	{
		return coinBox.size();
	}
}