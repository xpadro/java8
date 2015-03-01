package xpadro.java.streams.model;

import java.io.Serializable;

public class Client implements Serializable {
	private static final long serialVersionUID = -6358742378177948329L;

	private String name;
	private double purchases;
	
	public Client() {}
	
	public Client(String name, double purchases) {
		this.name = name;
		this.purchases = purchases;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPurchases() {
		return purchases;
	}

	public void setPurchases(double purchases) {
		this.purchases = purchases;
	}
}
