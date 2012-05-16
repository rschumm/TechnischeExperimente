package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.Wochentag;
import ch.lepeit.stundenabrechnung.service.JournalService;

@Named
@SessionScoped
public class JournalController implements Serializable {
	private static final long serialVersionUID = 20120516L;

	private Date woche;
	
	private List<Wochentag> wochentage;
	
	@EJB
	private JournalService journalService;
	
	@PostConstruct
	public void init() {
		// Startdatum auf Tagesdatum setzen, Wochentage berechnen und Buchungen laden
		this.loadWoche(new Date());
	}
	
	public String letzteWoche() {
		Calendar c = new GregorianCalendar();
		
		c.setTime(this.getWoche());
		
		c.add(Calendar.WEEK_OF_MONTH, -1);
		
		this.loadWoche(c.getTime());
		
		return null;
	}
	
	public String naechsteWoche() {		
		Calendar c = new GregorianCalendar();
		
		c.setTime(this.getWoche());
		
		c.add(Calendar.WEEK_OF_MONTH, 1);
		
		this.loadWoche(c.getTime());
		
		return null;
	}

	public Date getWoche() {
		return woche;
	}

	private void loadWoche(Date woche) {
		this.woche = woche;
		
		this.wochentage = new Vector<Wochentag>();
		
		Calendar c = new GregorianCalendar();
		
		c.setTime(woche);
		
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		for(int i = 0; i < 5; i++) {
			this.wochentage.add(new Wochentag(c.getTime(), journalService.getGroupedJournals(c.getTime())));
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	public void reload() {
		this.loadWoche(this.getWoche());
	}
	
	public List<Wochentag> getWochentage() {
		return wochentage;
	}
	
}
