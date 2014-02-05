package org.idiginfo.docsvc.svcapi.mas.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.SvcApiServiceFactory;
import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the MicrosoftAcademicSearch Publication as Document object
 * 
 * @author griccardi
 * 
 */

/**
 * <p>
 * Java class for Publication complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Publication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Abstract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Author" type="{http://research.microsoft.com}ArrayOfAuthor" minOccurs="0"/>
 *         &lt;element name="CitationContext" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Conference" type="{http://research.microsoft.com}Conference" minOccurs="0"/>
 *         &lt;element name="DOI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FullVersionURL" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Journal" type="{http://research.microsoft.com}Journal" minOccurs="0"/>
 *         &lt;element name="Keyword" type="{http://research.microsoft.com}ArrayOfKeyword" minOccurs="0"/>
 *         &lt;element name="ReferenceCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://schemas.datacontract.org/2004/07/Libra.Service.API}PublicationType" minOccurs="0"/>
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class MasPublication implements Document {

	@SerializedName("Abstract")
	protected String _abstract;

	protected List<MasAuthor> author;

	protected List<String> citationContext;

	protected Long citationCount;

	protected MasConference conference;

	@SerializedName("DOI")
	protected String doi;

	protected List<String> fullVersionURL;

	@SerializedName("ID")
	protected Long id;

	protected MasJournal journal;

	protected List<MasKeyword> keyword;

	protected Long referenceCount;

	protected String title;

	protected String type;

	protected Integer year;

	public String get_abstract() {
		return _abstract;
	}

	public String getAbstractText() {
		return _abstract;
	}

	@Override
	public Annotation getAnnotation(int i) {
		return null;
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	public List<MasAuthor> getAuthor() {
		return author;
	}

	@Override
	public List<String> getAuthorList() {
		if (author == null)
			author = new Vector<MasAuthor>();
		List<String> authorList = new Vector<String>();
		Iterator<MasAuthor> authors = author.iterator();
		while (authors.hasNext()) {
			MasAuthor authorItem = authors.next();
			authorList.add(authorItem.getName());
		}
		return authorList;
	}

	@Override
	public String getAuthors() {
		StringBuffer authors = new StringBuffer();
		String comma = "";
		for (MasAuthor author : this.author) {
			authors.append(comma).append(author.getName());
			comma = ", ";
		}
		return authors.toString();
	}

	public List<String> getCitationContext() {
		return citationContext;
	}

	public Long getCitationCount() {
		return citationCount;
	}

	public MasConference getConference() {
		return conference;
	}

	@Override
	public String getCopyright() {
		return null;
	}

	@Override
	public String getDate() {
		return null;
	}

	@Override
	public Date getDateObject() {
		return null;
	}

	public String getdOI() {
		return doi;
	}

	@Override
	public String getDoi() {
		return doi;
	}

	public List<String> getFullVersionURL() {
		return fullVersionURL;
	}

	@Override
	public String getGUID() {
		return getUri();
	}

	public Long getiD() {
		return id;
	}

	@Override
	public String getId() {
		return "mas:" + Long.toString(id);
	}

	@Override
	public String getIsbn() {
		return null;
	}

	@Override
	public String getIssn() {
		return journal.getiSSN();
	}

	@Override
	public String getIssue() {
		return null;
	}

	public MasJournal getJournal() {
		return journal;
	}

	public List<MasKeyword> getKeyword() {
		return keyword;
	}

	@Override
	public List<String> getKeywords() {
		// TODO Auto-generated method stub
		List<String> keywords = new Vector<String>();
		for (MasKeyword keyword : this.keyword) {
			keywords.add(keyword.getName());
		}
		return keywords;
	}

	@Override
	public List<String> getMeshTerms() {
		return null;
	}

	@Override
	public String getName() {
		return title;
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
		return null;
	}

	@Override
	public String getPages() {
		return null;
	}

	@Override
	public Integer getPageStart() {
		return null;
	}

	@Override
	public Document getParent() {
		return null;
	}

	@Override
	public String getPublicationName() {
		if (journal == null)
			return null;
		return journal.getFullName();
	}

	@Override
	public String getPublisher() {
		if (journal == null)
			return null;
		return null;
	}

	public Long getReferenceCount() {
		return referenceCount;
	}

	@Override
	public String getSource() {
		return SvcApiServiceFactory.COLLECTION_MAS;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getUri() {
		return doi;
	}

	@Override
	public String getUrl() {
		if (fullVersionURL == null || fullVersionURL.size() == 0)
			return null;
		return fullVersionURL.get(0);
	}

	@Override
	public String getVolume() {
		if (journal == null)
			return null;
		return null;
	}

	public Integer getYear() {
		return year;
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
	public String getIssued() {
		return Integer.toString(year);
	}

	static DateFormat issuedFormatter = new SimpleDateFormat("yyyy");

	@Override
	public Date getIssuedDate() {
		if (year == null)
			return null;
		try {
			return issuedFormatter.parse(Integer.toString(year));
		} catch (ParseException e) {
			System.out.println("problem parsing year: '" + year
					+ "' for object " + id);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getSourceId() {
		// TODO Auto-generated method stub
		return null;
	}

}