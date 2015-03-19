package brandon.cs157.restful.orderApplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name="CustomerOrder.retrieveAllOrders", query="from CustomerOrder"),
    @NamedQuery(name="CustomerOrder.retrieveOrderById", query="from CustomerOrder where id = :id")
})
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double total;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	
	@OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
	private List<Product> products;
	
	public CustomerOrder()
	{
		products = new ArrayList<Product>();
		total = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void purchaseProduct(Product product)
	{
//		total += product.getPrice();
		products.add(product);
		total = this.getNewTotalPrice();
	}
	
	public void removeProduct(Product product)
	{
		
	}
	
	public String toString()
	{
		double total = 0;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Customer Order For ");
		stringBuilder.append(this.customer.getName());
		stringBuilder.append(" Order ID ");
		stringBuilder.append(this.id);
		stringBuilder.append("\n");
		stringBuilder.append("Customers Products:");
		stringBuilder.append("\n");
		if(!products.isEmpty())
		{
			System.out.println(products);////
			
			for (Product product : products) 
			{
				total += product.getPrice();
				stringBuilder.append("     " + product.toStringForCustomerOrders());
				
			}
		}
		else
		{
			stringBuilder.append("     Customer Has No Products In Their Order");
		}
		
		stringBuilder.append("\n");
		stringBuilder.append("Total Price: ");
		stringBuilder.append(total);
		stringBuilder.append("\n");
		return stringBuilder.toString();
		
	}
	
	public double getNewTotalPrice()
	{
		total = 0;
		
		for (Product product : products) {
			System.out.println(product.getName() + " " + product.getPrice());
			total += product.getPrice();
		}
		
		System.out.println(total);
		return total;
	}
	
	
}
