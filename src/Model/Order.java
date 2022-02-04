package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Utils.MyFileLogWriter;

public class Order implements Comparable<Order>, Serializable{
	/**
	 * 
	 */
	public static Restaurant res=Restaurant.getInstance();
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Integer id;
	private Customer customer;
	private ArrayList<Dish> dishes;
	private Delivery delivery;

	// constructors 

	public Order(Customer customer, ArrayList<Dish> dishes, Delivery delivery) {
		super();
		this.id = getIdCounter();
		this.customer = customer;
		this.dishes = dishes;
		this.delivery = delivery;
	}

	public Order(int id) {
		this.id = id;
	}
	// getters setters

	public static int getIdCounter() {
		int max = 0;
		if (res.getOrders().size() == 0) {
			idCounter = 1;
		} else {
			Iterator<Integer> iterator = res.getOrders().keySet().iterator();
			while (iterator.hasNext()) {
				int i = iterator.next();
				if (i > max) {
					max = i;
				}
				idCounter = max + 1;
			}

		}
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		Order.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Dish> getDishes() {
		return Collections.unmodifiableList(dishes);
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + "]";
	}

	// methods

	public double calcOrderRevenue() {
		double revenue = 0.0;
		for(Dish d : getDishes()) {
			double price = d.calcDishPrice();
			double cost = 0.0;
			for(Component c : d.getComponenets()) {
				cost += c.getPrice();
			}
			revenue += (price - cost);
		}
		MyFileLogWriter.println("Order Revenue = "+revenue);
		return revenue;
	}

	public int orderWaitingTime(DeliveryArea da) {
		int time = 0;
		time += da.getDeliverTime();
		for(Dish d : getDishes()) {
			time += d.getTimeToMake();
		}
		MyFileLogWriter.println("Time for order: "+this+" is "+time+" minutes");
		return time;
	}


	public boolean addDish(Dish d) {
		return dishes.add(d);
	}

	public boolean removeDish(Dish d) {
		return dishes.remove(d);
	}

	@Override
	public int compareTo(Order o) {
		return this.id.compareTo(o.getId());
	}

}
