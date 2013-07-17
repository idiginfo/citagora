package org.idiginfo.docsvc.svcapi.nature;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement Document object for Nature
 * 
 * @author griccardi
 * 
 */

public class NatureEntry implements Document {
	public class Article {
		@SerializedName("xhtml:head")
		Head head; //
	}

	public class ExtraRecordData {
		NatureEntry[] entry; // [
	}

	public class Head {
		@SerializedName("dc:identifier")
		String identifier; // "doi:10.1038/026533a0",
		@SerializedName("dc:title")
		String title;
		@SerializedName("prism:productCode")
		String productCode;
		@SerializedName("prism:publicationName")
		String publicationName;
		@SerializedName("prism:doi")
		String doi; // "10.1038/026533a0",
		@SerializedName("dc:publisher")
		String publisher;
		@SerializedName("dc:description")
		String description;

		@SerializedName("prism:publicationDate")
		String publicationDate;
		@SerializedName("prism:volume")
		String volume;
		@SerializedName("prism:number")
		Integer number;
		@SerializedName("prism:startingPage")
		Integer startingPage;
		@SerializedName("prism:endingPage")
		Integer endingPage;
		@SerializedName("prism:url")
		String url;
		@SerializedName("prism:genre")
		String genre;
		@SerializedName("prism:copyright")
		String copyright;
	}

	public class Message {
		@SerializedName("pam:article")
		Article article; //
	}

	public class RecordData {
		@SerializedName("pam:message")
		Message message; //
	}

	String title; //
	String link; // "http://dx.doi.org/10.1038/026533a0",
	String id; // "http://dx.doi.org/10.1038/026533a0",
	String updated; // "2013-02-07T21:51:07+00:00",

	@SerializedName("sru:recordSchema")
	String recordSchema; // "info:srw/schema/11/pam-v2.1",

	@SerializedName("sru:recordPacking")
	String recordPacking; // "packed",

	@SerializedName("sru:recordData")
	RecordData recordData; //

	@SerializedName("sru:recordPosition")
	String recordPosition; // 1,

	@SerializedName("sru:extraRecordData")
	ExtraRecordData extraRecordData; //

	@Override
	public Annotation getAnnotation(int i) {
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	@Override
	public List<String> getAuthorList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCopyright() {
		return getHead().copyright;
	}

	@Override
	public String getDate() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.publicationDate;
	}

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

	@Override
	public Date getDateObject() {
		String publicationDate = getDate();
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
	public String getDoi() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.doi;
	}

	@Override
	public String getGUID() {
		return getId();
	}

	private Head getHead() {
		if (recordData == null)
			return null;
		if (recordData.message == null)
			return null;
		if (recordData.message.article == null)
			return null;
		return recordData.message.article.head;
	}

	@Override
	public String getId() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.identifier;
	}

	@Override
	public String getIsbn() {
		return null;
	}

	@Override
	public String getIssn() {
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
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumAnnotations() {
		return 0;
	}

	@Override
	public String getOwner() {
		return null;
	}

	@Override
	public Integer getPageEnd() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.endingPage;
	}

	@Override
	public String getPages() {
		Integer pageStart = getPageStart();
		Integer pageEnd = getPageEnd();
		if (pageStart != null && pageEnd != null) {
			return pageStart + "-" + pageEnd;
		}
		return null;
	}

	@Override
	public Integer getPageStart() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.startingPage;
	}

	@Override
	public Document getParent() {
		return null;
	}

	@Override
	public String getPublicationName() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.publicationName;
	}

	@Override
	public String getPublisher() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.publisher;
	}

	@Override
	public String getSource() {
		return ServiceFactory.COLLECTION_NATURE;
	}

	@Override
	public String getTitle() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.title;
	}

	@Override
	public String getType() {
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
		Head head = getHead();
		if (head == null)
			return null;
		return head.url;
	}

	@Override
	public String getVolume() {
		Head head = getHead();
		if (head == null)
			return null;
		return head.volume;
	}

	@Override
	public String getIssued() {
		return getDate();
	}

	@Override
	public Date getIssuedDate() {
		return getDateObject();
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

	@Override
	public String getSourceId() {
		// TODO Auto-generated method stub
		return null;
	}

}
