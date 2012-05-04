package ch.schumm.fakeservice.main;

import ch.schumm.fake.FakeRecorder;
import ch.schumm.fakeservice.bean.FakeBean;
import ch.schumm.fakeservice.bean.KundeBean;
import ch.schumm.fakeservice.bean.RealBean;
import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

public class Main {

	/**
	 * Zu Testzwecken.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		//Record
		
		RealBean bean = new RealBean();

		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();
		Kunde hufnagel = bean.getKundeForKriterium(hufnagelsuche);

		Suchkriterium schummsuche = RealBean.generateSchummSuche();
		Kunde schumm = bean.getKundeForKriterium(schummsuche);

		FakeRecorder<Suchkriterium, Kunde> recorder = new FakeRecorder<Suchkriterium, Kunde>();
		recorder.record(schummsuche, schumm);
		recorder.record(hufnagelsuche, hufnagel);
		
		
		//Consume
		

		KundeBean fakebean = new FakeBean();

		Kunde schummFake = fakebean.getKundeForKriterium(RealBean
				.generateSchummSuche());
		System.out.println(schummFake);

	}

}
