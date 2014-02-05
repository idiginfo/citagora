package org.idiginfo.docsvc.svcapi.mendeley;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeader;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeaders;

/**
 * Class that implements the Mendeley Headers - a list of "search" results
 * containing the uuid values necessary for "details" based retrieval
 * 
 * @author sflager
 * 
 */

public class MendeleyHeaders extends Vector<MendeleyHeader> {

	private static final long serialVersionUID = 1L;

	/**
	 * Convert the List<MendeleyRecord> into List<Document>
	 * 
	 * @param records
	 */
	public MendeleyHeaders(List<MendeleyHeader> records) {
		if (records == null)
			return;
		Iterator<MendeleyHeader> documents = records.iterator();
		while (documents.hasNext()) {
			add(documents.next());
		}
	}

	public MendeleyHeader getHeader(int i) {
		return get(i);
	}

}