package org.idiginfo.docsvc.svcapi.mas;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Java class for Organization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthorCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="HomepageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PublicationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ResearchInterestDomain" type="{http://research.microsoft.com}ArrayOfDomain" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class MasOrganization
{

    @SerializedName("AuthorCount")
	protected Long authorCount;
    
    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("HomepageURL")
    protected String homepageURL;
    
    @SerializedName("ID")
    protected Long iD;
    
    @SerializedName("Name")
    protected String name;
    
    @SerializedName("PublicationCount")
    protected Long publicationCount;
    
    @SerializedName("ResearchInterestDomain")
    protected List<MasDomain> researchInterestDomain;

	public Long getAuthorCount() {
		return authorCount;
	}

	public void setAuthorCount(Long authorCount) {
		this.authorCount = authorCount;
	}

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPublicationCount() {
		return publicationCount;
	}

	public void setPublicationCount(Long publicationCount) {
		this.publicationCount = publicationCount;
	}

	public List<MasDomain> getResearchInterestDomain() {
		return researchInterestDomain;
	}

	public void setResearchInterestDomain(List<MasDomain> researchInterestDomain) {
		this.researchInterestDomain = researchInterestDomain;
	}

    
}