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

/**
 * JPA Model GroupedJournal
 * 
 * Journaleinträge gruppiert nach Tag und Task
 * 
 * @author Generated
 * @author Sven Tschui C910511
 * 
 */
@Entity
@Table(name = "GROUPED_JOURNAL")
public class GroupedJournal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private Date datum = new Date();

    private Double stunden;

    // bi-directional many-to-one association to Task
    @Id
    @ManyToOne
    @JoinColumn(name = "TASK")
    private Task task;

    public Date getDatum() {
        return datum;
    }

    public Double getStunden() {
        return stunden;
    }

    public Task getTask() {
        return task;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setStunden(Double stunden) {
        this.stunden = stunden;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}