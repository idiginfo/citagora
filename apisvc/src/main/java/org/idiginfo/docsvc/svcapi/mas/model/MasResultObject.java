package org.idiginfo.docsvc.svcapi.mas.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the MicrosoftAcademicSearch Result object
 * 
 * @author griccardi
 * 
 */

public class MasResultObject<T> {

	@SerializedName("__type")
	String responseType;
	Integer endIdx;
	Integer startIdx;
	Integer totalItem;
	public List<T> result;

	public String getResponseType() {
		return responseType;
	}

	public Integer getEndIdx() {
		return endIdx;
	}

	public Integer getStartIdx() {
		return startIdx;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public List<T> getResult() {
		return result;
	}

}
