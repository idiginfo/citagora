package org.idiginfo.docsvc.svcapi.nature;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

/**
 * Class to map Spring json object to Document
 * 
 * @author griccardi
 * 
 */
public class NatureRecord implements Document {
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
	    if (doi.startsWith("doi:"))
		return doi;
	    return "doi:" + doi;
	}
	return "http://ids.idiginfo.org/" + getId();
    }

    @Override
    public String getId() {
	return "doi:" + doi;
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
	// TODO Auto-generated method stub
	return null;
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
	    if (doi.startsWith("doi:"))
		return doi;
	    return "doi:" + doi;
	}
	return url;
    }

    public String getUrl() {
	return url;
    }

    public String getVolume() {
	return volume;
    }

}
