package org.idiginfo.docsvc.model.apisvc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * Deserializer class that supports a 1 element list with no list brackets
 * ("[]") If the json element is a single element, return a list of 1
 * 
 * @author griccardi
 * 
 * @param <T>
 */
public class ListTypeAdapter<T> implements JsonDeserializer<List<T>> {

	Class<?> classT;

	/**
	 * Constructor requires an element of the class T to use to get the Class of
	 * T
	 * 
	 * @param sample
	 */
	public ListTypeAdapter(T sample) {
		classT = sample.getClass();
	}

	@SuppressWarnings("unchecked")
	public List<T> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) {

		List<T> vals = new ArrayList<T>();
		if (json.isJsonArray()) {
			for (JsonElement e : json.getAsJsonArray()) {
				vals.add((T) ctx.deserialize(e, classT));
			}
		} else if (json.isJsonObject()) {
			vals.add((T) ctx.deserialize(json, classT));
		} else {
			throw new RuntimeException("Unexpected JSON type: "
					+ json.getClass());
		}
		return vals;
	}
}