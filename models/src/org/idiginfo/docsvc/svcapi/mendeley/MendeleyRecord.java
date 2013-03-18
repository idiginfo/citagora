package org.idiginfo.docsvc.svcapi.mendeley;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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
	@SerializedName("authors")
    Creator[] creators;
	@SerializedName("catagories")
	Catagory[] catagories;
    String doi;
    @SerializedName("identifiers")
	Ident[] idents; // doi, issn, etc.
    String isbn;
    String issn;
    @SerializedName("issue")
	String issue;
    @SerializedName("keywords")
	Keyword[] keywords;
    @SerializedName("mendeley_url")
	String mendeley_url;
    String number;
    @SerializedName("oa_journal")
	Boolean oa_journal;
	@SerializedName("pages")
	String pages;
    String publicationDate;
	String publicationName;
    @SerializedName("publication_outlet")
	String publication_outlet;
	@SerializedName("publisher")
	String publisher;
    String startingPage;
    @SerializedName("stats")
    Stat stats;
    @SerializedName("title")
    String title;
    @SerializedName("type")
    String type;
    @SerializedName("uuid")
    String uuid;
    @SerializedName("volume")
    String volume;
    @SerializedName("website")
    String url;
    @SerializedName("year")
    int publishYear;
    
    static class Author {
    	String author;
    
    public String toString() {
	    return author;
    }
    }
    
    static class Catagory {
    	int catagoryId;
    }

    static class Creator {
    	@SerializedName("forename")
    	String forename;
    	@SerializedName("surname")
    	String surname;
    
	public String toString() {
    	return forename + " " + surname;

	}
    }

    static class Ident {
    	String iLabel;
    	String iValue;
    	
    public String toString() {
    	return iLabel + ":" + iValue;
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

//    public String toString() {
//    	return type + "id : " + id + "name : " + name + "value :" + value; 
//    }
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
	return "doi:" + doi;
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
	String authors = StringUtils.join(creators, ", ");
	return authors;
    }

    @Override
    public List<String> getAuthorList() {
	List<String> authorList = new Vector<String>();
	for (int i = 0; i < creators.length; i++) {
	    authorList.add(creators[i].toString());
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

    public Creator[] getCreators() {
	return creators;
    }

    public String getPublicationName() {
	return publicationName;
    }

    public String getIssn() {
	return issn;
    }

    public String getIsbn() {
	return isbn;
    }

    public String getDoi() {
    String doi = "";
   	for (int i = 0; i < idents.length; i++) {
   		if (idents[i].iLabel == "doi") {
   			doi = idents[i].iValue;
   			return doi;
   		}
   	}
    	return null;
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
	return url;
    }

    public String getCopyright() {
	return null;
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
	return number;
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
