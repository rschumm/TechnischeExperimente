package ch.schumm.fake;

import ch.schumm.fakeservice.bean.FakeBean;
import ch.schumm.fakeservice.bean.KundeBean;
import ch.schumm.fakeservice.bean.RealBean;
import ch.schumm.fakeservice.model.Kunde;

public class Main {

	/**
	 * Zu Testtzwecken. 
	 * @param args
	 */
	public static void main(String[] args) {
		KundeBean fakebean = new FakeBean(); 
		
		Kunde schumm = fakebean.getKundeForKriterium(RealBean.generateSchummSuche());
		System.out.println(schumm);
		
		

	}

}
