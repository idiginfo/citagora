package org.idiginfo.docsvc.svcapi.mas.model;

import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
/**
 * <p>Java class for Publication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
public class MasPublication implements Document
{

    protected String _abstract;
    
    protected List<MasAuthor> author;
    
    protected List<String> citationContext;
    
	protected Long citationCount;
    
    protected MasConference conference;
    
    protected String dOI;
    
    protected List<String> fullVersionURL;
    
    protected Long iD;
    
    protected MasJournal journal;
    
    protected List<MasKeyword> keyword;
    
    protected Long referenceCount;
    
    protected String title;
    
    protected String type;

    protected Integer year;

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	public List<MasAuthor> getAuthor() {
		return author;
	}

	public void setAuthor(List<MasAuthor> author) {
		this.author = author;
	}

    public List<String> getCitationContext() {
		return citationContext;
	}

	public void setCitationContext(List<String> citationContext) {
		this.citationContext = citationContext;
	}

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
	}

	public MasConference getConference() {
		return conference;
	}

	public void setConference(MasConference conference) {
		this.conference = conference;
	}

	public String getdOI() {
		return dOI;
	}

	public void setdOI(String dOI) {
		this.dOI = dOI;
	}

	public List<String> getFullVersionURL() {
		return fullVersionURL;
	}

	public void setFullVersionURL(List<String> fullVersionURL) {
		this.fullVersionURL = fullVersionURL;
	}

	public Long getiD() {
		return iD;
	}

	public void setiD(Long iD) {
		this.iD = iD;
	}

	public MasJournal getJournal() {
		return journal;
	}

	public void setJournal(MasJournal journal) {
		this.journal = journal;
	}

	public List<MasKeyword> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<MasKeyword> keyword) {
		this.keyword = keyword;
	}

	public Long getReferenceCount() {
		return referenceCount;
	}

	public void setReferenceCount(Long referenceCount) {
		this.referenceCount = referenceCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getType() {
		return type;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
	public void setAuthors(String authors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCopyright(String copyright) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsbn(String isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIssn(String issn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOwner(String owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(Document parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPublicationName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPublisher(String publisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(String type) {
		this.type=type;
		
	}

	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVolume(String volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getPageStart() {
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

    
}