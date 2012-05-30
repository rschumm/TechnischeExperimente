package ch.lepeit.stundenabrechnung.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.lepeit.stundenabrechnung.model.GroupedJournal;
import ch.lepeit.stundenabrechnung.model.Journal;

/**
 * Service für den Zugriff auf (gruppierte) Journaleinträge.
 * 
 * @author Sven Tschui C910511
 * 
 */
@Stateless
public class JournalService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Löschen eines Journaleintrages
     * 
     * @param j
     */
    public void delete(Journal j) {
        this.em.remove(em.find(Journal.class, j.getNr()));
    }

    /**
     * Erstellt eine Liste von gruppierten Journaleinträgen für einen Wochentag.
     * 
     * @param tag
     * Tag, für welchen die Liste erstellt werden soll.
     * @return Liste der gruppierten Journaleinträge
     */
    public List<GroupedJournal> getGroupedJournals(Date tag) {
        TypedQuery<GroupedJournal> journals = em
                .createQuery(
                        "SELECT new ch.lepeit.stundenabrechnung.model.GroupedJournal(j.datum, SUM(j.stunden), j.task, MIN(j.plantaverbucht)) FROM Journal j WHERE j.datum = :tag GROUP BY j.datum, j.task",
                        GroupedJournal.class);

        journals.setParameter("tag", tag);

        return journals.getResultList();
    }

    /**
     * Erstellt eine Liste von Journaleinträgen für einen Wochentag.
     * 
     * @param tag
     * Tag, für welchen die Liste erstellt werden soll.
     * @return Liste der Journaleinträge
     */
    public List<Journal> getJournals(Date tag) {
        TypedQuery<Journal> journals = em.createQuery("SELECT j FROM Journal j WHERE j.datum = :tag", Journal.class);

        journals.setParameter("tag", tag);

        return journals.getResultList();
    }

    /**
     * Erstellt eine Liste von gruppierten Journaleinträgen, welche mit einem nicht verbuchbaren Task verknüpft sind,
     * für einen Wochentag.
     * 
     * @param tag
     * Tag, für welchen die Liste erstellt werden soll.
     * @return Liste der gruppierten Journaleinträge
     */
    public List<GroupedJournal> getNichtVerbuchbarGroupedJournals(Date monat) {
        TypedQuery<GroupedJournal> journals = em
                .createQuery(
                        "SELECT new ch.lepeit.stundenabrechnung.model.GroupedJournal(j.datum, SUM(j.stunden), j.task, MIN(j.plantaverbucht)) FROM Journal j WHERE YEAR(j.datum) = :jahr AND MONTH(j.datum) = :monat AND j.task.verbuchbar = 0 GROUP BY j.datum, j.task ORDER BY j.datum ASC",
                        GroupedJournal.class);

        Calendar c = new GregorianCalendar();
        c.setTime(monat);

        journals.setParameter("jahr", c.get(Calendar.YEAR));
        journals.setParameter("monat", c.get(Calendar.MONTH) + 1); // + 1 Da Calendar Monate von 0 aus zählt

        return journals.getResultList();
    }

    /**
     * Erstellt eine Liste von gruppierten Journaleinträgen welche nicht verbucht sind.
     * 
     * @return Liste der nicht verbuchten gruppierten Journaleinträge
     */
    public List<GroupedJournal> getNichtVerbuchteGroupedJournals() {
        TypedQuery<GroupedJournal> journals = em
                .createQuery(
                        "SELECT new ch.lepeit.stundenabrechnung.model.GroupedJournal(j.datum, SUM(j.stunden), j.task, MIN(j.plantaverbucht)) FROM Journal j WHERE j.plantaverbucht = false GROUP BY j.datum, j.task ORDER BY j.datum DESC",
                        GroupedJournal.class);

        return journals.getResultList();
    }

    /**
     * Speichern/erfassten eines neuen Journaleintrages
     * 
     * @param j
     * Der zu speichernde Journaleintrag
     */
    public void save(Journal j) {
        em.persist(j);
    }

    /**
     * Speichern/erfassten eines bestehenden Journaleintrages
     * 
     * @param j
     * Der zu speichernde Journaleintrag
     */
    public void update(Journal j) {
        em.merge(j);
    }

    /**
     * Ändern des verbucht Status aller Journaleinträge mit einem bestimmten Task an einem bestimmten Tag
     * 
     * @param datum
     * Tag, von welchem die Einträge auf verbucht gesetzt werden sollen
     * @param task
     * Task, von welchem die Einträge auf verbucht gesetzt werden sollen
     */
    public void verbuchen(Date datum, String task) {
        List<Journal> list = em
                .createQuery(
                        "SELECT j FROM Journal j WHERE j.plantaverbucht = false AND j.datum = :datum AND j.task.name = :task",
                        Journal.class).setParameter("datum", datum).setParameter("task", task).getResultList();

        for (Journal j : list) {
            j.setPlantaverbucht(true);
            em.persist(j);
        }

    }
}