package org.idiginfo.docsvc.svcapi.mas.model;

import java.util.Date;

/**
 * <p>Java class for CFPInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CFPInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AbstractSubmissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AgendaUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConferenceEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ConferenceStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinalVersionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="HomepageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaperSubmissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ResultNotificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class MasCFPInfo
{

    protected Date abstractSubmissionDate;
	
	protected String agendaURL;
	
	protected String city;
	
	protected Date conferenceEndDate;
	
	protected Date conferenceStartDate;
	
	protected String country;
	
	protected Date finalVersionDate;
	
	protected String homepageURL;
	
	protected Date paperSubmissionDate;
	
	protected Date resultNotificationDate;

	public Date getAbstractSubmissionDate() {
		return abstractSubmissionDate;
	}

	public void setAbstractSubmissionDate(Date abstractSubmissionDate) {
		this.abstractSubmissionDate = abstractSubmissionDate;
	}

 	public String getAgendaURL() {
		return agendaURL;
	}

	public void setAgendaURL(String agendaURL) {
		this.agendaURL = agendaURL;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getConferenceEndDate() {
		return conferenceEndDate;
	}

	public void setConferenceEndDate(Date conferenceEndDate) {
		this.conferenceEndDate = conferenceEndDate;
	}

	public Date getConferenceStartDate() {
		return conferenceStartDate;
	}

	public void setConferenceStartDate(Date conferenceStartDate) {
		this.conferenceStartDate = conferenceStartDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getFinalVersionDate() {
		return finalVersionDate;
	}

	public void setFinalVersionDate(Date finalVersionDate) {
		this.finalVersionDate = finalVersionDate;
	}

	public String getHomepageURL() {
		return homepageURL;
	}

	public void setHomepageURL(String homepageURL) {
		this.homepageURL = homepageURL;
	}

	public Date getPaperSubmissionDate() {
		return paperSubmissionDate;
	}

	public void setPaperSubmissionDate(Date paperSubmissionDate) {
		this.paperSubmissionDate = paperSubmissionDate;
	}

	public Date getResultNotificationDate() {
		return resultNotificationDate;
	}

	public void setResultNotificationDate(Date resultNotificationDate) {
		this.resultNotificationDate = resultNotificationDate;
	}

	
}