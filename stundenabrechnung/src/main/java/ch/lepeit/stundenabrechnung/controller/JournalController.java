package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;

@Named
@SessionScoped
public class JournalController implements Serializable {
	private static final long serialVersionUID = 20120510L;

	private Date woche = new Date();
	
	private Journal journal = new Journal();
	
	//@Inject
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
		
		// TODO: Dummy Daten ersetzen
		
		//TypedQuery<Journal> buchungen = em.createQuery("SELECT j FROM Journal j WHERE j.datum = :tag", Journal.class);
		//buchungen.setParameter("tag", tag);
		
		//return buchungen.getResultList();
	
		List<Journal> dummy = new Vector<Journal>();
		
		Journal j = new Journal();

		dummy.add(j);
		
		Task t = new Task();
		t.setJournals(dummy);
		t.setName("Dummy Task");
		j.setTask(t);
		j.setStunden(8.3);
		
		dummy.add(j);
		
		return dummy;
	}
	
	public Double getTagestotal(Date tag) {
		// TODO: Dummy Daten ersetzen
		return 12.32;
	}
	
	public List<SelectItem> getTasks() {
		List<SelectItem> tasks = new Vector<SelectItem>();
		
		Task t1 = new Task();
		Task t2 = new Task();
		Task t3 = new Task();

		t1.setName("T #1");
		t2.setName("T #2");
		t3.setName("T #3");

		tasks.add(new SelectItem(t1, "T1"));
		tasks.add(new SelectItem(t2, "T2"));
		tasks.add(new SelectItem(t3, "T3"));
		
		return tasks;
	}
	
	public String save() {
		System.out.println("Save Journal:");
		System.out.println(journal.getDatum());
		System.out.println(journal.getStunden());
		System.out.println(journal.getTask().getName());
		System.out.println(journal.getBemerkung());
		
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
	
}
