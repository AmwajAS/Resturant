package Model;

import java.io.Serializable;
import java.util.Comparator;

public class DeliveriesByPersonComp implements Comparator<Delivery> ,Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Delivery o1, Delivery o2) {
		if(o1.getDeliveredDate().getDayOfMonth() > o2.getDeliveredDate().getDayOfMonth())
			return 1;
		if(o1.getDeliveredDate().getDayOfMonth() < o2.getDeliveredDate().getDayOfMonth())
			return -1;
		return o1.getId()>o2.getId() ? 1 :-1;
	}



}
