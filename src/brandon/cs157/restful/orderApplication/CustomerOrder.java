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
		total += product.getPrice();
		products.add(product);
	}
	
	public void removeProduct(Product product)
	{
		
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Customer Order For ");
		stringBuilder.append(this.customer.getName());
		stringBuilder.append("\n");
		stringBuilder.append("Customers Products:");
		stringBuilder.append("\n");
		if(!products.isEmpty())
		{
			for (Product product : products) 
			{
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
	
	
}
