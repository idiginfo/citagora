
package com.thomsonreuters.wokmws.v3.woksearch;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * In version 2, the sequence of the elements was options, parent, queryID, records, recordsFound, recordsSearched.
 *         In version 3, the sequence is:  queryId, recordsFound, recordsSearched, parent, optionValue, records. The name options has changed to optionValue. 
 *         
 * 
 * <p>Java class for fullRecordSearchResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fullRecordSearchResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordsFound" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordsSearched" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="parent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionValue" type="{http://woksearch.v3.wokmws.thomsonreuters.com}labelValuesPair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="records" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fullRecordSearchResults", propOrder = {
    "queryId",
    "recordsFound",
    "recordsSearched",
    "parent",
    "optionValue",
    "records"
})
public class FullRecordSearchResults {

    protected String queryId;
    protected int recordsFound;
    protected long recordsSearched;
    protected String parent;
    @XmlElement(nillable = true)
    protected List<LabelValuesPair> optionValue;
    protected String records;

    /**
     * Gets the value of the queryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryId() {
        return queryId;
    }

    /**
     * Sets the value of the queryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryId(String value) {
        this.queryId = value;
    }

    /**
     * Gets the value of the recordsFound property.
     * 
     */
    public int getRecordsFound() {
        return recordsFound;
    }

    /**
     * Sets the value of the recordsFound property.
     * 
     */
    public void setRecordsFound(int value) {
        this.recordsFound = value;
    }

    /**
     * Gets the value of the recordsSearched property.
     * 
     */
    public long getRecordsSearched() {
        return recordsSearched;
    }

    /**
     * Sets the value of the recordsSearched property.
     * 
     */
    public void setRecordsSearched(long value) {
        this.recordsSearched = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParent(String value) {
        this.parent = value;
    }

    /**
     * Gets the value of the optionValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabelValuesPair }
     * 
     * 
     */
    public List<LabelValuesPair> getOptionValue() {
        if (optionValue == null) {
            optionValue = new ArrayList<LabelValuesPair>();
        }
        return this.optionValue;
    }

    /**
     * Gets the value of the records property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecords() {
        return records;
    }

    /**
     * Sets the value of the records property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecords(String value) {
        this.records = value;
    }

}
