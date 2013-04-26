package org.idiginfo.docsvc.svcapi.medline;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

/**
 * Class to implement Pubmed MEDLINE content mapping 
 * onto apisvc Document object via getters
 * 
 * @author sflager
 * 
 */

public class MedlineArticle implements Document {

	Map<String, List<String>> fields = new HashMap<>();

	public static MedlineArticle getNextArticle(BufferedReader in) {
		MedlineArticle article = new MedlineArticle();
		article.init(in);
		if (article.fields.size() == 0)
			return null;
		return article;
	}

	void init(BufferedReader in) {
		String line;
		String name = null;
		String value = null;

		try {
			line = in.readLine();
			while (line != null && line.length() > 0) {
				if (line.startsWith(" ")) {
					// continuation of value
					value += " " + line.trim();
				} else {
					if (name != null) {
						// save previous value
						fieldAdd(name, value);
					}
					int split = line.indexOf("-");
					name = line.substring(0, split).trim();
					value = line.substring(split + 1).trim();
				}
				line = in.readLine();
			}
			if (name != null) {
				// save previous value
				fieldAdd(name, value);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void fieldAdd(String name, String value) {
		// add to existing else add new list
		if (fields.containsKey(name)) {
			List<String> prev = fields.get(name);
			prev.add(value);
			fields.put(name, prev);
		} else {
			List<String> newvalue = new ArrayList<String>();
			newvalue.add(value);
			fields.put(name, newvalue);
		}
	}

	@Override
	public String getAbstractText() {
		List<String> temp = fields.get("AB");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getAggregationType() {
		return null;
	}

	@Override
	public Annotation getAnnotation(int i) {
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	@Override
	public String getarXivId() {
		return null;
	}

	@Override
	public List<String> getAuthorList() {
		List<String> temp = fields.get("FAU");
		return temp;
	}

	@Override
	public String getAuthorNotes() {
		List<String> temp = fields.get("GN");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getAuthors() {
		List<String> temp = fields.get("FAU");
		String authorString = StringUtils.join(temp, ';');
		return authorString.trim();
	}

	@Override
	public String getCopyright() {
		return null;
	}

	@Override
	public String getCoverDate() {
		List<String> temp = fields.get("DP");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getDate() {
		return null;
	}

	@Override
	public Date getDateObject() {
		return null;
	}

	@Override
	// repeating field
	public String getDoi() {
		List<String> temp = fields.get("AID");
		String tempdoi = null;
		int len = temp.size();
		for (int i = 0; i < len; i++) {
			String stemp = temp.get(i);
			if (stemp.contains("[doi]")) {
				int j = stemp.indexOf("[");
				tempdoi = stemp.substring(0, j);
			}
		}
		if (tempdoi == null)
			return null;
		return tempdoi.trim();
	}

	@Override
	public String getEdition() {
		List<String> temp = fields.get("EN");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String geteIssn() {
		return null;
	}

	@Override
	public String getGenre() {
		return null;
	}

	@Override
	public String getGUID() {
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIsbn() {
		List<String> temp = fields.get("ISBN");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	// repeating field:
	// defer to (Print), of not found use (Electronic), else null
	public String getIssn() {
		List<String> temp = fields.get("IS");
		String tempissnP = null;
		String tempissnE = null;
		int len = temp.size();
		for (int i = 0; i < len; i++) {
			String stemp = temp.get(i);
			if (stemp.contains("(Print)")) {
				int j = stemp.indexOf("(");
				tempissnP = stemp.substring(0, j);
			}
			if (stemp.contains("(Electronic)")) {
				int k = stemp.indexOf("(");
				tempissnE = stemp.substring(0, k);
			}
		}
		if (tempissnP == null) {
			if (tempissnE == null) {
				return null;
			} else {
				return tempissnE.trim();
			}
		}
		return tempissnP.trim();
	}

	@Override
	public String getIssue() {
		List<String> temp = fields.get("IP");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public List<String> getKeywords() {
		List<String> temp = fields.get("OT");
		return temp;
	}

	@Override
	public String getLanguage() {
		List<String> temp = fields.get("LA");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public List<String> getMeshTerms() {
		List<String> temp = fields.get("MH");
		return temp;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getNumAnnotations() {
		return 0;
	}

	@Override
	public String getItemNumber() {
		return null;
	}

	@Override
	public String getOwner() {
		return null;
	}

	@Override
	public Integer getPageEnd() {
		return null;
	}

	@Override
	public String getPages() {
		List<String> temp = fields.get("PG");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public Integer getPageStart() {
		return null;
	}

	@Override
	public Document getParent() {
		return null;
	}

	@Override
	public String getPublicationDate() {
		List<String> temp = fields.get("DP");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getPublicationName() {
		List<String> temp = fields.get("TA");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getPublisher() {
		return null;
	}

	@Override
	public String getPMId() {
		List<String> temp = fields.get("PMID");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getRights() {
		return null;
	}

	@Override
	public String getSeriesTitle() {
		List<String> temp = fields.get("JT");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getSource() {
		return ServiceFactory.COLLECTION_MEDLINE;
	}

	@Override
	public String getTitle() {
		List<String> temp = fields.get("TI");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getType() {
		List<String> temp = fields.get("PT");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getUri() {
		return null;
	}

	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public String getVolume() {
		List<String> temp = fields.get("VI");
		String stemp = temp.toString();
		return stemp.trim();
	}

	@Override
	public String getIssued() {
		return null;
	}

	@Override
	public Date getIssuedDate() {
		return null;
	}

	@Override
	public String getSourceId() {
		return null;
	}

}
