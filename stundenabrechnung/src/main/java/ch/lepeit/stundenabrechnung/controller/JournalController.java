package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;

@Named
@SessionScoped
public class JournalController implements Serializable {
	private static final long serialVersionUID = 20120510L;

	private Date woche = new Date();
	
	private Journal journal = new Journal();
	
	private String task;
	
	@Inject
	private EntityManager em;
	
	public List<Date> getWochentage() {
		List<Date> wochentage = new Vector<Date>();
		
		Calendar c = new GregorianCalendar();
		
		c.setTime(woche);
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		for(int i = 0; i < 5; i++) {
			wochentage.add(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return wochentage;
	}
	
	public List<Journal> getBuchungen(Date tag) {
		
		TypedQuery<Journal> buchungen = em.createQuery("SELECT j FROM Journal j WHERE j.datum = :tag", Journal.class);
		buchungen.setParameter("tag", tag);
		
		return buchungen.getResultList();
	}
	
	public Double getTagestotal(Date tag) {
		// TODO: Bessere Lösung
		
		double total = 0;
		
		for(Journal b : this.getBuchungen(tag)) {
			total += b.getStunden();
		}
		
		return total;
	}
	
	public List<Task> getTasks() {
		
		List<Task> tasks = new Vector<Task>();	
		
		Task t1 = new Task();
		Task t2 = new Task();
		Task t3 = new Task();

		t1.setName("T #1");
		t2.setName("T #2");
		t3.setName("T #3");

		tasks.add(t1);
		tasks.add(t2);
		tasks.add(t3);
		
		return tasks;
	}
	
	public String save() {
		try {
			System.out.println("Save Journal:");
			System.out.println(journal.getDatum());
			System.out.println(journal.getStunden());
			System.out.println(journal.getTask());
			System.out.println(journal.getBemerkung());
		} catch (Exception e) {
			// nothing
		}
		
		return null;
	}
	
	public String letzteWoche() {
		Calendar c = new GregorianCalendar();
		
		c.setTime(this.woche);
		
		c.add(Calendar.WEEK_OF_MONTH, -1);
		
		this.woche = c.getTime();
		
		return null;
	}
	
	public String naechsteWoche() {		
		Calendar c = new GregorianCalendar();
		
		c.setTime(this.woche);
		
		c.add(Calendar.WEEK_OF_MONTH, 1);
		
		this.woche = c.getTime();
		
		return null;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public Date getWoche() {
		return woche;
	}

	public void setWoche(Date woche) {
		this.woche = woche;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
}
