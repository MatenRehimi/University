import java.util.ArrayList;		//importing a class so I can access them in this class.
public class Customer
{
	private String name;		//creating name field of type String
	private ArrayList<Product> shoppingBasket = new ArrayList<Product>();	//Creating shoppingBasket which is an arraylist of type Product
	private ArrayList<Product> ownedProducts = new ArrayList<Product>();	//Creating ownedProducts which is an arraylist of type Product
	private ArrayList<GoldCoin> purse = new ArrayList<GoldCoin>();			//Creating purse which is an arraylist of type GoldCoin
	/**
	*This method is a constructor which assigns the name supplied to the field
	*/
	public Customer (String name)
	{
		this.name = name;
	}
	/**
	*This method adds the supplied product to the arraylist shoppingBasket
	*/
	public void addToShoppingBasket(Product product)
	{
		shoppingBasket.add(product);
	}
	/**
	*This method removes the supplied product from the arraylist. 
	*If a product is removed it removes true and if null was removed then it returns false
	*/
	public boolean removeFromShoppingBasket(Product product)
	{
		if(shoppingBasket.remove(product))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	*This method takes a supplied name and checks if it matches an element in the arraylist
	*If so it returns that element which is a product
	*If not it returns null
	*/
	public Product searchShoppingBasket (String name)
	{
		for (Product element : shoppingBasket)
		{
			if (name.equals(element.getName()))
			{
				return element;
			}
		}	
		return null;
	}
	/**
	*This method adds the supplied product to the ownedProducts arraylist
	*/
	public void addOwnedProduct (Product product)
	{
		ownedProducts.add(product);
	}
	/**
	*This method adds the supplied GoldCoin to the purse arraylist
	*/
	public void addCoin (GoldCoin coin)
	{
		purse.add(coin);
	}
	/**
	* A getter method for the name field
	*/
	public String getName()
	{
		return name;
	}
	/**
	* First, it checks all the elements in the shoppingBasket arraylist and adds up all the prices of the elements.
	* If the total price is more than the purse slot length then it returns false
	* Otherwise, it transfers the GoldCoins in the purse to the shop's coinbox.
	* Then it moves all the products in the shoppingBasket array list to the ownedProducts array list.
	* Then it clears all the slots in the shoppingBasket
	* Then the total of the all prices of the products are updated
	*/
	public boolean purchaseProducts(Shop shop)
	{
		int basketTotal = 0;
		for (Product element: shoppingBasket)
		{
			basketTotal = basketTotal + element.getPrice();
		}

		if ( basketTotal > purse.size())
		{
			return false;
		}
		else 
		{
			for (int j=0; j < basketTotal; j++)
			{
				shop.addGoldCoin(purse.get(j));
				
			}
			int num = purse.size();
			for (int j=purse.size()-1; j >= (num-basketTotal) ; j--)
			{
				purse.remove(j);
			}

			for (int k=0; k < shoppingBasket.size(); k++)
			{
				ownedProducts.add(shoppingBasket.get(k));
			}
			shoppingBasket.clear();
			shop.updateTotalSpend(this, basketTotal);
			return true;
			
		}
	}
	/**
	* A getter for the purse arraylist length
	*/
	public int getPurse()
	{
		return purse.size();
	}
	/**
	* A getter for the shoppingBasket arraylist 
	*/
	public ArrayList<Product> getShoppingBasket()
	{
		return shoppingBasket;
	}
}