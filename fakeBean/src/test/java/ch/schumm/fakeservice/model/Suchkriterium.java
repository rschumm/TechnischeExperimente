package ch.schumm.fakeservice.model;

public class Suchkriterium {
	
	private String name; 
	private String ort;
	
	
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	@Override
	public String toString() {
		return ort + name; 
	}

}
