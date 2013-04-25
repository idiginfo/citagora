package org.idiginfo.docsvc.svcapi.medline;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

public class MedlineArticle implements Document {

    Map<String, String> fields = new HashMap<>();

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
			fields.put(name, value);
		    }
		    String[] split = line.split("-");
		    name = split[0].trim();
		    value = split[1].trim();
		}
		line = in.readLine();
	    }
	    if (name != null) {
		// save previous value
		fields.put(name, value);
	    }

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public String getAbstractText() {
	return fields.get("ABS");
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getAuthorNotes() {
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCoverDate() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getDate() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Date getDateObject() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getDoi() {
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
    public String getGUID() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getId() {
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
    public String getLanguage() {
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
    public String getItemNumber() {
	// TODO Auto-generated method stub
	return null;
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
    public String getPublicationDate() {
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
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getTitle() {
	// TODO Auto-generated method stub
	return null;
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
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

    @Override
    public String getSourceId() {
	// TODO Auto-generated method stub
	return null;
    }

}
