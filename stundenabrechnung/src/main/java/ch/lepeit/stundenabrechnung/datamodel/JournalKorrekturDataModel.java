package ch.lepeit.stundenabrechnung.datamodel;

import javax.ejb.Stateful;

import ch.lepeit.stundenabrechnung.model.Journal;

@Stateful
public class JournalKorrekturDataModel extends GenericDataModel<Journal> {	
	public JournalKorrekturDataModel() {
		super(Journal.class);
	}
}
