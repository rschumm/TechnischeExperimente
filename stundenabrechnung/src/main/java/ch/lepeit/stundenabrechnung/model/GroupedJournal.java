package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GROUPED_JOURNAL")
public class GroupedJournal implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Task
	@Id
	@ManyToOne
	@JoinColumn(name = "TASK")
	private Task task;

	@Temporal(TemporalType.DATE)
	private Date datum = new Date();

	private Double stunden;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Double getStunden() {
		return stunden;
	}

	public void setStunden(Double stunden) {
		this.stunden = stunden;
	}

}