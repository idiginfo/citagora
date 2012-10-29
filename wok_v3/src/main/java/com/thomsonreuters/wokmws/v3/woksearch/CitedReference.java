
package com.thomsonreuters.wokmws.v3.woksearch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *     In version 2, the sequence was articleID, citedAuthor, citedTitle, citedWork, page, recID, refID, timesCited, volume, year.
 *     
 * 
 * <p>Java class for citedReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="citedReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="articleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedAuthor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timesCited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="citedWork" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citedReference", propOrder = {
    "uid",
    "docid",
    "articleId",
    "citedAuthor",
    "timesCited",
    "year",
    "page",
    "volume",
    "citedTitle",
    "citedWork",
    "hot"
})
public class CitedReference {

    protected String uid;
    protected String docid;
    protected String articleId;
    protected String citedAuthor;
    protected String timesCited;
    protected String year;
    protected String page;
    protected String volume;
    protected String citedTitle;
    protected String citedWork;
    protected String hot;

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the docid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocid() {
        return docid;
    }

    /**
     * Sets the value of the docid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocid(String value) {
        this.docid = value;
    }

    /**
     * Gets the value of the articleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * Sets the value of the articleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleId(String value) {
        this.articleId = value;
    }

    /**
     * Gets the value of the citedAuthor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedAuthor() {
        return citedAuthor;
    }

    /**
     * Sets the value of the citedAuthor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedAuthor(String value) {
        this.citedAuthor = value;
    }

    /**
     * Gets the value of the timesCited property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimesCited() {
        return timesCited;
    }

    /**
     * Sets the value of the timesCited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimesCited(String value) {
        this.timesCited = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
    }

    /**
     * Gets the value of the citedTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedTitle() {
        return citedTitle;
    }

    /**
     * Sets the value of the citedTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedTitle(String value) {
        this.citedTitle = value;
    }

    /**
     * Gets the value of the citedWork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitedWork() {
        return citedWork;
    }

    /**
     * Sets the value of the citedWork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitedWork(String value) {
        this.citedWork = value;
    }

    /**
     * Gets the value of the hot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHot() {
        return hot;
    }

    /**
     * Sets the value of the hot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHot(String value) {
        this.hot = value;
    }

}
