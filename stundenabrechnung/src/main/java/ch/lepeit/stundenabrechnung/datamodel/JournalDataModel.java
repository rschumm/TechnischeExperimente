package ch.lepeit.stundenabrechnung.datamodel;

import javax.ejb.Stateful;

import ch.lepeit.stundenabrechnung.model.Journal;

/**
 * GenericDataModel EJB für Journaleinträge
 * 
 * @author C910511
 *
 */
@Stateful
public class JournalDataModel extends GenericDataModel<Journal> {	
	public JournalDataModel() {
		super(Journal.class);
	}
}
