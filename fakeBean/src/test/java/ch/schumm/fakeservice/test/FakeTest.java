package ch.schumm.fakeservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
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
	public void testStringFieldsConcatenater() throws IllegalAccessException, Exception{
		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();	
		String stringOfFields = FakeRecorder.stringOfFields(hufnagelsuche); 
		assertEquals("HufnagelAlzenau", stringOfFields); 
		
	}
	
	@Test
	public void testStringFieldsConcatenaterNull() throws IllegalAccessException, Exception{
		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();
		hufnagelsuche.setName(null); 	
		String stringOfFields = FakeRecorder.stringOfFields(hufnagelsuche); 
		assertEquals("Alzenau", stringOfFields); 
		
	}
	
	@Test
	public void testMethodPresence(){
		KundeBean fakebean = new FakeBean();
		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();	
		assertTrue(FakeRecorder.hasToString(hufnagelsuche)); 

		assertFalse(FakeRecorder.hasToString(fakebean)); 
		
		
	}
	
	

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
	
	@After
	public void abraumen(){
		
	}


	


}
