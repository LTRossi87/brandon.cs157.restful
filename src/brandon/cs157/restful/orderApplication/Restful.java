package brandon.cs157.restful.orderApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/store")
public class Restful 
{
	OrderEntryService orderEntryService = new OrderEntryService();
	@Path("/title")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTitle()
	{
		return "Hellow from Restful.java";
	}
	
	@Path("/test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTrue()
	{
		orderEntryService.test();
		return "Done";
	}
	
	@Path("/orders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomerOrders()
	{
		return orderEntryService.getAllOrders();
	}
	
	

}
