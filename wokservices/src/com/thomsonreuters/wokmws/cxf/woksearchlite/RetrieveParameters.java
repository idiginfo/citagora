
package com.thomsonreuters.wokmws.cxf.woksearchlite;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fields" type="{http://woksearchlite.cxf.wokmws.thomsonreuters.com}queryField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="firstRecord" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveParameters", propOrder = {
    "count",
    "fields",
    "firstRecord"
})
public class RetrieveParameters {

    protected int count;
    @XmlElement(nillable = true)
    protected List<QueryField> fields;
    protected int firstRecord;

    /**
     * Gets the value of the count property.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the fields property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fields property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFields().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryField }
     * 
     * 
     */
    public List<QueryField> getFields() {
        if (fields == null) {
            fields = new ArrayList<QueryField>();
        }
        return this.fields;
    }

    /**
     * Gets the value of the firstRecord property.
     * 
     */
    public int getFirstRecord() {
        return firstRecord;
    }

    /**
     * Sets the value of the firstRecord property.
     * 
     */
    public void setFirstRecord(int value) {
        this.firstRecord = value;
    }

}
