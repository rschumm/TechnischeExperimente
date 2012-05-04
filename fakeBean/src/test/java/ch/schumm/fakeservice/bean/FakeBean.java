package ch.schumm.fakeservice.bean;

import ch.schumm.fake.FakeUnmarshaller;
import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

/**
 * Ein Fake-Bean, das den FakeUnmarshaller benutzt. 
 * @author C709360
 *
 */
public class FakeBean implements KundeBean {

	public class KundeBeanException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public KundeBeanException(Exception e) {
			super(e);
		}

	}

	public Kunde getKundeForKriterium(Suchkriterium kriterium) {
		try {
			FakeUnmarshaller<Suchkriterium, Kunde> fakeUnmarshaller = new FakeUnmarshaller<Suchkriterium, Kunde>(); 
			return fakeUnmarshaller.unmarshallResultForKriterium(kriterium, Kunde.class);
		} catch (Exception e) {
			throw new KundeBeanException(e);
		}

	}

}
