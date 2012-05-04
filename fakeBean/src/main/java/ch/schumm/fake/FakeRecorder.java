package ch.schumm.fake;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.codec.digest.DigestUtils;

import ch.schumm.fakeservice.bean.RealBean;
import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

/**
 * Nimmt Fake-Daten von einem realen Service auf und legt sie mittels JAXB als XML-Dateien ab. <br>
 * Die Dateinamen der XML-Dateien werden aus dem md5-Hash der Suchkriterien erzeugt. Dazu müssen sie eine eindeutige toString-Methode
 * implementieren. 
 * 
 * @author C709360 Rémy Schumm
 *
 * @param <S> Typ des Suchkriteriums
 * @param <R> Typ des Resultats. 
 */
public class FakeRecorder<S,R> {

	/**
	 * Zu Testzwecken. 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		RealBean bean = new RealBean();

		Suchkriterium hufnagelsuche = RealBean.generateHufnagelSuche();
		Kunde hufnagel = bean.getKundeForKriterium(hufnagelsuche);

		Suchkriterium schummsuche = RealBean.generateSchummSuche();
		Kunde schumm = bean.getKundeForKriterium(schummsuche);

		FakeRecorder<Suchkriterium, Kunde> recorder = new FakeRecorder<Suchkriterium, Kunde>(); 
		recorder.record(schummsuche, schumm);
		recorder.record(hufnagelsuche, hufnagel);

	}

	/**
	 * Nimmt einen Testdatensatz auf. 
	 * @param suche das Suchkriterium
	 * @param result das Resultat des realen Service. 
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	public void record(S suche, R result)
			throws JAXBException, PropertyException {
		JAXBContext context = JAXBContext.newInstance(result.getClass());

		// Marshalling...
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		String md5key = generateHashKey(suche);

		m.marshal(result, new File(md5key + ".xml"));
	}

	public static String generateHashKey(Object suche) {
		String key = DigestUtils.md5Hex(suche.toString());
		return key;
	}

}
