package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the BUCHART database table.
 * 
 */
@Entity
public class Buchart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String art;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="buchart")
	private Set<Task> tasks;

    public Buchart() {
    }

	public String getArt() {
		return this.art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
}