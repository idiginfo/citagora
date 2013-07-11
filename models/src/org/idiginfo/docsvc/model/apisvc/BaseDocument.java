package org.idiginfo.docsvc.model.apisvc;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Interface to implement the BaseDocument object
 * 
 * @author griccardi
 * 
 */

public class BaseDocument implements Document {

	String id;// object uri
	String doi; // BIBO.doi
	Date date;
	String name;
	String owner;
	String type;
	BaseDocument parent;
	String notes; // may be string?
	String title;
	String created;
	String tags;
	String pages;
	String srcext;
	String srcname;
	String srcsize;
	String imageformat;
	String imagequant;
	String thumb;
	String hasindex;
	String tinycode;
	String authors;
	String publicationName;
	String issn;
	String isbn;

	String publisher;
	String publicationDate;
	String volume;
	String number;
	String startingPage;
	String url;
	String copyright;

	String status;
	String json;
	String abstractText;
	String sourceId;

	public static String BASE_URI = "http://ids.idiginfo.org/";

	public static String doiUri(String doi) {
		if (doi == null)
			return null;
		if (doi.startsWith("doi:"))
			return doi;
		if (doi.startsWith("info:doi:"))
			return doi.substring(5);// start after "info:"
		return "doi:" + doi;
	}

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
		List<String> authorList = new Vector<String>();
		authorList.add(getAuthors());
		return authorList;
	}

	@Override
	public String getAuthors() {
		return authors;
	}

	@Override
	public String getCopyright() {
		return copyright;
	}

	public String getCreated() {
		return created;
	}

	@Override
	public String getDate() {
		// TODO return formatted date
		return null;
	}

	@Override
	public Date getDateObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDoi() {
		return doi;
	}

	@Override
	public String getGUID() {
		String uri = doiUri(doi);

		if (uri != null)
			return uri;
		if (id == null)
			return null;
		return BASE_URI + id;
	}

	public String getHasindex() {
		return hasindex;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getImageformat() {
		return imageformat;
	}

	public String getImagequant() {
		return imagequant;
	}

	@Override
	public String getIsbn() {
		return isbn;
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

	public String getJson() {
		return json;
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
		return name;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public int getNumAnnotations() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String getOwner() {
		return owner;
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
	public Integer getPageStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getParent() {
		return parent;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	@Override
	public String getPublicationName() {
		return publicationName;
	}

	@Override
	public String getPublisher() {
		return publisher;
	}

	@Override
	public String getSource() {
		return "default";
	}

	public String getSrcext() {
		return srcext;
	}

	public String getSrcname() {
		return srcname;
	}

	public String getSrcsize() {
		return srcsize;
	}

	public String getStartingPage() {
		return startingPage;
	}

	public String getStatus() {
		return status;
	}

	public String getTags() {
		return tags;
	}

	public String getThumb() {
		return thumb;
	}

	public String getTinycode() {
		return tinycode;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getVolume() {
		return volume;
	}

	@Override
	public Date getIssuedDate() {
		return getDateObject();
	}

	@Override
	public String getIssued() {
		return getDate();
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

	public String getSourceId() {
		return sourceId;
	}

}
