package org.idiginfo.docsvc.model.mas;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Java class for Keyword complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Keyword">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PublicationCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class Keyword
{
    @SerializedName("CitationCount")
    protected Long citationCount;
    
    @SerializedName("ID")
    protected Long iD;
    
    @SerializedName("Name")
    protected String name;

    @SerializedName("PublicationCount")
    protected Long publicationCount;

	public Long getCitationCount() {
		return citationCount;
	}

	public void setCitationCount(Long citationCount) {
		this.citationCount = citationCount;
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

    
}