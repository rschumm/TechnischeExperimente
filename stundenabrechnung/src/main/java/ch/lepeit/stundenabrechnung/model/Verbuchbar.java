package ch.lepeit.stundenabrechnung.model;

/**
 * Model Verbuchbar
 * 
 * @author Sven Tschui C910511
 * 
 */
public class Verbuchbar {
    private boolean verbuchbar;

    private double zeit;

    public Verbuchbar() {

    }

    public Verbuchbar(boolean verbuchbar, double zeit) {
        this.verbuchbar = verbuchbar;
        this.zeit = zeit;
    }

    public double getZeit() {
        return zeit;
    }

    public boolean isVerbuchbar() {
        return verbuchbar;
    }

    public void setVerbuchbar(boolean verbuchbar) {
        this.verbuchbar = verbuchbar;
    }

    public void setZeit(double zeit) {
        this.zeit = zeit;
    }
}