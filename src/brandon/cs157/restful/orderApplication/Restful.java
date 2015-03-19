package brandon.cs157.restful.orderApplication;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Restful 
{
	private static final OrderEntryService orderEntryService = new OrderEntryService();
	
	@Path("customer/{customerName}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCustomer(@PathParam("customerName") String customerName)
	{
		return orderEntryService.addCustomer(customerName);
	}
	
	@Path("customer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomers()
	{
		return orderEntryService.getCustomers();
	}
	
	@Path("products/{productName}/{productPrice}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduct(@PathParam("productName")String productName, @PathParam("productPrice") String productPrice)
	{
		return orderEntryService.addProduct(productName, productPrice);
	}
	
	@Path("products")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts()
	{
		return orderEntryService.getProducts();
	}
	
	@Path("products/{productId}/{newProductPrice}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProductPrice(@PathParam("productId") String productId, @PathParam("newProductPrice") String newProductPrice)
	{
		return orderEntryService.updateProductPrice(productId, newProductPrice);
	}
	
	@Path("order/{customerName}/{customerId}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addOrder(@PathParam("customerName") String customerName, @PathParam("customerId") String customerId)
	{
		return orderEntryService.addOrder(customerName, customerId);
	}
	
	@Path("purchase/{customerOrderId}/{productId}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String purchaseProduct(@PathParam("customerOrderId")String customerOrderId, @PathParam("productId")String productId)
	{
		return orderEntryService.purchaseProduct(customerOrderId, productId);
	}
	
	@Path("order")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllOrders()
	{
		return orderEntryService.getAllOrders();
	}
	
	@Path("order/{customerOrderId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrderById(@PathParam("customerOrderId") String customerOrderId)
	{
		return orderEntryService.getOrderById(customerOrderId);
	}
	
	@Path("order/{customerOrderId}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomerOrder(@PathParam("customerOrderId")String customerOrderId)
	{
		return orderEntryService.deleteCustomerOrder(customerOrderId);
	}
}