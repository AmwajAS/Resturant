package Model;

import java.io.Serializable;
import java.util.Comparator;

public class PopularComponentComp  implements Comparator<Component> , Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Component o1, Component o2) {
		int result = -1 * Restaurant.getInstance().getComponentsandAmount().get(o1).compareTo(Restaurant.getInstance().getComponentsandAmount().get(o2));
		if(result !=0)
			return result;
		if(o1.getId() > o2.getId())
			return -1;
		return 1;
	}

}
