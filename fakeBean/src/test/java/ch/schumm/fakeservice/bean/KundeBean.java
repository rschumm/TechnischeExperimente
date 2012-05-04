package ch.schumm.fakeservice.bean;

import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

public interface KundeBean {

	public abstract Kunde getKundeForKriterium(Suchkriterium kriterium);

}