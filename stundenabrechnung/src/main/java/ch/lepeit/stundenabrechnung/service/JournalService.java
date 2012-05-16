package ch.lepeit.stundenabrechnung.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.lepeit.stundenabrechnung.model.GroupedJournal;
import ch.lepeit.stundenabrechnung.model.Journal;

@Stateless
public class JournalService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Journal> getJournals() {
		return em.createQuery("SELECT j FROM Journal j", Journal.class).getResultList();
	}
	
	public List<Journal> getJournals(Date tag) {
		TypedQuery<Journal> journals = em.createQuery("SELECT j FROM Journal j WHERE j.datum = :tag ORDER BY j.datum DESC", Journal.class);
		
		journals.setParameter("tag", tag);
		
		return journals.getResultList();
	}
	
	public List<GroupedJournal> getGroupedJournals(Date tag) {
		TypedQuery<GroupedJournal> journals = em.createQuery("SELECT j FROM GroupedJournal j WHERE j.datum = :tag", GroupedJournal.class);
		
		journals.setParameter("tag", tag);
		
		return journals.getResultList();
	}
	
	public Double getTagestotal(Date tag) {
		// TODO: Bessere Lösung
		
		double total = 0;
		
		for(Journal b : this.getJournals(tag)) {
			total += b.getStunden();
		}
		
		return total;
	}
	
	public void delete(int nr) {
		// TODO: besser?
		
		this.em.remove(this.em.find(Journal.class, nr));
	}
	
	public void save(Journal j) {
		em.persist(j);
	}
	
	public void update(Journal j) {
		em.merge(j);
	}
}
