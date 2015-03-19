package brandon.cs157.restful.orderApplication;

import java.util.List;

import org.hibernate.*;

public class Hw2DAO implements DAO {

	private SessionFactory sessionFactory;
    
	
	public Hw2DAO()
	{
		try {
            this.sessionFactory = HibernateUtil.getSessionFactory();
        } catch (HibernateException hibernateException) {
            System.out.println("Problem getting Hibernate Session Factory");
            System.exit(1);
        }
        
	}
	
	@Override
	public void persistObject(Object object) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		if(object instanceof Customer)
		{
			session.save((Customer) object);
		}
		else if(object instanceof CustomerOrder)
		{
			session.save((CustomerOrder) object);
		}
		else if(object instanceof Product)
		{
			session.save((Product) object);
		}
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<CustomerOrder> getCustomerOrders() {
		Session session = sessionFactory.openSession();
		
		
		Query query;
        query = session.getNamedQuery("CustomerOrder.retrieveAllOrders");
        List<CustomerOrder> customerOrders = query.list();
        
        session.close();
		
		
        return customerOrders;
	}

	@Override
	public CustomerOrder getCustomerOrdersById(int customerOrderId) {
		
		Session session = sessionFactory.openSession();
		
		Query query;
        query = session.getNamedQuery("CustomerOrder.retrieveOrderById");
        query.setInteger("id", customerOrderId);
        CustomerOrder customerOrders = (CustomerOrder) query.uniqueResult();
        
        session.close();
		
        return customerOrders;
	}
	
	@Override
	public void updateCustomerOrder(int customerOrderId, Product product)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		CustomerOrder customerOrder = this.getCustomerOrdersById(customerOrderId);
		
		product.setCustomerOrder(customerOrder);
		session.update(product);
		customerOrder.purchaseProduct(product);
		session.update(customerOrder);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Override
	public List<Product> getProducts() {
		Session session = sessionFactory.openSession();
		
		Query query;
        query = session.getNamedQuery("Product.retrieveAllProducts");
        List<Product> products = query.list();
        
        session.close();
		
        return products;
	}
	
	@Override
	public Product getProductById(int productId)
	{
		Session session = sessionFactory.openSession();
		
		Query query;
        query = session.getNamedQuery("Product.retrieveProductById");
        query.setInteger("id", productId);
        Product product = (Product) query.uniqueResult();
        
        session.close();
		
        return product;
	}
	
	@Override
	public Product getProductByName(String name)
	{
		Session session = sessionFactory.openSession();
		System.out.println("The Name of the Product is " + name);
		Query query;
        query = session.getNamedQuery("Product.retrieveProductByName");
        query.setString("name", name);
        Product product = (Product) query.uniqueResult();
        System.out.println(product.getName());
        
        session.close();
		
        return product;
	}

	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.openSession();
		
		Query query;
        query = session.getNamedQuery("Customer.retrieveAllCustomers");
        List<Customer> products = query.list();
        
        session.close();
		
        return products;
	}
	
	@Override
	public Customer getCustomerByNameAndId(String customerName, int customerId)
	{
		Session session = sessionFactory.openSession();
		Query query;
		query = session.getNamedQuery("Customer.retrieveCustomersByNameAndId");
		query.setString("name", customerName);
		query.setInteger("id", customerId);
		Customer customer = (Customer) query.uniqueResult();
		session.close();
		return customer;
	}

	@Override
	public void updateProductPrice(int productId, double price) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
        query = session.getNamedQuery("Product.updatePrice");
        query.setInteger("id", productId);
        Product product = (Product) query.uniqueResult();
        product.setPrice(price);
        
        session.update(product);
        
        
        session.getTransaction().commit();
        session.close();
	}

	public void updateCustomerOrderTotal()
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<CustomerOrder> customerOrders = this.getCustomerOrders();
		
		for (CustomerOrder customerOrder : customerOrders) {
			double total = customerOrder.getNewTotalPrice();
			customerOrder.setTotal(total);
			session.update(customerOrder);
		}
		
		session.getTransaction().commit();
        session.close();
	}
	
	@Override
	public void deleteOrders(int customerOrderId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CustomerOrder customerOrder = this.getCustomerOrdersById(customerOrderId);
		for (Product product : customerOrder.getProducts()) 
		{
			product.setCustomerOrder(null);
			session.update(product);
		}
		customerOrder.getCustomer().setOrder(null);
		
		session.delete(customerOrder);
		
		session.getTransaction().commit();
		session.close();
		
	}
	

	@Override
	public void closeSessionFactory() {
		
		this.sessionFactory.close();
		
	}

}
