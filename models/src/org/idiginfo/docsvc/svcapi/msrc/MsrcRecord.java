package org.idiginfo.docsvc.svcapi.msrc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

/**
 * Class
 * 
 * @author griccardi
 * 
 */
public class MsrcRecord implements Document {

    String id;
    String created;
    String changed;
    String documentType; // ": "journal_article",
    String methodsSection;
    MsrcDocument document;
    List<String> creators;
    List<MsrcAnnotationRef> annotations;

    static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); // "2011-11-04T15:40:15-04:00"

    public static Date parseDate(String date) {
	try {
	    return formatter.parse(date);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}

    }

    @Override
    public Annotation getAnnotation(int i) {
	// TODO get annotation from Annotate service
	return null;
    }

    public List<MsrcAnnotationRef> getAnnotationRefs() {
	return annotations;
    }

    @Override
    public Annotation[] getAnnotations() {
	// TODO get annotations from Annotate service
	return null;
    }

    @Override
    public List<String> getAuthorList() {
	if (document == null)
	    return null;
	return document.getAuthorList();
    }

    @Override
    public String getAuthors() {
	if (document == null)
	    return null;
	return document.getAuthors();
    }

    @Override
    public String getCopyright() {
	if (document == null)
	    return null;
	return document.getCopyright();
    }

    @Override
    public String getDate() {
	if (document == null)
	    return null;
	return document.getDate();
    }

    @Override
    public Date getDateObject() {
	return parseDate(created);
    }

    @Override
    public String getDoi() {
	if (document == null)
	    return null;
	return document.getDoi();
    }

    @Override
    public String getGUID() {
	if (document == null)
	    return null;
	return document.getGUID();
    }

    @Override
    public String getId() {
	if (document == null)
	    return null;
	String docId = document.getId();
	if (docId != null && docId.startsWith("10.")) {
	    // a proper doi
	    return docId;
	}
	if (docId != null && docId.startsWith("doi:10.")) {
	    // does not start with "doi:10.
	    return docId.substring(3);
	}
	return id;
    }

    @Override
    public String getIsbn() {
	if (document == null)
	    return null;
	return document.getIsbn();
    }

    @Override
    public String getIssn() {
	if (document == null)
	    return null;
	return document.getIssn();
    }

    @Override
    public String getIssue() {
	if (document == null)
	    return null;
	return document.getIssue();
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
	if (document == null)
	    return null;
	return document.getName();
    }

    @Override
    public int getNumAnnotations() {
	if (document == null)
	    return 0;
	return document.getNumAnnotations();
    }

    @Override
    public String getOwner() {
	if (document == null)
	    return null;
	return document.getOwner();
    }

    @Override
    public Integer getPageEnd() {
	if (document == null)
	    return null;
	return document.getPageEnd();
    }

    @Override
    public String getPages() {
	if (document == null)
	    return null;
	return document.getPages();
    }

    @Override
    public Integer getPageStart() {
	if (document == null)
	    return null;
	return document.getPageStart();
    }

    @Override
    public Document getParent() {
	if (document == null)
	    return null;
	return document.getParent();
    }

    @Override
    public String getPublicationName() {
	if (document == null)
	    return null;
	return document.getPublicationName();
    }

    @Override
    public String getPublisher() {
	if (document == null)
	    return null;
	return document.getPublisher();
    }

    @Override
    public String getSource() {
	if (document == null)
	    return null;
	return document.getSource();
    }

    @Override
    public String getTitle() {
	if (document == null)
	    return null;
	return document.getTitle();
    }

    @Override
    public String getType() {
	return documentType;
    }

    @Override
    public String getUri() {
	return getId();
    }

    @Override
    public String getUrl() {
	if (document == null)
	    return null;
	return document.getUrl();
    }

    @Override
    public String getVolume() {
	if (document == null)
	    return null;
	return document.getVolume();
    }

    @Override
    public String getIssued() {
	if (document == null)
	    return null;
	return document.getIssued();
    }

    @Override
    public Date getIssuedDate() {
	if (document == null)
	    return null;
	return document.getIssuedDate();
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
