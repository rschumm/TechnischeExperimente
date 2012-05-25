package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    @EmbeddedId
    private GroupedJournalPK primary;

    private Double stunden;

    public Date getDatum() {
        return this.primary.getDatum();
    }

    public Double getStunden() {
        return stunden;
    }

    public Task getTask() {
        return this.primary.getTask();
    }

    public void setDatum(Date datum) {
        this.primary.setDatum(datum);
    }

    public void setStunden(Double stunden) {
        this.stunden = stunden;
    }

    public void setTask(Task task) {
        this.primary.setTask(task);
    }

}