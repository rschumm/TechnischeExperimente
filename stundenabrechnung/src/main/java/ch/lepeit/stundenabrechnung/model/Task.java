package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * JPA Model Task
 * 
 * @author Generated
 * @author Sven Tschui C910511
 * 
 */
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to Buchart
    @ManyToOne
    @JoinColumn(name = "BUCHART")
    private Buchart buchart;

    // bi-directional many-to-one association to Journal
    @OneToMany(mappedBy = "task")
    private List<Journal> journals;

    @Id
    private String name;

    private String plantaname;

    private boolean verbuchbar;

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        Task t = (Task) o;

        return this.name.equals(t.getName());
    }

    public Buchart getBuchart() {
        return this.buchart;
    }

    public List<Journal> getJournals() {
        return this.journals;
    }

    public String getName() {
        return this.name;
    }

    public String getPlantaname() {
        return this.plantaname;
    }

    public boolean getVerbuchbar() {
        return this.verbuchbar;
    }

    public void setBuchart(Buchart buchart) {
        this.buchart = buchart;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlantaname(String plantaname) {
        this.plantaname = plantaname;
    }

    public void setVerbuchbar(boolean verbuchbar) {
        this.verbuchbar = verbuchbar;
    }

}