package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the TASK database table.
 * 
 */
@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String name;

	private String plantaname;

	private boolean verbuchbar;

	//bi-directional many-to-one association to Journal
	@OneToMany(mappedBy="task")
	private Set<Journal> journals;

	//bi-directional many-to-one association to Buchart
    @ManyToOne
	@JoinColumn(name="BUCHART")
	private Buchart buchart;

    public Task() {
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlantaname() {
		return this.plantaname;
	}

	public void setPlantaname(String plantaname) {
		this.plantaname = plantaname;
	}

	public boolean getVerbuchbar() {
		return this.verbuchbar;
	}

	public void setVerbuchbar(boolean verbuchbar) {
		this.verbuchbar = verbuchbar;
	}

	public Set<Journal> getJournals() {
		return this.journals;
	}

	public void setJournals(Set<Journal> journals) {
		this.journals = journals;
	}
	
	public Buchart getBuchart() {
		return this.buchart;
	}

	public void setBuchart(Buchart buchart) {
		this.buchart = buchart;
	}
	
}