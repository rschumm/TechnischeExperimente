package ch.schumm.fake;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Reproduziert die mit dem FakeRecoder aufgenommen Testdaten. 
 * 
 * @author C709360
 *
 * @param <S> Typ des Suchkriteriums
 * @param <R> Typ des Resultats. 
 */
public class FakeUnmarshaller<S, R> {

	/**
	 * Rekonstruiert die Testdaten für ein Suchkriterium. 
	 * @param kriterium das Suchkriterium. 
	 * @param rootClassResult Der Klassentyp des Resultats. 
	 * @return
	 * @throws Exception
	 */
	public R unmarshallResultForKriterium(S kriterium, Class<R> rootClassResult) throws Exception {
		String key = FakeRecorder.generateHashKey(kriterium);

		JAXBContext context = JAXBContext.newInstance(rootClassResult);

		// Unamarshalling...
		Unmarshaller u = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		R kunde = (R) u.unmarshal(new File(key + ".xml"));

		return kunde;
	}

}
