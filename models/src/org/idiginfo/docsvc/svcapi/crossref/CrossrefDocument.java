package org.idiginfo.docsvc.svcapi.crossref;

import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

public class CrossrefDocument implements Document {

    // {
    // "doi": "10.1353/cal.2007.0180",
    // "score": 3.5215924,
    // "normalizedScore": 100,
    // "title": "Greater Love",
    // "fullCitation":
    // "Fred L Joiner, 2007, \u0027Greater Love\u0027, \u003ci\u003eCallaloo\u003c/i\u003e, vol. 30, no. 2",
    // "coins":
    // "ctx_ver\u003dZ39.88-2004\u0026amp;rft_id\u003dinfo%3Adoi%2F10.1353%2Fcal.2007.0180\u0026amp;rfr_id\u003dinfo%3Asid%2Fcrossref.org%3Asearch\u0026amp;rft.atitle\u003dGreater+Love\u0026amp;rft.jtitle\u003dCallaloo\u0026amp;rft.date\u003d2007\u0026amp;rft.volume\u003d30\u0026amp;rft.issue\u003d2\u0026amp;rft.aufirst\u003dFred+L\u0026amp;rft.aulast\u003dJoiner\u0026amp;rft_val_fmt\u003dinfo%3Aofi%2Ffmt%3Akev%3Amtx%3Ajournal\u0026amp;rft.genre\u003darticle\u0026amp;rft.au\u003dFred+L+Joiner",
    // "year": "2007"
    // },

    String doi;
    Double score;
    Integer normalizedScore;
    String title;
    String fullCitation;
    String coins;
    Integer year;

    transient CrossrefCoins coinsObject = null;

    CrossrefCoins getCoinsObject() {
	if (coinsObject == null) {
	    coinsObject = new CrossrefCoins(coins);
	}
	return coinsObject;
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
    public String getAuthors() {
	return getCoinsObject().getAuthorString();
    }

    @Override
    public List<String> getAuthorList() {
	return getCoinsObject().getAuthors();
    }

    @Override
    public String getCopyright() {
	return null;
    }

    @Override
    public String getDate() {
	return getCoinsObject().getValue("rft.date");
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
	return getId();
    }

    @Override
    public String getId() {
	return getCoinsObject().getValue("rft.id");
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
    public String getName() {
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
    public Document getParent() {
	return null;
    }

    @Override
    public String getPublicationName() {
	return getCoinsObject().getValue("rft.jtitle");
    }

    @Override
    public String getPublisher() {
	return null;
    }

    @Override
    public String getSource() {
	return ServiceFactory.COLLECTION_CROSSREF;
    }

    @Override
    public String getTitle() {
	return getCoinsObject().getValue("rft.atitle");
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUrl() {
	return null;
    }

    @Override
    public String getVolume() {
	return getCoinsObject().getValue("rft.volume");
    }

    @Override
    public Integer getPageStart() {
	try {
	    return Integer.getInteger(getCoinsObject().getValue("rft.spage"));
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    @Override
    public Integer getPageEnd() {
	return null;
    }

    @Override
    public String getPages() {
	return null;
    }

    @Override
    public String getIssue() {
	return getCoinsObject().getValue("rft.issue");
    }

    @Override
    public String getUri() {
	return null;
    }

    @Override
    public List<String> getKeywords() {
	return null;
    }

    @Override
    public List<String> getMeshTerms() {
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
    public String getPMId() {
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
