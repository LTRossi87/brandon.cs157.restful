package brandon.cs157.restful.orderApplication;

import java.util.List;

import org.hibernate.HibernateException;

public interface DAO {
	
	public void persistObject(Object object) throws HibernateException;
	
	public List<CustomerOrder> getCustomerOrders() throws HibernateException;
	
	public CustomerOrder getCustomerOrdersById(int customerOrderId) throws HibernateException;
	
	public void updateCustomerOrder(int customerOrderId, Product product);
	
	public List<Product> getProducts() throws HibernateException;
	
	public Product getProductById(int productId);
	
	public List<Customer> getCustomers() throws HibernateException; 
	
	public Customer getCustomerByNameAndId(String customerName, int customerId);
	
	public void updateProductPrice(int productId, double price) throws HibernateException;
	
	public void deleteOrders(int customerOrderId) throws HibernateException;
	
	public void closeSessionFactory() throws HibernateException;
	
}
