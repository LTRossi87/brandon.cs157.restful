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
		
		List<CustomerOrder> customerOrders;
		List<Product> products;
		List<Customer> customers;
		
		customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder2 : customerOrders) {
			System.out.println(customerOrder2.toString());
		}
		
		CustomerOrder customerOrder2 = hw2dao.getCustomerOrdersById(1);
		System.out.println(customerOrder2.toString());
		
		products = hw2dao.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}
		
		customers = hw2dao.getCustomers();
		for (Customer customer2 : customers) {
			System.out.println(customer2.toString());
		}
		
		//hw2dao.deleteOrders(1);
		
		hw2dao.updateProductPrice(1, 100.00);
		
		products = hw2dao.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}
		
		customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder3 : customerOrders) {
			System.out.println(customerOrder3.toString());
		}
		

		Product fish = new Product();
		fish.setPrice(10.0);
		fish.setName("Fish");
		Product cow = new Product();
		cow.setPrice(3000.0);
		cow.setName("Cow");
		Product hourse = new Product();
		hourse.setPrice(20000.0);
		hourse.setName("hourse");
		
		Customer anna = new Customer();
		anna.setName("Anna");
		Customer david = new Customer();
		david.setName("David");
		
		
		hw2dao.persistObject(fish);
		hw2dao.persistObject(cow);
		hw2dao.persistObject(hourse);
		hw2dao.persistObject(anna);
		hw2dao.persistObject(david);
		
		products = hw2dao.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}
		
		customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder3 : customerOrders) {
			System.out.println(customerOrder3.toString());
		}
		
		customers = hw2dao.getCustomers();
		for (Customer customer2 : customers) {
			System.out.println(customer2.toString());
		}
		
		
		CustomerOrder annaOrder = new CustomerOrder();
		anna.setOrder(annaOrder);
		annaOrder.setCustomer(anna);
		annaOrder.purchaseProduct(fish);
		fish.setCustomerOrder(annaOrder);
		
		
		hw2dao.persistObject(annaOrder);

		products = hw2dao.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}
		
		customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder3 : customerOrders) {
			System.out.println(customerOrder3.toString());
		}
		
		customers = hw2dao.getCustomers();
		for (Customer customer2 : customers) {
			System.out.println(customer2.toString());
		}
		
		CustomerOrder davidOrder = new CustomerOrder();
		
		davidOrder.purchaseProduct(cow);
		davidOrder.purchaseProduct(hourse);
		hourse.setCustomerOrder(davidOrder);
		cow.setCustomerOrder(davidOrder);
		
		david.setOrder(davidOrder);
		davidOrder.setCustomer(david);
		
		hw2dao.persistObject(davidOrder);
		
		
		products = hw2dao.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}
		
		customerOrders = hw2dao.getCustomerOrders();
		for (CustomerOrder customerOrder3 : customerOrders) {
			System.out.println(customerOrder3.toString());
		}
		
		customers = hw2dao.getCustomers();
		for (Customer customer2 : customers) {
			System.out.println(customer2.toString());
		}
		
		
		
		
		hw2dao.closeSessionFactory();
		
	}

}
