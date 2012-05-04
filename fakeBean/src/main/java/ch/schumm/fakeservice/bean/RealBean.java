package ch.schumm.fakeservice.bean;

import ch.schumm.fakeservice.model.Adresse;
import ch.schumm.fakeservice.model.Kunde;
import ch.schumm.fakeservice.model.Suchkriterium;

/**
 * Ein reelles Bean, dessen Daten vom FakeRecorder aufgenommen werden. 
 * @author C709360
 *
 */
public class RealBean implements KundeBean {

	public Kunde getKundeForKriterium(Suchkriterium kriterium) {
		
		if(kriterium.getName().equals("Hufnagel")){
			return generateBarbara(); 
		} else if (kriterium.getName().equals("Schumm")){
			return generateJosef(); 
		}
		return null;
	}
	




	
	
	
	public static Suchkriterium generateHufnagelSuche() {
		Suchkriterium hufnagelsuche = new Suchkriterium();
		hufnagelsuche.setName("Hufnagel"); 
		hufnagelsuche.setOrt("Alzenau");
		return hufnagelsuche;
	}


	public static Suchkriterium generateSchummSuche() {
		Suchkriterium schummsuche = new Suchkriterium();
		schummsuche.setName("Schumm"); 
		schummsuche.setOrt("Coburg");
		return schummsuche;
	}


	public static Kunde generateJosef() {
		Kunde kunde = new Kunde();
		kunde.setName("Schumm");
		kunde.setVorname("Josef");
	
		Adresse adresse = new Adresse();
		adresse.setOrt("Coburg");
		adresse.setStrasse("Schlossgasse");
	
		kunde.setAdresse(adresse);
		return kunde;
	}
	
	public static Kunde generateBarbara() {
		Kunde kunde = new Kunde();
		kunde.setName("Hufnagel");
		kunde.setVorname("Barbara");
	
		Adresse adresse = new Adresse();
		adresse.setOrt("Alzenau");
		adresse.setStrasse("Hanauer Strasse");
	
		kunde.setAdresse(adresse);
		return kunde;
	}

}
