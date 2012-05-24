package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * JPA Model Buchart
 * 
 * @author Generated
 * @author Sven Tschui C910511
 * 
 */
@Entity
public class Buchart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String art;

    // bi-directional many-to-one association to Task
    @OneToMany(mappedBy = "buchart")
    private List<Task> tasks;

    public Buchart() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        Buchart b = (Buchart) o;

        return this.art.equals(b.getArt());
    }

    public String getArt() {
        return this.art;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}