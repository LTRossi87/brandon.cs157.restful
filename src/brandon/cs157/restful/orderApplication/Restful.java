package brandon.cs157.restful.orderApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/store")
public class Restful 
{
	OrderEntryService orderEntryService = new OrderEntryService();
	
	@Path("/customer/{customerName}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewCustomer(@PathParam("customerName") String customerName)
	{
		
		orderEntryService.addCustomer(customerName);
		return "You Have Been Added TO The Store Database";
		 
	}
	
	@Path("/orders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomerOrders()
	{
		return orderEntryService.getAllOrders();
	}
	
	

}
