package sample;

/**
 * Order Class. Contains two integers<br> 
 * <b>customerId</b>  the ID of the customer who placed the order <br>
 * <b>quantity</b>  the amount of items in the order
 * @author Ashish 
 */
public class Order {
	private int customerId;
	private int quantity;
	
	/**
	 * Constructor
	 * @param customerId  the ID of the customer who placed the order
	 * @param quantity the amount of items in the order
	 */
	public Order(int customerId, int quantity) 
	{
		super();
		this.customerId = customerId;
		this.setQuantity(quantity);
	}
	
	/**
	 * Function to get the customerId of Order Object
	 * @return Integer value of ID of the Customer who placed the order
	 */
	public int getCustomerId() 
	{
		return customerId;
	}
	
	/**
	 * Function to set the customerId of Order Object
	 * @param customerId the ID of customer placing the order
	 */
	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}
	
	/**
	 * Function to return quantity of Order Object
	 * @return Integer value of the quantity in the order
	 */
	public int getQuantity() 
	{
		return quantity;
	}
	
	/**
	 * Function to set the quantity of Order
	 * @param quantity amount of items in the order
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	

}
