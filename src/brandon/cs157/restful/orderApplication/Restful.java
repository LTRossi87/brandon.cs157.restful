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
	public String addCustomer(@PathParam("customerName") String customerName)
	{
		Customer customer = new Customer();
		customer.setName(customerName);
		hw2dao.persistObject(customer);
		return "Customer " + customerName + " Has Been Added To The Database";
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
	
	@Path("/products/{productName}/{productPrice}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduct(@PathParam("productName")String productName, @PathParam("productPrice") String productPrice)
	{
		double price = Double.parseDouble(productPrice);
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		hw2dao.persistObject(product);
		
		return "Product: " + productName + " For $" + productPrice +" Has Been Added To The Database";
	}
	
	@Path("/products")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts()
	{
		StringBuilder stringBuilder = new StringBuilder();
		List<Product> products = hw2dao.getProducts();
		if(products.isEmpty())
		{
			return "There Are Currently No Products In The Database";
		}
		for (Product product : products) {
			stringBuilder.append(product.toString());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	@Path("/products/{productId}/{newProductPrice}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProductPrice(@PathParam("productId") String productId, @PathParam("newProductPrice") String newProductPrice)
	{
		int id = Integer.parseInt(productId);
		double price = Double.parseDouble(newProductPrice);
		hw2dao.updateProductPrice(id, price);
		hw2dao.updateCustomerOrderTotal();
		return "Updated Product: " + productId + " Price To: $" + newProductPrice;
	}
	
	@Path("/order/{customerName}/{customerId}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addOrder(@PathParam("customerName") String customerName, @PathParam("customerId") String customerId)
	{
		int id = Integer.parseInt(customerId);
		Customer customer = hw2dao.getCustomerByNameAndId(customerName, id);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer(customer);
		hw2dao.persistObject(customerOrder);
		
		return "Order For Customer " + customerName + " Has Been Created";
	}
	
	@Path("/purchase/{customerOrderId}/{productId}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String purchaseProduct(@PathParam("customerOrderId")String customerOrderId, @PathParam("productId")String productId)
	{
		
		int product_id = Integer.parseInt(productId);
		int customer_order_id = Integer.parseInt(customerOrderId);
		Product product = hw2dao.getProductById(product_id);
		hw2dao.updateCustomerOrder(customer_order_id, product);
		
		return "Product " + product.getName() + " Has Been Added To Customer Order: " + customerOrderId;
	}
	
	@Path("/order")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllOrders()
	{
		
		StringBuilder stringBuilder = new StringBuilder();
		
		List<CustomerOrder> customerOrders = hw2dao.getCustomerOrders();
		if(customerOrders.isEmpty())
		{
			return "There Are Currently No Customer Orders In The Database";
		}
		
		for (CustomerOrder customerOrder : customerOrders) 
		{
			stringBuilder.append(customerOrder.toString());
			stringBuilder.append("\n");
		}
		
		return stringBuilder.toString();
	}
	
	@Path("/order/{customerOrderId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOrderById(@PathParam("customerOrderId") String customerOrderId)
	{
		
		int id = Integer.parseInt(customerOrderId);
		StringBuilder stringBuilder = new StringBuilder();
		
		CustomerOrder customerOrder = hw2dao.getCustomerOrdersById(id);
		
		return customerOrder.toString();
	}
	
	@Path("/order/{customerOrderId}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomerOrder(@PathParam("customerOrderId")String customerOrderId)
	{
		int id = Integer.parseInt(customerOrderId);
		hw2dao.deleteOrders(id);
		return "Customer Order " + customerOrderId + " Has been Deleted";
	}
	
	
	
}
