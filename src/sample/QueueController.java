package sample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class to handle the REST Service and to provide endpoints
 * @author Ashish
 *
 */
@RestController
public class QueueController 
{
	private static ArrayList<Order> orderList = new ArrayList<Order>();
	
	
	/**
	 * Function to add an item to the list of orders
	 * @param customerId customer ID of the customer placing the order
	 * @param quantity amount of rubber ducks required in the order
	 * @return String with information if order was placed or not
	 */
	@RequestMapping("/addOrder")
	public String addItem(@RequestParam(value="customerId") int customerId,@RequestParam(value="quantity") int quantity)
	{
		if(customerId>20000)
		{
			return "Customer ID should be less than 20000";
		}
		if(quantity>25 || quantity < 1)
		{
			return "Quantity of order should be between 0 and 25";
		}
		Order order = new Order(customerId, quantity);
		//Check if order queue is empty
		if(orderList.size() == 0)
		{
			orderList.add(order);
			return "Order for Client ID " + String.valueOf(customerId) + " for " + String.valueOf(quantity) + " rubber ducks succesfully added.";  
		}
		else
		{
			//Check if customer has already place order
			for(int i = 0;i<orderList.size();i++)
			{
				if(orderList.get(i).getCustomerId() == customerId)
				{
					return "Order for Client ID " + String.valueOf(customerId) + " has been placed before. Cannot place new order.";
				}
			}
			//Check for Customer ID less than 1000
			if(customerId <1000)
			{
				int counter = 0;
				int customer = orderList.get(counter).getCustomerId();
				while((customer<1000)&&(counter<orderList.size()))
				{
					customer = orderList.get(counter).getCustomerId();
					counter++;
					
				}
				//If end of list has not been reached
				if(customer>=1000 && counter>0)
				{
					counter--;
				}
				orderList.add(counter, order);
				return "Order for Client ID " + String.valueOf(customerId) + " for " + String.valueOf(quantity) + " rubber ducks succesfully added.";  
			}
			else
			{
				orderList.add(order);
				return "Order for Client ID " + String.valueOf(customerId) + " for " + String.valueOf(quantity) + " rubber ducks succesfully added.";  
			}
		}

	}

	
	/**
	 * Function to remove a customer's order from the list
	 * @param customerId ID of the customer who wants the order to be removed
	 * @return String value with status
	 */
	@RequestMapping("/removeOrder")
	public String removeOrder(@RequestParam(value="customerId") int customerId)
	{
		if(customerId>20000)
		{
			return "Customer ID should be less than 20000";
		}
		for(int i =0;i<orderList.size();i++)
		{
			if(orderList.get(i).getCustomerId() == customerId)
			{
				orderList.remove(i);
				return "Order for customer " + String.valueOf(customerId) + " removed";
			}
		}
		return "Customer ID " + String.valueOf(customerId) + " not found in queue";
	}

	/**
	 * Function to get the list of orders currently in the queue
	 * @return ArrayList of orders
	 */
	@RequestMapping("/getList")
	public String getList()
	{
		JSONArray jsonArray = new JSONArray();
		for(int i = 0 ;i<orderList.size();i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Client ID", orderList.get(i).getCustomerId());
			jsonObject.put("Quantity", orderList.get(i).getQuantity());
			jsonObject.put("Waiting Time", getMinutes(i));
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}

	
	/**
	 * Function to get the current position of a customer's order in the queue and the approx. waiting time in minutes
	 * @param customerId ID of the customer requesting the status of order
	 * @return String with current position of order and approx. waiting time in minutes
	 */
	@RequestMapping("/getWaitTime")
	public String getWaitTime(@RequestParam(value="customerId") int customerId)
	{
		if(customerId>20000)
		{
			return "Customer ID should be less than 20000";
		}
		JSONObject jsonObject = new JSONObject();
		for(int i = 0;i<orderList.size();i++)
		{
			if(orderList.get(i).getCustomerId() == customerId)
			{
				jsonObject.append("Queue Position", i+1);
				int minutes = getMinutes(i);
				jsonObject.append("Wait Time", minutes);
				return jsonObject.toString();
			}
			
		}
		return "No order for customer ID " + customerId + " found.";
	}
	
	/**
	 * Function to calculate approx. waiting time for an order
	 * @param customerPos Position of customer
	 * @return Integer value of waiting time
	 */
	private int getMinutes(int customerPos)
	{
		//Count initialized to 1 to show minimum 5 minutes even if customer's order is next order to be processed
		int count = 1;
		int totalOrder = 0;
		for (int i = 0;i<=customerPos;i++)
		{
			if(totalOrder+orderList.get(i).getQuantity() <= 25)
			{
				totalOrder+=orderList.get(i).getQuantity();
				
				if(totalOrder == 25 && i!=customerPos)
				{
					totalOrder = 0;
					count++;
				}
			}
			else
			{
				totalOrder = orderList.get(i).getQuantity();
				count++;
			}
		}
		return count*5;
	}
	
	/**
	 * Function to delete orders which have been sent
	 * @param orders ArrayList of orders which have been sent
	 */
	private void deleteOrders(ArrayList<Order> orders)
	{
		for(int i = 0;i<orders.size();i++)
		{
			if(orderList.contains(orders.get(i)));
			{
				orderList.remove(orders.get(i));
			}
		}
	}
	
	/**
	 * Function to get the orders to be carried in the cart
	 * @return ArrayList of orders to be carried
	 */
	@RequestMapping("/getOrders")
	public ArrayList<Order> getOrders()
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		int totalOrder = 0;
		for(int i = 0;i<orderList.size();i++)
		{
			if(totalOrder+orderList.get(i).getQuantity() <= 25)
			{
				totalOrder+=orderList.get(i).getQuantity();
				orders.add(orderList.get(i));
				
				if(totalOrder == 25)
				{
					deleteOrders(orders);
					return orders;
				}
			}
			else
			{
				deleteOrders(orders);
				return orders;
			}
		}
		deleteOrders(orders);
		return orders;
	}

}
