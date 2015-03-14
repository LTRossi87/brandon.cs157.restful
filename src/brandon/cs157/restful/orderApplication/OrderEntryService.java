package brandon.cs157.restful.orderApplication;

import java.util.List;

public class OrderEntryService {
	
	Hw2DAO hw2dao = new Hw2DAO();
	
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
