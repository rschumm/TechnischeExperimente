package ch.schumm.fakeservice.model;

import java.io.Serializable;

public class Adresse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String strasse; 
	String ort;
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	} 
	
	@Override
	public String toString() {
		return "an " + strasse + " in " + ort;
	}
	
	

}
