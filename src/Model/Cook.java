package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;

import Utils.Expertise;
import Utils.Gender;

public class Cook extends Person implements Serializable{
	/**
	 * 
	 */
	public static Restaurant res = Restaurant.getInstance();
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Expertise expert;
	private boolean isChef;
	
	public Cook(String firstName, String lastName, LocalDate birthDay, Gender gender, Expertise expert,
			boolean isChef) {
		super(getIdCounter(), firstName, lastName, birthDay, gender);
		this.expert = expert;
		this.isChef = isChef;
	}
	
	public Cook(int id) {
		super(id);
	}

	public Expertise getExpert() {
		return expert;
	}

	public void setExpert(Expertise expert) {
		this.expert = expert;
	}

	public boolean isChef() {
		return isChef;
	}

	public void setChef(boolean isChef) {
		this.isChef = isChef;
	}
	

	public static int getIdCounter() {
		int max = 0;
		if (res.getCooks().size() == 0) {
			idCounter = 1;
		} else {
			Iterator<Integer> iterator = res.getCooks().keySet().iterator();
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
		Cook.idCounter = idCounter;
	}

	@Override
	public String toString() {
		return super.toString()+" Cook [expert=" + expert + "]";
	}
}
