package Model;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.naming.ldap.Rdn;

import Alerts.alerts;
import Exceptions.ConvertToExpressException;
import Exceptions.IllegalCustomerException;
import Exceptions.NoComponentsExceptions;
import Exceptions.SensitiveException;
import Utils.Expertise;
import Utils.MyFileLogWriter;
import Utils.Neighberhood;
import application.editCompCont;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class Restaurant  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Restaurant restaurant = null;

	private HashMap<Integer, Cook> cooks;
	private HashMap<Integer, DeliveryPerson> deliveryPersons;
	private HashMap<Integer, Customer> customers;
	private HashMap<Integer, Dish> dishes;
	private HashMap<Integer, Component> componenets;
	private HashMap<Integer, Order> orders;
	private HashMap<Integer, Delivery> deliveries;
	private HashMap<Integer, DeliveryArea> areas;

	/*NEW*/
	private HashMap<Customer, TreeSet<Order>> orderByCustomer;
	private HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea;
	private HashSet<Customer> blackList;

	public static Restaurant getInstance()
	{
		if(restaurant == null) {
			alerts.showAlert(AlertType.INFORMATION, "Data", "Data Was Loaded successfully.", ButtonType.CLOSE);
			restaurant = loadData();
			if(restaurant == null)
				restaurant = new Restaurant();
		}
		return restaurant;
	}

	public static void setInstance(Restaurant res) {
		restaurant = res;
	}

	private Restaurant() {
		cooks = new HashMap<>();
		deliveryPersons = new HashMap<>();
		customers = new HashMap<>();
		dishes = new HashMap<>();
		componenets = new HashMap<>();
		orders = new HashMap<>();
		deliveries = new HashMap<>();
		areas = new HashMap<>();
		orderByCustomer = new HashMap<>();
		orderByDeliveryArea = new HashMap<>();
		blackList = new HashSet<>();
	}



	public static Restaurant loadData() {
		String filename = "Rest.ser";
		Restaurant cont = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			if(fis == null) {
				File f = new File("Rest.ser");
				f.createNewFile();
			}
			in = new ObjectInputStream(fis);
			cont = (Restaurant) in.readObject();
			in.close();
			fis.close();

		}catch (FileNotFoundException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Didn't Found any Data", "Missing Data", ButtonType.CLOSE);

		}catch (EOFException e) {
			// TODO: handle exception
		}catch (IOException i) {
			// TODO: handle exception
			i.printStackTrace();
			alerts.showAlert(AlertType.ERROR, "Error Occurres", "Reading Date Failed", ButtonType.CLOSE);

		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cont;

	}



	public static void savaData() {
		String filename = "Rest.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(restaurant);
			alerts.showAlert(AlertType.INFORMATION, "Data Saved Successfully.", "Saved Data", ButtonType.CLOSE);
			oos.close();
			fos.close();
		}catch (IOException e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Error Occured", "Failed Saving Data", ButtonType.CLOSE);
		}
		catch (Exception e) {
			// TODO: handle exception
			alerts.showAlert(AlertType.ERROR, "Error Occured", "Failed Saving Data", ButtonType.CLOSE);


		}
	}


	public HashMap<Integer, Cook> getCooks() {
		return cooks;
	}

	public void setCooks(HashMap<Integer, Cook> cooks) {
		this.cooks = cooks;
	}

	public HashMap<Integer, DeliveryPerson> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(HashMap<Integer, DeliveryPerson> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	public HashMap<Integer, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<Integer, Customer> customers) {
		this.customers = customers;
	}

	public HashMap<Integer, Dish> getDishes() {
		return dishes;
	}

	public void setDishes(HashMap<Integer, Dish> dishes) {
		this.dishes = dishes;
	}

	public HashMap<Integer, Component> getComponenets() {
		return componenets;
	}

	public void setComponenets(HashMap<Integer, Component> componenets) {
		this.componenets = componenets;
	}

	public HashMap<Integer, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}

	public HashMap<Integer, Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(HashMap<Integer, Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public HashMap<Integer, DeliveryArea> getAreas() {
		return areas;
	}

	public void setAreas(HashMap<Integer, DeliveryArea> areas) {
		this.areas = areas;
	}

	public HashMap<Customer, TreeSet<Order>> getOrderByCustomer() {
		return orderByCustomer;
	}

	public void setOrderByCustomer(HashMap<Customer, TreeSet<Order>> orderByCustomer) {
		this.orderByCustomer = orderByCustomer;
	}

	public HashMap<DeliveryArea, HashSet<Order>> getOrderByDeliveryArea() {
		return orderByDeliveryArea;
	}

	public void setOrderByDeliveryArea(HashMap<DeliveryArea, HashSet<Order>> orderByDeliveryArea) {
		this.orderByDeliveryArea = orderByDeliveryArea;
	}

	public HashSet<Customer> getBlackList() {
		return blackList;
	}

	public void setBlackList(HashSet<Customer> blackList) {
		this.blackList = blackList;
	}

	public boolean addCook(Cook cook) {
		if(cook == null || getCooks().containsKey(cook.getId()))
			return false;

		return getCooks().put(cook.getId(), cook) == null;
	}

	public boolean addDeliveryPerson(DeliveryPerson dp, DeliveryArea da) {
		if(dp == null || getDeliveryPersons().containsKey(dp.getId()) || !getAreas().containsKey(da.getId()))
			return false;

		return deliveryPersons.put(dp.getId() , dp) ==null && da.addDelPerson(dp);
	}

	public boolean addCustomer(Customer cust) {
		if(cust == null || getCustomers().containsKey(cust.getId()))
			return false;

		return getCustomers().put(cust.getId(), cust) ==null;
	}

	public boolean addDish(Dish dish) {
		if(dish == null || getDishes().containsKey(dish.getId()))
			return false;
		for(Component c : dish.getComponenets()) {
			if(!getComponenets().containsKey(c.getId()))
				return false;
		}

		return getDishes().put(dish.getId(), dish) ==null;
	}

	public boolean addComponent(Component comp) {
		if(comp == null || getComponenets().containsKey(comp.getId()))
			return false;

		return getComponenets().put(comp.getId(), comp) ==null;
	}

	public boolean addOrder(Order order) {
		try {
			if(order == null || getOrders().containsKey(order.getId()))
				return false;
			if(order.getCustomer() == null || !getCustomers().containsKey(order.getCustomer().getId()))
				return false;
			if(getBlackList().contains(order.getCustomer())) {
				throw new IllegalCustomerException();
			}
			for(Dish d : order.getDishes()){
				if(!getDishes().containsKey(d.getId()))
					return false;
				for(Component c: d.getComponenets()) {
					if(order.getCustomer().isSensitiveToGluten() && c.isHasGluten()) {
						throw new SensitiveException(order.getCustomer().getFirstName() + " " +order.getCustomer().getLastName(), d.getDishName());
					}
					else if(order.getCustomer().isSensitiveToLactose() && c.isHasLactose()) {
						throw new SensitiveException(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(), d.getDishName());
					}
				}
			}
			return getOrders().put(order.getId(), order) == null;
		}catch(SensitiveException e) {
			//			Utils.MyFileLogWriter.println(e.getMessage());
			return false;
		}catch(IllegalCustomerException e) {
			//			Utils.MyFileLogWriter.println(e.getMessage());
			return false;
		}
	}

	public boolean addDelivery(Delivery delivery) {
		try {
			if(delivery == null || getDeliveries().containsKey(delivery.getId()) || !getDeliveryPersons().containsKey(delivery.getDeliveryPerson().getId()))
			{
				return false;
			}
			if(delivery instanceof RegularDelivery) {
				RegularDelivery rd = (RegularDelivery)delivery;
				for(Order o : rd.getOrders()){
					if(!getOrders().containsKey(o.getId()))
						return false;
					o.setDelivery(delivery);
				}
				if(rd.getOrders().size() ==1) {
					throw new ConvertToExpressException();
				}
				if(rd.getOrders().isEmpty())
					return false;
			}
			else {
				ExpressDelivery ed = (ExpressDelivery)delivery;
				if(!getOrders().containsKey(ed.getOrder().getId()))
					return false;
				ed.getOrder().setDelivery(delivery);
			}
		}catch(ConvertToExpressException e) {
			//			Utils.MyFileLogWriter.println(e.getMessage());
			RegularDelivery rd = (RegularDelivery)delivery;
			delivery = new ExpressDelivery(rd.getDeliveryPerson(), rd.getArea(),rd.isDelivered(), rd.getOrders().first() ,rd.getDeliveredDate());
		}finally {
			delivery.getArea().addDelivery(delivery);
			if(delivery instanceof RegularDelivery) {
				RegularDelivery rg = (RegularDelivery)delivery;
				for(Order o: rg.getOrders()) {
					TreeSet<Order> orders = orderByCustomer.get(o);
					if(orders == null)
						orders = new TreeSet<>(new DeliveryComp()); 

					orders.add(o);
					orderByCustomer.put(o.getCustomer(), orders);
				}
			}
			else {
				ExpressDelivery ex = (ExpressDelivery)delivery;
				TreeSet<Order> orders = orderByCustomer.get(ex.getOrder());
				if(orders == null)
					orders = new TreeSet<>(new DeliveryComp());

				orders.add(ex.getOrder());
				orderByCustomer.put(ex.getOrder().getCustomer(), orders);
			}
		}
		return getDeliveries().put(delivery.getId(),delivery) ==null;
	}

	public boolean addDeliveryArea(DeliveryArea da) {
		if(da == null || getAreas().containsKey(da.getId()))
			return false;
		return getAreas().put(da.getId(), da) ==null;
	}

	public boolean addCustomerToBlackList(Customer c) {
		if(c == null)
			return false;
		return getBlackList().add(c);
	}

	public boolean removeCook(Cook cook) {
		if(cook == null || !getCooks().containsKey(cook.getId()))
			return false;
		return getCooks().remove(cook.getId()) !=null;
	}

	public boolean removeDeliveryPerson(DeliveryPerson dp) {
		if(dp == null || !getDeliveryPersons().containsKey(dp.getId()))
			return false;
		for(Delivery d : getDeliveries().values()) {
			if(d.getDeliveryPerson().equals(dp)) {
				d.setDeliveryPerson(null);
			}
		}
		return getDeliveryPersons().remove(dp.getId())!= null && dp.getArea().removeDelPerson(dp);
	}

	public boolean removeCustomer(Customer cust) {
		if(cust == null || !getCustomers().containsKey(cust.getId()))
			return false;
		return getCustomers().remove(cust.getId())!=null;
	}

	public boolean removeDish(Dish dish) {
		if(dish == null || !getDishes().containsKey(dish.getId()))
			return false;
		for(Delivery d : deliveries.values()) {
			if(!d.isDelivered()) {
				if(d instanceof RegularDelivery) {
					RegularDelivery rd = (RegularDelivery)d;
					for(Order o : rd.getOrders()) {
						o.removeDish(dish);
					}
				}
				else {
					ExpressDelivery ed = (ExpressDelivery)d;
					ed.getOrder().removeDish(dish);
				}
			}
		}
		return getDishes().remove(dish.getId())!=null;
	}

	public boolean removeComponent(Component comp) {
		Dish removeDish = null;
		try {
			if(comp == null || !getComponenets().containsKey(comp.getId()))
				return false;
			for(Dish d : getDishes().values()) {
				d.removeComponent(comp);
				if(d.getComponenets().isEmpty()) {
					removeDish = d;
					throw new NoComponentsExceptions(d);
				}
			}
		}catch(NoComponentsExceptions e) {
			//			Utils.MyFileLogWriter.println(e.getMessage());
			removeDish(removeDish);
		}
		return getComponenets().remove(comp.getId())!=null;
	}

	public boolean removeOrder(Order order) {
		if(order == null || !getOrders().containsKey(order.getId()))
			return false;
		if(getOrders().remove(order.getId())!=null) {
			try {


				if(order.getDelivery() instanceof RegularDelivery) {
					RegularDelivery rd = (RegularDelivery) order.getDelivery();
					return rd.removeOrder(order);
				}
				else {
					ExpressDelivery ed = (ExpressDelivery) order.getDelivery();

					ed.setOrder(null);
					return true;
				}	
			} catch (NullPointerException e) {
				// TODO: handle exception
				getOrders().remove(order.getId());
				return true;
			}
		}
		return false;
	}

	public boolean removeDelivery(Delivery delivery) {
		if(delivery == null || !getDeliveries().containsKey(delivery.getId()))
			return false;
		if(delivery instanceof RegularDelivery) {
			RegularDelivery rd = (RegularDelivery)delivery;
			for(Order o : rd.getOrders()) {
				o.setDelivery(null);
			}
		}
		else {
			ExpressDelivery ed = (ExpressDelivery) delivery;
			ed.getOrder().setDelivery(null);
		}
		return getDeliveries().remove(delivery.getId()) != null && delivery.getArea().removeDelivery(delivery);
	}

	public boolean removeDeliveryArea(DeliveryArea oldArea, DeliveryArea newArea) {
		if(oldArea == null || newArea == null || !getAreas().containsKey(oldArea.getId()) || !getAreas().containsKey(newArea.getId()))
			return false;
		for(Neighberhood n : oldArea.getNeighberhoods()) {
			newArea.addNeighberhood(n);
		}
		for(Delivery d : oldArea.getDelivers()) {
			newArea.addDelivery(d);
		}
		for(DeliveryPerson dp : oldArea.getDelPersons()) {
			newArea.addDelPerson(dp);
		}
		for(DeliveryPerson dp : oldArea.getDelPersons()) {
			dp.setArea(newArea);
		}
		for(Delivery d : oldArea.getDelivers()) {
			d.setArea(newArea);
		}
		return getAreas().remove(oldArea.getId()) != null;
	}

	public Cook getRealCook(int id) {
		return getCooks().get(id);
	}

	public DeliveryPerson getRealDeliveryPerson(int id) {
		return getDeliveryPersons().get(id);
	}

	public Customer getRealCustomer(int id) {
		return getCustomers().get(id);
	}

	public Dish getRealDish(int id) {
		return getDishes().get(id);
	}

	public Component getRealComponent(int id) {
		return getComponenets().get(id);
	}

	public Order getRealOrder(int id) {
		return getOrders().get(id);
	}

	public Delivery getRealDelivery(int id) {
		return getDeliveries().get(id);
	}

	public DeliveryArea getRealDeliveryArea(int id) {
		return getAreas().get(id);
	}


	/*QUEREIES*/
	public Collection<Dish> getReleventDishList(Customer c){
		ArrayList<Dish> dishList = new ArrayList<>();
		if(!c.isSensitiveToGluten() && !c.isSensitiveToLactose())
			return getDishes().values();
		for(Dish d : getDishes().values()) {
			boolean isValid = true;
			for(Component comp : d.getComponenets()) {
				if(c.isSensitiveToGluten() && c.isSensitiveToLactose()) {
					if(comp.isHasGluten() || comp.isHasLactose())
						isValid = false;
				}
				else if(c.isSensitiveToGluten() && comp.isHasGluten()) {
					isValid = false;
				}
				else if(c.isSensitiveToLactose() && comp.isHasLactose()) {
					isValid = false;
				}
			}
			if(isValid)
				dishList.add(d);
		}
		return dishList;
	}

	public void deliver(Delivery d) {
		d.setDelivered(true);
		if(d instanceof RegularDelivery) {
			RegularDelivery rd = (RegularDelivery)d;
			for(Order o : rd.getOrders()) {
				MyFileLogWriter.println(o+" had reached to Customer "+o.getCustomer());
			}
		}
		else {
			ExpressDelivery ed = (ExpressDelivery)d;
			MyFileLogWriter.println(ed.getOrder()+" had reached to Customer "+ed.getOrder().getCustomer());
		}

	}

	public ArrayList<Cook> GetCooksByExpertise(Expertise e){
		ArrayList<Cook> cooks = new ArrayList<>();
		for(Cook c : getCooks().values()) {
			if(c.getExpert().equals(e))
				cooks.add(c);
		}
		return cooks;
	}

	/*NEW QUERIES*/
	public TreeSet<Delivery> getDeliveriesByPerson(DeliveryPerson dp , int month){
		TreeSet<Delivery> delivered = new TreeSet<>(new DeliveriesByPersonComp());

		for(Delivery d: getDeliveries().values()) {
			if(d.getDeliveryPerson().equals(dp) && d.getDeliveredDate().getMonthValue() == month)
				delivered.add(d);
		}
		return delivered;
	}

	public HashMap<String,Integer> getNumberOfDeliveriesPerType(){
		HashMap<String, Integer> deliveriesPerType = new HashMap<>();
		deliveriesPerType.put("Regular delivery", 0);
		deliveriesPerType.put("Express delivery", 0);
		int amount;
		for(Delivery d: getDeliveries().values()) {
			LocalDate today = LocalDate.now();
			if(d instanceof RegularDelivery && d.getDeliveredDate().getYear() == today.getYear()) {
				amount = deliveriesPerType.get("Regular delivery");
				deliveriesPerType.put("Regular delivery",amount+1);
			}
			else {
				if(d.getDeliveredDate().getYear() == today.getYear()) {
					amount = deliveriesPerType.get("Express delivery");
					deliveriesPerType.put("Express delivery",amount+1);
				}
			}
		}
		return deliveriesPerType;
	}

	public double revenueFromExpressDeliveries() {
		HashSet<Customer> customers = new HashSet<>();
		double amountOfPostages = 0;
		for(Delivery d: getDeliveries().values()) {
			if(d instanceof ExpressDelivery) {
				ExpressDelivery ed = (ExpressDelivery)d;
				amountOfPostages+=ed.getPostage();
				customers.add(ed.getOrder().getCustomer());
			}
		}
		amountOfPostages+=(30*customers.size());
		return amountOfPostages;
	}
	private HashMap<Component, Integer> componentsandAmount = new HashMap<>();

	public HashMap<Component, Integer> getComponentsandAmount() {
		return componentsandAmount;
	}

	public void setComponentsandAmount(HashMap<Component, Integer> componentsandAmount) {
		this.componentsandAmount = componentsandAmount;
	}

	public LinkedList<Component> getPopularComponents(){

		for(Dish d: getDishes().values()) {
			for(Component c: d.getComponenets()) {
				Integer numOfComponent = componentsandAmount.get(c);
				if(numOfComponent == null)
					numOfComponent = 0;
				componentsandAmount.put(c, numOfComponent+1);
			}
		}
		LinkedList<Component> popularComponents = new LinkedList<>();
		for(Component c: componentsandAmount.keySet()) {
			popularComponents.add(c);
		}
		popularComponents.sort(new PopularComponentComp()); 

		return popularComponents;
	}

	public TreeSet<Dish> getProfitRelation(){
		TreeSet<Dish> profit = new TreeSet<Dish>(new profitRelationComp());
		for(Dish d: getDishes().values()) {
			profit.add(d);
		}
		return profit;
	}

	public TreeSet<Delivery> createAIMacine(DeliveryPerson dp, DeliveryArea da, TreeSet<Order> orders){
		TreeSet<Delivery> AIDecision = new TreeSet<>(new createAIMacineComp()); 

		TreeSet<Order> toRegularDelivery = new TreeSet<>();
		if(orders.size()<=2) {
			for(Order o: orders) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, o,LocalDate.of(2021,1,1));
				AIDecision.add(ed);
			}
		}
		else {
			for(Order o: orders) {
				if(o.getCustomer().isSensitiveToGluten() || o.getCustomer().isSensitiveToLactose()) {
					ExpressDelivery ed = new ExpressDelivery(dp, da, false, o,LocalDate.of(2021,1,1));
					AIDecision.add(ed);
				}
				else
					toRegularDelivery.add(o);
			}
			if(toRegularDelivery.size()<2) {
				ExpressDelivery ed = new ExpressDelivery(dp, da, false, toRegularDelivery.first(),LocalDate.of(2021, 1, 1));
				AIDecision.add(ed);
			}
			else {
				RegularDelivery rd = new RegularDelivery(toRegularDelivery, dp, da, false, LocalDate.of(2021, 1, 1));
				AIDecision.add(rd);
			}
		}
		return AIDecision;




	}
}
