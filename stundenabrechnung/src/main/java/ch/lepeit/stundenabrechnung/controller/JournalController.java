package ch.lepeit.stundenabrechnung.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.GroupedJournal;
import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.model.Task;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;

@Named
@SessionScoped
public class JournalController implements Serializable {
	private static final long serialVersionUID = 20120516L;

	private Date woche = new Date();
	
	private Journal journal = new Journal();
	
	private String task = null;
	
	@EJB
	private JournalService journalService;
	
	@EJB
	private TaskService taskService;
	
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
	
	public List<GroupedJournal> getBuchungen(Date tag) {
		return journalService.getGroupedJournals(tag);
	}
	
	public Double getTagestotal(Date tag) {
		return journalService.getTagestotal(tag);
	}
	
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	public String save() {
		// gewählten task in der db suchen
		Task t = taskService.getTask(this.task);
		
		if(t == null) {
			// TODO: Faces Message
			System.out.println("Task null");
			return null;
		}
		
		this.journal.setTask(t);
		
		journalService.save(this.journal);
		
		this.journal = new Journal();
		this.task = null;
		
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
