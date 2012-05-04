package ch.schumm.fakeservice.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Kunde implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4539708693456998133L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	String name;
	String vorname;
	Adresse adresse;
	
	@Override
	public String toString() {
		return vorname + " " + name + " wohnt " + adresse.toString(); 
	}

}
