package org.idiginfo.docsvc.svcapi.mas;

import java.util.List;
import com.google.gson.annotations.SerializedName;

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
public class MasPublication
{

    @SerializedName("Abstract")
    protected String _abstract;
    
    @SerializedName("Author")
    protected List<MasAuthor> author;
    
    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("Conference")
    protected MasConference conference;
    
    @SerializedName("DOI")
    protected String dOI;
    
    @SerializedName("FullVersionURL")
    protected List<String> fullVersionURL;
    
    @SerializedName("ID")
    protected Long iD;
    
    @SerializedName("Journal")
    protected MasJournal journal;
    
    @SerializedName("Keyword")
    protected List<MasKeyword> keyword;
    
    @SerializedName("ReferenceCount")
    protected Long referenceCount;
    
    @SerializedName("Title")
    protected String title;
    
    @SerializedName("Type")
    protected MasPublicationType type;

    @SerializedName("Year")
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

	public MasPublicationType getType() {
		return type;
	}

	public void setType(MasPublicationType type) {
		this.type = type;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

    
}