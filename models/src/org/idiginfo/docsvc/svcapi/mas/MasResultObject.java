package org.idiginfo.docsvc.svcapi.mas;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MasResultObject<T> {

	@SerializedName("__type")
	String responseType;
	Integer endIdx;
	Integer startIdx;
	Integer totalItem;
	public List<T> result;

}
