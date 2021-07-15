public class Product
{
	private String name;	//creating the fields while specifying the type and accessibility from other classes
	private int price;
	/**
	*This method is a constructor
	*This assigns the paramter to the field
	*/

	public Product (String name, int price)
	{
		this.name = name;
		this.price = price;
	}
	/**
	*Creating a toString method which returns 8 strings and 2 fields.
	*To print this out you must print out the class.
	*/
	public String toString()
	{
		return "Product" + "["+"name"+"=" +name+", "+"price"+"=" +price+"]";
	}
	/**
	*Creating a getter for the name field.
	*/
	public String getName()
	{
		return name;
	}
	/**
	*Creating a getter for the price field.	
	*/
	public int getPrice()
	{
		return price;
	}
}