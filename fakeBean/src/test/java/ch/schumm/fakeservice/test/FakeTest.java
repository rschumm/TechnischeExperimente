package ch.schumm.fakeservice.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.schumm.fake.FakeRecorder;
import ch.schumm.fakeservice.bean.FakeBean;
import ch.schumm.fakeservice.bean.KundeBean;
import ch.schumm.fakeservice.bean.RealBean;
import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

/**
 * Testet die FakeRecorder und FakeUnmarshaller Infrastruktur. 
 * @author C709360
 *
 */
public class FakeTest {

	@Test
	public void testRecorder() throws Exception {
		RealBean bean = new RealBean();

		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();
		Kunde hufnagel = bean.getKundeForKriterium(hufnagelsuche);

		Suchkriterium schummsuche = RealBean.generateSchummSuche();
		Kunde schumm = bean.getKundeForKriterium(schummsuche);

		FakeRecorder<Suchkriterium, Kunde> recorder = new FakeRecorder<Suchkriterium, Kunde>(); 
		recorder.record(schummsuche, schumm);
		recorder.record(hufnagelsuche, hufnagel);
		
	}

	
	@Test
	public void testFakeImplSchumm() {
		KundeBean fakebean = new FakeBean();

		Kunde kunde = fakebean.getKundeForKriterium(RealBean
				.generateSchummSuche());

		assertEquals("Josef Schumm wohnt an Schlossgasse in Coburg",
				kunde.toString());
	}
	
	@Test
	public void testFakeImpl() {
		KundeBean fakebean = new FakeBean();

		Kunde kunde = fakebean.getKundeForKriterium(RealBean
				.generateHufnagelSuche());

		assertEquals("Barbara Hufnagel wohnt an Hanauer Strasse in Alzenau",
				kunde.toString());
	}
	


}
