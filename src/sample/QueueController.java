package sample;

import java.util.ArrayList;

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
		Order order = new Order(customerId, quantity);
		if(orderList.size() == 0)
		{
			orderList.add(order);
			return "Order for Client ID " + String.valueOf(customerId) + " for " + String.valueOf(quantity) + " rubber ducks succesfully added.";  
		}
		else
		{
			for(int i = 0;i<orderList.size();i++)
			{
				if(orderList.get(i).getCustomerId() == customerId)
				{
					return "Order for Client ID " + String.valueOf(customerId) + " has been placed before. Cannot place new order.";
				}
			}
			if(customerId <1000)
			{
				int counter = 0;
				int customer = orderList.get(counter).getCustomerId();
				while((customer<1000)&&(counter<orderList.size()))
				{
					customer = orderList.get(counter).getCustomerId();
					counter++;
					
				}
				/*for(int i = orderList.size();i>counter;i--)
				{
					orderList.set(i, orderList.get(i-1));
				}
				orderList.set(counter, order);*/
				if(customer>=1000)
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
	 * Function to remove a customer;s order from the list
	 * @param customerId ID of the customer who wants the order to be removed
	 */
	@RequestMapping("/removeOrder")
	public void removeOrder(@RequestParam(value="customerId") int customerId)
	{
		for(int i =0;i<orderList.size();i++)
		{
			if(orderList.get(i).getCustomerId() == customerId)
			{
				orderList.remove(i);
				return;
			}
		}
	}

	/**
	 * Function to get the list of orders currently in the queue
	 * @return ArrayList of orders
	 */
	@RequestMapping("/getList")
	public ArrayList<Order> getList()
	{
		return orderList;
	}

	
	/**
	 * Function to get the current position of a customer's order in the queue and the approx. waiting time in minutes
	 * @param customerId ID of the customer requesting the status of order
	 * @return String with current position of order and approx. waiting time in mib=nutes
	 */
	@RequestMapping("/getWaitTime")
	public String getWaitTime(@RequestParam(value="customerId") int customerId)
	{
		int totalPackages = 0;
		JSONObject jsonObject = new JSONObject();
		for(int i = 0;i<orderList.size();i++)
		{
			if(orderList.get(i).getCustomerId() == customerId)
			{
				jsonObject.append("Queue Position", i+1);
				jsonObject.append("Wait Time", (totalPackages/25)*5);
				//return (String.valueOf(i) + " " + String.valueOf((totalPackages/25)*5));
				return jsonObject.toString();
			}
			totalPackages+= orderList.get(i).getQuantity();
		}
		return null;
	}
	
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
				//orderList.remove(i);

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
