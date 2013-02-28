package org.idiginfo.docsvc.svcapi.nature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

public class NatureDocument implements Document {

	class CoreData {
		@SerializedName("prism:url")
		String url; // "http://api.elsevier.com/content/article/DOI:10.1016/j.jpsychires.2008.05.001",

		@SerializedName("dc:identifier")
		String id; // : "DOI:10.1016/j.jpsychires.2008.05.001",
		@SerializedName("prism:doi")
		String doi; // : "10.1016/j.jpsychires.2008.05.001",
		@SerializedName("pii")
		String pii; // : "S0022-3956(08)00114-3",
		@SerializedName("dc:title")
		String title; // "Substance use disorders and ...",
		@SerializedName("prism:publicationName")
		String pubName; // : "Journal of Psychiatric Research",
		@SerializedName("prism:aggregationType")
		String aggregationType; // : "Journal",
		@SerializedName("prism:issn")
		String issn; // : "00223956",
		@SerializedName("prism:coverDate")
		String coverDate;// : "2009-01-31"
	}

	class FullTextRetrievalResponse {
		@SerializedName("coredata")
		public CoreData coreData;
	}

	@SerializedName("full-text-retrieval-response")
	FullTextRetrievalResponse fullTextRetrievalResponse;

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
	public String getAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate() {
		if (fullTextRetrievalResponse == null
				|| fullTextRetrievalResponse.coreData == null)
			return null;

		return fullTextRetrievalResponse.coreData.coverDate;
	}

	@Override
	public Date getDateObject() {
		// try {
		// return formatter.parse(date);
		// } catch (ParseException e) {
		// }
		return null;
	}

	@Override
	public String getDoi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGUID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		if (fullTextRetrievalResponse == null
				|| fullTextRetrievalResponse.coreData == null)
			return null;
		return fullTextRetrievalResponse.coreData.id;
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
	public Document getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPubName() {
		if (fullTextRetrievalResponse == null
				|| fullTextRetrievalResponse.coreData == null)
			return null;
		return fullTextRetrievalResponse.coreData.pubName;
	}

	@Override
	public String getTitle() {
		if (fullTextRetrievalResponse == null
				|| fullTextRetrievalResponse.coreData == null)
			return null;
		return fullTextRetrievalResponse.coreData.title;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAuthorList() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getCopyright() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getIsbn() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getIssn() {
	    // TODO Auto-generated method stub
	    return null;
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
	public String getPublicationName() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getPublisher() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getSource() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getUri() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getUrl() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getVolume() {
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getAbstractText() {
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

}
