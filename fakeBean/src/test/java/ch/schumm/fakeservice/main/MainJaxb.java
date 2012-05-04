package ch.schumm.fakeservice.main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.schumm.fakeservice.bean.RealBean;
import ch.schumm.fakeservice.model.Kunde;

/**
 * Marshallt einen Kunden in ein XML-File, unmarshallt ihn grad wieder und zeigt ihn an. 
 * Entwicklungs-Beispiel. 
 * 
 */
public class MainJaxb {
	public static void main(String[] args) throws Exception {
		

		JAXBContext context = JAXBContext.newInstance(Kunde.class);

		//Marshalling... 
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
		
		Kunde kunde = RealBean.generateJosef();

		
		m.marshal(kunde, new File("josef.xml"));
		
		//Unamarshalling... 
		Unmarshaller u = context.createUnmarshaller(); 
		Object object = u.unmarshal(new File("josef.xml"));
		System.out.println(object.toString());


	}
	
	
}
