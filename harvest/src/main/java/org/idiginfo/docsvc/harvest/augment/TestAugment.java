package org.idiginfo.docsvc.harvest.augment;

import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;

/**
 * Class to test the Augment model 
 * 
 * @author griccardi
 * 
 */

public class TestAugment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Augment augmenter = new Augment();
		CitagoraFactory factory = CitagoraFactory.getFactory();
		augmenter.addMissingSources("10.1002/jts.20383");

	}

}
