package brandon.cs157.restful.orderApplication;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@Entity
@NamedQueries({
    @NamedQuery(name="Product.retrieveAllProducts", query="from Product"),
    @NamedQuery(name="Product.retrieveProductById", query="from Product where id = :id and CustomerOrder_id = NULL"),
    @NamedQuery(name="Product.updatePrice", query="from Product where id = :id")
})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CustomerOrder customerOrder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
	
	public String toStringForCustomerOrders()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("     Product Name: ");
		stringBuilder.append(this.getName());
		stringBuilder.append(" : ");
		stringBuilder.append(this.getPrice());
		stringBuilder.append("\n");
		
		return stringBuilder.toString();
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Product Name: ");
		stringBuilder.append(this.getName());
		stringBuilder.append(" : $");
		stringBuilder.append(this.getPrice());
		stringBuilder.append(" : ");
		stringBuilder.append("Product ID: ");
		stringBuilder.append(this.id);
		
		
		return stringBuilder.toString();
	}
	
}
