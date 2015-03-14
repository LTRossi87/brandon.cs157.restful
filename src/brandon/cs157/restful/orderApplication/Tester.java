package brandon.cs157.restful.orderApplication;

import java.util.List;

public class Tester {

	public static void main(String[] args) {
		
		Hw2DAO hw2dao = new Hw2DAO();
		
		
		
		Product product = new Product();
		Product product2 = new Product();
		Customer customer = new Customer();
		CustomerOrder customerOrder = new CustomerOrder();
		
		product.setName("Dog");
		product.setPrice(25.0);
		product2.setName("Cat");
		product2.setPrice(15.25);
		customer.setName("Brandon");
		
		hw2dao.persistObject(product);
		hw2dao.persistObject(product2);
		hw2dao.persistObject(customer);
		
		customer.setOrder(customerOrder);
		customerOrder.setCustomer(customer);
		customerOrder.purchaseProduct(product);
		product.setCustomerOrder(customerOrder);
		customerOrder.purchaseProduct(product2);
		product2.setCustomerOrder(customerOrder);
		
		
		
		hw2dao.persistObject(customerOrder);
		
		
		List<CustomerOrder> customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder2 : customerOrders) {
			System.out.println(customerOrder2.toString());
		}
		
		hw2dao.closeSessionFactory();

	}

}
