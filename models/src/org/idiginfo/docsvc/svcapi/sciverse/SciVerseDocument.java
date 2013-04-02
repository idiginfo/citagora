package org.idiginfo.docsvc.svcapi.sciverse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

public class SciVerseDocument implements Document {

	public class Affiliation {
		@SerializedName("affilname")
		String affiliationName; // "Penn State College of Medicine | Pennsylvania State University ^ Pennsylvania State Univ. ^ Pennsylvania State University College of Medicine ^ Pennsylvania State Univ. Coll. Med."
	}

	@SerializedName("prism:url")
	String url; // "http://api.elsevier.com/content/article/DOI:10.1016/j.jpsychires.2008.05.001",

	@SerializedName("link")
	List<SciVerseLink> links;
	@SerializedName("dc:identifier")
	String id; // : "SCOPUS_ID:...",
	@SerializedName("prism:doi")
	String doi; // : "10.1016/j.jpsychires.2008.05.001",
	@SerializedName("pii")
	String pii; // : "S0022-3956(08)00114-3",
	@SerializedName("dc:title")
	String title; // "Substance use disorders and ...",

	@SerializedName("dc:creator")
	String creator; // : "Mayes, S.D.",
	@SerializedName("prism:publicationName")
	String pubName; // : "Journal of Psychiatric Research",
	@SerializedName("prism:aggregationType")
	String aggregationType; // : "Journal",
	@SerializedName("prism:issn")
	String issn; // : "00223956",
	@SerializedName("prism:coverDate")
	String coverDate;// : "2009-01-31"
	@SerializedName("prism:eIssn")
	String eIssn; // : "18780237",
	@SerializedName("prism:volume")
	String volume; // : "7",
	@SerializedName("prism:issueIdentifier")
	String issueId; // : "1",

	@SerializedName("prism:pageRange")
	String pageRange;// : "109-119",
	@SerializedName("prism:coverDisplayDate")
	String displayDate; // : "January 2013",
	@SerializedName("citedby-count")
	Integer citedByCount; // : "0",
	List<Affiliation> affiliation;
	String subtype; // : "ip",

	String subtypeDescription; // : "Article in Press"

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

	@Override
	public Annotation getAnnotation(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAuthorList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthors() {
		return creator;
	}

	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate() {
		return coverDate;
	}

	@Override
	public Date getDateObject() {
		try {
			return formatter.parse(coverDate);
		} catch (ParseException e) {
		}
		return null;
	}

	@Override
	public String getDoi() {
		return doi;
	}

	@Override
	public String getGUID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getIsbn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssn() {
		return issn;
	}

	@Override
	public String getIssue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMeshTerms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumAnnotations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOwner() {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPageStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationName() {
		return pubName;
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPubName() {
		return pubName;
	}

	@Override
	public String getSource() {
		return SciVerseApiParams.SOURCE;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return subtype;
	}

	@Override
	public String getUri() {
		if (doi != null && doi.length() > 0) {
			if (doi.startsWith("doi:"))
				return doi;
			return "doi:" + doi;
		}
		return id;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getVolume() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public String getCoverDate() {
		// TODO Auto-generated method stub
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
	public String getPublicationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPMId() {
		// TODO Auto-generated method stub
		return null;
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
	public String getAbstractText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getIssuedDate() {
		return getDateObject();
	}

	@Override
	public String getIssued() {
		return getDate();
	}
}
