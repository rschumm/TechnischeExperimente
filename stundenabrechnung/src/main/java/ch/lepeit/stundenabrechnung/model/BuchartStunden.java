package ch.lepeit.stundenabrechnung.model;

public class BuchartStunden {
    private String buchart;

    private Double stunden;

    public BuchartStunden() {

    }

    public BuchartStunden(String buchart, Double stunden) {
        super();
        this.buchart = buchart;
        this.stunden = stunden;
    }

    public String getBuchart() {
        return buchart;
    }

    public Double getStunden() {
        return stunden;
    }

    public void setBuchart(String buchart) {
        this.buchart = buchart;
    }

    public void setStunden(Double stunden) {
        this.stunden = stunden;
    }

}
