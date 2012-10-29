
package com.thomsonreuters.wokmws.v3.woksearch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *       In version 2, the faultInformation and rawFaultInformation elements did not exist. It is not required that the service return 
 *       these elements. 
 *       
 * 
 * <p>Java class for AuthenticationException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticationException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faultInformation" type="{http://woksearch.v3.wokmws.thomsonreuters.com}FaultInformation" minOccurs="0"/>
 *         &lt;element name="rawFaultInformation" type="{http://woksearch.v3.wokmws.thomsonreuters.com}RawFaultInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationException", propOrder = {
    "faultInformation",
    "rawFaultInformation"
})
public class AuthenticationException {

    protected FaultInformation faultInformation;
    protected RawFaultInformation rawFaultInformation;

    /**
     * Gets the value of the faultInformation property.
     * 
     * @return
     *     possible object is
     *     {@link FaultInformation }
     *     
     */
    public FaultInformation getFaultInformation() {
        return faultInformation;
    }

    /**
     * Sets the value of the faultInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FaultInformation }
     *     
     */
    public void setFaultInformation(FaultInformation value) {
        this.faultInformation = value;
    }

    /**
     * Gets the value of the rawFaultInformation property.
     * 
     * @return
     *     possible object is
     *     {@link RawFaultInformation }
     *     
     */
    public RawFaultInformation getRawFaultInformation() {
        return rawFaultInformation;
    }

    /**
     * Sets the value of the rawFaultInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RawFaultInformation }
     *     
     */
    public void setRawFaultInformation(RawFaultInformation value) {
        this.rawFaultInformation = value;
    }

}
