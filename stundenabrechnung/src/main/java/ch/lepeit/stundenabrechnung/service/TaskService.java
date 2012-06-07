package ch.lepeit.stundenabrechnung.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.lepeit.stundenabrechnung.model.Task;

/**
 * Service für den zugriff auf die Task Entität
 * 
 * @author Sven Tschui C910511
 * 
 */
@Stateless
public class TaskService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Löschen eines Tasks über dessen Namen
     * 
     * @param name
     * Der Name des zu löschenden Task
     */
    public void delete(String name) {
        em.remove(em.find(Task.class, name));
    }

    /**
     * Suchen einer Task Entität mit dessen Name (Primärschlüssel)
     * 
     * @param name
     * Name des Tasks
     * @return Gefundener Task oder null
     */
    public Task getTask(String name) {
        return em.find(Task.class, name);
    }

    /**
     * Erstellt eine Liste aller Tasks
     * 
     * @return Liste aller Tasks
     */
    public List<Task> getTasks() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    /**
     * Speichern eines Tasks
     * 
     * @param task
     * Der zu speichernde Task
     */
    public void save(Task task) {
        em.persist(task);
    }
}
