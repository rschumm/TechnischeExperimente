package ch.lepeit.stundenabrechnung.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ch.lepeit.stundenabrechnung.model.Journal;
import ch.lepeit.stundenabrechnung.service.JournalService;
import ch.lepeit.stundenabrechnung.service.TaskService;
import java.io.Serializable;

@Named
@SessionScoped
public class JournalKorrekturController implements Serializable {
	private static final long serialVersionUID = 20120516L;

	@EJB
	private JournalService journalService;
	
	@EJB
	private TaskService taskService;
	
	private Journal currentJournal;
	
	private int currentJournalIndex;
	
	public List<Journal> getBuchungen() {
		return journalService.getJournals();
	}
	
	public void remove() {
	}
	
	public void save() {
	}
	
	public Journal getCurrentJournal() {
		return currentJournal;
	}
	
	public void setCurrentJournal(Journal currentJournal) {
		this.currentJournal = currentJournal;
	}
	
	public int getCurrentJournalIndex() {
		return currentJournalIndex;
	}
	
	public void setCurrentJournalIndex(int currentJournalIndex) {
		this.currentJournalIndex = currentJournalIndex;
	}
}
