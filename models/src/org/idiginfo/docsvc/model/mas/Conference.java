package org.idiginfo.docsvc.model.mas;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Java class for Conference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Conference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFP" type="{http://research.microsoft.com}CFPInfo" minOccurs="0"/>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="EndYear" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HomepageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="PublicationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ResearchInterestDomain" type="{http://research.microsoft.com}ArrayOfDomain" minOccurs="0"/>
 *         &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartYear" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class Conference
{

    @SerializedName("CPF")
    protected CFPInfo cFP;
    
    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("EndYear")
    protected Integer endYear;
    
    @SerializedName("FullName")
    protected String fullName;
    
    @SerializedName("HomepageURL")
    protected String homepageURL;
    
    @SerializedName("ID")
    protected Long iD;
    
    @SerializedName("PublicationCount")
    protected Long publicationCount;
    
    @SerializedName("ResearchInterestDomain")
    protected List<Domain> researchInterestDomain;
    
    @SerializedName("ShortName")
    protected String shortName;
    
    @SerializedName("StartYear")
    protected Integer startYear;

	public CFPInfo getcFP() {
		return cFP;
	}

	public void setcFP(CFPInfo cFP) {
		this.cFP = cFP;
	}

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHomepageURL() {
		return homepageURL;
	}

	public void setHomepageURL(String homepageURL) {
		this.homepageURL = homepageURL;
	}

	public Long getiD() {
		return iD;
	}

	public void setiD(Long iD) {
		this.iD = iD;
	}

	public Long getPublicationCount() {
		return publicationCount;
	}

	public void setPublicationCount(Long publicationCount) {
		this.publicationCount = publicationCount;
	}

	public List<Domain> getResearchInterestDomain() {
		return researchInterestDomain;
	}

	public void setResearchInterestDomain(List<Domain> researchInterestDomain) {
		this.researchInterestDomain = researchInterestDomain;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

    
}