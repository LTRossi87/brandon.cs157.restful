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
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double total;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Customer customer;
	
	
	@OneToMany(mappedBy = "customerOrder", targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Product> products;
	
	public CustomerOrder()
	{
		products = new ArrayList<Product>();
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
		products.add(product);
	}
	
	public void removeProduct(Product product)
	{
		
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Customer Order For ");
		stringBuilder.append(this.getCustomer().getName());
		stringBuilder.append("\n");
		stringBuilder.append("Customers Products:");
		stringBuilder.append("\n");
		for (Product product : products) 
		{
			stringBuilder.append("     " + product.toString());
			
		}
		return stringBuilder.toString();
		
	}
	
	
}
