package brandon.cs157.restful.orderApplication;

import java.util.List;

public class OrderEntryService {
	
	Hw2DAO hw2dao = new Hw2DAO();
	public void test()
	{
		
		Product product = new Product();
		Customer customer = new Customer();
		CustomerOrder customerOrder = new CustomerOrder();
		
		product.setName("Dog");
		product.setPrice(25.0);
		
		customer.setName("Brandon");
		
		hw2dao.persistObject(product);
		hw2dao.persistObject(customer);
		hw2dao.persistObject(customerOrder);
		
		hw2dao.closeSessionFactory();
	}
	
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
