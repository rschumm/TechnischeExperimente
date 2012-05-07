package ch.schumm.fake;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Nimmt Fake-Daten von einem realen Service auf und legt sie mittels JAXB als
 * XML-Dateien ab. <br>
 * Die Dateinamen der XML-Dateien werden aus dem md5-Hash der Suchkriterien
 * erzeugt. Dazu müssen sie eine eindeutige toString-Methode implementieren.
 * 
 * @author C709360 Rémy Schumm
 * 
 * @param <S>
 *            Typ des Suchkriteriums
 * @param <R>
 *            Typ des Resultats.
 */
public class FakeRecorder<S, R> {

	/**
	 * Nimmt einen Testdatensatz auf.
	 * 
	 * @param suche
	 *            das Suchkriterium
	 * @param result
	 *            das Resultat des realen Service.
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	public void record(S suche, R result) throws IllegalAccessException,
			Exception {
		JAXBContext context = JAXBContext.newInstance(result.getClass());

		// Marshalling...
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		String md5key = generateHashKey(suche);

		m.marshal(result, new File(md5key + ".xml"));
	}

	protected static String generateHashKey(Object suche)
			throws IllegalAccessException, Exception {
		String key = null; 
		if (hasToString(suche)) {
			 key = DigestUtils.md5Hex(suche.toString());
		} else {
			 key = DigestUtils.md5Hex(stringOfFields(suche));
		}
		return key;
	}

	/**
	 * Berechnet aus den String-Feldern eines Suchkriteriums einen
	 * Concatenierten String; wird für den md5 Hash benötigt.
	 * 
	 * @param suche
	 *            das zu berechnende Objekt.
	 * @return den berechneten String.
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	public static String stringOfFields(Object suche) throws Exception,
			IllegalAccessException {
		String felder = "";
		Field[] fieldsSuche = suche.getClass().getDeclaredFields();
		for (Field field : fieldsSuche) {
			Class<?> type = field.getType();
			field.setAccessible(true);
			Object value = field.get(suche);
			if (value != null && type == String.class) {
				felder += value.toString();
			}

		}
		return felder;
	}

	public static boolean hasToString(Object kunde) {
		try {
			kunde.getClass().getDeclaredMethod("toString");
			@SuppressWarnings("unused")
			Method[] declaredMethods = kunde.getClass().getDeclaredMethods();
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

}
