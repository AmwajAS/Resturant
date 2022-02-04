package Model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Utils.Neighberhood;

public class DeliveryArea implements Serializable{
	/**
	 * 
	 */
;
	/**
	 * 
	 */
public static Restaurant res = Restaurant.getInstance();
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private int id;
	private String areaName;
	private HashSet<DeliveryPerson> delPersons;
	private HashSet<Delivery> delivers;
	private HashSet<Neighberhood> neighberhoods;
	private final int deliverTime;
	
	public DeliveryArea(String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
		super();
		this.id = getIdCounter();
		this.areaName = areaName;
		this.neighberhoods = neighberhoods;
		this.deliverTime = deliverTime;
		delPersons = new HashSet<>();
		delivers = new HashSet<>();
	}
	
	public DeliveryArea(int id) {
		this.id = id;
		this.deliverTime = 0;
	}

	public static int getIdCounter() {
		int max = 0;
		if (res.getAreas().size() == 0) {
			idCounter = 1;
		} else {
			Iterator<Integer> iterator = res.getAreas().keySet().iterator();
			while (iterator.hasNext()) {
				int i= iterator.next();
				if (i > max) {
					max = i;
				}
				idCounter = max + 1;
			}

		}
		return idCounter;
	}
	public static void setIdCounter(int idCounter) {
		DeliveryArea.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Set<DeliveryPerson> getDelPersons() {
		return Collections.unmodifiableSet(delPersons);
	}

	public Set<Delivery> getDelivers() {
		return Collections.unmodifiableSet(delivers);
	}

	public Set<Neighberhood> getNeighberhoods() {
		return Collections.unmodifiableSet(neighberhoods);
	}

	public int getDeliverTime() {
		return deliverTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		DeliveryArea other = (DeliveryArea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeliveryArea [id=" + id + ", areaName=" + areaName + ", neighberhoods=" + neighberhoods
				+ ", deliverTime=" + deliverTime + "]";
	}
	
	//methods
	public boolean addDelPerson(DeliveryPerson dp) {
		return delPersons.add(dp);
	}
	
	public boolean removeDelPerson(DeliveryPerson dp) {
		return delPersons.remove(dp);
	}
	
	public boolean addDelivery(Delivery d) {
		return delivers.add(d);
	}
	
	public boolean removeDelivery(Delivery d) {
		return delivers.remove(d);
	}
	
	public boolean addNeighberhood(Neighberhood neighberhood) {
		return neighberhoods.add(neighberhood);
	}
	
	public boolean removeNeighberhood(Neighberhood neighberhood) {
		return neighberhoods.remove(neighberhood);
	}
}
