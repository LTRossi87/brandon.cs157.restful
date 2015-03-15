package brandon.cs157.restful.orderApplication;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name="Customer.retrieveAllCustomers", query="from Customer")
})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	private CustomerOrder order;
	
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
	public CustomerOrder getOrder() {
		return order;
	}
	public void setOrder(CustomerOrder customerOrder) {
		this.order = customerOrder;
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Customer Name:");
		stringBuilder.append(this.name);
		stringBuilder.append("\n");
		stringBuilder.append("  Customer ID: ");
		stringBuilder.append(this.id);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
	
}
