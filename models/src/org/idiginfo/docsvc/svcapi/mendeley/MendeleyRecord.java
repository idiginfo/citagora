package org.idiginfo.docsvc.svcapi.mendeley;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.apache.commons.lang.StringUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map Mendeley json object to Document
 * 
 * @author sflager
 * 
 */
public class MendeleyRecord implements Document {
	@SerializedName("abstract")
	String abstractText;
	Author[] authors;
	int categories[];
	String doi;
	HashMap<String, String> identifiers; // doi, issn, etc.
	String isbn;
	String issn;
	String issue;
	List<String> keywords;
	@SerializedName("mendeley_url")
	String mendeleyUrl;
	String number;
	@SerializedName("oa_journal")
	Boolean oaJournal;
	String pages;
	String publicationDate;
	String publicationName;
	@SerializedName("publication_outlet")
	String publicationOutlet;
	String publisher;
	String startingPage;
	Stat stats;
	String title;
	String type;
	String uuid;
	String volume;
	@SerializedName("website")
	String url;
	int publishYear;

	static class Categories {
		int catagoryId;
	}

	static class Author {
		@SerializedName("forename")
		String forename;
		@SerializedName("surname")
		String surname;

		public String toString() {
			return forename + " " + surname;

		}
	}

	static class Keyword {
		String keyword;

		public String toString() {
			return keyword;
		}
	}

	static class Stat {

		@SerializedName("country")
		Country country[];
		@SerializedName("discipline")
		Discipline discipline[];
		@SerializedName("readers")
		int readers;
		@SerializedName("status")
		Status status[];

		// public String toString() {
		// return type + "id : " + id + "name : " + name + "value :" + value;
		// }
	}

	static class Country {
		@SerializedName("name")
		String countryName;
		@SerializedName("value")
		int countryPercent;
	}

	static class Discipline {
		@SerializedName("id")
		int discipineId;
		@SerializedName("name")
		String disciplineName;
		@SerializedName("value")
		int disciplinePercent;
	}

	static class Status {
		@SerializedName("name")
		String statusName;
		@SerializedName("value")
		int statusPercent;
	}

	@Override
	public String getId() {
		if (identifiers == null || identifiers.isEmpty()) {
			String url = getUrl();
			if (url != null)
				return url;
			String uuid = getUUID();
			return uuid;
		}
		String pmid = getPMId();
		if (pmid != null)
			return pmid;
		String issn = getIssn();
		if (issn != null)
			return issn;
		String isbn = getIsbn();
		if (isbn != null)
			return isbn;
		String doi = getDoi();
		if (doi != null)
			return doi;
		return null;
	}

	@Override
	public String getDate() {
		return publicationDate;
	}

	transient DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date getDateObject() {
		if (publicationDate == null)
			return null;
		try {
			return formatter.parse(publicationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOwner() {
		return null;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Document getParent() {
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAuthors() {
		String authorstring = StringUtils.join(authors, ", ");
		return authorstring;
	}

	@Override
	public List<String> getAuthorList() {
		List<String> authorList = new Vector<String>();
		for (int i = 0; i < authors.length; i++) {
			authorList.add(authors[i].toString());
		}
		return authorList;

	}

	@Override
	public int getNumAnnotations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Annotation getAnnotation(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPublicationName() {
		return publicationName;
	}

	@Override
	public String getIssn() {
		if (identifiers == null || identifiers.isEmpty())
			return null;
		String issn = null;
		issn = identifiers.get("issn");
		return issn;
	}

	@Override
	public String getIsbn() {
		if (identifiers == null || identifiers.isEmpty())
			return null;
		String isbn = null;
		isbn = identifiers.get("isbn");
		return isbn;
	}

	@Override
	public String getDoi() {
		if (identifiers == null || identifiers.isEmpty())
			return null;
		String doi = null;
		doi = identifiers.get("doi");
		return doi;
	}

	public String getUUID() {
		return uuid;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public String getVolume() {
		return volume;
	}

	public String getNumber() {
		return number;
	}

	public String getStartingPage() {
		return startingPage;
	}

	public String getUrl() {
		return mendeleyUrl;
	}

	public String getCopyright() {
		return publicationDate;
	}

	@Override
	public String getGUID() {
		if (doi != null) {
			if (doi.startsWith("doi:"))
				return doi;
			return "doi:" + doi;
		}
		return "http://ids.idiginfo.org/" + getId();
	}

	@Override
	public String getSource() {
		return MendeleyApiParams.SOURCE;
	}

	@Override
	public Integer getPageStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPageEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPages() {
		return pages;
	}

	@Override
	public String getIssue() {
		return issue;
	}

	@Override
	public String getUri() {
		doi = getDoi();
		if (doi != null && doi.length() > 0) {
			if (doi.startsWith("doi:"))
				return doi;
			return "doi:" + doi;
		}
		url = getUrl();
		return url;
	}

	@Override
	public List<String> getKeywords() {
		return keywords;
	}

	@Override
	public List<String> getMeshTerms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAbstractText() {
		return abstractText;
	}

	@Override
	public String getAggregationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getarXivId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getCategories() {
		return categories;
	}

	@Override
	public String getCoverDate() {
		return null;
	}

	@Override
	public String getEdition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String geteIssn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGenre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItemNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPMId() {
		if (identifiers == null || identifiers.isEmpty())
			return null;
		String pmid = null;
		pmid = identifiers.get("pmid");
		return pmid;
	}

	@Override
	public String getRights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSeriesTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssued() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getIssuedDate() {
		// TODO Auto-generated method stub
		return null;
	}
}
