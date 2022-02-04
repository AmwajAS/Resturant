package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;

import Utils.Gender;
import Utils.Vehicle;

public class DeliveryPerson extends Person implements Serializable {
	/**
	 * 
	 */
	public static Restaurant res = Restaurant.getInstance();
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Vehicle vehicle;
	private DeliveryArea area;
	
	public DeliveryPerson(String firstName, String lastName, LocalDate birthDay, Gender gender, Vehicle vehicle,
			DeliveryArea area) {
		super(getIdCounter(), firstName, lastName, birthDay, gender);
		this.vehicle = vehicle;
		this.area = area;
	}
	
	public DeliveryPerson(int id) {
		super(id);
	}
	
	public static int getIdCounter() {
		int max = 0;
		if (res.getDeliveryPersons().size() == 0) {
			idCounter = 1;
		} else {
			Iterator<Integer> iterator = res.getDeliveryPersons().keySet().iterator();
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
		DeliveryPerson.idCounter = idCounter;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public DeliveryArea getArea() {
		return area;
	}
	public void setArea(DeliveryArea area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return super.toString()+" DeliveryPerson [vehicle=" + vehicle + "]";
	}

}
