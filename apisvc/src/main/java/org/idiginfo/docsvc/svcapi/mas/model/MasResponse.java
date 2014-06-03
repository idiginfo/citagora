package org.idiginfo.docsvc.svcapi.mas.model;

import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Class to unmarshal the result of an MAS service call
 * 
 * @author griccardi
 * 
 */

public class MasResponse {
	@SerializedName("d")
	MasResponseObject d;

	public MasResponseObject getD() {
		return d;
	}

	public MasResponseObject getResultObject() {
		return d;
	}

	public static int getResultCode(JsonElement response) {
		// test structure of the response
		if (response == null || !response.isJsonObject())
			return MasApiParams.UNKNOWN_ERROR_CODE;
		JsonElement jsonElement = response.getAsJsonObject().get("d");
		if (jsonElement == null || !jsonElement.isJsonObject()) {
			return MasApiParams.UNKNOWN_ERROR_CODE;
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		if (!jsonObject.has("ResultCode")) {
			return MasApiParams.UNKNOWN_ERROR_CODE;
		}
		return jsonObject.get("ResultCode").getAsInt();
	}

	public static boolean isError(JsonElement response) {
		int resultCode = getResultCode(response);
		return resultCode != 0;
	}

	public static String getMessage(JsonElement response) {
		int resultCode = getResultCode(response);
		return MasApiParams.RESPONSE_CODE_MESSAGES[resultCode];
	}

}
