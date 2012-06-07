package ch.lepeit.stundenabrechnung.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.lepeit.stundenabrechnung.model.Buchart;

@Stateless
public class BuchartService {

    @PersistenceContext
    private EntityManager em;

    /**
     * Suchen einer Buchart Entität mit dessen Art (Primärschlüssel)
     * 
     * @param art
     * Art der Buchart
     * @return Gefundene Buchart oder null
     */
    public Buchart getBuchart(String art) {
        return em.find(Buchart.class, art);
    }

    /**
     * Erstellt eine Liste aller Bucharten
     * 
     * @return Liste aller Bucharten
     */
    public List<Buchart> getBucharten() {
        return em.createQuery("SELECT b FROM Buchart b", Buchart.class).getResultList();
    }
}
