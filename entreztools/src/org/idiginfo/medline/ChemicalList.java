//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.12 at 03:35:38 PM EDT 
//


package org.idiginfo.medline;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chemical"
})
@XmlRootElement(name = "ChemicalList")
public class ChemicalList {

    @XmlElement(name = "Chemical", required = true)
    protected List<Chemical> chemical;

    /**
     * Gets the value of the chemical property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chemical property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChemical().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Chemical }
     * 
     * 
     */
    public List<Chemical> getChemical() {
        if (chemical == null) {
            chemical = new ArrayList<Chemical>();
        }
        return this.chemical;
    }

}
