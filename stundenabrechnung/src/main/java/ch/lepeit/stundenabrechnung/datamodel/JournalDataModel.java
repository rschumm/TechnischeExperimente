package ch.lepeit.stundenabrechnung.datamodel;

import javax.ejb.Stateful;

import ch.lepeit.stundenabrechnung.model.Journal;

@Stateful
public class JournalDataModel extends GenericDataModel<Journal> {	
	public JournalDataModel() {
		super(Journal.class);
	}
}
