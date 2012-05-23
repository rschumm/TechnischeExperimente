package ch.lepeit.stundenabrechnung.model;


public class Verbuchbar {
	private boolean verbuchbar;
	
	private double zeit;
	
	public Verbuchbar() {
		
	}
	
	public Verbuchbar(boolean verbuchbar, double zeit) {
		this.verbuchbar = verbuchbar;
		this.zeit = zeit;
	}

	public boolean isVerbuchbar() {
		return verbuchbar;
	}

	public void setVerbuchbar(boolean verbuchbar) {
		this.verbuchbar = verbuchbar;
	}

	public double getZeit() {
		return zeit;
	}

	public void setZeit(double zeit) {
		this.zeit = zeit;
	}
}