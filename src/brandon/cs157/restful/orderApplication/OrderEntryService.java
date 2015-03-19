package brandon.cs157.restful.orderApplication;

import java.util.List;

public class OrderEntryService {
	private static final Hw2DAO hw2dao = new Hw2DAO();
	
	public String addCustomer(String customerName)
	{
		Customer customer = new Customer();
		customer.setName(customerName);
		hw2dao.persistObject(customer);
		return "Customer " + customerName + " Has Been Added To The Database";
	}
	
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
	
	public String addProduct(String productName, String productPrice)
	{
		double price = Double.parseDouble(productPrice);
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		hw2dao.persistObject(product);
		
		return "Product: " + productName + " For $" + productPrice +" Has Been Added To The Database";
	}
	
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
	
	public String updateProductPrice(String productId, String productPrice)
	{
		int id = Integer.parseInt(productId);
		double price = Double.parseDouble(productPrice);
		hw2dao.updateProductPrice(id, price);
		hw2dao.updateCustomerOrderTotal();
		return "Updated Product: " + productId + " Price To: $" + productPrice;
	}
	
	
	public String addOrder(String customerName, String customerId)
	{
		int id = Integer.parseInt(customerId);
		Customer customer = hw2dao.getCustomerByNameAndId(customerName, id);
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomer(customer);
		hw2dao.persistObject(customerOrder);
		
		return "Order For Customer " + customerName + " Has Been Created";
	}
	
	public String purchaseProduct(String customerOrderId, String productId)
	{
		try
		{
			int product_id = Integer.parseInt(productId);
			int customer_order_id = Integer.parseInt(customerOrderId);
			Product product = hw2dao.getProductById(product_id);
			hw2dao.updateCustomerOrder(customer_order_id, product);
		
			return "Product " + product.getName() + " Has Been Added To Customer Order: " + customerOrderId;
		}catch (Exception e)
		{
			return "Sorry All Out Of That Product";
		}
	}
	
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
	
	public String getOrderById(String customerOrderId)
	{

		int id = Integer.parseInt(customerOrderId);
		StringBuilder stringBuilder = new StringBuilder();
		
		CustomerOrder customerOrder = hw2dao.getCustomerOrdersById(id);
		
		return customerOrder.toString();
	}
	
	public String deleteCustomerOrder(String customerOrderId)
	{
		int id = Integer.parseInt(customerOrderId);
		hw2dao.deleteOrders(id);
		return "Customer Order " + customerOrderId + " Has been Deleted";
	}
	
}
