package brandon.cs157.restful.orderApplication;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/store")
public class Restful 
{
	
	private static final Hw2DAO hw2dao = new Hw2DAO();
	
	
	
	@Path("/customer/{customerName}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public void addCustomer(@PathParam("customerName") String customerName)
	{
		Customer customer = new Customer();
		customer.setName(customerName);
		hw2dao.persistObject(customer);
	}
	
	@Path("/customer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomers()
	{
		
		StringBuilder stringBuilder =  new StringBuilder();
		List<Customer> customers = hw2dao.getCustomers();
		if(customers.isEmpty())
		{
			return "There Are Currently No Customers In The Database"; 
		}
		for (Customer customer : customers) {
			stringBuilder.append(customer.toString());
		}
		return stringBuilder.toString();
	}
	
	@Path("/orders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllOrders()
	{
		List<CustomerOrder> customerOrders = hw2dao.getCustomerOrders();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (CustomerOrder customerOrder : customerOrders) 
		{
			stringBuilder.append(customerOrder.toString());
			stringBuilder.append("\n");
		}
		
		return stringBuilder.toString();
	}
}
