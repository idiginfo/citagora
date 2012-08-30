package org.idiginfo.docsvc.svcapi.mas.model;

import com.google.gson.annotations.SerializedName;


/**
 * <p>Java class for Domain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Domain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="DomainID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PublicationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="SubDomainID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre> 
 * 
 */
public class MasDomain
{

    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("DomainID")
    protected Long domainID;
    
    @SerializedName("Name")
    protected String name;
    
    @SerializedName("PublicationCount")
    protected Long publicationCount;
    
    @SerializedName("SubDomainID")
    protected Long subDomainID;

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
	}

	public Long getDomainID() {
		return domainID;
	}

	public void setDomainID(Long domainID) {
		this.domainID = domainID;
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

	public Long getSubDomainID() {
		return subDomainID;
	}

	public void setSubDomainID(Long subDomainID) {
		this.subDomainID = subDomainID;
	}
    

}