package org.idiginfo.docsvc.svcapi.springer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.BaseDocument;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.SvcApiServiceFactory;
import org.apache.commons.lang.StringUtils;

/**
 * Class to map Springer json object to Document
 * 
 * @author griccardi
 * 
 */
public class SpringerRecord implements Document {
	static class Creator {
		String creator;

		public String toString() {
			return creator;
		}
	}

	String identifier;
	String title;
	Creator[] creators;
	String publicationName;
	String issn;
	String isbn;
	String doi;
	String publisher;
	String publicationDate;
	String volume;
	String number;
	String startingPage;
	String url;

	String copyright;

	transient DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Annotation getAnnotation(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	@Override
	public List<String> getAuthorList() {
		List<String> authorList = new Vector<String>();
		for (int i = 0; i < creators.length; i++) {
			authorList.add(creators[i].creator);
		}
		return authorList;

	}

	@Override
	public String getAuthors() {
		String authors = StringUtils.join(creators, ", ");
		return authors;
	}

	public String getCopyright() {
		return copyright;
	}

	public Creator[] getCreators() {
		return creators;
	}

	@Override
	public String getDate() {
		return publicationDate;
	}

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

	public String getDoi() {
		return doi;
	}

	@Override
	public String getGUID() {
		if (doi != null) {
			return BaseDocument.doiUri(doi);
		}
		return BaseDocument.BASE_URI + getId();
	}

	@Override
	// defer to doi if it exists, otherwise use native id value
	public String getId() {
		if (doi != null) {
			String tempId = BaseDocument.doiUri(doi);
			if (tempId.trim() != "http://dx.doi.org/") {
				return tempId;
			}
		}
		return "springer:" + getIdentifier();
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getIssn() {
		return issn;
	}

	@Override
	public String getIssue() {
		return number;
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
		return null;
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
		try {
			return Integer.getInteger(startingPage);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public Document getParent() {
		return null;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public String getPublisher() {
		return publisher;
	}

	@Override
	public String getSource() {
		return SvcApiServiceFactory.COLLECTION_SPRINGER;
	}

	public String getStartingPage() {
		return startingPage;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String getUri() {
		if (doi != null && doi.length() > 0) {
			return BaseDocument.doiUri(doi);
		}
		return url;
	}

	public String getUrl() {
		return url;
	}

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

	@Override
	public String getSourceId() {
		// TODO Auto-generated method stub
		return null;
	}

}
