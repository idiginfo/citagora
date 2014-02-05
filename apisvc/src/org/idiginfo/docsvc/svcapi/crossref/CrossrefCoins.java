package org.idiginfo.docsvc.svcapi.crossref;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

/**
 * Class to process Crossref coins object
 * 
 * @author griccardi
 * 
 */

public class CrossrefCoins {
	// "coins":
	// "ctx_ver\u003dZ39.88-2004
	// &rft_id\u003dinfo%3Adoi%2F10.2307%2F318776
	// &rfr_id\u003dinfo%3Asid%2Fcrossref.org%3Asearch
	// &rft.atitle\u003dRute+e+Alberto
	// &rft.jtitle\u003dThe+Modern+Language+Journal
	// &rft.date\u003d1946
	// &rft.volume\u003d30
	// &rft.issue\u003d7
	// &rft.spage\u003d507
	// &rft.aufirst\u003dVirgil+A.
	// &rft.aulast\u003dWarren
	// &rft_val_fmt\u003dinfo%3Aofi%2Ffmt%3Akev%3Amtx%3Ajournal
	// &rft.genre\u003darticle
	// &rft.au\u003dVirgil+A.+Warren
	// &rft.au\u003d+Cecilia+Meireles
	// &rft.au\u003d+Virginia+Joiner
	// &rft.au\u003d+Eunice+Joiner+Gates", String[] coinArray;
	protected String[] coinArray;
	protected Map<String, String> coinPairs;
	protected List<String> authors = new Vector<String>();

	CrossrefCoins(String coins) {
		coinArray = coins.split("&");
		coinPairs = new HashMap<>();
		for (String coinPair : coinArray) {
			String[] pair = coinPair.split(":");
			if (pair[0].equals("rft.au")) {
				authors.add(pair[1]);
			} else {
				coinPairs.put(pair[0], pair[1]);
			}
		}
	}

	public String getValue(String key) {
		if (coinPairs == null)
			return null;
		return coinPairs.get(key);
	}

	public String getAuthorString() {
		return StringUtils.join(authors, ",");
	}

	public List<String> getAuthors() {
		return authors;
	}
}
