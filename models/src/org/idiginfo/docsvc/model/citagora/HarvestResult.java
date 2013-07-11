package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

/**
 * Interface to implement the Citagora HarvestResult object
 * 
 * @author griccardi
 * 
 */

public interface HarvestResult extends UriObject {

	public static final String TYPE = "harvestResult";

	Date getCreated();

	void setReference(Reference reference);

	Reference getReference();

	String getDescription();

	void setDescription(String description);

	Boolean getSuccess();

	void setSuccess(Boolean success);

	Integer getMyId();

	void setMyId(Integer myId);

	String getSource();

	void setSource(String source);

	String getIdentifier();

	void setIdentifier(String doi);

}
