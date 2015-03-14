package brandon.cs157.restful.orderApplication;

import javax.persistence.*;

@Entity
public class Customer {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
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
	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
	
}
