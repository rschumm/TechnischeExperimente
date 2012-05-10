package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;

@Named
@SessionScoped
public class JournalController implements Serializable {
	private static final long serialVersionUID = 20120510L;

	private Date woche = new Date();
	
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

	public Date getWoche() {
		return woche;
	}

	public void setWoche(Date woche) {
		this.woche = woche;
	}
	
}
