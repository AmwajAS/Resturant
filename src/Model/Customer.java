package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person implements Serializable {
	
	/**
	 * 
	 */
	public static Restaurant res = Restaurant.getInstance();
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private String userName;
	private String userPassword;

	
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String userName, String userPassword) {
		super(getIdCounter(), firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
		this.userName =userName;
		this.userPassword = userPassword;

	}
	
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood, boolean isSensitiveToLactose, boolean isSensitiveToGluten) {
		super(idCounter++, firstName, lastName, birthDay, gender);
		this.neighberhood = neighberhood;
		this.isSensitiveToLactose = isSensitiveToLactose;
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Customer(int id) {
		super(id);
	}

	public static int getIdCounter() {
		int max = 0;
		if (res.getCustomers().size() == 0) {
			idCounter = 1;
		} else {
			Iterator<Integer> iterator = res.getCustomers().keySet().iterator();
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
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean isSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean isSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	@Override
	public String toString() {
		return super.toString()+" Customer [isSensitiveToLactose=" + isSensitiveToLactose + ", isSensitiveToGluten=" + isSensitiveToGluten
				+ "]";
	}

	
	
}
