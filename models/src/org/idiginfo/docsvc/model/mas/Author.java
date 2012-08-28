package org.idiginfo.docsvc.model.mas;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Java class for Author complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Author">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Affiliation" type="{http://research.microsoft.com}Organization" minOccurs="0"/>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="DisplayPhotoURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="HIndex" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="HomepageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NativeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
public class Author
{

    @SerializedName("Affiliation")
    protected Organization affiliation;
    
    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("DisplayPhotoURL")
    protected String displayPhotoURL;
    
    @SerializedName("FirstName")
    protected String firstName;
    
    @SerializedName("GIndex")
    protected Long gIndex;
    
    @SerializedName("HIndex")
    protected Long hIndex;
    
    @SerializedName("HomepageURL")
    protected String homepageURL;
    
    @SerializedName("ID")
    protected Long iD;
    
    @SerializedName("LastName")
    protected String lastName;
    
    @SerializedName("MiddleName")
    protected String middleName;
    
    @SerializedName("NativeName")
    protected String nativeName;
    
    @SerializedName("PublicationCount")
    protected Long publicationCount;
    
    @SerializedName("ResearchInterestDomain")
    protected List<Domain> researchInterestDomain;

	public Organization getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Organization affiliation) {
		this.affiliation = affiliation;
	}

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
	}

	public String getDisplayPhotoURL() {
		return displayPhotoURL;
	}

	public void setDisplayPhotoURL(String displayPhotoURL) {
		this.displayPhotoURL = displayPhotoURL;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getgIndex() {
		return gIndex;
	}

	public void setgIndex(Long gIndex) {
		this.gIndex = gIndex;
	}

	public Long gethIndex() {
		return hIndex;
	}

	public void sethIndex(Long hIndex) {
		this.hIndex = hIndex;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
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

    
}