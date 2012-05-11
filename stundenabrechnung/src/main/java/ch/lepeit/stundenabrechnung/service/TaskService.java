package ch.lepeit.stundenabrechnung.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.lepeit.stundenabrechnung.model.Task;

@Stateless
public class TaskService {

	@PersistenceContext
	private EntityManager em;
	
	public Task getTask(String name) {
		return em.find(Task.class, name);
	}
	
	public List<Task> getTasks() {
		return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
	}
}
