package org.idiginfo.docsvc.svcapi.mas;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class MasResponseObject {
	@SerializedName("__type")
	String responseType;
	Integer resultCode;
	String version;

	int getResultCode() {
		if (resultCode == null)
			return MasApiParams.UNKNOWN_ERROR_CODE; // problem with response
		return resultCode;
	}

}
