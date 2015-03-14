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
	public List<CustomerOrder> getCustomerOrdersById(int customerOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProductPrice(int productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrders(int customerOrders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeSessionFactory() {
		
		this.sessionFactory.close();
		
	}

}
