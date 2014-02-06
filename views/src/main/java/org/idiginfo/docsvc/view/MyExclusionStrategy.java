package org.idiginfo.docsvc.view;

import org.idiginfo.docsvc.model.apisvc.GsonTransient;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Class to implement Exclusion Strategy for GSON to JSON output
 * 
 * @author griccardi
 * 
 */

public class MyExclusionStrategy implements ExclusionStrategy {
	// Excludes any field (or class) that is tagged with an "@FooAnnotation"
	public boolean shouldSkipClass(Class<?> clazz) {
		// return clazz.getAnnotation(GsonTransient.class) != null;
		return false;
	}

	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(GsonTransient.class) != null;
	}
}
