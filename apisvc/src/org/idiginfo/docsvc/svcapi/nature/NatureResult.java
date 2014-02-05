package org.idiginfo.docsvc.svcapi.nature;

import com.google.gson.JsonElement;

/**
 * Class to map Spring result json document
 * 
 * Note that although this class has a set of SpringerRecord (implements
 * Documents) it must create a more generic List<Document> to use for the
 * Documents interface. See method SpringerDocuments(records)
 * 
 * @author griccardi
 * 
 */
public class NatureResult {

	String comment; // "nature.com OpenSearch: urn:uuid:b8c9c71b-2faa-47b5-9606-507b6d112335",
	JsonElement namespaces; // {
	NatureFeed feed; //
}
