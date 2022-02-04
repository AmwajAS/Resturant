package Model;

import java.io.Serializable;
import java.util.Comparator;

public class createAIMacineComp  implements Comparator<Delivery> ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Delivery o1, Delivery o2) {
		// TODO Auto-generated method stub
		
		
			if(o2 instanceof RegularDelivery && o1 instanceof ExpressDelivery)
				return -1;
			if(o2 instanceof ExpressDelivery && o1 instanceof RegularDelivery)
				return 1;
			return o2.getId()>o1.getId() ? -1: 1;
		

	}

}
