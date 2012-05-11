package ch.lepeit.stundenabrechnung.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ch.lepeit.stundenabrechnung.model.Journal;

@Stateless
public class JournalService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Journal> getJournals() {
		return em.createQuery("SELECT j FROM Journal j", Journal.class).getResultList();
	}
	
	public List<Journal> getJournals(Date tag) {
		TypedQuery<Journal> journals = em.createQuery("SELECT j FROM Journal j WHERE j.datum = :tag", Journal.class);
		
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
	
	public void save(Journal j) {
		em.persist(j);
	}
}