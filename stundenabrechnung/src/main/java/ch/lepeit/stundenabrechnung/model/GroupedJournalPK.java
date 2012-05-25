package ch.lepeit.stundenabrechnung.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class GroupedJournalPK implements Serializable {
    private static final long serialVersionUID = 20120525;

    @Temporal(TemporalType.DATE)
    private Date datum;

    // bi-directional many-to-one association to Task
    @ManyToOne
    @JoinColumn(name = "TASK")
    private Task task;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.datum == null || this.task == null) {
            return false;
        }

        GroupedJournalPK primary = (GroupedJournalPK) obj;

        return (this.datum.equals(primary.getDatum()) && this.task.equals(primary.getTask()));
    }

    public Date getDatum() {
        return datum;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
